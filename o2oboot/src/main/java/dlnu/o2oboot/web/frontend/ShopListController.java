package dlnu.o2oboot.web.frontend;

import dlnu.o2oboot.dto.ShopExecution;
import dlnu.o2oboot.entity.Area;
import dlnu.o2oboot.entity.Shop;
import dlnu.o2oboot.entity.ShopCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import dlnu.o2oboot.service.AreaService;
import dlnu.o2oboot.service.ShopCategoryService;
import dlnu.o2oboot.service.ShopService;
import dlnu.o2oboot.util.HttpServletRequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/frontend")
public class ShopListController {

    @Autowired
    private ShopCategoryService shopCategoryService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private AreaService areaService;


    @RequestMapping(value="/listshopspageinfo",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> listShopsPageInfo(HttpServletRequest request){
        Map<String,Object> model=new HashMap<String, Object>();
        try{
            String id=request.getParameter("parentId");
            List<Area> areaList=areaService.getAreaList();
            model.put("areaList",areaList);
            if(id!=null&&!id.equals("")){
                Long parentId=Long.valueOf(id);
                ShopCategory shopCategoryCondition=new ShopCategory();
                ShopCategory parent=new ShopCategory();
                parent.setShopCategoryId(parentId);
                shopCategoryCondition.setParent(parent);
                List<ShopCategory> shopCategoryList=shopCategoryService.queryShopCategoryList(shopCategoryCondition);
                model.put("success",true);
                model.put("shopCategoryList",shopCategoryList);
            }else{
                List<ShopCategory> shopCategoryList=shopCategoryService.queryShopCategoryList(null);
                model.put("success",true);
                model.put("shopCategoryList",shopCategoryList);
            }



        }catch (Exception e){
            e.printStackTrace();
            model.put("success",false);
            model.put("errMsg",e.toString());
        }
        return model;
    }


    @RequestMapping(value="listshops",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> listShops(HttpServletRequest request){
        Map<String,Object> model=new HashMap<String, Object>();
        int pageIndex= HttpServletRequestUtil.getInt(request,"pageIndex");
        int pageSize=HttpServletRequestUtil.getInt(request,"pageSize");
        if(pageIndex>-1&&pageSize>-1){
            long parentId=HttpServletRequestUtil.getLong(request,"parentId");
            long shopCategoryId=HttpServletRequestUtil.getLong(request,"shopCategoryId");
            String shopName=HttpServletRequestUtil.getString(request,"shopName");
            int areaId=HttpServletRequestUtil.getInt(request,"areaId");
            Shop shopCondition=compactShopConditionForSearch(parentId,shopCategoryId,shopName,areaId);
            ShopExecution se=shopService.getShopList(shopCondition,pageIndex,pageSize);
            model.put("shopList",se.getShops());
            model.put("count",se.getCount());
            model.put("success",true);
        }else{
            model.put("success",true);
            model.put("errMsg","empty pageSize or pageIndex");
        }
        return model;
    }

    private Shop compactShopConditionForSearch(long parentId,long shopCategoryId,String shopName,int areaId)    {
        Shop shopCondition=new Shop();
        if(parentId!=-1L){
            ShopCategory shopCategory=new ShopCategory();
            ShopCategory parent=new ShopCategory();
            parent.setShopCategoryId(parentId);
            shopCategory.setParent(parent);
            shopCondition.setShopCategory(shopCategory);
        }
        if(shopCategoryId!=-1L){
            ShopCategory shopCategory=new ShopCategory();
            shopCategory.setShopCategoryId(shopCategoryId);
            shopCondition.setShopCategory(shopCategory);
        }
        if(shopName!=null){
            shopCondition.setShopName(shopName);
        }

        if(areaId!=-1){
            Area area=new Area();
            area.setAreaId(areaId);
            shopCondition.setArea(area);
        }

        shopCondition.setEnableStatus(1);
        return  shopCondition;

    }

}

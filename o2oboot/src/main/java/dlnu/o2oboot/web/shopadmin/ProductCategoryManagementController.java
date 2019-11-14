package dlnu.o2oboot.web.shopadmin;

import dlnu.o2oboot.dto.ProductCategoryExecution;
import dlnu.o2oboot.entity.ProductCategory;
import dlnu.o2oboot.entity.Shop;
import dlnu.o2oboot.enums.ProductCategoryStateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import dlnu.o2oboot.service.ProductCategoryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/shopadmin")
public class ProductCategoryManagementController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping(value = "/getproductcategorylist")
    @ResponseBody
    public Map<String,Object> getProductCategoryList(HttpSession session){
        Map<String,Object> model=new HashMap<String, Object>();

        try{
//
//            Shop t=new Shop();
//            t.setShopId(68L);
//            session.setAttribute("currentShop",t);


            Shop shop=(Shop) session.getAttribute("currentShop");
            Long shopId=shop.getShopId();
            List<ProductCategory> list=productCategoryService.getProductCategoryByshopId(shopId);
            if(shop!=null && shop.getShopId()>0){
                model.put("success",true);
                model.put("productCategoryList",list);
            }else{
                model.put("success",false);
                model.put("errMsg","查询错误");
            }
        }catch (Exception e){
            model.put("success",false);
            model.put("errMsg",e.toString());
        }

        return model;
    }


    @RequestMapping(value = "/batchaddproductcategory",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> batchAddProductCategory(HttpServletRequest request, @RequestBody List<ProductCategory> list ){
        Map<String,Object> model=new HashMap<String, Object>();
        HttpSession session=request.getSession();
        Shop shop= (Shop) session.getAttribute("currentShop");
        if(shop==null){
            model.put("success",false);
            model.put("errMsg","无商店信息");
            return model;
        }
        for(ProductCategory p : list){
            p.setShopId(shop.getShopId());
            p.setCreateTime(new Date());
        }

        if(list!=null && list.size()>0) {
            ProductCategoryExecution pe = productCategoryService.batchAddProductCategory(list);
            if (pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
                model.put("success", true);
                return model;
            } else {
                model.put("success", false);
                model.put("errMsg", pe.getStateInfo());
                return model;
            }
        }
        else{
            model.put("success", false);
            model.put("errMsg", "无输入商品类别");
            return model;
        }
    }

    @RequestMapping(value="/removeproductcategory",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> removeProductCategory(Long productCategoryId,HttpSession session){

       //TODO 删除商品类别时应将该类别下的商品的category置为null

        Map<String,Object> model=new HashMap<String, Object>();
        Shop shop=(Shop)session.getAttribute("currentShop");
        try {
            if (productCategoryId != null) {
                ProductCategoryExecution pe = productCategoryService.deleteProductCategory(productCategoryId, shop.getShopId());

                if (pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
                    model.put("success", true);
                    return model;
                } else {
                    model.put("success", false);
                    model.put("errMsg", pe.getStateInfo());
                    return model;
                }

            } else {
                model.put("success", false);
                model.put("errMsg", "无此类别");
                return model;
            }
        }catch (Exception e){
            e.printStackTrace();
            model.put("success", false);
            model.put("errMsg", e.toString());
            return model;
        }
    }


}

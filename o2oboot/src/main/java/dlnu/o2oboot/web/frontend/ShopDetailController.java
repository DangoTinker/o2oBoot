package dlnu.o2oboot.web.frontend;

import dlnu.o2oboot.dto.ProductExecution;
import dlnu.o2oboot.entity.Product;
import dlnu.o2oboot.entity.ProductCategory;
import dlnu.o2oboot.entity.Shop;
import dlnu.o2oboot.enums.ProductStateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import dlnu.o2oboot.service.ProductCategoryService;
import dlnu.o2oboot.service.ProductService;
import dlnu.o2oboot.service.ShopService;
import dlnu.o2oboot.util.HttpServletRequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/frontend")
public class ShopDetailController {

    @Autowired
    private ShopService shopService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping(value="/listshopdetailpageinfo",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> listShopDetailPageInfo(HttpServletRequest request){
        Map<String,Object> model=new HashMap<String, Object>();

        long shopId= HttpServletRequestUtil.getLong(request,"shopId");
        if(shopId!=-1){
            Shop shop=shopService.getShopById(shopId);
            List<ProductCategory> productCategoryList
                    =productCategoryService.getProductCategoryByshopId(shopId);
            model.put("shop",shop);
            model.put("productCategoryList",productCategoryList);
            model.put("success",true);


        }else{
            model.put("success",false);
            model.put("errMsg","empty shopId");
        }
        return model;
    }


    @RequestMapping(value="/listproductsbyshop",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> listProductsByShop(HttpServletRequest request){
        Map<String,Object> model=new HashMap<String, Object>();
        long shopId=HttpServletRequestUtil.getLong(request,"shopId");
        int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
        int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");

        if(pageIndex>-1&&pageSize>-1&&shopId!=-1){

            long productCategoryId=HttpServletRequestUtil.getLong(request,"productCategoryId");

            String productName=HttpServletRequestUtil.getString(request,"productName");

            Product productCondition=compactProductConditionForSearch(shopId,productCategoryId,productName);

            ProductExecution pe=productService.getProductList(productCondition,pageIndex,pageSize);
            if(pe.getState()== ProductStateEnum.SUCCESS.getState()){
                List<Product> list=pe.getList();
                model.put("success",true);
                model.put("productList",pe.getList());
                model.put("count",pe.getCount());
            }else{
                model.put("success",false);
                model.put("errMsg",pe.getStateInfo());
            }



        }else{
            model.put("success",false);
            model.put("errMsg","empty pageIndex or pageSize or shopId");
        }

        return model;

    }

    private Product compactProductConditionForSearch(long shopId,long productCategoryId,String productName
                                                     ){

        Product productCondition=new Product();
        if(shopId!=-1){
            Shop shop=new Shop();
            shop.setShopId(shopId);
            productCondition.setShop(shop);
        }
        if(productCategoryId!=-1){
            ProductCategory productCategory=new ProductCategory();
            productCategory.setProductCategoryId(productCategoryId);
            productCondition.setProductCategory(productCategory);
        }
        if(productName!=null){
            productCondition.setProductName(productName);
        }
        return productCondition;


    }


}

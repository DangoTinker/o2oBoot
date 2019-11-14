package dlnu.o2oboot.web.frontend;

import dlnu.o2oboot.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import dlnu.o2oboot.service.ProductCategoryService;
import dlnu.o2oboot.service.ProductService;
import dlnu.o2oboot.util.HttpServletRequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/frontend")
public class ProductDetailController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;



    @RequestMapping(value="/listproductdetailpageinfo",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> listProductDetailPageInfo(HttpServletRequest request){
        Map<String,Object> model=new HashMap<String, Object>();

        long productId= HttpServletRequestUtil.getLong(request,"productId");
        if(productId!=-1L){
            Product product=productService.getProductById(productId);
            model.put("success",true);
            model.put("product",product);
        }else{
            model.put("success",false);
            model.put("errMsg","empty productId");
        }
        return model;
    }



}

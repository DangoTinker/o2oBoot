package dlnu.o2oboot.web.shopadmin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/shopadmin")
public class ProductCategoryAdminController {


    @RequestMapping(value = "/productcategoryoperation")
    public String productCategoryOperation(){
        return "/product/productcategorylist";
    }

}

package dlnu.o2oboot.web.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/frontend")
public class FrontendAdminController {

    @RequestMapping("/index")
    public String productOperation(){
        return "frontend/index";
    }

    @RequestMapping("/shoplist")
    public String shopList(){
        return "frontend/shoplist";
    }

    @RequestMapping("/shopdetail")
    public String shopDetail(){
        return "frontend/shopdetail";
    }

    @RequestMapping("/productdetail")
    public String productDetail(){
        return "frontend/productdetail";
    }

}

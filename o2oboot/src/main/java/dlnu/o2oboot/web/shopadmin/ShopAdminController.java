package dlnu.o2oboot.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping(value="/shopadmin")
public class ShopAdminController {



    @RequestMapping(value="/shopoperation")
    private String shopRegister(){
        return "shop/shopOperation";
    }

    @RequestMapping(value="/shoplist")
    private String shoptest(){

        return "shop/shoplist";
    }

    @RequestMapping(value="/shopmanagement")
    private String shopManagement(){
        return "shop/shopmanagement";
    }

}

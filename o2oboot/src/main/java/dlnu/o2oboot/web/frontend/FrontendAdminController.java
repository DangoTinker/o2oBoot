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

    @RequestMapping(value = "/awardlist")
    private String showAwardList() {
        return "frontend/awardlist";
    }

    @RequestMapping(value = "/pointrecord")
    private String showPointRecord() {
        return "frontend/pointrecord";
    }

    @RequestMapping(value = "/myawarddetail")
    private String showMyAwardDetail() {
        return "frontend/myawarddetail";
    }


    @RequestMapping(value = "/myrecord")
    private String showMyRecord() {
        return "frontend/myrecord";
    }

    /**
     * 用户各店铺积分信息页路由
     *
     * @return
     */
    @RequestMapping(value = "/mypoint")
    private String showMyPoint() {
        return "frontend/mypoint";
    }
}

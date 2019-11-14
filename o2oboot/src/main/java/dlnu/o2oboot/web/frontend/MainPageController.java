package dlnu.o2oboot.web.frontend;


import dlnu.o2oboot.entity.HeadLine;
import dlnu.o2oboot.entity.ShopCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import dlnu.o2oboot.service.HeadLineService;
import dlnu.o2oboot.service.ShopCategoryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/frontend")
public class MainPageController {

    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private HeadLineService headLineService;



    @RequestMapping(value = "/listmainpageinfo",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> listMainPageInfo(){
        Map<String,Object> model=new HashMap<String, Object>();
        HeadLine headLine=new HeadLine();
        headLine.setEnableStatus(1);
        try{
            List<HeadLine> headLineList=headLineService.getHeadLineList(headLine);
            List<ShopCategory> shopCategoryList=shopCategoryService.queryShopCategoryList(null);

                model.put("success",true);
                model.put("headLineList",headLineList);
                model.put("shopCategoryList",shopCategoryList);
        }catch (Exception e){
            model.put("success",false);
            model.put("errMsg",e.toString());
        }
        return model;
    }


}

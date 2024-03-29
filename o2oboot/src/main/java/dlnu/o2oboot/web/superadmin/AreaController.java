package dlnu.o2oboot.web.superadmin;



import dlnu.o2oboot.entity.Area;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import dlnu.o2oboot.service.AreaService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/superadmin")
public class AreaController {

    private Logger logger= LoggerFactory.getLogger(AreaController.class);


    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/listarea",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listArea(){
        logger.info("===start===");

        Map<String,Object> model=new HashMap<String, Object>();
        List<Area> list=null;
        long startTime=System.currentTimeMillis();
        try {
            list=areaService.getAreaList();
            model.put("rows", list);
            model.put("total", list.size());
        }catch (Exception e){
            e.printStackTrace();
            model.put("success",false);
            model.put("errMsg",e.toString());
        }
        long endTime=System.currentTimeMillis();
        logger.error("testError");
        logger.debug("costTime:"+(endTime-startTime));
        logger.info("===end===");
        return model;
    }

}

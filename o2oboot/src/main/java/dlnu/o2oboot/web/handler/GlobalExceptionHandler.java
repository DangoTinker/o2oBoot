package dlnu.o2oboot.web.handler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    public Map<String,Object> handle(Exception e){
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("success",false);
        if(e instanceof Exception){
            model.put("errMsg",e.toString());
        }else{
            logger.error("系统异常",e.getMessage());
            model.put("errMsg","未知错误");
        }
        return model;
    }

}

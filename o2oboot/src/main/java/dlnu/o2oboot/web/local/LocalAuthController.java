package dlnu.o2oboot.web.local;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/local")
public class LocalAuthController {
//    @Autowired
//    private LocalAuthService localAuthService;
//
//    @RequestMapping(value="/addlocalauth",method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String,Object> addLocalAuth(HttpServletRequest request){
//        Map<String,Object> model=new HashMap<String,Object>();
//        String username= HttpServletRequestUtil.getString(request,"username");
//        String password=HttpServletRequestUtil.getString(request,"password");
//        long userId=HttpServletRequestUtil.getLong(request,"userId");
//        LocalAuth localAuth=new LocalAuth();
//        PersonInfo personInfo=new PersonInfo();
//        personInfo.setPersonId(userId);
//        localAuth.setPersonInfo(personInfo);
//        localAuth.setUsername(username);
//        localAuth.setPassword(password);
//
//        LocalAuth res=localAuthService.addLocalAuth(localAuth);
////TODO 应该用LocalAuthExecution判断结果
//        if(res.getLocalAuthId()!=null){
//            model.put("success",true);
//
//        }else{
//            model.put("success",false);
//            model.put("errMsg","注册失败");
//        }
//        return model;
//    }
//
//    @RequestMapping(value="/logincheck",method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String,Object> loginCheck(HttpServletRequest request){
//        Map<String,Object> model=new HashMap<String,Object>();
//        String username= HttpServletRequestUtil.getString(request,"username");
//        String password=HttpServletRequestUtil.getString(request,"password");
//        LocalAuth localAuth=localAuthService.getLocalAuthByUsernameAndPwd(username,password);
//
//        if(username!=null&&password!=null){
//            if(localAuth.getLocalAuthId()!=null){
//                model.put("success",true);
//                request.getSession().setAttribute("user",localAuth.getPersonInfo());
//            }else{
//                model.put("success",false);
//                model.put("errMsg","用户名或密码错误");
//            }
//        }else{
//            model.put("success",false);
//            model.put("errMsg","用户名或密码不能为空");
//        }
//
//        return model;
//
//    }
//
//
//    @RequestMapping(value = "changelocalpwd",method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String,Object> changeLocalPwd(HttpServletRequest request){
//        Map<String,Object> model=new HashMap<String,Object>();
//        Long userId=((PersonInfo)request.getSession().getAttribute("user")).getPersonId();
//
////        Long userId=9L;
//        String password = HttpServletRequestUtil.getString(request, "password");
//        String temp= MD5.getMd5(password);
//        String newPassword=HttpServletRequestUtil.getString(request,"newPassword");
//        LocalAuth oldLocalAuth=localAuthService.getLocalAuthByUserId(userId);
//        if(newPassword!=null && !oldLocalAuth.getPassword().equals(newPassword)){
//            boolean res=localAuthService.modifyLocalAuth(userId,oldLocalAuth.getUsername(),password,newPassword,new Date());
//            if(res){
//                model.put("success",true);
//            }else{
//                model.put("success",false);
//                model.put("errMsg","修改失败");
//            }
//        }else{
//            model.put("success",false);
//            model.put("errMsg","新密码为空或新旧密码一致");
//        }
//        return model;
//    }
//
//
//    @RequestMapping(value = "logout",method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String,Object> logOut(HttpServletRequest request){
//        Map<String,Object> model=new HashMap<String,Object>();
//        request.getSession().setAttribute("user",null);
//        model.put("success",true);
//        return model;
//    }


}

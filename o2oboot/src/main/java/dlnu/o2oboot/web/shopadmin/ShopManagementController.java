package dlnu.o2oboot.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import dlnu.o2oboot.dto.ImageHolder;
import dlnu.o2oboot.dto.ShopExecution;
import dlnu.o2oboot.entity.Area;
import dlnu.o2oboot.entity.PersonInfo;
import dlnu.o2oboot.entity.Shop;
import dlnu.o2oboot.entity.ShopCategory;
import dlnu.o2oboot.enums.ShopStateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import dlnu.o2oboot.service.AreaService;
import dlnu.o2oboot.service.ShopCategoryService;
import dlnu.o2oboot.service.ShopService;
import dlnu.o2oboot.util.HttpServletRequestUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private AreaService areaService;


    @RequestMapping( value="/shopinitinfo",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getShopInitInfo(){
        HashMap<String,Object> model=new HashMap<String, Object>();
        try{

            List<ShopCategory> shopCategories=shopCategoryService.queryShopCategoryList(new ShopCategory());
            List<Area> areas=areaService.getAreaList();
//            System.out.println(shopCategories.size()+" : "+areas.size());

            model.put("success",true);
            model.put("shopCategoryList",shopCategories);
            model.put("areaList",areas);
        }catch (Exception e){
            model.put("success",false);
            e.printStackTrace();
            model.put("errMsg",e.toString());
        }
        return model;
    }



    @RequestMapping(value = "/shopoperation",method= RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> registerShop(@RequestParam("shopStr")String shopStr, HttpServletRequest request){
//        System.out.println(shopStr);
//        String shopStr=request.getParameter("shopStr");
        Map<String,Object> model=new HashMap<String,Object>();
//        System.out.println(request.getParameter("verifyCodeActual"));
//        System.out.println((String)request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY));
        if(request.getParameter("verifyCodeActual")==null||!request.getParameter("verifyCodeActual").equals((String)request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY))){
            model.put("success",false);
            model.put("errMsg","验证码错误");
            return model;
        }

        shopStr=shopStr.trim();
//        System.out.println("tttt: "+shopStr);

        ObjectMapper objectMapper=new ObjectMapper();
        Shop shop=null;
        try {
//            System.out.println(shopStr==null);
            shop=objectMapper.readValue(shopStr,Shop.class);
        } catch (IOException e) {
            e.printStackTrace();
//            System.out.println("错");
            model.put("success",false);
            model.put("errMsg",e.getMessage());
            return model;
        }
        CommonsMultipartFile shopImg=null;
        CommonsMultipartResolver commonsMultipartResolver=new CommonsMultipartResolver(
                request.getSession().getServletContext()
        );
        if(commonsMultipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multipartHttpServletRequest=
                    (MultipartHttpServletRequest) request;
            shopImg= (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        }

        if(shopImg==null){
            model.put("success", false);
            model.put("errMsg", "上传图片不能为空");
            return model;
        }

        PersonInfo personInfo= (PersonInfo) request.getSession().getAttribute("user");



        shop.setOwner(personInfo);

        ShopExecution se= null;
        try {
            se = shopService.addShop(shop,new ImageHolder(shopImg.getInputStream(),shopImg.getOriginalFilename()));



        } catch (IOException e) {
            e.printStackTrace();
            model.put("success",false);
            model.put("errMsg",e.getMessage());
            return model;
        }
        if(se.getState()== ShopStateEnum.CHECK.getState()){
            model.put("success",true);
            List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
            if (shopList == null || shopList.size() == 0) {
                shopList = new ArrayList<Shop>();
            }
            shopList.add(se.getShop());
            request.getSession().setAttribute("shopList", shopList);

        }
        else{
            model.put("success",false);
            model.put("errMsg","请输入店铺信息");
        }

        return model;

    }


    @RequestMapping(value="/shop",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getShop(HttpServletRequest request){
        Map<String,Object> model=new HashMap<String, Object>();
        long shopId= HttpServletRequestUtil.getLong(request,"shopId");
        if(shopId>-1){
            try{
                Shop shop=shopService.getShopById(shopId);
                List<Area> areaList=areaService.getAreaList();
                if(shop!=null){
                    model.put("success",true);
                    model.put("areaList",areaList);
                    model.put("shop",shop);
                }else{
                    model.put("success",false);
                    model.put("errMsg","无此店铺");
                }
            }catch (Exception e){
                model.put("success",false);
                model.put("errMsg",e.toString());
            }
        }
        else{
            model.put("success",false);
            model.put("errMsg","empty shopId");
        }
        return model;
    }

    @RequestMapping(value="/shops",method=RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getShops(){
        Map<String,Object> model = new HashMap<String, Object>();
        try{
            List<Shop> list=shopService.getShops();
            if(list!=null){
                model.put("success",true);
                model.put("shopList",list);
            }else{
                model.put("success",false);
                model.put("errMsg","无商店信息");
            }
        }catch (Exception e){
            model.put("success",false);
            model.put("errMsg","查询失败");
        }
        return model;
    }

    @RequestMapping(value="/getshoplist",method=RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getShopList(HttpServletRequest request){
        HttpSession session=request.getSession();
        Map<String,Object> model = new HashMap<String, Object>();

        try{

//            PersonInfo tempUser=new PersonInfo();
//            tempUser.setUserId(9L);
//            tempUser.setName("王磊");
//            session.setAttribute("user",tempUser);


            PersonInfo user= (PersonInfo) session.getAttribute("user");
            Shop shopCondition=new Shop();
            shopCondition.setOwner(user);
            ShopExecution se=shopService.getShopList(shopCondition,0,10);

            request.getSession().setAttribute("shopList", se.getShops());

            if(se.getState()==ShopStateEnum.SUCCESS.getState()){
                model.put("success",true);
                model.put("shopList",se.getShops());
                model.put("user",user);
            }else{
                model.put("success",false);
                model.put("errMsg",se.getStateInfo());
            }

        }catch (Exception e){
            model.put("success",false);
            model.put("errMsg",e.toString());
        }
        return model;
    }


    @RequestMapping(value="/modifyshop",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getShop(@RequestParam("shopStr") String shopStr,HttpServletRequest httpServletRequest) {
        Map<String,Object> model=new HashMap<String, Object>();
        try{

            if(httpServletRequest.getParameter("verifyCodeActual")==null||!httpServletRequest.getParameter("verifyCodeActual").equals((String)httpServletRequest.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY))){
                model.put("success",false);
                model.put("errMsg","验证码错误");
                return model;
            }


            ObjectMapper objectMapper=new ObjectMapper();
            Shop shop=objectMapper.readValue(shopStr,Shop.class);



            CommonsMultipartFile shopImg=null;

            CommonsMultipartResolver commonsMultipartResolver=new CommonsMultipartResolver(
                    httpServletRequest.getSession().getServletContext()
            );
            if(commonsMultipartResolver.isMultipart(httpServletRequest)){
                MultipartHttpServletRequest request=(MultipartHttpServletRequest)httpServletRequest;
                shopImg=(CommonsMultipartFile)request.getFile("shopImg");
            }
            ShopExecution se;
            if(shop.getShopId()!=null){
                if(shopImg==null){
                    se=shopService.modifyShop(shop,new ImageHolder(null,null));
                }else{
                    se=shopService.modifyShop(shop,new ImageHolder(shopImg.getInputStream(),shopImg.getOriginalFilename()));
                }
                if(ShopStateEnum.SUCCESS.getState()==se.getState()){
                    model.put("success",true);
                }else {
                    model.put("success",false);
                    model.put("errMsg",se.getStateInfo());
                }

            }else{
                model.put("success",false);
                model.put("errMsg","请输入店铺id");
            }



        }catch (Exception e){
            e.printStackTrace();
            model.put("success",false);
            model.put("errMsg",e.toString());
        }
        return model;
    }



    @RequestMapping(value = "/getshopmanagementinfo" ,method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getShopManagementInfo(HttpServletRequest request){
        Map<String,Object> model=new HashMap<String, Object>();
        HttpSession session=request.getSession();
//        System.out.println(request.getRequestURL());
        long shopId;
        try{
            shopId= Long.valueOf( request.getParameter("shopId") );
        }catch (Exception e){
            shopId=-1;
        }
        //TODO 可能有问题:  我自己多加了个||shopID==null
        if(shopId<=0 ){
            Shop shop= (Shop) session.getAttribute("currentShop");
            if(shop==null){
                model.put("redirect",true);
                //TODO 可能有问题:  url地址可能有错 /shop/shoplist
                model.put("url","/shopadmin/shoplist");
            }else{
                model.put("redirect",false);
                model.put("currentShop",shop);
            }
        }else{
            Shop shop=new Shop();
            shop.setShopId(shopId);
            session.setAttribute("currentShop",shop);
            model.put("currentShop",shop);
            model.put("redirect",false);
        }
        return model;


    }



}

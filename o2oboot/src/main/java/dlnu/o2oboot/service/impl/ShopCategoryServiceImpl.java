package dlnu.o2oboot.service.impl;

import dlnu.o2oboot.cache.JedisUtil;
import dlnu.o2oboot.dao.ShopCategoryDao;
import dlnu.o2oboot.entity.ShopCategory;
import dlnu.o2oboot.service.ShopCategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Autowired
    private JedisUtil.Keys keys;
    @Autowired
    private JedisUtil.Strings strings;



    @Override
    public List<ShopCategory> queryShopCategoryList(ShopCategory shopCategory) {
        ObjectMapper objectMapper=new ObjectMapper();
        String key=SCLISTKEY;
        if(shopCategory==null){
            key=key+"_allfirstlevel";
        }
        else if(shopCategory!=null && shopCategory.getParent()!=null &&
                shopCategory.getParent().getShopCategoryId()!=null){
            key=key+"_parent"+shopCategory.getParent().getShopCategoryId();
        }
        else if(shopCategory!=null){
            key=key+"_allsecondlevel";
        }

        if(!keys.exists(SCLISTKEY)){
            List<ShopCategory> list=shopCategoryDao.queryShopCategory(shopCategory);
            try {
                String json=objectMapper.writeValueAsString(list);
                strings.set(key,json);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            return list;

        }else{
            String json=strings.get(key);
            JavaType javaType=objectMapper.getTypeFactory().constructParametricType(ArrayList.class,ShopCategory.class);
            try {
                List<ShopCategory> list=objectMapper.readValue(json,javaType);
                return list;
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }

        }


    }
}

package dlnu.o2oboot.service.impl;

import dlnu.o2oboot.cache.JedisLockUtil;
import dlnu.o2oboot.cache.JedisUtil;
import dlnu.o2oboot.dao.AreaDao;
import dlnu.o2oboot.entity.Area;
import dlnu.o2oboot.service.AreaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDao areaDao;

    @Autowired
    private JedisUtil.Keys keys;
    @Autowired
    private JedisUtil.Strings strings;

    @Autowired
    private JedisLockUtil lockUtil;

//    private static ReentrantLock reentrantLock=new ReentrantLock(true);
//    private static int i=0;

    @Override
    @Transactional
    //TODO 解决一下synchronized的问题
    public  List<Area> getAreaList() {
        ObjectMapper objectMapper = new ObjectMapper();

        while(true){
            try{
                if(keys.exists(AREALISTKEY)){

                    String json=strings.get(AREALISTKEY);
                    JavaType javaType=objectMapper.getTypeFactory().constructParametricType(ArrayList.class,Area.class);
                    try {
                        List<Area> list=objectMapper.readValue(json,javaType);

                        return list;
                    } catch (IOException e) {
                        e.printStackTrace();

                        throw new RuntimeException(e);
                    }
                }else{
                    List<Area> list=doQuery();
                   if(list!=null){
                       return list;
                   }

                }
            }catch (Exception e){
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }


    }


    private List<Area> doQuery() throws InterruptedException, JsonProcessingException {
//        reentrantLock.lock();
        if(keys.exists(AREALISTKEY)){
//            reentrantLock.unlock();
            return null;
        }
        if(lockUtil.acquire(AREALISTKEY)){
            System.out.println("拿到锁了");
            List<Area> list=areaDao.queryArea();
            ObjectMapper objectMapper = new ObjectMapper();
            String json=objectMapper.writeValueAsString(list);
            strings.set(AREALISTKEY,json);
//                        Thread.sleep(100);
            lockUtil.release();
//            reentrantLock.unlock();
            return list;
        }
//        reentrantLock.unlock();
        return null;
    }


}
//@Service
//public class AreaServiceImpl implements AreaService {
//
//    @Autowired
//    private AreaDao areaDao;
//
//    @Autowired
//    private JedisUtil.Keys keys;
//    @Autowired
//    private JedisUtil.Strings strings;
//
//
//    @Override
//    @Transactional
//    public List<Area> getAreaList() {
//        ObjectMapper objectMapper=new ObjectMapper();
//        if(!keys.exists(AREALISTKEY)){
//            List<Area> list=areaDao.queryArea();
//            try {
//                String json=objectMapper.writeValueAsString(list);
//                strings.set(AREALISTKEY,json);
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//                throw new RuntimeException(e);
//            }
//            return list;
//        }else {
//            String json=strings.get(AREALISTKEY);
//            JavaType javaType=objectMapper.getTypeFactory().constructParametricType(ArrayList.class,Area.class);
//            try {
//                List<Area> list=objectMapper.readValue(json,javaType);
//                return list;
//            } catch (IOException e) {
//                e.printStackTrace();
//                throw new RuntimeException(e);
//            }
//
//        }
//
//
//
//
//    }
//}

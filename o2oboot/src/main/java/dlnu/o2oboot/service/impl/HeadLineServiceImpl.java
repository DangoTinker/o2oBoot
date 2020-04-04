package dlnu.o2oboot.service.impl;

import dlnu.o2oboot.cache.JedisLockUtil;
import dlnu.o2oboot.cache.JedisUtil;
import dlnu.o2oboot.dao.HeadLineDao;
import dlnu.o2oboot.entity.Area;
import dlnu.o2oboot.entity.HeadLine;
import dlnu.o2oboot.service.HeadLineService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class HeadLineServiceImpl implements HeadLineService {
    @Autowired
    private HeadLineDao headLineDao;

    @Autowired
    private JedisUtil.Keys keys;
    @Autowired
    private JedisUtil.Strings strings;
    @Autowired
    private JedisLockUtil lockUtil;



    @Override
    @Transactional
    public List<HeadLine> getHeadLineList(HeadLine headLineCondition) {
        ObjectMapper objectMapper=new ObjectMapper();
        String key=HEADLINELISTKEY;
        if(headLineCondition!=null&&headLineCondition.getEnableStatus()!=null){
            key =key +"_"+headLineCondition.getEnableStatus();
        }

//        if(!keys.exists(key)){
//            List<HeadLine> list=headLineDao.queryHeadLine(headLineCondition);
//            try {
//                String json=objectMapper.writeValueAsString(list);
//                strings.set(key,json);
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//                throw new RuntimeException(e);
//            }
//            return list;
//        }else{
//            String json=strings.get(key);
//            JavaType javaType=objectMapper.getTypeFactory().constructParametricType(ArrayList.class,HeadLine.class);
//            try {
//                List<HeadLine> list=objectMapper.readValue(json,javaType);
//                return list;
//            } catch (IOException e) {
//                e.printStackTrace();
//                throw new RuntimeException(e);
//            }
//
//        }


        while(true){
            try{
                if(keys.exists(HEADLINELISTKEY)){

                    String json=strings.get(HEADLINELISTKEY);
                    JavaType javaType=objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Area.class);
                    try {
                        List<HeadLine> list=objectMapper.readValue(json,javaType);

                        return list;
                    } catch (IOException e) {
                        e.printStackTrace();

                        throw new RuntimeException(e);
                    }
                }else{
                    List<HeadLine> list=doQuery(headLineCondition);
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


    private List<HeadLine> doQuery(HeadLine headLineCondition) throws InterruptedException, JsonProcessingException {
//        reentrantLock.lock();
        if(keys.exists(HEADLINELISTKEY)){
//            reentrantLock.unlock();
            return null;
        }
        if(lockUtil.acquire(HEADLINELISTKEY)){
            System.out.println("拿到锁了");
            List<HeadLine> list=headLineDao.queryHeadLine(headLineCondition);
            ObjectMapper objectMapper = new ObjectMapper();
            String json=objectMapper.writeValueAsString(list);
            strings.set(HEADLINELISTKEY,json);
//                        Thread.sleep(100);
            lockUtil.release();
//            reentrantLock.unlock();
            return list;
        }
//        reentrantLock.unlock();
        return null;
    }
}

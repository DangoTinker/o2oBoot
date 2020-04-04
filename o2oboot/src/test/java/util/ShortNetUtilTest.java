package util;

import com.google.zxing.common.BitMatrix;
import dlnu.o2oboot.O2oApplication;
import dlnu.o2oboot.dao.ProductDao;
import dlnu.o2oboot.dto.ProductExecution;
import dlnu.o2oboot.entity.Area;
import dlnu.o2oboot.entity.Product;
import dlnu.o2oboot.entity.ProductCategory;
import dlnu.o2oboot.entity.Shop;
import dlnu.o2oboot.service.AreaService;
import dlnu.o2oboot.service.ProductService;
import dlnu.o2oboot.util.CodeUtil;
import dlnu.o2oboot.util.ShortNetAddressUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = O2oApplication.class)
public class ShortNetUtilTest{

    @Autowired
    private AreaService areaService;

    class LockThread implements Runnable{
        private String name;

        public void setName(String n){
            name=n;
        }
        @Override
        public void run() {
            Random random=new Random();
//            try {
//                int a=random.nextInt(100)+100;
//                Thread.sleep(a);
//                System.out.println(a);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println("basdd");
            List<Area> list=ShortNetUtilTest.this.areaService.getAreaList();

            System.out.println("ddd");
            for(Area a:list){
                System.out.println(name+" : "+a.getAreaName());
            }
        }
    }

    @Test
    public void threadTest() throws InterruptedException {
        LockThread lockThread1=new LockThread();
        LockThread lockThread2=new LockThread();
        LockThread lockThread3=new LockThread();
        LockThread lockThread4=new LockThread();

        lockThread1.setName("1");
        lockThread2.setName("2");
        lockThread3.setName("3");
        lockThread4.setName("4");

        Thread thread1=new Thread(lockThread1);
        Thread thread2=new Thread(lockThread2);
        Thread thread3=new Thread(lockThread3);
        Thread thread4=new Thread(lockThread4);


        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        Thread.sleep(1000000);
        System.out.println("asd");

    }




    @Test
    public void test(){
        String longUrl="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx0c2b6c7c43f19ff1&redirect_uri=http://121.199.77.177/shopadmin/adduserproductmap&response_type=code&scope=snsapi_userinfo&state=%7BaaaproductIdaaa%3A48%2CaaacustomerIdaaa%3A12%2CaaacreateTimeaaa%3A1574128564540%7D#wechat_redirect";
        String shortUrl = ShortNetAddressUtil.getShortURL(longUrl);
        System.out.println(longUrl);
        System.out.println(shortUrl);
    }

    @Test
    public void testLock(){
        List<Area> list=areaService.getAreaList();
        for(Area a:list){
            System.out.println(a.getAreaName());
        }

    }



}
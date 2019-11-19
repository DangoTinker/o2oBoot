package util;

import com.google.zxing.common.BitMatrix;
import dlnu.o2oboot.util.CodeUtil;
import dlnu.o2oboot.util.ShortNetAddressUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = ShortNetUtilTest.class)
public class ShortNetUtilTest{

    @Test
    public void testQueryArea(){
        String longUrl="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx0c2b6c7c43f19ff1&redirect_uri=http://121.199.77.177/shopadmin/adduserproductmap&response_type=code&scope=snsapi_userinfo&state=%7BaaaproductIdaaa%3A48%2CaaacustomerIdaaa%3A12%2CaaacreateTimeaaa%3A1574128564540%7D#wechat_redirect";
        String shortUrl = ShortNetAddressUtil.getShortURL(longUrl);
        System.out.println(longUrl);
        System.out.println(shortUrl);
    }
}
package dlnu.o2oboot.util;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

//需要在pom.xml引入以下依赖
/**
 <dependency>
<groupId>com.google.code.gson</groupId>
<artifactId>gson</artifactId>
<version>2.8.6</version>
</dependency>
**/
public class ShortNetAddressUtil {
	   final static String CREATE_API = "https://dwz.cn/admin/v2/create";
	    // 设置Token，在https://dwz.cn/console/userinfo 获取自己的token，复制粘贴到这里
	    final static String TOKEN = "7585fda35f10fea8f838cace567acba2";

	    class UrlResponse {
	        @SerializedName("Code")
	        private int code;

	        @SerializedName("ErrMsg")
	        private String errMsg;

	        @SerializedName("LongUrl")
	        private String longUrl;

	        @SerializedName("ShortUrl")
	        private String shortUrl;

	        public int getCode() {
	            return code;
	        }

	        public void setCode(int code) {
	            this.code = code;
	        }

	        public String getErrMsg() {
	            return errMsg;
	        }

	        public void setErrMsg(String errMsg) {
	            this.errMsg = errMsg;
	        }

	        public String getLongUrl() {
	            return longUrl;
	        }

	        public void setLongUrl(String longUrl) {
	            this.longUrl = longUrl;
	        }

	        public String getShortUrl() {
	            return shortUrl;
	        }

	        public void setShortUrl(String shortUrl) {
	            this.shortUrl = shortUrl;
	        }
	    }

	    /**
	     * 创建短网址
	     *
	     * @param longUrl
	     *            长网址：即原网址
	     * @return  成功：短网址
	     *          失败：返回空字符串
	     */
	    public static String getShortURL(String longUrl) {
	        String params = "{\"Url\":\""+ longUrl + "\"}";

	        BufferedReader reader = null;
	        try {
	            // 创建连接
	            URL url = new URL(CREATE_API);
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setDoOutput(true);
	            connection.setDoInput(true);
	            connection.setUseCaches(false);
	            connection.setInstanceFollowRedirects(true);
	            connection.setRequestMethod("POST"); // 设置请求方式
	            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
	            connection.setRequestProperty("Token", TOKEN); // 设置发送数据的格式");

	            // 发起请求
	            connection.connect();
	            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
	            out.append(params);
	            out.flush();
	            out.close();

	            // 读取响应
	            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
	            String line;
	            String res = "";
	            while ((line = reader.readLine()) != null) {
	                res += line;
	            }
	            reader.close();

	            // 抽取生成短网址
	            UrlResponse urlResponse = new Gson().fromJson(res, UrlResponse.class);
	            if (urlResponse.getCode() == 0) {
	                return urlResponse.getShortUrl();
	            } else {
	                System.out.println(urlResponse.getErrMsg());
	            }

	            return ""; // TODO：自定义错误信息
	        } catch (IOException e) {
	            // TODO
	            e.printStackTrace();
	        }
	        return ""; // TODO：自定义错误信息
	    }

	    public static void main(String[] args) {
	        String res = getShortURL("http://myo2o.yitiaojieinfo.com/myo2o/frontend/index");
	        System.out.println(res);
	    }
}

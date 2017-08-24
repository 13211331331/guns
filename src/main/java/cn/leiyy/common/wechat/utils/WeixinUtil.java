package cn.leiyy.common.wechat.utils;

import java.io.*;
import java.net.ConnectException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;


import cn.leiyy.common.wechat.message.menu.MenuList;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import cn.leiyy.common.wechat.message.menu.Menu;
import cn.leiyy.common.wechat.message.pojo.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author HuangHL
 * @date 2017/2/27
 * @see
 */
public class WeixinUtil
{
    private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);

    // 获取access_token的接口地址（GET） 限200（次/天）
    public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    // 菜单创建（POST） 限100（次/天）
    public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    /**
     * 创建菜单
     *
     * @param menu 菜单实例
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public static String createMenu(Menu menu, String accessToken) {
        String result = "0";
        // 拼装创建菜单的url
        String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
        // 将菜单对象转换成json字符串
        String jsonMenu;
        jsonMenu = JSONObject.toJSONString(menu);
        // 调用接口创建菜单
        JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);
        if (null != jsonObject) {
            if (!jsonObject.getString("errcode").equals("0")) {
                result = jsonObject.getString("errcode");
                log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getString("errcode"), jsonObject.getString("errmsg"));
            }
        }

        return result;
    }


    /**
     * 创建菜单
     *
     * @param menu 菜单实例
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public static String createMenuList(MenuList menu, String accessToken) {
        String result = "0";
        // 拼装创建菜单的url
        String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
        // 将菜单对象转换成json字符串
        String jsonMenu;
        jsonMenu = JSONObject.toJSONString(menu);
        // 调用接口创建菜单
        JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);
        if (null != jsonObject) {
            if (!jsonObject.getString("errcode").equals("0")) {
                result = jsonObject.getString("errcode");
                log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getString("errcode"), jsonObject.getString("errmsg"));
            }
        }

        return result;
    }



    /**
     * 获取access_token
     *
     * @param appid 凭证
     * @param appsecret 密钥
     * @return
     */
    public static AccessToken getAccessToken(String appid, String appsecret) {
        AccessToken accessToken = null;

        String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
        // 如果请求成功
        if (null != jsonObject) {
            try {
                accessToken = new AccessToken();
                accessToken.setToken(jsonObject.getString("access_token"));
                accessToken.setExpiresIn(jsonObject.getString("expires_in"));
            } catch (JSONException e) {
                accessToken = null;
                // 获取token失败
                log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getString("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return accessToken;
    }


    /**
     * 描述:  发起https请求并获取结果
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);

            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSONObject.parseObject(buffer.toString());
        } catch (ConnectException ce) {
            log.error("Weixin server connection timed out.");
        } catch (Exception e) {
            log.error("https request error:{}", e);
        }
        return jsonObject;
    }

    /**
     * 方法名：getWxConfig</br>
     * 详述：获取微信的配置信息 </br>
     * 开发人员：souvc  </br>
     * 创建时间：2016-1-5  </br>
     * @param request
     * @return 说明返回值含义
     * @throws
     */
    public static Map<String, Object> getWxConfig(HttpServletRequest request) {
        Map<String, Object> ret = new HashMap<String, Object>();

        String appId = "wxc0bb68f91a99b46c"; // 必填，公众号的唯一标识
        String secret = "2972a0f9b1cb18c35af96d732bbe7b9d";

        String requestUrl = request.getRequestURL().toString();
        System.out.println("requestUrl:"+requestUrl);
        String access_token = "";
        String jsapi_ticket = "";
        String timestamp = Long.toString(System.currentTimeMillis() / 1000); // 必填，生成签名的时间戳
        String nonceStr = UUID.randomUUID().toString(); // 必填，生成签名的随机串
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ appId + "&secret=" + secret;

        JSONObject json = WeixinUtil.httpRequest(url, "GET", null);

        if (json != null) {
            //要注意，access_token需要缓存
            access_token = json.getString("access_token");

            url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+ access_token + "&type=jsapi";
            json = WeixinUtil.httpRequest(url, "GET", null);
            if (json != null) {
                jsapi_ticket = json.getString("ticket");
            }
        }
        String signature = "";
        // 注意这里参数名必须全部小写，且必须有序
        String sign = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonceStr+ "&timestamp=" + timestamp + "&url=" + requestUrl;
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(sign.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ret.put("appId", appId);
        ret.put("timestamp", timestamp);
        ret.put("nonceStr", nonceStr);
        ret.put("signature", signature);
        return ret;
    }




    /**
     * 方法名：byteToHex</br>
     * 详述：字符串加密辅助方法 </br>
     * 开发人员：souvc  </br>
     * 创建时间：2016-1-5  </br>
     * @param hash
     * @return 说明返回值含义
     * @throws
     */
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;

    }
}

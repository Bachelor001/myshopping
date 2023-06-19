package Jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    static String secret = "bjwl@admin001.com#bj~!-=15xiaobaobei.com";

    /**
     * 生成token字符串
     */
    public static String createToken() {


        //设置标头的map集合
        Map<String, Object> headerMap = new HashMap<String, Object>() {{
            put("type", "JWT");//设置token的类型
            put("alg", "HS256");//设置签名算法
        }};
        //设置负载信息的map集合，在其中可以添加token的附加数据和官方提供的字段
        Map<String, Object> payloadMap = new HashMap<String, Object>() {{
            //用户编号
            put("user_id", 1001);
            //用户名
            put("user_name", "admin");
            //签发人    一下为jwt提供的标准字段
            put("iss", "hetong");
            //过期时间
            put("exp", new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 7)));
            //设置签发时间
            put("iat", new Date());
            //主题
            put("sub", "JWT测试");
            //设置编号
            put("jti", "test01");

        }};
        String token = Jwts
                .builder()//创建jwts的构建器对象
                    .setHeader(headerMap)//设置token的标头
                    .setClaims(payloadMap)//token负载信息
                    //设置密钥
                    .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)),
                            SignatureAlgorithm.HS256)
                    .compact();//生成token字符串

        return token;
    }

    /**
     * 解析token，必须提供token字符串和密钥
     * @param token
     */
    public static void parseToken(String token) {
        //解析指定的token字符串并返回jwt对象
        Jws<Claims> claimsJws = Jwts
                //创建解析构建器对象
                .parserBuilder()
                    //设置解析器解析时所使用的密钥
                    .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                    //获得一个token的解析器对象
                    .build()
                        //解析指定的token
                        .parseClaimsJws(token);
        //获得签名
        String signature = claimsJws.getSignature();
        System.out.println(signature);
        Claims body = claimsJws.getBody();
        System.out.println(body);
        JwsHeader header = claimsJws.getHeader();
        System.out.println(header.getAlgorithm());
    }
}

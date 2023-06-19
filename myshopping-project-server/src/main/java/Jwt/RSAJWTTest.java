package Jwt;

import com.shopping.utils.RsaUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RSAJWTTest {
    public static void main(String[] args) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        String token = createToken();
        System.out.println(token);
    }

    /**
     * 生成token字符串，签发
     *
     * @return
     */
    public static String createToken() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        //读取私钥
        PrivateKey privateKey = RsaUtils.getPrivateKey("D:\\IDEA\\workspences\\myshopping\\myshopping-project-server\\src\\main\\resources\\keys\\key.pri");
        PublicKey publicKey = RsaUtils.getPublicKey("D:\\IDEA\\workspences\\myshopping\\myshopping-project-server\\src\\main\\resources\\keys\\key.pub");
        //设置标头的map集合
        Map<String, Object> headerMap = new HashMap<String, Object>() {{
            put("typ", "JWT");//设置token的类型
            put("alg", "HS256");//设置签名算法
        }};

        //设置负载信息的map集合,在其中可以添加token的附加数据和官方提供的字段
        Map<String, Object> payloadMap = new HashMap<String, Object>() {{
            put("user_id", 1001);//用户编号
            put("user_name", "admin");//用户名
            //以下为JWT提供的标准字段
            put("iss", "ht");//签发人
            put("exp", new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 7)));//过期时间
            put("iat", new Date());//设置签发时间
            put("sub", "JWT测试");//主题
            put("jti", "admin001");//设置编号
        }};
        String token = Jwts
                .builder()//创建JWTS的构建器对象
                .setHeader(headerMap)//设置token的标头(可以不设置存在默认值)
                .setClaims(payloadMap)//设置token的负载信息
                .signWith(privateKey)//设置签名使用私钥生成
                .compact();//生成token字符串

        return token;
    }


    /**
     * 解析token
     * 解析token时必须提供token字符串和秘钥
     *
     * @param token
     */
    public static void parseToken(String token) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {

        //读取私钥
        PublicKey privateKey = RsaUtils.getPublicKey("D:\\IDEA\\workspences\\myshopping\\myshopping-project-server\\src\\main\\resources\\keys\\key.pri");
        //读取公钥
        PublicKey publicKey = RsaUtils.getPublicKey("D:\\IDEA\\workspences\\myshopping\\myshopping-project-server\\src\\main\\resources\\keys\\key.pub");

        //解析指定的token字符串并返回JWT对象
        Jws<Claims> claimsJws = Jwts
                .parserBuilder()//创建解析构建器对象
                .setSigningKey(publicKey)//设置解析器解析时所使用的私钥
                .build()//获得一个token的解析器对象
                .parseClaimsJws(token);//解析指定的token

        String signature = claimsJws.getSignature();//获得签名
        System.out.println(signature);
        JwsHeader header = claimsJws.getHeader();//获得标头对象
        System.out.println(header.getAlgorithm());//获得签名算法
        //获得负载数据payload
        Claims claims = claimsJws.getBody();
        System.out.println(claims.getSubject());//获得主题
        System.out.println(claims.getIssuer());//获得签发人
        System.out.println(claims.getId());//获得编号
        System.out.println(claims.getIssuedAt());//获得签发时间
        System.out.println(claims.getExpiration());//获得过期时间

        System.out.println(claims.get("user_id", Integer.class));//获得userId
        System.out.println(claims.get("user_name", String.class));//获得附加信息userName
    }
}

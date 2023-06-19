package Jwt;

import com.shopping.utils.RsaUtils;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class RsaTest {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        String publicKeyFileName = "D:\\IDEA\\workspences\\myshopping\\myshopping-project-server\\src\\main\\resources\\keys\\key.pub";
        String privateKeyFileName = "D:\\IDEA\\workspences\\myshopping\\myshopping-project-server\\src\\main\\resources\\keys\\key.pri";
        RsaUtils.generateKey(publicKeyFileName,privateKeyFileName,"ht",1024);



    }
}

package Presentacion;

import org.apache.commons.codec.digest.DigestUtils;

public class PruebaEncriptacion {
    public static void main(String[] args) {
        String md5 = DigestUtils.md5Hex("48221945");
        
        System.out.println(md5);
    }
}

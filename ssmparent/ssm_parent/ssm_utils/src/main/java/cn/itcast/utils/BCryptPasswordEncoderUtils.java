package cn.itcast.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @program: ssmparent
 * @description:
 * @author: Mr.Cai
 * @create: 2019-06-21 20:47
 */
public class BCryptPasswordEncoderUtils {

    private static BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
    public static String encodePassword(String password){
        String s = bCryptPasswordEncoder.encode(password);
        return s;
    }
    public static void main(String[] args) {
        String password = encodePassword("wangrui");
        //$2a$10$Y36kJ18O6lfue3VHVZyMLOd8/Cm4UVqrSpYYwRDkkc23.XmLU28qS
        System.out.println(password);
    }
}

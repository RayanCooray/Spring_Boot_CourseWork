package lk.ijse.gdse.aad.spring_boot_coursework.util;

import java.util.Base64;

public class Imp {
    public static String convertBase64(String data){
        return Base64.getEncoder().encodeToString(data.getBytes());
    }
}

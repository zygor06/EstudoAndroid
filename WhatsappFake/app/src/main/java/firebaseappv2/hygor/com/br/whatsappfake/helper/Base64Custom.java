package firebaseappv2.hygor.com.br.whatsappfake.helper;

import android.util.Base64;

/**
 * Created by Aragorn on 01/09/2017.
 */

public class Base64Custom {

    public static String codificar64(String s){
       return  Base64.encodeToString(s.getBytes(), Base64.DEFAULT).replaceAll("(\\n|\\r)", "");
    }

    public static String decodificar64(String s){
        return  new String(Base64.decode(s.getBytes(), Base64.DEFAULT));
    }

}

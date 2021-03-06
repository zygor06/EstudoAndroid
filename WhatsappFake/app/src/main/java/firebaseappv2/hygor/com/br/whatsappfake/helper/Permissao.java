package firebaseappv2.hygor.com.br.whatsappfake.helper;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.ConditionVariable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by x05291657162 on 30/08/2017.
 */

public class Permissao {

    public static boolean validaPermissoes(Activity activity, String[] permissoes, int requestCode){

        if(Build.VERSION.SDK_INT >= 23){

            List<String> listaPermissoes = new ArrayList<String>();

            for(String permissao : permissoes){
                boolean valida = ContextCompat.checkSelfPermission(activity, permissao) == PackageManager.PERMISSION_GRANTED;
                if(!valida) listaPermissoes.add(permissao);
            }

            if(listaPermissoes.isEmpty())return true;

            String[] novasPermissoes = new String[listaPermissoes.size()];
            listaPermissoes.toArray(novasPermissoes);

            ActivityCompat.requestPermissions(activity, novasPermissoes, requestCode);
        }
        return true;
    }
}

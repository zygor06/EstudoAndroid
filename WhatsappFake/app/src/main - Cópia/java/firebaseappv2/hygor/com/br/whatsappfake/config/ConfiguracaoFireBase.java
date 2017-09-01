package firebaseappv2.hygor.com.br.whatsappfake.config;

import android.provider.ContactsContract;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Aragorn on 31/08/2017.
 */

public final class ConfiguracaoFireBase {

    private static DatabaseReference referenciaFirebase;

    public static DatabaseReference getFirebase(){

        if(referenciaFirebase == null){
            referenciaFirebase = FirebaseDatabase.getInstance().getReference();
        }

        return referenciaFirebase;
    }


}

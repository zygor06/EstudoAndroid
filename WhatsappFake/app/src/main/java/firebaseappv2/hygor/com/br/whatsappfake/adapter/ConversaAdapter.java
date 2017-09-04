package firebaseappv2.hygor.com.br.whatsappfake.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import firebaseappv2.hygor.com.br.whatsappfake.R;
import firebaseappv2.hygor.com.br.whatsappfake.helper.Preferencias;
import firebaseappv2.hygor.com.br.whatsappfake.model.Conversa;

import static android.R.attr.resource;

/**
 * Created by Aragorn on 02/09/2017.
 */

public class ConversaAdapter extends ArrayAdapter<Conversa>{

    private ArrayList<Conversa> conversas;
    private Context context;


    public ConversaAdapter(@NonNull Context c, @NonNull ArrayList<Conversa> objects) {
        super(c, 0, objects);
        this.conversas = objects;
        this.context = c;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = null;

        if(conversas != null){

            Preferencias preferencias = new Preferencias(context);

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

                        view = inflater.inflate(R.layout.lista_conversa, parent, false);

            TextView nomeConversa = (TextView) view.findViewById(R.id.tv_nomeConversa);
            TextView ultimaMensagem = (TextView) view.findViewById(R.id.tv_ultimaMensagem);

            Conversa conversa = conversas.get(position);

            nomeConversa.setText(conversa.getNome());

            ultimaMensagem.setText(conversa.getMensagem());

        }

        return view;
    }
}

package firebaseappv2.hygor.com.br.whatsappfake.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.zip.Inflater;

import firebaseappv2.hygor.com.br.whatsappfake.R;
import firebaseappv2.hygor.com.br.whatsappfake.activity.ConversaActivity;
import firebaseappv2.hygor.com.br.whatsappfake.adapter.ConversaAdapter;
import firebaseappv2.hygor.com.br.whatsappfake.config.ConfiguracaoFireBase;
import firebaseappv2.hygor.com.br.whatsappfake.helper.Base64Custom;
import firebaseappv2.hygor.com.br.whatsappfake.helper.Preferencias;
import firebaseappv2.hygor.com.br.whatsappfake.model.Contato;
import firebaseappv2.hygor.com.br.whatsappfake.model.Conversa;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConversasFragment extends Fragment {

    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<Conversa> conversas;
    private DatabaseReference reference;
    private ValueEventListener eventListener;


    public ConversasFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        if(reference != null)
        reference.addValueEventListener(eventListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(reference != null)
        reference.removeEventListener(eventListener);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        conversas = new ArrayList<>();

        View view = inflater.inflate(R.layout.fragment_conversas, container, false);
        listView = (ListView) view.findViewById(R.id.lv_conversas);

        adapter = new ConversaAdapter(getActivity(), conversas);
        listView.setAdapter(adapter);

        Preferencias preferencias = new Preferencias(getActivity());
        String identificadorUsuarioLogado = preferencias.getIdentificador();
        reference = ConfiguracaoFireBase.getFirebase().child("conversas").child(identificadorUsuarioLogado);

        eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                conversas.clear();

                for (DataSnapshot dados : dataSnapshot.getChildren()){

                    Conversa conversa = dados.getValue(Conversa.class);
                    conversas.add(conversa);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(), ConversaActivity.class);

                //ENVIANDO DADOS

                Conversa conversa = conversas.get(position);

                intent.putExtra("nome", conversa.getNome());
                intent.putExtra("email", Base64Custom.decodificar64(conversa.getIdUsuario()));

                startActivity(intent);

            }
        });

        return view;
    }

}

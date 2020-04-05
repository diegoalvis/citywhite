package brayan.rivera.whitecity.ui.iglesias;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import brayan.rivera.whitecity.R;
import brayan.rivera.whitecity.controlador.AdaptadorSitios;
import brayan.rivera.whitecity.controlador.FireBaseHelper;
import brayan.rivera.whitecity.data.modelos.Sitio;


public class IglesiasFragment extends Fragment implements View.OnClickListener {

    private RecyclerView rvLista;
    private TextView titulo;
    private AdaptadorSitios adaptador;

    FireBaseHelper helper;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista, container, false);
        titulo = view.findViewById(R.id.tv_titulo);
        rvLista = view.findViewById(R.id.rv_lista);

        titulo.setText(R.string.title_iglesias);
        adaptador = new AdaptadorSitios(this);

        rvLista.setAdapter(adaptador);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

        // cargamos datos de Firebase (iglesias)
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("sitios/iglesias");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Sitio> sitios = new ArrayList<>();
                for (DataSnapshot iglesiaSnapshot : dataSnapshot.getChildren()) {
                    sitios.add(iglesiaSnapshot.getValue(Sitio.class));
                    sitios.add(iglesiaSnapshot.getValue(Sitio.class));
                    sitios.add(iglesiaSnapshot.getValue(Sitio.class));
                    sitios.add(iglesiaSnapshot.getValue(Sitio.class));
                    sitios.add(iglesiaSnapshot.getValue(Sitio.class));
                    sitios.add(iglesiaSnapshot.getValue(Sitio.class));
                    sitios.add(iglesiaSnapshot.getValue(Sitio.class));
                    sitios.add(iglesiaSnapshot.getValue(Sitio.class));
                }
                adaptador.setSitios(sitios);
                adaptador.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("error", databaseError.toException());
            }
        });
    }


//            helper.listarsitios(rv_lista_Sitios);
//
//            FireBaseHelper.adaptador_sitios.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                 FireBaseHelper.posicion2=rv_lista_Sitios.getChildLayoutPosition(v);
//                Toast.makeText(getContext(),"Ingresaste a sitio turistico : "+FireBaseHelper.sitios.get(rv_lista_Sitios.getChildAdapterPosition(v)).getNombre(),Toast.LENGTH_SHORT).show();
//
//                // Crea el nuevo fragmento y la transacción.
//                Fragment nuevoFragmento = new Detalle_Sitio();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.nav_host_fragment, nuevoFragmento);
//                transaction.addToBackStack(null);
//
//                // Commit a la transacción
//                transaction.commit();
//
//            }
//        });
//
//

    @Override
    public void onClick(View v) {

    }
}

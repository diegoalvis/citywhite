package brayan.rivera.whitecity.ui.favoritos;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
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
import brayan.rivera.whitecity.controlador.SessionHelper;
import brayan.rivera.whitecity.data.modelos.Sitio;

public class FavoritosFragment extends Fragment {

    private RecyclerView rvLista;
    private TextView titulo;
    private TextView noHaySitios;
    private ProgressBar progressBar;

    private AdaptadorSitios adaptador;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista, container, false);
        titulo = view.findViewById(R.id.tv_titulo);
        rvLista = view.findViewById(R.id.rv_lista);
        progressBar = view.findViewById(R.id.progress);
        noHaySitios = view.findViewById(R.id.tv_no_hay_sitios);

        titulo.setText(R.string.title_favoitos);
        adaptador = new AdaptadorSitios(getActivity());

        rvLista.setAdapter(adaptador);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        progressBar.setVisibility(View.VISIBLE);
        // cargamos datos de Firebase (iglesias)
        String key = new SessionHelper(getContext()).getUserId();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("usuarios/" + key + "/favoritos/");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Sitio> sitios = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    sitios.add(snapshot.getValue(Sitio.class));
                }
                if (sitios.isEmpty()) {
                    noHaySitios.setVisibility(View.VISIBLE);
                } else {
                    noHaySitios.setVisibility(View.GONE);
                }
                progressBar.setVisibility(View.GONE);
                adaptador.setSitios(sitios);
                adaptador.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("error", databaseError.toException());
                progressBar.setVisibility(View.GONE);
            }
        });


//        registrarNuevoUsuario(new Usuario("teae", "sdasdsa", "asdas", false));
    }


}
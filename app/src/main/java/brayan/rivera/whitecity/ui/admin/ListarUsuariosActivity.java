package brayan.rivera.whitecity.ui.admin;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import brayan.rivera.whitecity.R;
import brayan.rivera.whitecity.controlador.AdaptadorUsuarios;
import brayan.rivera.whitecity.data.modelos.Usuario;

public class ListarUsuariosActivity extends AppCompatActivity {

    private RecyclerView rvLista;
    private TextView titulo;
    private ProgressBar progressBar;

    private AdaptadorUsuarios adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_lista);

        titulo = findViewById(R.id.tv_titulo);
        rvLista = findViewById(R.id.rv_lista);
        progressBar = findViewById(R.id.progress);

        titulo.setText(R.string.title_iglesias);
        adaptador = new AdaptadorUsuarios();

        rvLista.setAdapter(adaptador);

        progressBar.setVisibility(View.VISIBLE);
        // cargamos datos de Firebase (iglesias)
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("usuarios");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Usuario> usuarios = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    usuarios.add(snapshot.getValue(Usuario.class));

                }
                progressBar.setVisibility(View.GONE);
                adaptador.setUsuarios(usuarios);
                adaptador.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("error", databaseError.toException());
                progressBar.setVisibility(View.GONE);
            }
        });
    }

}

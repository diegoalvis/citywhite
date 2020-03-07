package brayan.rivera.whitecity.ui.MenuPrincipal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import brayan.rivera.whitecity.R;
import brayan.rivera.whitecity.ui.MenuPrincipal.modelos.Sitio;

public class CrearSitios extends AppCompatActivity {

    private EditText editTextNombre;
    private EditText editTextDescripcion;
    private EditText editTextDireccion;
    private EditText editTextTelefono;
    private EditText editTextFacebook;
    private List<Sitio> listadoSitiosRegistrados;
    private ListView listView;
    private ArrayAdapter<Sitio> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_sitios);
        inicializarViews();
        consultarSitios();
    }

    private void inicializarViews() {
        editTextNombre = findViewById(R.id.nombreSitio);
        editTextDescripcion  = findViewById(R.id.descripcionSitio);
        editTextDireccion = findViewById(R.id.direccionSitio);
        editTextTelefono = findViewById(R.id.telefonoSitio);
        editTextFacebook = findViewById(R.id.facebookSitio);
        listadoSitiosRegistrados = new ArrayList<>();
        listView = findViewById(R.id.listaSitios);
    }

    public void createSitio(View view) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("sitios");

        Sitio sitio = new Sitio();
        sitio.setNombre(editTextNombre.getText().toString());
        sitio.setDescripcion(editTextDescripcion.getText().toString());
        sitio.setTelefono(editTextTelefono.getText().toString());
        sitio.setDireccion(editTextDireccion.getText().toString());
        sitio.setFacebook(editTextFacebook.getText().toString());
        sitio.setIdSitio(UUID.randomUUID().toString());


        myRef.child("Comida tradicional").child(sitio.getIdSitio()).setValue(sitio); //crear categoria iglesias


    }

    public void consultarSitios(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("sitios");
        listadoSitiosRegistrados.clear();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot item : dataSnapshot.getChildren()){
                    Sitio temporal = item.getValue(Sitio.class);
                    listadoSitiosRegistrados.add(temporal);
                }
                arrayAdapter = new ArrayAdapter<Sitio>(CrearSitios.this, android.R.layout.simple_list_item_1,listadoSitiosRegistrados);
                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

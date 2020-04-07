package brayan.rivera.whitecity.ui.detalle;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import brayan.rivera.whitecity.R;
import brayan.rivera.whitecity.data.modelos.Sitio;

public class DetalleActivity extends AppCompatActivity {

    private Sitio sitio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sitio = (Sitio) extras.getSerializable("sitio");
            cargarInfo(sitio);
        }
    }

    private void cargarInfo(Sitio sitio) {
        //sitio.getNombre();

        TextView nombre = (TextView) findViewById(R.id.titulo_lugar_detalle);
        TextView descripcion = (TextView) findViewById(R.id.descripcion_lugar_detalle);
        TextView direccion = (TextView) findViewById(R.id.direccion_lugar_detalle);
        TextView telefono = (TextView) findViewById(R.id.telefono_lugar_detalle);
        TextView facebook = (TextView) findViewById(R.id.facebook_lugar_detalle);



        nombre.setText(sitio.getNombre());
        descripcion.setText(sitio.getDescripcion());
        direccion.setText(sitio.getDireccion());
        telefono.setText(sitio.getTelefono());
        facebook.setText(sitio.getFacebook());



    }


}

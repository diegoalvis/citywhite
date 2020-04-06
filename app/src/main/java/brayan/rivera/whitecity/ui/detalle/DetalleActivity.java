package brayan.rivera.whitecity.ui.detalle;

import android.os.Bundle;

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
        sitio.getNombre();
    }


}

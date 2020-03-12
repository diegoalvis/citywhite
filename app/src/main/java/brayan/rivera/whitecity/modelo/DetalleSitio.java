package brayan.rivera.whitecity.modelo;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import brayan.rivera.whitecity.R;
import brayan.rivera.whitecity.controlador.FireBaseHelper;

public class DetalleSitio extends AppCompatActivity {

    TextView txt_nombre_Sitio_DETALLE_SITIO,txt_descripcion_Sitio_DETALLE_SITIO,
             txt_direccion_DETALLE_SITIO,txt_telefono_DETALLE_SITIO;
    ImageView img_sitio_DETALLE_SITIO;
    FireBaseHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_sitio);

        txt_nombre_Sitio_DETALLE_SITIO=findViewById(R.id.txt_nombre_DETALLE_SITIO);
        txt_descripcion_Sitio_DETALLE_SITIO=findViewById(R.id.txt_descripcion_DETALLE_SITIO);
        txt_direccion_DETALLE_SITIO=findViewById(R.id.txt_direccion_DETALLE_SITIO);
        txt_telefono_DETALLE_SITIO=findViewById(R.id.txt_telefono_DETALLE_SITIO);

        img_sitio_DETALLE_SITIO=findViewById(R.id.img_sitio_DETALLE_SITIO);


        //guardo el valor de mi posicion en un objeto de tipo sitio
        Sitio sitio=Adaptador_Sitios.ListaSitios.get(FireBaseHelper.posicion2);

        txt_nombre_Sitio_DETALLE_SITIO.setText(sitio.getNombre());
        txt_descripcion_Sitio_DETALLE_SITIO.setText(sitio.getDescripcion());
        txt_direccion_DETALLE_SITIO.setText(sitio.getDireccion());
        txt_telefono_DETALLE_SITIO.setText(sitio.getTelefono());

        //metodo para estraer la foto a cargar
        String nombrefoto=MainActivity.listafotos.get(FireBaseHelper.posicion2);

        helper=new FireBaseHelper(this);

        helper.consultarImagen(img_sitio_DETALLE_SITIO,nombrefoto);


    }
}

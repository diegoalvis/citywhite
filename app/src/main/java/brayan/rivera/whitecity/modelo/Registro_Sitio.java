package brayan.rivera.whitecity.modelo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import brayan.rivera.whitecity.R;
import brayan.rivera.whitecity.controlador.FireBaseHelper;
import brayan.rivera.whitecity.controlador.Sitio;

public class Registro_Sitio extends AppCompatActivity implements View.OnClickListener{

    EditText txt_nombre_Sitio_REGISTRO_SITIO;
    EditText txt_descripcion_REGISTRO_SITIO;
    EditText txt_direccion_REGISTRO_SITIO;
    EditText txt_telefono_REGISTRO_SITIO;
    EditText txt_facebook_REGISTRO_SITIO;

    String nombre;
    String descripcion;
    String direccion;
    String telefono;
    String facebook;

    Button btn_subir_Sitios_REGISTRO_SITIO;

    Spinner sp_categorias_REGISTRO_SITIO;
    FireBaseHelper helper;
    public  static String categoria;

    Sitio sitio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro__sitio);

        sitio=new Sitio();
        helper= new FireBaseHelper(this);

        //metodo para instanciar los elementos de la vista
        referenciarElementos();
        cargarCategorias();

        btn_subir_Sitios_REGISTRO_SITIO.setOnClickListener(this);

    }

    public  void referenciarElementos()
    {
        txt_nombre_Sitio_REGISTRO_SITIO=findViewById(R.id.txt_nombre_Sitio_REGISTRO_SITIO);
        txt_descripcion_REGISTRO_SITIO=findViewById(R.id.txt_descripcion_Sitio_REGISTRO_SITIO);
        txt_direccion_REGISTRO_SITIO=findViewById(R.id.txt_direccion_Sitio_REGISTRO_SITIO);
        txt_telefono_REGISTRO_SITIO=findViewById(R.id.txt_telefono_Sitio_REGISTRO_SITIO);
        txt_facebook_REGISTRO_SITIO=findViewById(R.id.txt_facebook_Sitio_REGISTRO_SITIO);
        sp_categorias_REGISTRO_SITIO=findViewById(R.id.sp_categorias_Sitio_REGISTRO_SITIIO);
        btn_subir_Sitios_REGISTRO_SITIO=findViewById(R.id.btn_subir_Sitio_REGISTRO_SITIO);
    }

    public void cargarCategorias()
    {
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.categorias,android.R.layout.simple_spinner_item);
        sp_categorias_REGISTRO_SITIO.setAdapter(adapter);
        sp_categorias_REGISTRO_SITIO.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> spn, android.view.View v, int posicion, long id) {

                switch (posicion)
                {

                    case 1:
                        categoria="Iglesias";
                        break;
                    case 2:
                        categoria="Sitios de Interes";
                        break;
                    case 3:
                        categoria="Museos";
                        break;
                    case 4:
                        categoria="Comida Tipica";
                        break;
                    case 5:
                        categoria="Hoteles";
                        break;
                }

            }
            public void onNothingSelected(AdapterView<?> spn) { }
        });

    }

    public void extraerDatos()
    {
        nombre=txt_nombre_Sitio_REGISTRO_SITIO.getText().toString();
        descripcion=txt_descripcion_REGISTRO_SITIO.getText().toString();
        direccion=txt_direccion_REGISTRO_SITIO.getText().toString();
        telefono=txt_telefono_REGISTRO_SITIO.getText().toString();
        facebook=txt_facebook_REGISTRO_SITIO.getText().toString();

        helper.registrarSitio(nombre,descripcion,direccion,telefono,facebook);
    }



    @Override
    public void onClick(View v) {

        extraerDatos();

    }
}

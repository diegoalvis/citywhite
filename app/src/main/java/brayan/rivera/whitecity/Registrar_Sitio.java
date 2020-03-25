package brayan.rivera.whitecity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import brayan.rivera.whitecity.controlador.FireBaseHelper;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Registrar_Sitio#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Registrar_Sitio extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    View view;
    Spinner sp_categorias_REGISTRO_ADMIN;
    EditText txt_nombre_Sitio_REGISTRO_ADMIN;
    EditText txt_descripcion_REGISTRO_ADMIN;
    EditText txt_direccion_REGISTRO_ADMIN;
    EditText txt_telefono_REGISTRO_ADMIN;
    EditText txt_facebook_REGISTRO_ADMIN;
    Button btn_subir_Sitios_REGISTRO_ADMIN;

    public  static String categoria;
    FireBaseHelper helper;

    String nombre;
    String descripcion;
    String direccion;
    String telefono;
    String facebook;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Registrar_Sitio() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Registrar_Sitio.
     */
    // TODO: Rename and change types and number of parameters
    public static Registrar_Sitio newInstance(String param1, String param2) {
        Registrar_Sitio fragment = new Registrar_Sitio();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        helper = new FireBaseHelper(getActivity());
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_registrar__sitio, container, false);

        referenciarElementos();
        cargarCategorias();
        btn_subir_Sitios_REGISTRO_ADMIN.setOnClickListener(this);


        return view;
    }

    public  void referenciarElementos()
    {
        txt_nombre_Sitio_REGISTRO_ADMIN=view.findViewById(R.id.txt_nombre_Sitio_REGISTRO_ADMIN);
        txt_descripcion_REGISTRO_ADMIN=view.findViewById(R.id.txt_descripcion_Sitio_REGISTRO_ADMIN);
        txt_direccion_REGISTRO_ADMIN=view.findViewById(R.id.txt_direccion_Sitio_REGISTRO_ADMIN);
        txt_telefono_REGISTRO_ADMIN=view.findViewById(R.id.txt_telefono_Sitio_REGISTRO_ADMIN);
        txt_facebook_REGISTRO_ADMIN=view.findViewById(R.id.txt_facebook_Sitio_REGISTRO_ADMIN);
        sp_categorias_REGISTRO_ADMIN=view.findViewById(R.id.sp_categorias_Sitio_REGISTRO_ADMIN);
        btn_subir_Sitios_REGISTRO_ADMIN=view.findViewById(R.id.btn_subir_Sitio_REGISTRO_ADMIN);
    }

    public void cargarCategorias()
    {
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(getActivity(),R.array.categorias,android.R.layout.simple_spinner_item);
        sp_categorias_REGISTRO_ADMIN.setAdapter(adapter);
        sp_categorias_REGISTRO_ADMIN.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
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
        nombre=txt_nombre_Sitio_REGISTRO_ADMIN.getText().toString();
        descripcion=txt_descripcion_REGISTRO_ADMIN.getText().toString();
        direccion=txt_direccion_REGISTRO_ADMIN.getText().toString();
        telefono=txt_telefono_REGISTRO_ADMIN.getText().toString();
        facebook=txt_facebook_REGISTRO_ADMIN.getText().toString();

        helper.registrarSitio(nombre,descripcion,direccion,telefono,facebook);
    }


    @Override
    public void onClick(View v) {
        extraerDatos();

    }
}

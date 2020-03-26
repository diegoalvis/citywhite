package brayan.rivera.whitecity.modelo;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.ArrayList;

import brayan.rivera.whitecity.R;
import brayan.rivera.whitecity.controlador.Adaptador_Sitios;
import brayan.rivera.whitecity.controlador.FireBaseHelper;
import brayan.rivera.whitecity.controlador.Sitio;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Detalle_Sitio#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Detalle_Sitio extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;


    TextView txt_nombre_Sitio_DETALLE_SITIO;
    TextView txt_descripcion_Sitio_DETALLE_SITIO;
    TextView txt_direccion_DETALLE_SITIO;
    TextView txt_telefono_DETALLE_SITIO;

    ImageView img_sitio_DETALLE_SITIO;
    //creo un objeto de la clase firebasehelper para traer datos
    FireBaseHelper helper;
    //variable tipo lista string para guardar los nombres de las fotos de los sitios
    ArrayList<String>listalugares;
    //vartiable tipo lista sting para guardar el nmbre de los soidos de los sitios
    ArrayList<String>listasonidos;

    //instanciamos un nuevo objeto de la clase sitio
    Sitio sitio;

    //variable tipo boton para reproducir el sonido
    Button btn_reproducir_Sonido_DETALLE_SITIO;

    MediaPlayer mp_sonido_Sitio_DETALLE_SITIO;
    SeekBar   sb_sonido_Sitio_DETALLE_SITIO;

    String nombresonido;

    Handler handler;
    Runnable runnable;

    View view;
    public Detalle_Sitio() {
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view =inflater.inflate(R.layout.fragment_detalle__sitio, container, false);


        //creamos un nuevo objeto de la clase firebasehelper
        helper=new FireBaseHelper(getActivity());
        //metodo para instanciar los elementos de la vista
        referenciaElementos();
        //metodo para obtener la informacion de cada sitio
        obtenerInformacion();
        //metodo para traer el sonido del storage
        consultarSonido();

        //le digo que el boton me detecte el click en este contesto
        btn_reproducir_Sonido_DETALLE_SITIO.setOnClickListener(this);

        handler=new Handler();



        return view;
    }
    public  void referenciaElementos()

    {
        txt_nombre_Sitio_DETALLE_SITIO=view.findViewById(R.id.txt_nombre_DETALLE_SITIO);
        txt_descripcion_Sitio_DETALLE_SITIO=view.findViewById(R.id.txt_descripcion_DETALLE_SITIO);
        txt_direccion_DETALLE_SITIO=view.findViewById(R.id.txt_direccion_DETALLE_SITIO);
        txt_telefono_DETALLE_SITIO=view.findViewById(R.id.txt_telefono_DETALLE_SITIO);
        img_sitio_DETALLE_SITIO=view.findViewById(R.id.img_sitio_DETALLE_SITIO);
        btn_reproducir_Sonido_DETALLE_SITIO=view.findViewById(R.id.btn_reproducir_sonido);
        sb_sonido_Sitio_DETALLE_SITIO=view.findViewById(R.id.sb_sonido_Sitio_DETALLE_SITIO);

    }

    public  void obtenerInformacion()
    {

        //creamos un nuevo objeto de tipo sitio para traer los contenidos multimedia de cada sitio
        sitio=new Sitio();
        //guardo el valor de mi posicion en un objeto de tipo sitio
        sitio= Adaptador_Sitios.ListaSitios.get(FireBaseHelper.posicion2);

        //obtenemos la informacion especifica de cada sitio y la asignamos a los elementos de la vista
        txt_nombre_Sitio_DETALLE_SITIO.setText(sitio.getNombre());
        txt_descripcion_Sitio_DETALLE_SITIO.setText(sitio.getDescripcion());
        txt_direccion_DETALLE_SITIO.setText(sitio.getDireccion());
        txt_telefono_DETALLE_SITIO.setText(sitio.getTelefono());

        //obtenemos el nombre de la foto del lugar que queremos consultar
        String nombrefoto=sitio.getNombre();
        //obtenemos el nombre del sonido del lugar que queremos consultar
        nombresonido=sitio.getNombresonido();

         /*invocamos el metdo consultarimagen de la clase firebasehelper para mandarle el img de nuestra vista
        y  el nombre de la foto que queremosmconsultar*/
        helper.consultarImagen(img_sitio_DETALLE_SITIO,nombrefoto);


        mp_sonido_Sitio_DETALLE_SITIO=new MediaPlayer();

    }



    public void consultarSonido()
    {

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://whitecity-16b15.appspot.com");

        storageRef.child("Sonidos").child(MainActivity.nodo).child(nombresonido).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                try {
                    mp_sonido_Sitio_DETALLE_SITIO.setDataSource(uri.toString());
                    mp_sonido_Sitio_DETALLE_SITIO.prepare();
                    mp_sonido_Sitio_DETALLE_SITIO.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            sb_sonido_Sitio_DETALLE_SITIO.setMax(mp_sonido_Sitio_DETALLE_SITIO.getDuration());
                            cambiarSeekBar();
                        }
                    });

                    sb_sonido_Sitio_DETALLE_SITIO.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            if (fromUser)
                            {
                                mp_sonido_Sitio_DETALLE_SITIO.seekTo(progress);
                            }

                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                        }
                    });


                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.e("Sonido ", "catedral url: " + uri.toString());
            }

        });
    }







    public static Detalle_Sitio newInstance(String param1, String param2) {
        Detalle_Sitio fragment = new Detalle_Sitio();
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


    private void cambiarSeekBar() {

        sb_sonido_Sitio_DETALLE_SITIO.setProgress(mp_sonido_Sitio_DETALLE_SITIO.getCurrentPosition());
        if (mp_sonido_Sitio_DETALLE_SITIO.isPlaying())
        {
            runnable=new Runnable() {
                @Override
                public void run() {
                    cambiarSeekBar();
                }
            };
            handler.postDelayed(runnable,1000);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_reproducir_sonido:
                if (mp_sonido_Sitio_DETALLE_SITIO.isPlaying())
                {
                    mp_sonido_Sitio_DETALLE_SITIO.pause();
                }else{

                    mp_sonido_Sitio_DETALLE_SITIO.start();
                    cambiarSeekBar();
                }
                break;
        }



    }

    //metodo para detener los elementos de la vista cuando se cierre la actividad
    @Override
    public void onDestroy() {
        super.onDestroy();


            mp_sonido_Sitio_DETALLE_SITIO.stop();




    }




}

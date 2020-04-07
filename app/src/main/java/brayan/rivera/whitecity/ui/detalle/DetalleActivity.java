package brayan.rivera.whitecity.ui.detalle;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import brayan.rivera.whitecity.R;
import brayan.rivera.whitecity.controlador.FireBaseHelper;
import brayan.rivera.whitecity.controlador.SessionHelper;
import brayan.rivera.whitecity.data.modelos.Sitio;
import brayan.rivera.whitecity.ui.login.LoginActivity;

public class DetalleActivity extends AppCompatActivity implements View.OnClickListener {

    private Sitio sitio;

    MediaPlayer mediaPlayer = new MediaPlayer();

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

    private void cargarInfo(final Sitio sitio) {

        TextView nombre = (TextView) findViewById(R.id.titulo_lugar_detalle);
        TextView verUbicacion = (TextView) findViewById(R.id.ubicion_detalle);
        TextView descripcion = (TextView) findViewById(R.id.descripcion_lugar_detalle);
        TextView direccion = (TextView) findViewById(R.id.direccion_lugar_detalle);
        TextView telefono = (TextView) findViewById(R.id.telefono_lugar_detalle);
        TextView facebook = (TextView) findViewById(R.id.facebook_lugar_detalle);
        final ImageView imagen = (ImageView) findViewById(R.id.imagen_lugar_detalle);
        ImageView favorito = (ImageView) findViewById(R.id.btn_add_favorito_detalle);
        ImageView playSonido = (ImageView) findViewById(R.id.btn_play_sonido);

        // cargar imagen guardada en firebase
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        storageRef.child("fotos/" + sitio.getImagenPath()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri)
                        .resize(600, 900)
                        .centerInside()
                        .into(imagen);
            }
        });

        // cargar informacion del sitio
        nombre.setText(sitio.getNombre());
        descripcion.setText(sitio.getDescripcion());
        direccion.setText("Direccion: " + sitio.getDireccion());
        telefono.setText("Telefono: " + sitio.getTelefono());
        facebook.setText(sitio.getFacebook());

        // agregar listener para el boton agregar a favoritos
        favorito.setOnClickListener(this);
        // agregar listener para el boton ver en mapa
        verUbicacion.setOnClickListener(this);
        // agregar listener para el boton facebook
        facebook.setOnClickListener(this);
        // agregar listener para el boton play sonido
        playSonido.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_add_favorito_detalle) {
            SessionHelper session = new SessionHelper(DetalleActivity.this);
            // validar si el usuario tiene id (ya se ha logueado)
            if (session.getUserId() != null) {
                // esto cuando esta logueado
                String key = new SessionHelper(DetalleActivity.this).getUserId();
                FireBaseHelper fireBaseHelper = new FireBaseHelper();
                fireBaseHelper.agregarFavorito(sitio, key);
                Toast.makeText(DetalleActivity.this, "Sitio agregado a favoritos", Toast.LENGTH_SHORT).show();

            } else {
                // navegar al registro
                Intent intent = new Intent(DetalleActivity.this, LoginActivity.class);
                DetalleActivity.this.startActivity(intent);
            }
        }

        if (v.getId() == R.id.ubicion_detalle) {
            String uri = "https://www.google.com/maps/search/?api=1&query=" + sitio.getLat() + "," + sitio.getLng();
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
        }

        if (v.getId() == R.id.facebook_lugar_detalle) {
            String uri = sitio.getFacebook();
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
        }

        if (v.getId() == R.id.btn_play_sonido) {
            final ProgressBar progressBar = findViewById(R.id.progress);
            progressBar.setVisibility(View.VISIBLE);
            // cargar sonido url desde firebase storage
            StorageReference storageRef = FirebaseStorage.getInstance().getReference();
            storageRef.child("sonidos/" + sitio.getNombreSonido()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    try {
                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        mediaPlayer.setDataSource(uri.toString());
                        mediaPlayer.prepare(); // might take long! (for buffering, etc)

                        progressBar.setVisibility(View.GONE);
                        mediaPlayer.start();
                    } catch (IOException e) {
                        progressBar.setVisibility(View.GONE);
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    // detener audio cuando la activitidad se destruye
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }
}

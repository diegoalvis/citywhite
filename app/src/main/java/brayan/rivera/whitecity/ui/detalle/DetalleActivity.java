package brayan.rivera.whitecity.ui.detalle;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import brayan.rivera.whitecity.R;
import brayan.rivera.whitecity.controlador.FireBaseHelper;
import brayan.rivera.whitecity.controlador.SessionHelper;
import brayan.rivera.whitecity.data.modelos.Sitio;
import brayan.rivera.whitecity.ui.login.LoginActivity;

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

    private void cargarInfo(final Sitio sitio) {

        TextView nombre = (TextView) findViewById(R.id.titulo_lugar_detalle);
        TextView descripcion = (TextView) findViewById(R.id.descripcion_lugar_detalle);
        TextView direccion = (TextView) findViewById(R.id.direccion_lugar_detalle);
        TextView telefono = (TextView) findViewById(R.id.telefono_lugar_detalle);
        TextView facebook = (TextView) findViewById(R.id.facebook_lugar_detalle);
        final ImageView imagen = (ImageView) findViewById(R.id.imagen_lugar_detalle);
        ImageView favorito = (ImageView) findViewById(R.id.btn_add_favorito_detalle);


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

        nombre.setText(sitio.getNombre());
        descripcion.setText(sitio.getDescripcion());
        direccion.setText("Direccion: "+ sitio.getDireccion());
        telefono.setText("Telefono: "+sitio.getTelefono());
        facebook.setText(sitio.getFacebook());



        favorito.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

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
                });


    }


}

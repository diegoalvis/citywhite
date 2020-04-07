package brayan.rivera.whitecity.ui.registrar_sitio;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

import brayan.rivera.whitecity.R;
import brayan.rivera.whitecity.controlador.FireBaseHelper;
import brayan.rivera.whitecity.data.modelos.Sitio;


public class RegistrarSitioActivity extends AppCompatActivity implements View.OnClickListener {

    Spinner sp_categorias_REGISTRO_ADMIN;
    EditText txt_nombre_Sitio_REGISTRO_ADMIN;
    EditText txt_descripcion_REGISTRO_ADMIN;
    EditText txt_direccion_REGISTRO_ADMIN;
    EditText txt_telefono_REGISTRO_ADMIN;
    EditText txt_facebook_REGISTRO_ADMIN;
    EditText txt_nombre_Sonido_ADMIN;
    Button btn_subir_Sitios_REGISTRO_ADMIN;
    ImageView ibtn_escoger_Sonido_ADMIN;
    ImageView img_imagen_Sitio_ADMIN;
    ProgressBar progressBar;


    static final int PICK_IMAGE_REQUEST = 71;

    String categoria;
    String nombre;
    String descripcion;
    String direccion;
    String telefono;
    String facebook;
    String nombreImagen;
    String nombreAudio;
    Uri uriImage;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_sitio);

        progressBar = findViewById(R.id.progress);
        txt_nombre_Sitio_REGISTRO_ADMIN = findViewById(R.id.txt_nombre_Sitio_REGISTRO_ADMIN);
        txt_descripcion_REGISTRO_ADMIN = findViewById(R.id.txt_descripcion_Sitio_REGISTRO_ADMIN);
        txt_direccion_REGISTRO_ADMIN = findViewById(R.id.txt_direccion_Sitio_REGISTRO_ADMIN);
        txt_telefono_REGISTRO_ADMIN = findViewById(R.id.txt_telefono_Sitio_REGISTRO_ADMIN);
        txt_facebook_REGISTRO_ADMIN = findViewById(R.id.txt_facebook_Sitio_REGISTRO_ADMIN);
        txt_nombre_Sonido_ADMIN = findViewById(R.id.edt_nombre_Sonido_ADMIN);
        sp_categorias_REGISTRO_ADMIN = findViewById(R.id.sp_categorias_Sitio_REGISTRO_ADMIN);
        ibtn_escoger_Sonido_ADMIN = findViewById(R.id.ibtn_escoger_Sonido_ADMIN);
        img_imagen_Sitio_ADMIN = findViewById(R.id.img_imagen_Sitio_ADMIN);
        btn_subir_Sitios_REGISTRO_ADMIN = findViewById(R.id.btn_subir_Sitio_REGISTRO_ADMIN);

        img_imagen_Sitio_ADMIN.setOnClickListener(this);
        ibtn_escoger_Sonido_ADMIN.setOnClickListener(this);
        btn_subir_Sitios_REGISTRO_ADMIN.setOnClickListener(this);

        cargarCategorias();
    }


    public void cargarCategorias() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categorias, android.R.layout.simple_spinner_item);
        sp_categorias_REGISTRO_ADMIN.setAdapter(adapter);
        sp_categorias_REGISTRO_ADMIN.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> spn, android.view.View v, int posicion, long id) {
                switch (posicion) {
                    case 1:
                        categoria = "iglesias";
                        break;
                    case 2:
                        categoria = "museos";
                        break;
                    case 3:
                        categoria = "comida_tradicional";
                        break;
                    case 4:
                        categoria = "hoteles";
                        break;
                }
            }

            public void onNothingSelected(AdapterView<?> spn) {
            }
        });
    }

    private void realizarRegistro() {
        nombre = txt_nombre_Sitio_REGISTRO_ADMIN.getText().toString();
        descripcion = txt_descripcion_REGISTRO_ADMIN.getText().toString();
        direccion = txt_direccion_REGISTRO_ADMIN.getText().toString();
        telefono = txt_telefono_REGISTRO_ADMIN.getText().toString();
        facebook = txt_facebook_REGISTRO_ADMIN.getText().toString();
        nombreImagen = nombre + "_imagen";
        nombreAudio = txt_nombre_Sonido_ADMIN.getText().toString();

        if (uriImage != null && categoria != null) {
            progressBar.setVisibility(View.VISIBLE);
            // subir imagen primero
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storagereference = storage.getReference("fotos/" + nombreImagen);
            storagereference.putFile(uriImage)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressBar.setVisibility(View.GONE);
                            Sitio sitio = new Sitio(nombre, descripcion, direccion, telefono, facebook, nombreImagen, nombreAudio, 0.0, 0.0);
                            FireBaseHelper helper = new FireBaseHelper();
                            helper.registrarSitio(sitio, categoria);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressBar.setVisibility(View.GONE);
                        }
                    })
            ;
        } else {
            Toast.makeText(this, "Falta informacion", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_subir_Sitio_REGISTRO_ADMIN:
                realizarRegistro();
                break;
            case R.id.img_imagen_Sitio_ADMIN:
                escogerImagen();
                break;
        }

    }

    private void escogerImagen() {
        //vamos a crear un intent para abrir el storage del celular  y traer un archivo tipo imagen
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Selecione una imagen"), PICK_IMAGE_REQUEST);
    }


    // fuente https://stackoverflow.com/a/58761883/7089060
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            uriImage = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriImage);
                img_imagen_Sitio_ADMIN.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

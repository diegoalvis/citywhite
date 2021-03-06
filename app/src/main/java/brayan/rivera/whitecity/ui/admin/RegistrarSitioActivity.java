package brayan.rivera.whitecity.ui.admin;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

import brayan.rivera.whitecity.R;
import brayan.rivera.whitecity.controlador.FireBaseHelper;
import brayan.rivera.whitecity.data.modelos.Sitio;


public class RegistrarSitioActivity extends AppCompatActivity implements View.OnClickListener {

    ProgressBar progressBar;
    Spinner sp_categorias_REGISTRO_ADMIN;
    EditText txt_nombre_Sitio_REGISTRO_ADMIN;
    EditText txt_descripcion_REGISTRO_ADMIN;
    EditText txt_direccion_REGISTRO_ADMIN;
    EditText txt_telefono_REGISTRO_ADMIN;
    EditText txt_facebook_REGISTRO_ADMIN;
    EditText txt_latitud_ADMIN;
    EditText txt_longitud_ADMIN;
    Button btn_subir_Sitios_REGISTRO_ADMIN;
    Button btn_eliminar_Sitios_REGISTRO_ADMIN;
    ImageView ibtn_escoger_Sonido_ADMIN;
    ImageView img_imagen_Sitio_ADMIN;


    static final int PICK_IMAGE_REQUEST = 71;
    static final int PICK_AUDIO_REQUEST = 72;

    String categoria;
    String nombre;
    String descripcion;
    String direccion;
    String telefono;
    String facebook;
    String nombreImagen;
    String nombreAudio;
    Double latitud, longitud;
    Uri uriImage;
    Uri uriAudio;

    Sitio sitio;

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
        sp_categorias_REGISTRO_ADMIN = findViewById(R.id.sp_categorias_Sitio_REGISTRO_ADMIN);
        ibtn_escoger_Sonido_ADMIN = findViewById(R.id.ibtn_escoger_Sonido_ADMIN);
        img_imagen_Sitio_ADMIN = findViewById(R.id.img_imagen_Sitio_ADMIN);
        btn_subir_Sitios_REGISTRO_ADMIN = findViewById(R.id.btn_subir_Sitio_REGISTRO_ADMIN);
        btn_eliminar_Sitios_REGISTRO_ADMIN = findViewById(R.id.btn_eliminar_sitio_REGISTRO_ADMIN);
        txt_latitud_ADMIN = findViewById(R.id.coord_latitud_REGISTRO_ADMIN);
        txt_longitud_ADMIN = findViewById(R.id.coord_longitud_REGISTRO_ADMIN);

        img_imagen_Sitio_ADMIN.setOnClickListener(this);
        ibtn_escoger_Sonido_ADMIN.setOnClickListener(this);
        btn_subir_Sitios_REGISTRO_ADMIN.setOnClickListener(this);
        btn_eliminar_Sitios_REGISTRO_ADMIN.setOnClickListener(this);

        cargarCategorias();

        // Obtener sitio. Si ha sido enviado por extras significa que esta en modo de edicion
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sitio = (Sitio) extras.getSerializable("sitio");
        }

        if (sitio != null) {
            btn_subir_Sitios_REGISTRO_ADMIN.setText("Actualizar sitio");
            btn_eliminar_Sitios_REGISTRO_ADMIN.setVisibility(View.VISIBLE);
            sp_categorias_REGISTRO_ADMIN.setVisibility(View.GONE);
            cargarDatosSitio(sitio);
        }
    }

    private void cargarDatosSitio(Sitio sitio) {
        txt_nombre_Sitio_REGISTRO_ADMIN.setText(sitio.getNombre());
        txt_descripcion_REGISTRO_ADMIN.setText(sitio.getDescripcion());
        txt_direccion_REGISTRO_ADMIN.setText(sitio.getDireccion());
        txt_telefono_REGISTRO_ADMIN.setText(sitio.getTelefono());
        txt_facebook_REGISTRO_ADMIN.setText(sitio.getFacebook());
        txt_latitud_ADMIN.setText(String.valueOf(sitio.getLat()));
        txt_longitud_ADMIN.setText(String.valueOf(sitio.getLng()));

        nombreImagen = sitio.getImagenPath();
        nombreAudio = sitio.getNombreSonido();

        categoria = sitio.getCategoria();

        // cargar imagen guardada en firebase
        try {
            final File localFile = File.createTempFile("images", "jpg");
            StorageReference storageRef = FirebaseStorage.getInstance().getReference();
            storageRef.child("fotos/" + sitio.getImagenPath()).getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    try {
                        uriImage = Uri.fromFile(localFile);
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriImage);
                        img_imagen_Sitio_ADMIN.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                    default:
                        categoria = null;
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

        latitud = Double.parseDouble(txt_latitud_ADMIN.getText().toString());
        longitud = Double.parseDouble(txt_latitud_ADMIN.getText().toString());


        if (uriImage != null && categoria != null && !nombre.isEmpty() && !descripcion.isEmpty()) {
            progressBar.setVisibility(View.VISIBLE);
            // subir imagen primero
            final FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storagereference = storage.getReference("fotos/" + nombreImagen);
            storagereference.putFile(uriImage)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            if (uriAudio != null) {
                                // subir audio
                                StorageReference storagereference = storage.getReference("sonidos/" + nombreAudio);
                                storagereference.putFile(uriAudio).addOnCompleteListener(
                                        new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                                progressBar.setVisibility(View.GONE);
                                                registrarSitio();
                                            }
                                        });
                            } else {
                                progressBar.setVisibility(View.GONE);
                                registrarSitio();
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressBar.setVisibility(View.GONE);
                        }
                    });
        } else {
            Toast.makeText(this, "Falta informacion", Toast.LENGTH_SHORT).show();
        }
    }

    private void registrarSitio() {
        // en caso de que se este editando se borra el registro anterior
        eliminarRegistro();

        Sitio sitio = new Sitio(nombre, descripcion, direccion, telefono, facebook, nombreImagen, nombreAudio, latitud, longitud);
        FireBaseHelper helper = new FireBaseHelper();
        helper.registrarSitio(sitio, categoria);
        finish();
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
            case R.id.ibtn_escoger_Sonido_ADMIN:
                escogerAudio();
                break;
            case R.id.btn_eliminar_sitio_REGISTRO_ADMIN:
                eliminarRegistro();
                finish();
                break;
        }
    }

    private void eliminarRegistro() {
        if(categoria != null && sitio != null) {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            database.getReference("sitios/" + categoria + "/" + sitio.getNombre()).getRef().removeValue();
        }
    }

    private void escogerImagen() {
        //vamos a crear un intent para abrir el storage del celular y traer un archivo tipo imagen
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Selecione una imagen"), PICK_IMAGE_REQUEST);
    }

    private void escogerAudio() {
        //vamos a crear un intent para abrir el storage del celular y traer un archivo de audio
        Intent intent_upload = new Intent();
        intent_upload.setType("audio/*");
        intent_upload.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent_upload, PICK_AUDIO_REQUEST);
    }

    // fuente https://stackoverflow.com/a/58761883/7089060
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && data != null) {
            if (requestCode == PICK_IMAGE_REQUEST) {
                uriImage = data.getData();
                nombreImagen = nombre + "_imagen";
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriImage);
                    img_imagen_Sitio_ADMIN.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (requestCode == PICK_AUDIO_REQUEST) {
                uriAudio = data.getData();
                nombreAudio = nombre + "_audio";
            }
        }
    }
}

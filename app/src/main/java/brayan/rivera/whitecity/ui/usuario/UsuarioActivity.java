package brayan.rivera.whitecity.ui.usuario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import brayan.rivera.whitecity.R;
import brayan.rivera.whitecity.controlador.SessionHelper;
import brayan.rivera.whitecity.data.modelos.Usuario;
import brayan.rivera.whitecity.ui.SplashActivity;

public class UsuarioActivity extends AppCompatActivity implements View.OnClickListener {

    private Usuario usuario;

    private ProgressBar progressBar;
    private EditText editNombre;
    private EditText editEmail;
    private EditText editPass;
    private EditText editPass2;
    private Button btnActualizar;
    private Button btnCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            usuario = (Usuario) extras.getSerializable("usuario");
            cargarInfo(usuario);
        }
    }

    private void cargarInfo(Usuario usuario) {

        progressBar = findViewById(R.id.progress);
        editNombre = findViewById(R.id.name_edit_usuario);
        editEmail = findViewById(R.id.email_edit_usuario);
        editPass = findViewById(R.id.pass_edit_usuario);
        editPass2 = findViewById(R.id.pass_2_edit_usuario);
        btnActualizar = findViewById(R.id.btn_actualizar_datos_usuario);
        btnCerrarSesion = findViewById(R.id.btn_cerrar_sesion_usuario);

        editNombre.setText(usuario.getNombre());
        editEmail.setText(usuario.getCorreo());
        btnActualizar.setOnClickListener(this);
        btnCerrarSesion.setOnClickListener(this);
    }

    private void validarCampos() {
        if (editNombre.getText().toString().isEmpty()) {
            Toast.makeText(this, "Nombre esta vacio", Toast.LENGTH_SHORT).show();
        } else if (editEmail.getText().toString().isEmpty()) {
            Toast.makeText(this, "Email esta vacio", Toast.LENGTH_SHORT).show();
        } else if (editPass.getText().toString().isEmpty()) {
            Toast.makeText(this, "Contraseña esta vacia", Toast.LENGTH_SHORT).show();
        } else if (editPass2.getText().toString().isEmpty()) {
            Toast.makeText(this, "Contraseña esta vacia", Toast.LENGTH_SHORT).show();
        } else if (!editPass.getText().toString().equals(editPass2.getText().toString())) {
            Toast.makeText(this, "Las Contraseñas No coinciden", Toast.LENGTH_SHORT).show();
        } else {
            Usuario usuario = new Usuario(editNombre.getText().toString(), editEmail.getText().toString(), editPass.getText().toString(), false);
            actualizarUsuario(usuario);
        }
    }


    private void actualizarUsuario(Usuario usuario) {
        progressBar.setVisibility(View.VISIBLE);

        SessionHelper session = new SessionHelper(this);
        String key = session.getUserId();

        boolean isAdmin = session.getIsAdmin();
        usuario.setAdmin(isAdmin);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database.child("usuarios/" + key + "/").setValue(usuario)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(UsuarioActivity.this, "No se pudo actualizar usuario.", Toast.LENGTH_LONG).show();
                    }
                });
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_actualizar_datos_usuario) {
            validarCampos();
        }
        if (v.getId() == R.id.btn_cerrar_sesion_usuario) {
            cerrarSession();
        }
    }

    private void cerrarSession() {
        SessionHelper sessionHelper = new SessionHelper(this);
        sessionHelper.cerrarSession();
        finishAffinity();
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);
    }
}

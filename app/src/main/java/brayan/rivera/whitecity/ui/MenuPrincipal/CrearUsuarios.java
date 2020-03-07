package brayan.rivera.whitecity.ui.MenuPrincipal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import brayan.rivera.whitecity.R;

public class CrearUsuarios extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText editTextNuevoCorreo; //crear nuevos usuarios
    private EditText editTextNuevoPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuarios);
        // Inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        editTextNuevoCorreo = findViewById(R.id.nuevoCorreo);
        editTextNuevoPassword = findViewById(R.id.nuevoPassword);
    }

    public void createUser(View view) {

        String email = editTextNuevoCorreo.getText().toString();
        String password = editTextNuevoPassword.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(CrearUsuarios.this, "Usuario Registrado!!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(CrearUsuarios.this, "Error al registrar nuevo usuario",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}

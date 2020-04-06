package brayan.rivera.whitecity.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import brayan.rivera.whitecity.R;

public class LoginAdmin extends AppCompatActivity {

    private EditText editTextCorreo;
    private EditText editTextPassword;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_admin);
        inicializarViews();
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    private void inicializarViews() {
        editTextCorreo = findViewById(R.id.correo);
        editTextPassword = findViewById(R.id.password);
    }

    public void loginUser(View view) {

        if (validar()) {
            //conectar con firebase

            String email = editTextCorreo.getText().toString(); //capturar texto gmail
            String password = editTextPassword.getText().toString(); //capturar texto de password

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("ANDROID", "signInWithEmail:éxito");
                                FirebaseUser user = mAuth.getCurrentUser();
                                //Intent intent = new Intent(LoginAdmin.this, AdminActivity.class);
                                //startActivity(intent);

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("ANDROID", "signInWithEmail:fallo", task.getException());
                                Toast.makeText(LoginAdmin.this, "Authentication fallida.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(this, "Corregir los errores", Toast.LENGTH_LONG).show();
        }
    }

    private boolean validar() {
        if (editTextCorreo.getText().toString().length() == 0) {
            editTextCorreo.setError("Debe ingresar un correo electroico");
            return false;
        }
        if (editTextPassword.getText().toString().length() == 0) {
            editTextPassword.setError("Debe ingresar un password para el usuario");
            return false;
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);

    }
}






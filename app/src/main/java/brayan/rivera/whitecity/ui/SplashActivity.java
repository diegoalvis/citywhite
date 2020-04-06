package brayan.rivera.whitecity.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import brayan.rivera.whitecity.R;
import brayan.rivera.whitecity.controlador.SessionHelper;
import brayan.rivera.whitecity.data.modelos.Usuario;
import brayan.rivera.whitecity.ui.home.MainActivity;
import brayan.rivera.whitecity.ui.login.LoginActivity;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    final private int REQUEST_CODE_PERMISSION = 111;

    private TextView invitadoButton;
    private Button loginRegistroButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        invitadoButton = findViewById(R.id.button_invitado);
        loginRegistroButton = findViewById(R.id.button_LoginRegistro);

        solicitarPermisos();
    }

    private void solicitarPermisos() {
        int permisoInternet = ActivityCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.INTERNET);
        int permisoLocalisacion = ActivityCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
        int permisoCoarseLocalisacion = ActivityCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION);

        if (permisoInternet != PackageManager.PERMISSION_GRANTED || permisoLocalisacion != PackageManager.PERMISSION_GRANTED || permisoCoarseLocalisacion != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.INTERNET, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE_PERMISSION);
        } else {
            validarUsuarioLogueado();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        validarUsuarioLogueado();
    }

    private void validarUsuarioLogueado() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final SessionHelper session = new SessionHelper(SplashActivity.this);
                // validar si el usuario tiene id (ya se ha logueado)
                if (session.getUserId() != null) {
                    // leer dato para saber si es admin o no
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("usuarios");
                    ref.child(session.getUserId()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Usuario usuario = dataSnapshot.getValue(Usuario.class);
                            // actualizar localmente si es admin o no
                            session.saveIsAdmin(usuario.isAdmin());

                            // navegar al main
                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            // navegar al main
                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                } else {
                    loginRegistroButton.setVisibility(View.VISIBLE);
                    loginRegistroButton.setOnClickListener(SplashActivity.this);

                    invitadoButton.setVisibility(View.VISIBLE);
                    invitadoButton.setOnClickListener(SplashActivity.this);
                }
            }
        }, 200);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_invitado) {
            // navegar al main
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        if (v.getId() == R.id.button_LoginRegistro) {
            // navegar al registro
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}

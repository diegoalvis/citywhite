package brayan.rivera.whitecity.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;

import brayan.rivera.whitecity.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private InicioSesionFragment inicioSesionFragment;
    private RegistroFragment registroFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inicioSesionFragment = new InicioSesionFragment(this);
        registroFragment = new RegistroFragment(this);

        cargarFragmentInicioSession();
    }

    private void cargarFragmentInicioSession() {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.container, inicioSesionFragment).commit();
    }

    private void cargarFragmentRegistro() {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.container, registroFragment).commit();
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_registrarme_iniciar_sesion) {
            cargarFragmentRegistro();
        }

        if (v.getId() == R.id.btn_tengo_cuenta) {
            cargarFragmentInicioSession();
        }
    }
}

package brayan.rivera.whitecity.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import brayan.rivera.whitecity.R;

public class InicioSesionFragment extends Fragment implements View.OnClickListener {

    private View.OnClickListener irARegistroListener;

    private TextView registro;
    private EditText editEmail;
    private Button buttonIniciar;

    public InicioSesionFragment(View.OnClickListener irARegistroListener) {
        this.irARegistroListener = irARegistroListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_inicio_sesion, container, false);

        registro = v.findViewById(R.id.btn_registrarme_iniciar_sesion);
        registro.setOnClickListener(irARegistroListener);

        editEmail = v.findViewById(R.id.input_email_iniciar_sesion);
        buttonIniciar = v.findViewById(R.id.btn_login_iniciar_sesion);
        buttonIniciar.setOnClickListener(this);

        return v;
    }


    private void validarCampos() {
        if (editEmail.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Email esta vacio", Toast.LENGTH_LONG).show();
        } else if (editEmail.getText().toString().isEmpty()) {

        } else {
            Toast.makeText(getContext(), "Correcto", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        validarCampos();
    }
}

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

public class RegistroFragment extends Fragment implements View.OnClickListener{

    private View.OnClickListener irALoginistener;

    private EditText editNombre;
    private EditText editEmail;
    private EditText editPass;
    private EditText editPass2;
    private Button buttonRegistrar;



    public RegistroFragment(View.OnClickListener irALoginistener) {
        this.irALoginistener = irALoginistener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_registro, container, false);

        TextView login = v.findViewById(R.id.btn_tengo_cuenta);
        login.setOnClickListener(irALoginistener);

        editNombre = v.findViewById(R.id.input_name_registro);
        editEmail = v.findViewById(R.id.input_email_registro);
        editPass = v.findViewById(R.id.input_pass_registro);
        editPass2 = v.findViewById(R.id.input_pass_2_registro);
        buttonRegistrar = v.findViewById(R.id.btn_registrar);
        buttonRegistrar.setOnClickListener(this);

        return v;
    }

    private void validarCampos() {
        if (editNombre.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Nombre esta vacio", Toast.LENGTH_LONG).show();
        } else if (editEmail.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Email esta vacio", Toast.LENGTH_LONG).show();
        } else if(editPass.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "Contraseña esta vacia", Toast.LENGTH_LONG).show();
        } else if (editPass2.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "Contraseña esta vacia", Toast.LENGTH_LONG).show();
        } else if (!editPass.getText().toString().equals( editPass2.getText().toString()) ){
            Toast.makeText(getContext(), "Las Contraseñas No coinciden", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), "Registrando Usuario...", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        validarCampos();
    }
}

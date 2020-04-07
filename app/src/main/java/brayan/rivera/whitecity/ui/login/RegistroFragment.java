package brayan.rivera.whitecity.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import brayan.rivera.whitecity.R;
import brayan.rivera.whitecity.controlador.SessionHelper;
import brayan.rivera.whitecity.data.modelos.Usuario;
import brayan.rivera.whitecity.ui.home.MainActivity;

public class RegistroFragment extends Fragment implements View.OnClickListener {

    private View.OnClickListener irALoginistener;

    private EditText editNombre;
    private EditText editEmail;
    private EditText editPass;
    private EditText editPass2;
    private Button buttonRegistrar;
    private ProgressBar progressBar;


    public RegistroFragment(View.OnClickListener irALoginistener) {
        this.irALoginistener = irALoginistener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_registro, container, false);

        TextView login = v.findViewById(R.id.btn_tengo_cuenta);
        login.setOnClickListener(irALoginistener);

        progressBar = v.findViewById(R.id.progress);
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
            Toast.makeText(getContext(), "Nombre esta vacio", Toast.LENGTH_SHORT).show();
        } else if (editEmail.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Email esta vacio", Toast.LENGTH_SHORT).show();
        } else if (editPass.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Contraseña esta vacia", Toast.LENGTH_SHORT).show();
        } else if (editPass2.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Contraseña esta vacia", Toast.LENGTH_SHORT).show();
        } else if (!editPass.getText().toString().equals(editPass2.getText().toString())) {
            Toast.makeText(getContext(), "Las Contraseñas No coinciden", Toast.LENGTH_SHORT).show();
        } else {
            Usuario usuario = new Usuario(editNombre.getText().toString(), editEmail.getText().toString(), editPass.getText().toString(), false);
            registrarNuevoUsuario(usuario);
        }
    }


    private void registrarNuevoUsuario(Usuario usuario) {
        progressBar.setVisibility(View.VISIBLE);
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        final String key = database.child("usuarios").push().getKey();
        database.child("usuarios/" + key + "/").setValue(usuario)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        progressBar.setVisibility(View.GONE);
                        SessionHelper session = new SessionHelper(getContext());
                        session.saveUserId(key);
                        session.saveIsAdmin(false);
                        // navegar al main
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getContext(), "No se pudo crear usuario.", Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public void onClick(View v) {
        validarCampos();
    }
}

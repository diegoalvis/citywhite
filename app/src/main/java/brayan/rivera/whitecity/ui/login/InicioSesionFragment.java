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

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import brayan.rivera.whitecity.R;
import brayan.rivera.whitecity.controlador.SessionHelper;
import brayan.rivera.whitecity.data.modelos.Usuario;
import brayan.rivera.whitecity.ui.home.MainActivity;

public class InicioSesionFragment extends Fragment implements View.OnClickListener {

    private View.OnClickListener irARegistroListener;

    private TextView registro;
    private EditText editEmail;
    private EditText editPass;
    private Button buttonIniciar;
    private ProgressBar progressBar;

    public InicioSesionFragment(View.OnClickListener irARegistroListener) {
        this.irARegistroListener = irARegistroListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_inicio_sesion, container, false);

        registro = v.findViewById(R.id.btn_registrarme_iniciar_sesion);
        registro.setOnClickListener(irARegistroListener);

        progressBar = v.findViewById(R.id.progress);
        editEmail = v.findViewById(R.id.input_email_iniciar_sesion);
        buttonIniciar = v.findViewById(R.id.btn_login_iniciar_sesion);
        editPass = v.findViewById(R.id.input_pass_iniciar_sesion);
        buttonIniciar.setOnClickListener(this);

        return v;
    }


    private void validarCampos() {
        if (editEmail.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Email esta vacio", Toast.LENGTH_SHORT).show();
        } else if (editPass.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Contraseña esta vacio", Toast.LENGTH_SHORT).show();
        } else {
            login(editEmail.getText().toString(), editPass.getText().toString());
        }
    }

    private void login(String email, final String pass) {
        progressBar.setVisibility(View.VISIBLE);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query = reference.child("usuarios").orderByChild("correo").equalTo(email);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressBar.setVisibility(View.GONE);
                if (dataSnapshot.exists()) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Usuario usuario = data.getValue(Usuario.class);
                        if (usuario.getPassword().equals(pass)) {
                            SessionHelper session = new SessionHelper(getContext());
                            session.saveUserId(data.getKey());
                            session.saveIsAdmin(usuario.isAdmin());
                            // navegar al main
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                            return;
                        }
                    }
                }
                Toast.makeText(getContext(), "Datos incorrectos.", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Ocurrio un error al iniciar session.", Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onClick(View v) {
        validarCampos();
    }
}

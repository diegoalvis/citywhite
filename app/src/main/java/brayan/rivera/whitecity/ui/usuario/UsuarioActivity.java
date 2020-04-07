package brayan.rivera.whitecity.ui.usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import brayan.rivera.whitecity.R;
import brayan.rivera.whitecity.data.modelos.Sitio;
import brayan.rivera.whitecity.data.modelos.Usuario;

public class UsuarioActivity extends AppCompatActivity {

    private Usuario usuario;

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

        TextView nombre = (TextView) findViewById(R.id.name_edit_usuario);
        TextView email = (TextView) findViewById(R.id.email_edit_usuario);



        nombre.setText(usuario.getNombre());
        email.setText(usuario.getCorreo());

    }
}

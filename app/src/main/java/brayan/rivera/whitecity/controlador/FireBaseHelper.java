package brayan.rivera.whitecity.controlador;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import brayan.rivera.whitecity.data.modelos.Sitio;


public class FireBaseHelper {

    //crear la intacia a la vase de tados dentro de firebase
    private FirebaseDatabase database;

    public FireBaseHelper() {
        this.database = FirebaseDatabase.getInstance();
    }

    public void agregarFavorito(Sitio sitio, String key) {
        DatabaseReference ref = database.getReference().child("usuarios/" + key + "/favoritos/" + sitio.getNombre());
        ref.setValue(sitio);
    }

    public void registrarSitio(Sitio sitio, String categoria) {
        DatabaseReference ref = database.getReference("sitios/" + categoria + "/" + sitio.getNombre());
        ref.setValue(sitio);
    }

}

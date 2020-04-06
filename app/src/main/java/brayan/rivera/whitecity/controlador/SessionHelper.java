package brayan.rivera.whitecity.controlador;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;
import brayan.rivera.whitecity.data.modelos.Usuario;


public class SessionHelper {

    private Context context;
    private String preferencias = "preferencias";

    public SessionHelper(Context context) {
        this.context = context;
    }

    public void saveUserId(String id) {
        SharedPreferences preferences = context.getSharedPreferences(preferencias, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("id", id);
        editor.apply();
    }

    public String getUserId() {
        SharedPreferences preferences = context.getSharedPreferences(preferencias, Context.MODE_PRIVATE);
        return preferences.getString("id", null);
    }

    public void saveIsAdmin(boolean isAdmin) {
        SharedPreferences preferences = context.getSharedPreferences(preferencias, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("admin", isAdmin);
        editor.apply();
    }

    public boolean getIsAdmin() {
        SharedPreferences preferences = context.getSharedPreferences(preferencias, Context.MODE_PRIVATE);
        return preferences.getBoolean("admin", false);
    }


    public void registrarNuevoUsuario(Usuario usuario) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        String key = database.child("usuarios").push().getKey();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/usuarios/" + key, usuario);
        database.updateChildren(childUpdates)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Write was successful!
                        // ...
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Write failed
                        // ...
                    }
                });
    }

}

package brayan.rivera.whitecity.controlador;

import android.content.Context;
import android.content.SharedPreferences;


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

    public void cerrarSession() {
        SharedPreferences preferences = context.getSharedPreferences(preferencias, Context.MODE_PRIVATE);
        preferences.edit().clear().apply();
    }
}

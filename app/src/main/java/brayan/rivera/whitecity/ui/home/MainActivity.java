package brayan.rivera.whitecity.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import brayan.rivera.whitecity.AdministradorActivity;
import brayan.rivera.whitecity.R;
import brayan.rivera.whitecity.data.modelos.Sitio;


public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navHostFragment.getNavController());
    }


    //meotodo por defecto para poner los icono en el action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);

        MenuItem item = menu.findItem(R.id.item_buscador);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);

        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                return true;
            }
        });

        //creamos un item del menu para buscar
        return true;
    }


    //metodo por defecto para detectar que icono se presiona en el action bar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //implemento un switch para realizar las acciones dependiendo del boton presionado
        switch (item.getItemId()) {

            case R.id.item_menu_principal:
                break;

            case R.id.item_registrar_sitios:
                Intent intent = new Intent(MainActivity.this, AdministradorActivity.class);
                startActivity(intent);
                break;


            default:
                return super.onOptionsItemSelected(item);

        }


        return true;

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        try {

//            FireBaseHelper.adaptador_sitios.setFilter(filter(FireBaseHelper.todosLosSitios,newText));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public ArrayList<Sitio> filter(ArrayList<Sitio> sitios, String texto) {
        ArrayList<Sitio> listafiltrada = new ArrayList<>();
        try {
            texto = texto.toLowerCase();
            for (Sitio sitio : sitios) {
                String nombre = sitio.getNombre().toLowerCase();
                if (nombre.contains(texto)) {
                    listafiltrada.add(sitio);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listafiltrada;
    }
}

package brayan.rivera.whitecity.modelo;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import brayan.rivera.whitecity.R;

import brayan.rivera.whitecity.controlador.FireBaseHelper;



public class MainActivity extends AppCompatActivity {
    public static String nodo="";
    public static ArrayList<String>listafotos;
   FireBaseHelper helper;


   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_iglesias, R.id.navigation_comida_tradicional, R.id.navigation_museos,R.id.navigation_hoteles,R.id.navigation_sitios_interes)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }

    public  void cambiarFragmento()
    {}


    //meotodo por defecto para poner los icono en el action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        //creamos un item del menu para buscar
        MenuItem item=menu.findItem(R.id.buscador_palabras);
        //casteamos el item a una variable tipo search viw para ejecutar la busqueda
        SearchView searchView= (SearchView) MenuItemCompat.getActionView(item);
        return true;
    }

    //metodo por defecto para detectar que icono se presiona en el action bar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        //implemento un switch para realizar las acciones dependiendo del boton presionado
        switch (item.getItemId())
        {

            case R.id.item_menu_principal:

            case R.id.item_registrar_sitios:


                return  true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }
}

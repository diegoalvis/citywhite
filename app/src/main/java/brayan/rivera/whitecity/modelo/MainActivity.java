package brayan.rivera.whitecity.modelo;

import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;


import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import brayan.rivera.whitecity.Administrador;
import brayan.rivera.whitecity.R;

import brayan.rivera.whitecity.controlador.Adaptador_Sitios;
import brayan.rivera.whitecity.controlador.FireBaseHelper;
import brayan.rivera.whitecity.controlador.Sitio;


public class MainActivity extends AppCompatActivity implements  SearchView.OnQueryTextListener {
    public static String nodo="";
    public static ArrayList<String>listafotos;


   FireBaseHelper helper;


   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper=new FireBaseHelper(this);

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







    //meotodo por defecto para poner los icono en el action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);

        MenuItem item= menu.findItem(R.id.item_buscador);
        SearchView searchView= (SearchView) MenuItemCompat.getActionView(item);

        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                FireBaseHelper.adaptador_sitios.setFilter(FireBaseHelper.sitios);
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
        switch (item.getItemId())
        {

            case R.id.item_menu_principal:
                break;

            case R.id.item_registrar_sitios:
                Intent intent= new Intent(MainActivity.this, Administrador.class);
                startActivity(intent);
            break;


            default:
                return super.onOptionsItemSelected(item);

        }


        return  true;

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
       try{

            FireBaseHelper.adaptador_sitios.setFilter(filter(FireBaseHelper.todosLosSitios,newText));

       }
       catch (Exception e)
       {
            e.printStackTrace();
       }
        return false;
    }


    public  ArrayList<Sitio>filter(ArrayList<Sitio>sitios,String texto)
    {

        ArrayList<Sitio>listafiltrada=new ArrayList<>();
        try{
             texto=texto.toLowerCase();
             for (Sitio sitio:sitios)
             {
                 String nombre=sitio.getNombre().toLowerCase();
                 if (nombre.contains(texto) )
                 {
                    listafiltrada.add(sitio);
                 }
             }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
     return listafiltrada;
    }
}

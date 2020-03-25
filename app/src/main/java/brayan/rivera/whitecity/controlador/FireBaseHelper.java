package brayan.rivera.whitecity.controlador;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import brayan.rivera.whitecity.Registrar_Sitio;
import brayan.rivera.whitecity.modelo.CrearUsuarios;
import brayan.rivera.whitecity.modelo.Registro_Sitio;
import brayan.rivera.whitecity.modelo.MainActivity;
import brayan.rivera.whitecity.modelo.comida_tradicional.ComidaTradicional;
import brayan.rivera.whitecity.modelo.hoteles.Hoteles;
import brayan.rivera.whitecity.modelo.iglesias.Iglesias;
import brayan.rivera.whitecity.modelo.museos.Museos;
import brayan.rivera.whitecity.modelo.sitios_interes.SitiosInteres;


public class FireBaseHelper {
    //crear la intacia a la vase de tados dentro de firebase
    private FirebaseDatabase database;
    //crear una referencia para navegar dentro el arbl de la base de datos en firebase
    private DatabaseReference myref;
    //crear el login para vlidar dats dentro de firebase
    private FirebaseAuth login;
    private Context context;

    public static  int posicion2;
    public static  Adaptador_Sitios adaptador_sitios;

    public  static ArrayList<Sitio> sitios;



    public static ArrayList<Sitio> todosLosSitios;



    public FireBaseHelper( Context context) {

        this.context = context;
    }


    public void registrarSitio(String nomb,String desc,String dir,String tel,String face)
    {
        FirebaseDatabase bd=FirebaseDatabase.getInstance();
        DatabaseReference indice=bd.getReference("Sitios");
        Sitio datos=new Sitio();

        datos.setNombre(nomb);
        datos.setDescripcion(desc);
        datos.setDireccion(dir);
        datos.setTelefono(tel);
        datos.setFacebook(face);

        indice.child(Registrar_Sitio.categoria).child(nomb).setValue(datos);

        Toast.makeText(context, "Sitio subido exitosamente...", Toast.LENGTH_LONG).show();
    }


    public void listarsitios (final RecyclerView rv_lista_sitios){

        sitios =new ArrayList<>();
        database=FirebaseDatabase.getInstance();

        myref=database.getReference("Sitios").child(MainActivity.nodo);

        myref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                sitios.removeAll(sitios);
                for (DataSnapshot dato: dataSnapshot.getChildren()) {

                    Sitio sitio=dato.getValue(Sitio.class);
                    sitios.add(sitio);
                }

                adaptador_sitios.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        adaptador_sitios=new Adaptador_Sitios(sitios);
        rv_lista_sitios.setAdapter(adaptador_sitios);


    }



    public void consultarImagen(final ImageView img_sitio, String nombreforo)
    {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://whitecity-16b15.appspot.com");

        storageRef.child("fotos").child(MainActivity.nodo).child(nombreforo).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                Glide.with(context).load(uri).into(img_sitio);
                Log.e("Imagen", "Catedral url: " + uri.toString());
                //Handle whatever you're going to do with the URL here
            }
        });
    }

    public void consultarTodosLosSitios( final RecyclerView rv_lista_sitios)
    {
        database=FirebaseDatabase.getInstance();
        todosLosSitios=new ArrayList<>();

       ArrayList<String>categoria=new ArrayList<>();
       categoria.add("Iglesias");
       categoria.add("Sitios de Interes");
       categoria.add("Museos");
       categoria.add("Comida Tipica");
       categoria.add("Hoteles");

        DatabaseReference myref1=database.getReference("Sitios");

        if (todosLosSitios.size()>2)
        {
        todosLosSitios.removeAll(todosLosSitios);
        }

       for (int i=0;i<=categoria.size()-1;i++)
       {

           myref1.child(categoria.get(i)).addValueEventListener(new ValueEventListener() {

               @Override
               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                   for (DataSnapshot dato: dataSnapshot.getChildren()) {

                       Sitio sitio1=dato.getValue(Sitio.class);
                       todosLosSitios.add(sitio1);
                   }

;                       adaptador_sitios.notifyDataSetChanged();

               }

               @Override
               public void onCancelled(@NonNull DatabaseError databaseError) {

               }
           });


       }

        adaptador_sitios=new Adaptador_Sitios(todosLosSitios);
        rv_lista_sitios.setAdapter(adaptador_sitios);

    }

}

package brayan.rivera.whitecity.controlador;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;


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


import java.io.IOException;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import brayan.rivera.whitecity.modelo.MainActivity;


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

    public FireBaseHelper( Context context) {

        this.context = context;
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

}

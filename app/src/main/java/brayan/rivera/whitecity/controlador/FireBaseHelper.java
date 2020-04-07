package brayan.rivera.whitecity.controlador;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import brayan.rivera.whitecity.data.modelos.Sitio;


public class FireBaseHelper {

    //crear la intacia a la vase de tados dentro de firebase
    private FirebaseDatabase database;

    public FireBaseHelper() {
        this.database = FirebaseDatabase.getInstance();
    }

    void agregarFavorito(Sitio sitio, String key) {
        DatabaseReference ref = database.getReference().child("usuarios/" + key + "/favoritos/" + sitio.getNombre());
        ref.setValue(sitio);
    }

    public void registrarSitio(Sitio sitio, String categoria) {
        DatabaseReference ref = database.getReference("sitios/" + categoria + "/" + sitio.getNombre());
        ref.setValue(sitio);
    }


    public void subirImagen(String img) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storagereference = storage.getReference("");


//        if (AdministradorActivity.url_Imagen_ADMIN != null) {
//
//            final ProgressDialog progressdialog = new ProgressDialog(context);
//            progressdialog.setTitle("subiendo...");
//            progressdialog.show();
//
//            StorageReference ref = storagereference2.child("fotos/").child(img);
//            ref.putFile(AdministradorActivity.url_Imagen_ADMIN)
//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                            progressdialog.dismiss();
//
//                            Toast.makeText(context, "Imagen subida", Toast.LENGTH_SHORT).show();
//
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//
//                            progressdialog.dismiss();
//                            Toast.makeText(context, "Fallo " + e.getMessage(), Toast.LENGTH_SHORT).show();
//
//                        }
//                    })
//                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
//
//                            double progreso = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
//
//                            progressdialog.setMessage("Subiendo... " + (int) progreso + " %");
//
//                        }
//                    });
//
//
//        }
//

    }


//
//    public void consultarImagen(final ImageView img_sitio, String nombreforo) {
//        FirebaseStorage storage = FirebaseStorage.getInstance();
//        StorageReference storageRef = storage.getReferenceFromUrl("gs://whitecity-16b15.appspot.com");
//
//        storageRef.child("fotos").child(MainActivity.nodo).child(nombreforo).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//
//                Glide.with(context).load(uri).into(img_sitio);
//                Log.e("Imagen", "Catedral url: " + uri.toString());
//                //Handle whatever you're going to do with the URL here
//            }
//        });
//    }
//
//    public void consultarTodosLosSitios(final RecyclerView rv_lista_sitios) {
//        database = FirebaseDatabase.getInstance();
//        todosLosSitios = new ArrayList<>();
//
//        ArrayList<String> categoria = new ArrayList<>();
//        categoria.add("Iglesias");
//        categoria.add("Sitios de Interes");
//        categoria.add("Museos");
//        categoria.add("Comida Tipica");
//        categoria.add("Hoteles");
//
//        DatabaseReference myref1 = database.getReference("Sitios");
//
//        if (todosLosSitios.size() > 2) {
//            todosLosSitios.removeAll(todosLosSitios);
//        }
//
//        for (int i = 0; i <= categoria.size() - 1; i++) {
//
//            myref1.child(categoria.get(i)).addValueEventListener(new ValueEventListener() {
//
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                    for (DataSnapshot dato : dataSnapshot.getChildren()) {
//
//                        Sitio sitio1 = dato.getValue(Sitio.class);
//                        todosLosSitios.add(sitio1);
//                    }
//
//                    ;
//                    adaptador_sitios.notifyDataSetChanged();
//
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                }
//            });
//
//
//        }
//
//        adaptador_sitios = new AdaptadorSitios(todosLosSitios);
//        rv_lista_sitios.setAdapter(adaptador_sitios);
//
//    }

}

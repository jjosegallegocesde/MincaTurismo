package com.example.mincaturismo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText cajanombre,cajadescripcion,cajafoto;
    Button  botonregistrar,botonlistar;

    FirebaseFirestore baseDatos=FirebaseFirestore.getInstance();
    Map<String,Object> PaqueteMinca = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cajanombre=findViewById(R.id.nombreMain);
        cajadescripcion=findViewById(R.id.descripcionMain);
        cajafoto=findViewById(R.id.urlfot);

        botonregistrar=findViewById(R.id.botonregistrar);
        botonlistar=findViewById(R.id.botonlistar);

        botonregistrar.setOnClickListener(v -> {
            String nombreMain=cajanombre.getText().toString();
            String descripcionMain=cajadescripcion.getText().toString();
            String urlfot=cajafoto.getText().toString();

            PaqueteMinca.put("nombreMain",nombreMain);
            PaqueteMinca.put("descripcionMain",descripcionMain);
            PaqueteMinca.put("urlfot",urlfot);

            registrarSitio();
        });

        botonlistar.setOnClickListener(v -> {
            Intent intent =new Intent(MainActivity.this,Home.class);
            startActivity(intent);
        });
    }

    private void registrarSitio(){
        baseDatos.collection("PaqueteMinca")
                .add(PaqueteMinca)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        cajanombre.setText("");
                        cajadescripcion.setText("");
                        cajafoto.setText("");
                        Toast.makeText(getApplicationContext(),"exito agregando sitio",Toast.LENGTH_LONG).show();
                    }
                })
                  .addOnFailureListener(new OnFailureListener() {
                      @Override
                      public void onFailure(@NonNull Exception e) {
                          Toast.makeText(getApplicationContext(),"error agregando"+e,Toast.LENGTH_LONG).show();
                      }

                  });

    }
}

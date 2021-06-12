package com.example.mincaturismo;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class Home extends AppCompatActivity {

    ArrayList<PaqueteMinca> listaDeDatos = new ArrayList<>();
    RecyclerView listado;
    FirebaseFirestore baseDatos = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listado = findViewById(R.id.listado);

        listado.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        crearListado();

        Adaptador adaptador = new Adaptador(listaDeDatos);
        listado.setAdapter(adaptador);
    }

    private void crearListado() {

        baseDatos.collection("PaqueteMinca")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        int [] textos={R.string.texto1,R.string.texto2};
                        int contador=0;

                        for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                            String nombreTuristico = Objects.requireNonNull(document.get("nombre")).toString();
                            String fotoSitio = Objects.requireNonNull(document.get("fotoSitio")).toString();

                            listaDeDatos.add(new PaqueteMinca(nombreTuristico,fotoSitio,getString(textos[contador])));
                            contador++;
                        }
                        Adaptador adaptador = new Adaptador(listaDeDatos);
                        listado.setAdapter(adaptador);
                    } else {
                        Toast.makeText(getApplicationContext(), "error consultando", Toast.LENGTH_LONG).show();
                    }
                });
    }


    public void cambiaIdioma(String lenguaje) {
        Locale idioma = new Locale(lenguaje);
        Locale.setDefault(idioma);

        Configuration configurationtelefono = getResources().getConfiguration();
        configurationtelefono.locale = idioma;
        getBaseContext().getResources().updateConfiguration(configurationtelefono, getBaseContext().getResources().getDisplayMetrics());

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem opcion){
        int id=opcion.getItemId();

        switch (id) {

            case (R.id.opcion1):
                Intent intent;
                intent = new Intent(Home.this, Perfil.class);
                startActivity(intent);
                break;
            case (R.id.opcion2):
                this.cambiaIdioma("es");
                Intent intent2;
                intent2 = new Intent(Home.this, Home.class);
                startActivity(intent2);
                break;
            case (R.id.opcion3):
                this.cambiaIdioma("en");
                Intent intent3 = new Intent(Home.this, Home.class);
                startActivity(intent3);
                break;
        }
        return super.onOptionsItemSelected(opcion);


    }
}
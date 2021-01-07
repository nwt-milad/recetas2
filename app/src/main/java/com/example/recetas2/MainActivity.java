package com.example.recetas2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

        private ListView listado;
        private AyudanteDB ayudanteDB;
        private TextView txtAviso;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            ayudanteDB = new AyudanteDB(getApplicationContext());
            listado = findViewById(R.id.listado);
            txtAviso = findViewById(R.id.txt_aviso);
            Button btn = (Button)findViewById(R.id.btn_nueva_receta);
            btn.setOnClickListener(this);

            mostrarRecetas();
        }

        public void mostrarRecetas() {
            String message = "No hay Recetas guardadas";
            ArrayList listadoTareas = ayudanteDB.devolverRecetas();
            AdapterList adapter = new AdapterList(this, ayudanteDB.devolverRecetas());
            listado.setAdapter(adapter);
            txtAviso.setText((listadoTareas != null && listadoTareas.size() > 0) ? "" : "No hay Recetas guardadas");

        }

        public void actualizarReceta(String[] parametros) {
            ayudanteDB.editarReceta(parametros);
            mostrarRecetas();
            Toast.makeText(getApplicationContext(),"Receta Actualizada",Toast.LENGTH_SHORT).show();
        }

        public void eliminarReceta(Receta receta) {
            ayudanteDB.borrarReceta(receta.getId());
            mostrarRecetas();
            Toast.makeText(getApplicationContext(),"Receta eliminada",Toast.LENGTH_SHORT).show();
        }


        @Override
        public void onClick (View v){
            Intent miintent = new Intent(MainActivity.this, EditarReceta.class);
            startActivity(miintent);
        }

}
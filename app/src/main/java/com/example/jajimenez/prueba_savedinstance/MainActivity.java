package com.example.jajimenez.prueba_savedinstance;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Spinner.OnItemSelectedListener{
    ImageView imagen;
    Button boton;
    Button botonCamara;
    Spinner lista;
    ArrayList<String> opciones = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        opciones.add("Restaurants");
        opciones.add("Hotel");
        opciones.add("Museum");
        opciones.add("Sports");

        imagen = (ImageView) findViewById(R.id.imagenPrueba);
        if(savedInstanceState != null){
            Integer recurso = savedInstanceState.getInt("variable_imagen",0);
            if(recurso != 0) {
                imagen.setImageDrawable(getResources().getDrawable(recurso));
            }

        }
        boton = (Button) findViewById(R.id.botonPrueba);
        boton.setOnClickListener(this);

        botonCamara = (Button) findViewById(R.id.botonCamara);
        botonCamara.setOnClickListener(this);
        lista = (Spinner) findViewById(R.id.spinner);
        lista.setOnItemSelectedListener(this);
        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, opciones);
        lista.setAdapter(spinnerArrayAdapter);

    }


    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.botonPrueba:
                onClickBoton1();
                break;
            case R.id.botonCamara:
                onClickBoton3();
                break;
        }
    }
    public void onClickBoton1(){
        imagen.setImageDrawable(getResources().getDrawable(R.drawable.thunder));
    }

    //método que abre google maps
    public void onClickBoton2() {

        Uri gmmIntentUri = Uri.parse("geo:37.473708, -5.651173");

// Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
// Make the Intent explicit by setting the Google Maps package
        //en español, esta línea obliga a abrir con google maps

        //mapIntent.setPackage("com.google.android.apps.maps");

// Attempt to start an activity that can handle the Intent
        startActivity(mapIntent);
    }
    public void onClickBoton3(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(takePictureIntent);
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        if(imagen.getDrawable()!=null) {
            int fondo = R.drawable.thunder;
            savedInstanceState.putInt("variable_imagen", fondo);
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String opcion = opciones.get(position);

        Uri gmmIntentUri = Uri.parse("geo:37.473708, -5.651173?q="+opcion);

// Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
// Make the Intent explicit by setting the Google Maps package
        //en español, esta línea obliga a abrir con google maps

        //mapIntent.setPackage("com.google.android.apps.maps");

// Attempt to start an activity that can handle the Intent
        startActivity(mapIntent);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
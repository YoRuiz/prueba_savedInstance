package com.example.jajimenez.prueba_savedinstance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView imagen;
    Button boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imagen = (ImageView) findViewById(R.id.imagenPrueba);
        if(savedInstanceState != null){
            Integer recurso = savedInstanceState.getInt("variable_imagen",0);
            if(recurso != 0) {
                imagen.setImageDrawable(getResources().getDrawable(recurso));
            }

        }
        boton = (Button) findViewById(R.id.botonPrueba);
        boton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        imagen.setImageDrawable(getResources().getDrawable(R.drawable.thunder));
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        if(imagen.getDrawable()!=null) {
            int fondo = R.drawable.thunder;
            savedInstanceState.putInt("variable_imagen", fondo);
        }

    }
}
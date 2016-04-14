package com.abhirupleo.android.pokemongame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class battleselection extends AppCompatActivity {

    Button button_pikachu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battleselection);
        addListenerOnButton();
    }
    public void addListenerOnButton() {

        final Context context = this;

        button_pikachu = (Button) findViewById(R.id.pikachu_vs_charmander);

        button_pikachu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, battle1.class);
                startActivity(intent);

            }

        });

    }

}

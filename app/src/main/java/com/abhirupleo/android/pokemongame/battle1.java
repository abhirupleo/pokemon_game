package com.abhirupleo.android.pokemongame;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Random;

public class battle1 extends AppCompatActivity {
    private static TextView hp_pikachu;
    private static TextView hp_charmander;
    private static Button refresh;
    private static RadioGroup radio_g;
    private static RadioButton radio_button;
    private static Button button;
    private static TextView attack;
    private int pikachu_health;
    private int charmander_heatlh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pikachu_health = charmander_heatlh=100;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pickachu);
        attack();
        refresh();
    }
    private void attack(){
        hp_pikachu = (TextView) findViewById(R.id.label_pikachu);
        hp_charmander = (TextView) findViewById(R.id.label_charmander);
        radio_g = (RadioGroup) findViewById(R.id.radio_group);
        radio_button = (RadioButton) findViewById(R.id.radioButton);
        attack = (TextView) findViewById(R.id.attacklog);
        button = (Button) findViewById(R.id.attack_button);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String[] charmander_moves = {
                          "Ember","Scratch","Fireblast","Bite"
                        };
                        int button_selected = radio_g.getCheckedRadioButtonId();
                        radio_button = (RadioButton) findViewById(button_selected);
                        int idx = radio_g.indexOfChild(radio_button);
                        int hplost;
                        int hpwon;
                        Random random_number = new Random();
                        int charmander_index = random_number.nextInt(charmander_moves.length);
                        switch (charmander_index){
                            case 0 : hplost = 15;break;
                            case 1 : hplost = 10;break;
                            case 2 : hplost = 30;break;
                            case 3 : hplost = 15;break;
                            default:hplost = 0;
                        }
                        switch (idx){
                            case 0 : hpwon = 25;break;
                            case 1 : hpwon = 30;break;
                            case 2 : hpwon = 10;break;
                            case 3 : hpwon = 15;break;
                            default:hpwon = 0;
                        }
                        charmander_heatlh = charmander_heatlh - hpwon;
                        pikachu_health = pikachu_health - hplost;
                        String print_charmander=" ";
                        print_charmander = charmander_moves[charmander_index];
                        if (pikachu_health>0 && charmander_heatlh>0){
                            attack.setText("Pikachu used " + radio_button.getText() + " and caused " + String.valueOf(hpwon)+ "damage" + '\n'+ "Charmander used " + print_charmander + " and caused " + hplost + "damage");
                            hp_charmander.setText("HP : " + String.valueOf(charmander_heatlh));
                            hp_pikachu.setText("HP : " + String.valueOf(pikachu_health));
                        }
                        else if(charmander_heatlh <= 0 && pikachu_health>0){
                            attack.setText("Charmander Fainted!! Congratulations you won!!");
                            hp_charmander.setText("HP : " + String.valueOf(0));
                            hp_pikachu.setText("HP : " + String.valueOf(pikachu_health));
                            button.setClickable(false);
                            button.setBackgroundColor(Color.DKGRAY);
                        }
                        else if(pikachu_health <= 0 && charmander_heatlh>0){
                            attack.setText("Pikachu Fainted!! You lost better luck next time.");
                            hp_charmander.setText(String.valueOf(charmander_heatlh));
                            hp_pikachu.setText("HP : " + 0);
                            button.setClickable(false);
                            button.setBackgroundColor(Color.DKGRAY);
                        }
                        else if (pikachu_health <= 0 && charmander_heatlh <= 0){
                            attack.setText("Both the pokemons cannot fight anymore, Its a Draw.");
                            hp_charmander.setText("HP : " + 0);
                            hp_pikachu.setText("HP : " + 0);
                            button.setClickable(false);
                            button.setBackgroundColor(Color.DKGRAY);
                        }
                        else{

                        }

                    }
                }
        );
    }
    private void refresh(){
        radio_g = (RadioGroup) findViewById(R.id.radio_group);
        radio_button = (RadioButton) findViewById(R.id.radioButton);
        attack = (TextView) findViewById(R.id.attacklog);
        refresh = (Button) findViewById(R.id.refresh);
        refresh.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                }
        );
    }

}

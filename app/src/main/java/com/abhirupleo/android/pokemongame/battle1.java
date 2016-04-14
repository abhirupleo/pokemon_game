package com.abhirupleo.android.pokemongame;

// adding all necessary imports
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Random;

public class battle1 extends AppCompatActivity {

    // Declaring all member variables
    private static TextView hp_pikachu;
    private static TextView hp_charmander;
    private static Button refresh;
    private static RadioGroup radio_g;
    private static RadioButton radio_button;
    private static Button button;
    private static TextView attack;

    // Declaring health variables for both the pokemons
    private int pikachu_health;
    private int charmander_heatlh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Initializing the health of both the pokemons
        pikachu_health = charmander_heatlh=100;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.pickachu);

        // Calling both the methods used in this Activity
        attack();
        refresh();

    }

    // Method to calculate the damages and display the appropriate message
    private void attack(){
        // Initialising all the various objects used using the findViewById method
        hp_pikachu = (TextView) findViewById(R.id.label_pikachu);
        hp_charmander = (TextView) findViewById(R.id.label_charmander);
        radio_g = (RadioGroup) findViewById(R.id.radio_group);
        radio_button = (RadioButton) findViewById(R.id.radioButton);
        attack = (TextView) findViewById(R.id.attacklog);
        button = (Button) findViewById(R.id.attack_button);

        // Listing whether the button is clicked and proceding in required way
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Declaring an array to store the moves of the Opponent
                        String[] charmander_moves = {
                          "Ember","Scratch","Fireblast","Bite"
                        };

                        // Declaring the local variables for this method
                        int button_selected = radio_g.getCheckedRadioButtonId();
                        int idx = radio_g.indexOfChild(radio_button); // this gets the index of the radio button the user selects
                        int hplost; // this stores the damage the user does
                        int hpwon; // this stores the damage the opponent inflicts on the user

                        // Initialising the radio button by using the index recieved above
                        radio_button = (RadioButton) findViewById(button_selected);

                        // Generating a random move
                        Random random_number = new Random();    //generating a random number using the Random class and creating an object random_number
                        int charmander_index = random_number.nextInt(charmander_moves.length); // setting the charmander_index as a random variable between 0 and length of the array of the moves

                        // Using switch method to decide the damage caused and suffered by the user
                        switch (charmander_index){
                            case 0 : hplost = 15;break;     // setting the damage suffered to the user using the charmarnder_index random variable
                            case 1 : hplost = 10;break;
                            case 2 : hplost = 30;break;
                            case 3 : hplost = 15;break;
                            default:hplost = 0;
                        }
                        switch (idx){
                            case 0 : hpwon = 25;break;      // setting the damage caused by the user using the idx variable which depends on the index of the radio button selected by the user
                            case 1 : hpwon = 30;break;
                            case 2 : hpwon = 10;break;
                            case 3 : hpwon = 15;break;
                            default:hpwon = 0;
                        }

                        // Updating the health remaning for the user and the CPU
                        charmander_heatlh = charmander_heatlh - hpwon;
                        pikachu_health = pikachu_health - hplost;

                        // Displaying messages using appropriate logical conditions
                        if (pikachu_health>0 && charmander_heatlh>0){ // ongoing battle message
                            attack.setText("Pikachu used " + radio_button.getText() + " and caused " + String.valueOf(hpwon)+ "damage" + '\n'+ "Charmander used " + charmander_moves[charmander_index] + " and caused " + hplost + "damage");
                            hp_charmander.setText("HP : " + String.valueOf(charmander_heatlh));
                            hp_pikachu.setText("HP : " + String.valueOf(pikachu_health));
                        }

                        // If the health of any pokemon reaches zero the attack button is disabled and color is changed to dark gray
                        else if(charmander_heatlh <= 0 && pikachu_health>0){
                            attack.setText("Charmander Fainted!! Congratulations you won!!"); // message when the user wins
                            hp_charmander.setText("HP : " + String.valueOf(0));
                            hp_pikachu.setText("HP : " + String.valueOf(pikachu_health));
                            button.setClickable(false);
                            button.setBackgroundColor(Color.DKGRAY);
                        }
                        else if(pikachu_health <= 0 && charmander_heatlh>0){
                            attack.setText("Pikachu Fainted!! You lost better luck next time."); // message when the cpu wins
                            hp_charmander.setText(String.valueOf(charmander_heatlh));
                            hp_pikachu.setText("HP : " + 0);
                            button.setClickable(false);
                            button.setBackgroundColor(Color.DKGRAY);
                        }
                        else if (pikachu_health <= 0 && charmander_heatlh <= 0){
                            attack.setText("Both the pokemons cannot fight anymore, Its a Draw."); // message when its a draw
                            hp_charmander.setText("HP : " + 0);
                            hp_pikachu.setText("HP : " + 0);
                            button.setClickable(false);
                            button.setBackgroundColor(Color.DKGRAY);
                        }
                    }
                }
        );
    }

    // Method to refresh the app and initialise the game
    private void refresh(){
        // Initialising objects
        radio_g = (RadioGroup) findViewById(R.id.radio_group);
        radio_button = (RadioButton) findViewById(R.id.radioButton);
        attack = (TextView) findViewById(R.id.attacklog);
        refresh = (Button) findViewById(R.id.refresh);

        // Cheking for a click on the refesh button
        refresh.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Code to initialie the activity
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                }
        );
    }

}

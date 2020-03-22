package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Declaring views
    private TextView information;
    private Button clearButton;

    private boolean activePlayer = false;
    // activePlayer = false means that yellow is the active player
    // activePlayer = true means that red is the active player

    int[] gameStatus = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    //value 2 at any index refers that it is empty, i.e not occupied by any player
    //value 0 at any index refers that it is occupied by yellow
    //value 1 at any index refers that it is occupied by red

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Linking views
        information = findViewById(R.id.informationTV);
        clearButton = findViewById(R.id.clearButton);
    }

    //dropIn function gets called when any of the grid layout position is tapped
    public void dropIn(View v){
        ImageView image = (ImageView) v;
        final String updatedInfo;

        image.setTranslationY(-1000); // Initially translating the image beyond the screen in upward direction

        //Setting image resource and information text view depending on active Player
        if(activePlayer) {
            image.setImageResource(R.drawable.red);
            updatedInfo = "Yellow's Turn";
        } else{
            image.setImageResource(R.drawable.yellow);
            updatedInfo = "Red's Turn";
        }

        //information text update takes a delay of 300ms
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                information.setText(updatedInfo);
            }
        }, 300);

        activePlayer ^= true; //Changing the value of active player

        image.animate().translationYBy(1000).rotationBy(1800).setDuration(300);
    }
}

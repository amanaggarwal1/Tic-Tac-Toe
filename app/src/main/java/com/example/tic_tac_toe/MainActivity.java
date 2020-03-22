package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private GridLayout boardGL;
    private TextView information;
    private Button clearButton;
    private ImageView position1, position2, position3, position4, position5, position6, position7, position8, position9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boardGL = findViewById(R.id.boardGridLayout);
        information = findViewById(R.id.informationTV);
        clearButton = findViewById(R.id.clearButton);
        position1 = findViewById(R.id.position1IV);
        position2 = findViewById(R.id.position2IV);
        position3 = findViewById(R.id.position3IV);
        position4 = findViewById(R.id.position4IV);
        position5 = findViewById(R.id.position5IV);
        position6 = findViewById(R.id.position6IV);
        position7 = findViewById(R.id.position7IV);
        position8 = findViewById(R.id.position8IV);
        position9 = findViewById(R.id.position9IV);

    }

    public void dropIn(View view){
        ImageView v = (ImageView) view;
        v.setTranslationY(-1000);
        v.setImageResource(R.drawable.yellow);
        v.animate().translationYBy(1000).rotationBy(1800).setDuration(300);
    }
}

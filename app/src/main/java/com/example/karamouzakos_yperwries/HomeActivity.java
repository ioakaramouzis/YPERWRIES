package com.example.karamouzakos_yperwries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class HomeActivity extends AppCompatActivity {

    //ΠΡΟΣΘΗΚΗ SPLASH SCREEN

    private  static int  SPASH_TIME_OUT = 4000;


    //ΠΡΟΣΘΗΚΗ SPLASH SCREEN




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);





        //SPLASH SCREEN

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(HomeActivity.this,MainActivity.class);
                startActivity(homeIntent);
                finish();
            }
        },SPASH_TIME_OUT     );
        //SPLASH SCREEN

    }
}

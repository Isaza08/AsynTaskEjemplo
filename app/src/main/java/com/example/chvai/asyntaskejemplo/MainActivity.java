package com.example.chvai.asyntaskejemplo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBarHorizontal, progressBarCircular;
    Button btnproceso;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        añadirBar();

        btnproceso.setOnClickListener((v) ->{
            new AsyncTask_load().execute();
            btnproceso.setClickable(false);
        });

    }

    private void añadirBar() {

        btnproceso = (Button)findViewById(R.id.button);

        progressBarHorizontal = (ProgressBar)findViewById(R.id.progressBar_Horizontal);

        progressBarCircular = (ProgressBar)findViewById(R.id.progressBar_Circular);

    }


    public class AsyncTask_load extends AsyncTask<Void, Integer, Void> {

        int progreso;

        @Override
        protected void onPreExecute() {
            Toast.makeText(MainActivity.this, "OnProExecute", Toast.LENGTH_SHORT).show();
            progreso = 0;
            progressBarCircular.setVisibility(View.VISIBLE);
            progressBarHorizontal.setVisibility(View.VISIBLE);
        }


        @Override
        protected Void doInBackground(Void... params) {
            while(progreso<100){
                progreso++;
                publishProgress(progreso);
                SystemClock.sleep(20);
            }
            return null;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBarHorizontal.setProgress(values[0]);
            progressBarCircular.setProgress(values[0]);
        }



        @Override
        protected void onPostExecute(Void aVoid) {
          Toast.makeText(MainActivity.this, "OnPostExecute", Toast.LENGTH_SHORT).show();
            btnproceso.setClickable(true);
            progressBarCircular.setVisibility(View.INVISIBLE);
        }
    }



    }

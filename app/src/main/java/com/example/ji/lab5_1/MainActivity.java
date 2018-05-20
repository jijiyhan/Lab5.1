package com.example.ji.lab5_1;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView imageView1;
    ImageView imageView2;
    EditText editText;
    Button button;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DogThread thread1 = new DogThread(0);
                thread1.start();
                DogThread thread2 = new DogThread(1);
                thread2.start();
            }
        });
    }

    class DogThread extends Thread {
        int DogIndex;
        int stateIndex;
        ArrayList<Integer> images = new ArrayList<Integer>();

        public DogThread(int index) {
            DogIndex = index;
            images.add(R.drawable.dog1);
            images.add(R.drawable.dog2);
            images.add(R.drawable.dog3);
        }

        public void run() {

            stateIndex = 0;
            for (int i = 0; i < 9; i++)
            {
                final String msg = "Dog #" + DogIndex + " state: " + stateIndex;
                handler.post(new Runnable() {
                    public void run() {
                        editText.append(msg + "\n");
                        if (DogIndex == 0) {
                            imageView1.setImageResource(images.get(stateIndex));
                        } else if (DogIndex == 1) {
                            imageView2.setImageResource(images.get(stateIndex));
                        }
                    }
                });
                try {
                    int sleepTime = getRandomTime(500, 3000);
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stateIndex++;
                if (stateIndex >= images.size()) {
                    stateIndex = 0;
                }
            }

        }
        public int getRandomTime(int min, int max) {

            return min+(int)(Math.random() * (max-min));
        }

    }


}


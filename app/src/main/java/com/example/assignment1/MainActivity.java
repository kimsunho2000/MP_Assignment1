package com.example.assignment1;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> imageList = new ArrayList<>();
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    EditText editText;
    Button button1;
    Button button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inflate wigets
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        editText = findViewById(R.id.editText);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        imageIndexable();

        //default settings
        editText.setText("1");
        imageView1.setImageResource(R.drawable.image1);

        //set EventListener
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int textNum = Integer.parseInt(editText.getText().toString()); //현재 editText의 숫자
                textNum -= 1;
                imageView1.setImageResource(imageList.get(textNum - 1));
                editText.setText(String.valueOf(textNum));
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int textNum = Integer.parseInt(editText.getText().toString());
                textNum += 1;
                imageView1.setImageResource(imageList.get(textNum - 1));
                editText.setText(String.valueOf(textNum));
            }
        });

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (editText.getText().equals("1")) {
                        button1.setEnabled(false);
                        imageView2.setActivated(false);
                    }
                    else if (editText.getText().equals("20")) {
                        button2.setActivated(false);
                        imageView3.setActivated(false);
                    }
                    return true;
                }
                return false;
            }
        });

    }

    public void imageIndexable() {
        for (int i = 1; i < 21; i++) {
            String temp = "image" + i;
            imageList.add(getResources().getIdentifier(temp, "drawable", getPackageName()));
        }
    }

}

package com.example.assignment1;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        imageIndexAble();

        //default settings
        editText.setText("1");
        button1.setVisibility(View.INVISIBLE);
        imageView1.setImageResource(R.drawable.image1);
        imageView3.setImageResource(R.drawable.image2);

        //set EventListener
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateImagesAndButtons(-1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateImagesAndButtons(1);
            }
        });


        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE ||
                        (keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN)) {
                    editTextInputCheck();
                    return true;
                }
                return false;
            }
        });

    }

    private void imageIndexAble() {
        for (int i = 1; i < 21; i++) {
            String temp = "image" + i;
            imageList.add(getResources().getIdentifier(temp, "drawable", getPackageName()));
        }
    }

    private void updateImagesAndButtons(int step) {
        int textNum = Integer.parseInt(editText.getText().toString()); // 현재 EditText의 숫자
        textNum += step;

        // EditText 업데이트
        editText.setText(String.valueOf(textNum));

        // 버튼 상태 업데이트
        button1.setVisibility(textNum == 1 ? View.INVISIBLE : View.VISIBLE);
        button2.setVisibility(textNum == 20 ? View.INVISIBLE : View.VISIBLE);

        // 이미지 뷰 업데이트
        imageView1.setImageResource(imageList.get(textNum - 1));
        imageView2.setVisibility(textNum > 1 ? View.VISIBLE : View.INVISIBLE);
        imageView3.setVisibility(textNum < 20 ? View.VISIBLE : View.INVISIBLE);

        if (textNum > 1) {
            imageView2.setImageResource(imageList.get(textNum - 2));
        }
        if (textNum < 20) {
            imageView3.setImageResource(imageList.get(textNum));
        }
    }

    private void editTextInputCheck() {
        try {
            // 입력값을 Float로 변환하여 소수점 처리 가능
            float floatNum = Float.parseFloat(editText.getText().toString());

            // 반올림 처리
            int textNum = Math.round(floatNum);

            // 입력값이 1~20 범위를 벗어난 경우 Toast 메시지 발생
            if (textNum > 20 || textNum < 1) {
                Toast.makeText(getApplicationContext(), "사진은 1 ~ 20까지 있습니다.", Toast.LENGTH_LONG).show();

                // 입력값이 20보다 큰 경우 20으로 설정
                if (textNum > 20) {
                    textNum = 20;
                }
                // 입력값이 1보다 작은 경우 1로 설정
                else {
                    textNum = 1;
                }
            }

            // 최종 수정된 값을 EditText에 설정
            editText.setText(String.valueOf(textNum));
            updateImagesAndButtons(0); //image view 변경

        } catch (NumberFormatException e) {
            // 숫자가 아닌 값이 입력된 경우 Toast 메시지 발생
            Toast.makeText(getApplicationContext(), "유효하지 않은 입력입니다. 숫자를 입력하세요.", Toast.LENGTH_LONG).show();
            // 기본값으로 1 설정
        }
    }
}

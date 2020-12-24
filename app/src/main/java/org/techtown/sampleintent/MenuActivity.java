package org.techtown.sampleintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button button = findViewById(R.id.button); //버튼 객체를 참조하겠다.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("name", "mike"); // (21,22) <--인텐트 객체를 생성하고, name의 값을 부가 데이터로 넣기
                setResult(RESULT_OK, intent); //응답 보내기, 원래 액티비티에 응답코드와 인텐트가 전달이 된다.
                finish(); //현재 액티비티 없애기
            }
        });
    }
}
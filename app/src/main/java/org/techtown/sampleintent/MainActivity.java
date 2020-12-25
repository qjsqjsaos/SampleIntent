package org.techtown.sampleintent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_MENU = 101; // 새 액티비티를 띄울 때 보낼 요청 코드, 코드는 내가 맘대로 지정해도 된다., 여러개일 경우 중복되지 아니 한다.
    //이 값은 나중에 새 액티비티로부터 응답을 받을 때 다시 전달받을 값입니다.

    //EX) 액티비티 Main에서 띄우기(요청코드)를 액티비티 Menu로 보낸다.
    // 그러면 액티비티 Menu는 그 요청코드에 응답을해준다(요청코드, 응답코드) <-- Main에서 전달한 101이라는 값과 응답코드(RESULT_OK) 등을 받는다.
    //이러한 방식으로 어던 액티비티로부터 온 응답인지 구분할 수있는 것이다.

    @Override //MenuActivity에서 전달한 응답코드를 받는 메서드, 응답을 보내오면 그 응답을 처리해준다.
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { //여기서 Intent data 파라미터는 데이터를 전달할 때 사용하는데,
                                                                                                //방법은 putExtra 메서드를 사용하는 것이다.
                                                                                                //이 메서드를 사용할때는 키 값과 데이터 값을 넣고, 이 값을 다시 가져올 때는 키값을
                                                                                                //이용하는 것입니다.
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_MENU) { //요청하는 코드
            Toast.makeText(getApplicationContext(), "onActivityResult 메서드 호출됨. 요청 코드 :" + requestCode +
                    ", 결과 코드 : " + resultCode, Toast.LENGTH_LONG).show();

            if(resultCode == RESULT_OK); { //처음 요청하여 전달한 코드가 다시 돌아오면
                String name = data.getStringExtra("name"); //name값을 받아온다.
                Toast.makeText(getApplicationContext(), "응답으로 전달된 name :" + name,
                        Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class); //getApplicationContext는 현재 액티비티의 정보가 담겨있다고 해석한다.
                //MainActivity의 인스턴스인 MenuActivity.class를 두번째 파라미터로 전달하고,
                //첫번쨰 파라미터인 Context는 액티비티도 컨텍스트 객체가 될 수 있어 this값을 써도 되지만,
                //이벤트 처리 메서드 안에서 this 변수로 MainActivity객체를 참조할 수 없으므로 getApplicationContext메서드를 사용해
                //Context 객체를 참조한 후 전달한 것이다.

                startActivityForResult(intent, REQUEST_CODE_MENU); //startActivity메서드도 새 액티비티를 띄우지만,
                //startActivityForResult는 새 액티비티를띄우는 동시에 새 액티비티로 부터 응답을 받을 수 있다.
            }
        });
    }
}
package com.example.mycalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    char[] array = new char[100];
    int index = 0;
    int a = 0;
    int b = 0;
    char operator = '\0';
    int result = 0;
    boolean cal = false; // clear의 경우를 대비, b가 입력된 건지 안된건지 판단

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //숫자 버튼 클릭 시 이 메소드에서 배열에 추가, 에디트텍스트 편집 모두 진행!
    public void buttonNumber(View v) {
        String s = ((Button) v).getText().toString();
        ((EditText) findViewById(R.id.editText)).setText(((EditText) findViewById(R.id.editText)).getText() + s);

        array[index] = s.toCharArray()[0];
        index++;
    }

    // 아무 숫자도 입력 안하고 연산부호만 입력하는 경우 방지
    public void buttonOperator(View v) {
        if (index != 0 && operator == '\0') {
            String s = ((Button) v).getText().toString();
            ((EditText) findViewById(R.id.editText)).setText(((EditText) findViewById(R.id.editText)).getText() + s);

            array[index] = s.toCharArray()[0];
            operator = array[index]; ///////  미리 설정!
            index++;
        }

        // 아무 숫자도 입력하지 않고 연산자만 입력하면 무반응.
    }

    // =누르면 실행. 연산자, 피연산자 구분, 예외처리와 계산 후 에디트텍스트에 결과 값 표시
    public void calculate(View v) {

        int i; // a의 자릿수-1 :자릿수가 1이면 0
        int j; // b의 자릿수-1 :자릿수가 1이면 0
        int[] num_a = new int[100];
        int[] num_b = new int[100];

        i = 0;
        while (true) {
            if ((array[i] == '+') || (array[i] == '-') || (array[i] == '*') || (array[i] == '/') || (array[i] == '\0'))
                break;
            num_a[i] = array[i] - '0'; // char to int
            i++;
        }

        // a저장
        int n = 0;
        for (int m = i - 1; m >= 0; m--) {
            a += num_a[n] * Math.pow(10, m);
            n++;
        }

        i++;

        // 피연산자 구분
        for (j = 0; array[i + j] != '\0'; j++) {
            num_b[j] = array[i + j] - '0'; // char to int
            cal = true;
        }

        // b저장
        n = 0;
        for (int m = j - 1; m >= 0 && cal; m--) { // 조건문에 cal넣은 이유: b가 미입력된 상황 방지하기 위해
            b += num_b[n] * Math.pow(10, m);
            n++;
        }

        // 연산
        switch (operator) {
            case '+': {
                if (cal) {
                    result = a + b;
                    ((EditText) findViewById(R.id.editText)).setText(((EditText) findViewById(R.id.editText)).getText() + "=" + result);
                } else Toast.makeText(this, "완성되지 않은 수식입니다.", Toast.LENGTH_SHORT).show();

                break;
            }
            case '-': {
                if (cal) {
                    result = a - b;
                    ((EditText) findViewById(R.id.editText)).setText(((EditText) findViewById(R.id.editText)).getText() + "=" + result);
                } else Toast.makeText(this, "완성되지 않은 수식입니다.", Toast.LENGTH_SHORT).show();
                break;
            }
            case '*': {
                if (cal) {
                    result = a * b;
                    ((EditText) findViewById(R.id.editText)).setText(((EditText) findViewById(R.id.editText)).getText() + "=" + result);
                } else Toast.makeText(this, "완성되지 않은 수식입니다.", Toast.LENGTH_SHORT).show();

                break;
            }
            case '/': {

                if (b == 0) Toast.makeText(this, "0으로 나눌 수 없습니다.", Toast.LENGTH_SHORT).show();

                else {
                    if (cal) {
                        result = a / b;
                        ((EditText) findViewById(R.id.editText)).setText(((EditText) findViewById(R.id.editText)).getText() + "=" + result); /////////////////////////////////////////////////////////////자동으로 되나???????????
                    } else Toast.makeText(this, "완성되지 않은 수식입니다.", Toast.LENGTH_SHORT).show();
                }

                break;
            }

            case '\0': {
                result = a;
                ((EditText) findViewById(R.id.editText)).setText(((EditText) findViewById(R.id.editText)).getText() + "=" + result);

                break;
            }
        }

        // 예외 #1: 0으로 나눌 수 없습니다. line101

        // 예외 #2: 완성되지 않은 식입니다. 각 case마다 if문 삽입

        // 예외 #3: 연산부호 연속으로 입력 시 무반응
        // github연동아 제발 되자

    }

    public void clear(View v) {
        // 초기화~!
        index = 0;
        a = 0;
        b = 0;
        operator = '\0';
        result = 0;
        cal = false; // b가 입력되지 않은 경우는 연산 불가능~

        for (int i = 0; i < array.length; i++) {
            array[i] = '\0';
        }

        ((EditText) findViewById(R.id.editText)).setText(""); // 칸 비워주기
    }
}
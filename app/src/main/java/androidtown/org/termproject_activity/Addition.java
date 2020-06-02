package androidtown.org.termproject_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Addition extends AppCompatActivity {
    EditText foodName, foodLimit,foodEntity;
    RadioGroup radioGroup;
    RadioButton refrigerator, freezer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);

        foodName = findViewById(R.id.editName);
        foodLimit = findViewById(R.id.editLimit);
        foodEntity = findViewById(R.id.editEntity);
        radioGroup = findViewById(R.id.radioGroup);
        refrigerator = findViewById(R.id.refrigerator);
        freezer = findViewById(R.id.freezer);

        Button buttonFinish = findViewById(R.id.finish);
        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int radioId = radioGroup.getCheckedRadioButtonId();
                //냉장 선택시
                if(refrigerator.getId()==radioId) {
                    //정보 보내기
                    /*
                    FragmentHigh fragmentHigh = (FragmentHigh) getSupportFragmentManager().findFragmentById(R.id.highFragment);
                    Bundle mybundle1 = new Bundle();

                    mybundle1.putString("name", foodName.getText().toString());
                    mybundle1.putString("limit", foodLimit.getText().toString());
                    mybundle1.putString("entity", foodEntity.getText().toString());

                    fragmentHigh.setArguments(mybundle1);
                    //getSupportFragmentManager().beginTransaction().add(R.id.highContainer, fragmentHigh).commit();
*/
                    //Toast 확인
                    Toast.makeText(getApplicationContext(), "냉장 저장 완료", Toast.LENGTH_SHORT).show();
                    finish();
                }
                //냉동 선택시
                else if(freezer.getId()==radioId) {
                    //정보 보내기
                    /*
                    Bundle mybundle2 = new Bundle();

                    mybundle2.putString("name", foodName.getText().toString());
                    mybundle2.putString("limit", foodLimit.getText().toString());
                    mybundle2.putString("entity", foodEntity.getText().toString());

                    fragmentLow.setArguments(mybundle2);
                    getSupportFragmentManager().beginTransaction().add(R.id.lowFragment, fragmentLow).commit();
*/
                    //Toast 확인
                    Toast.makeText(getApplicationContext(),"냉동 저장 완료", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        Button buttonCancle = findViewById(R.id.cancle);
        buttonCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}


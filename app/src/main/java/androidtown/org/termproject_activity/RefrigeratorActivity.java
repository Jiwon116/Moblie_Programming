package androidtown.org.termproject_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class RefrigeratorActivity extends AppCompatActivity {
    Button home;
    Button refrigerator;
    Button recipe;
    Button setting;

    FragmentHigh highFrag;
    FragmentLow lowFrag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refrigerator);

        this.InitializeView();
        this.SetListener();

        FragmentManager manager = getSupportFragmentManager();
        highFrag = (FragmentHigh) manager.findFragmentById(R.id.highFragment);
        lowFrag = (FragmentLow) manager.findFragmentById(R.id.lowFragment);

        //추가버튼 기능 구현 -> 추가 창 이동
        Button button1 = findViewById(R.id.plus);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Addition.class);
                startActivity(intent);
            }
        });

        //삭제버튼 기능 구현 -> 삭제 창 이동
        Button button2 = findViewById(R.id.delete);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Delete.class);
                startActivity(intent);
            }
        });

        //수정버튼 기능 구현 -> 삭제 창 이동후 선택 -> data 묶어서 추가창으로 전송
        Button button3 = findViewById(R.id.edit);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Edit.class);
                startActivity(intent);
            }
        });
    }

    private void InitializeView() {
        home = (Button) findViewById(R.id.home_button);
        refrigerator = (Button) findViewById(R.id.refrigerator_button);
        recipe = (Button) findViewById(R.id.recipe_button);
        setting = (Button) findViewById(R.id.setting_button);
    }

    private void SetListener() {
        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        refrigerator.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), RefrigeratorActivity.class);
                startActivity(intent);
            }
        });

        recipe.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), RecipeActivity.class);
                startActivity(intent);
            }
        });

        setting.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
            }
        });
    }
}
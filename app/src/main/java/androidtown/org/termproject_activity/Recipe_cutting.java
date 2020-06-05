package androidtown.org.termproject_activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class Recipe_cutting extends AppCompatActivity {
    Button home;
    Button refrigerator;
    Button recipe;
    Button setting;
    Speaker speaker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_cutting);

        this.InitializeView();
        this.SetListener();

        FragmentManager manager = getSupportFragmentManager();
        speaker = (Speaker) manager.findFragmentById(R.id.speaker);

        //왼쪽버튼 기능 구현 -> 이전 창, 시작 창으로 이동
        ImageButton button1 = findViewById(R.id.leftButton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Recipe_cooking.class);
                startActivity(intent);
            }
        });

        //오른쪽버튼 기능 구현 -> 다음 창 이동
        ImageButton button2 = findViewById(R.id.rightButton);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Recipe_boiling.class);
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
                Intent intent = new Intent(getApplicationContext(), Refrigerator.class);
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
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

public class Recipe_cooking extends AppCompatActivity {
    Button home;
    Button refrigerator;
    Button recipe;
    Button setting;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_cooking);

        this.InitializeView();
        this.SetListener();

        //List View Activity
        String[] items={"감자 2개", "양파 1개", "두부 반모", "청양고추 2개", "다진마늘 1T", "된장 2T", "고춧가루 2T", "설탕 1T","쌀뜨물 8컵"};
        ListView listView = (ListView) findViewById(R.id.recipeList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        //왼쪽버튼 기능 구현 -> 이전 창, 시작 창으로 이동
        ImageButton button1 = findViewById(R.id.leftButton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Recipe_start.class);
                startActivity(intent);
            }
        });

        //오른쪽버튼 기능 구현 -> 다음 창 이동
        ImageButton button2 = findViewById(R.id.rightButton);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Recipe_cutting.class);
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
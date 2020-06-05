package androidtown.org.termproject_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity{
    Button home;
    Button refrigerator;
    Button recipe;
    Button setting;
    private Spinner spinner;
    ImageView eggmayotoast;
    ImageView curry;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        eggmayotoast=findViewById(R.id.eggmayotoast);
        curry=findViewById(R.id.curry);

        Intent passedIntent = getIntent();

        this.InitializeView();
        this.SetListener();

        spinner = (Spinner)findViewById(R.id.spinner);

        final ArrayList<String> list = new ArrayList<>();
        list.add("추천순");
        list.add("인기순");
        list.add("내가 찜한 순");

        ArrayAdapter spinnerAdapter;
        spinnerAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, list);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(RecipeActivity.this,"정렬 방식 : "+spinner.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
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
        eggmayotoast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), RecipeMain.class);
                intent.putExtra("name","마약 토스트");
                startActivity(intent);
            }
        });
        curry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), RecipeMain.class);
                intent.putExtra("name","카레");
                startActivity(intent);
            }
        });

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

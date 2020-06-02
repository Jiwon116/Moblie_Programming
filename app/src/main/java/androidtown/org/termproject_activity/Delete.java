package androidtown.org.termproject_activity;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Delete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        //List View Activity
        String[] items={"양파","호박","고추장","달걀","불고기"};
        ListView listView = (ListView) findViewById(R.id.deleteListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //make toast
                String item = ((TextView)view).getText().toString();
                Toast.makeText(getApplicationContext(),item,Toast.LENGTH_SHORT).show();
            }
        });

        Button buttonDelete = findViewById(R.id.deleteButton);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //삭제하는 기능 추가
                finish();
            }
        });

        Button buttonCancle2 = findViewById(R.id.cancleButton);
        buttonCancle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

package androidtown.org.termproject_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Addition extends AppCompatActivity {
    String ingName;
    String ingAm;
    String ingU;

    private final String dbName = "cook";
    private final String high = "highRef";
    private final String low = "lowRef";

    static SQLiteDatabase sampleDB = null;
    ListAdapter adapter;
    EditText tx1;
    EditText tx2;
    EditText tx3;
    int click =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);

        //어느 냉장고가 열렸는지 받아오기!
        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle = intent.getExtras();

        int openH = bundle.getInt("H_open");
        int openL = bundle.getInt("L_open");

        //냉장고에 넣는버튼
        Button initB = findViewById(R.id.initB);
        TextView name1 = (TextView) findViewById(R.id.reName);

        ///////////////////////냉동고에 넣기 ///////////////////////////////////////
        if(openH==1) {
            name1.setText("냉동고에 넣기 ");

            tx1 = (EditText) findViewById(R.id.ingName);
            tx2 = (EditText) findViewById(R.id.ingAm);
            tx3 = (EditText) findViewById(R.id.ingU);

            initB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ingName = tx1.getText().toString();
                    ingAm = tx2.getText().toString();
                    ingU = tx3.getText().toString();
                    click = 1;

                    Toast.makeText(getApplicationContext(), "입력 되었습니다!", Toast.LENGTH_LONG).show();

                    inHDB();
                }
            });
        }
        ////////////////////////////냉장고에 넣기 //////////////////////////////////////////////
        if(openL==1) {
            name1.setText("냉장고에 넣기 ");

            tx1 = (EditText) findViewById(R.id.ingName);
            tx2 = (EditText) findViewById(R.id.ingAm);
            tx3 = (EditText) findViewById(R.id.ingU);

            initB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ingName = tx1.getText().toString();
                    ingAm = tx2.getText().toString();
                    ingU = tx3.getText().toString();
                    click = 1;

                    Toast.makeText(getApplicationContext(), "입력 되었습니다!", Toast.LENGTH_LONG).show();

                    inLDB();
                }
            });
        }

        //냉장고로 돌아가는 버튼 !!
        Button returnB = findViewById(R.id.returnB);
        returnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void inHDB(){
        if (click == 1) {
            try {

                sampleDB = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);

                sampleDB.execSQL("INSERT INTO highRef values("+"'"+ingName+"', '"+ingAm+"', '"+ingU+"')");

                sampleDB.close();
            } catch (SQLiteException se) {
                Toast.makeText(getApplicationContext(), se.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("", se.getMessage());
            }
            Toast.makeText(getApplicationContext(), "냉동고에 입력완료", Toast.LENGTH_LONG).show();
        }
        click =0;
    }

    private void inLDB(){
        if (click == 1) {
            try {
                sampleDB = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);

                sampleDB.execSQL("INSERT INTO lowRef values("+"'"+ingName+"', '"+ingAm+"', '"+ingU+"')");

                sampleDB.close();
            } catch (SQLiteException se) {
                Toast.makeText(getApplicationContext(), se.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("", se.getMessage());
            }
            Toast.makeText(getApplicationContext(), "냉장고에 입력완료", Toast.LENGTH_LONG).show();
        }
        click =0;
    }
}
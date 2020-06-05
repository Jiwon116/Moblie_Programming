package androidtown.org.termproject_activity;


import android.content.Intent;
import android.os.Bundle;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;


public class RecipeMain extends AppCompatActivity{
    String name;
    String calories;
    String level ;
    String[] ingred=new String[10];
    String[] amount=new String[10];
    String[] inam=new String[10];
    ArrayList<HashMap<String,String>> ingredList;

    ListView list;

    private final String dbName = "cook";
    private final String table1Name = "main"; //이름 , 칼로리, 난이도
    private final String table2Name = "recipe"; //이름, 레시피1줄, 시간, 단계
    private final String table3Name = "ingredients"; //이름, 재료, 양

    private final String tableName = "person";

    static SQLiteDatabase sampleDB = null;
    ListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_ingredient);
        Button button1 = findViewById(R.id.rrrrr);
        //list = (ListView) findViewById(R.id.listView);
        Intent passedIntent = getIntent();
        EditText editText = (EditText)findViewById(R.id.ingredient_editText);

        String passedName= passedIntent.getStringExtra("name");

        try {

            sampleDB = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);
            //테이블이 존재하지 않으면 새로 생성합니다.

            //테이블이 존재하는 경우 기존 데이터를 지우기 위해서 사용합니다.
            sampleDB.execSQL("DELETE FROM " + table1Name );
            sampleDB.execSQL("DELETE FROM highRef"  );
            sampleDB.execSQL("DELETE FROM lowRef" );
            sampleDB.execSQL("DELETE FROM recipe" );
            sampleDB.execSQL("DROP TABLE main");
            sampleDB.execSQL("DROP TABLE recipe");
            sampleDB.execSQL("DROP TABLE ingredients");
            sampleDB.execSQL("DROP TABLE highRef");
            sampleDB.execSQL("DROP TABLE lowRef");

            sampleDB.execSQL("CREATE TABLE main (name VARCHAR(20) primary key, calories int, level int)");
            sampleDB.execSQL("CREATE TABLE recipe (name VARCHAR(20) , toto varchar(100),  timer long,step int,FOREIGN KEY (name) REFERENCES main(name))");
            sampleDB.execSQL("CREATE TABLE ingredients (name VARCHAR(20), ingred varchar(10), amount varchar(10),FOREIGN KEY (name) REFERENCES main(name))");
            //
            sampleDB.execSQL("CREATE TABLE highRef (name VARCHAR(20) primary key, amount varchar(10), limitt varchar(10))");
            sampleDB.execSQL("CREATE TABLE lowRef (name VARCHAR(20) primary key, amount varchar(10), limitt varchar(10))");


            sampleDB.execSQL("INSERT INTO main values('마약 토스트', 1000, 1)");

            sampleDB.execSQL("INSERT INTO recipe values('마약 토스트','식빵 테두리를 따라 마요네즈를 네모 모양으로 짜줍니다.',null, 1)");
            sampleDB.execSQL("INSERT INTO recipe values('마약 토스트','마요네즈 테두리 안에 계란을 깨서 올려줍니다.',null, 2)");
            sampleDB.execSQL("INSERT INTO recipe values('마약 토스트','그 위에 설탕을 솔솔 뿌려줍니다.',null, 3)");
            sampleDB.execSQL("INSERT INTO recipe values('마약 토스트','오븐에 넣고 180도에서 10분 구워줍니다.',12000, 4)");
            sampleDB.execSQL("INSERT INTO recipe values('마약 토스트','맛있게 냠냠.',null, 5)");

            sampleDB.execSQL("INSERT INTO ingredients values('마약 토스트','식빵', '1장')");
            sampleDB.execSQL("INSERT INTO ingredients values('마약 토스트','계란', '1개')");
            sampleDB.execSQL("INSERT INTO ingredients values('마약 토스트','마요네즈', '넉넉히')");
            sampleDB.execSQL("INSERT INTO ingredients values('마약 토스트','설탕', '조금')");


            sampleDB.execSQL("INSERT INTO main values('카레', '500', '2')");

            sampleDB.execSQL("INSERT INTO recipe values('카레','야채와 돼지고기를 깍둑썰기 합니다.',null, 1)");
            sampleDB.execSQL("INSERT INTO recipe values('카레','돼지고기를 강불에 볶아줍니다.',null, 2)");
            sampleDB.execSQL("INSERT INTO recipe values('카레','볶은 돼지고기에 물을 부어줍니다.',null, 3)");
            sampleDB.execSQL("INSERT INTO recipe values('카레','그 안에 야채들을 넣어서 끓여줍니다',10000, 4)");
            sampleDB.execSQL("INSERT INTO recipe values('카레','카레루를 넣습니다.',null, 5)");
            sampleDB.execSQL("INSERT INTO recipe values('카레','적당한 농도가 될 때 까지 끓여줍니다.',null, 6)");
            sampleDB.execSQL("INSERT INTO recipe values('카레','밥 위에 카레를 부어 줍니다.',null, 7)");
            sampleDB.execSQL("INSERT INTO recipe values('카레','맛있게 냠냠.',null, 8)");

            sampleDB.execSQL("INSERT INTO ingredients values('카레','밥', '1공기')");
            sampleDB.execSQL("INSERT INTO ingredients values('카레','양파', '1/2개')");
            sampleDB.execSQL("INSERT INTO ingredients values('카레','당근', '1/3개')");
            sampleDB.execSQL("INSERT INTO ingredients values('카레','감자', '1/2개')");
            sampleDB.execSQL("INSERT INTO ingredients values('카레','돼지고기', '100g')");

            sampleDB.close();

        } catch (SQLiteException se) {
            Toast.makeText(getApplicationContext(),  se.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("", se.getMessage());


        }

        showList(passedName,editText);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), Recipe_start.class); //보내주는 인텐트 생성

                startActivity(intent1); //번들을 인텐트에 실어서 보내주기
                Toast.makeText(getApplicationContext(),  "intent11 OK", Toast.LENGTH_LONG).show();
            }
        });

    }

    protected void showList(String passedName, TextView editText){

        try {

            SQLiteDatabase ReadDB = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);



            //SELECT문을 사용하여 테이블에 있는 데이터를 가져옵니다..
            Cursor c = ReadDB.rawQuery("SELECT * from ingredients WHERE name='"+passedName+"'",null);

            if (c != null) {
                int j=0;
                if (c.moveToFirst()) {

                    do {

                        //테이블에서 세개의 행을 가져온다
                        name = c.getString(c.getColumnIndex("name"));


                        TextView name1 = (TextView) findViewById(R.id.Name1);
                        name1.setText(name);


                        ingred[j] = c.getString(c.getColumnIndex("ingred"));
                        amount[j] = c.getString(c.getColumnIndex("amount"));
                        // String ing = c.getString(c.getColumnIndex("ingred"));
                        // String amo = c.getString(c.getColumnIndex("amount"));
                        //ingred[j]=ing;
                        //amount[j]=amo;




                        // ingred = c.getString(c.getColumnIndex("ingred"));
                        //String amount = c.getString(c.getColumnIndex("amount"));

                        //HashMap<String,String> data=new HashMap<String,String>();
                        //data.put("ingredient",ingred);
                        //data.put("amount",amount);

                        //ingredList.add(data);





/*
                        //이부분!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                        TextView name1 = (TextView) findViewById(R.id.Name1);
                        EditText editText = (EditText)findViewById(R.id.ingredient_editText);


                        String ing=null;
                        String amo=null;
                        for(int i=0;i<10;i++)
                        {
                            inam[i]=ingred[i]+" - "+amount[i];
                            if(ingred[])
                        }

                        name1.setText(name);
                        editText.setText(inam[0]+"\n");
                        editText.append(inam[1]+"\n");
                        editText.append(inam[2]+"\n");
                        editText.append(inam[3]);

 */

                        j++;
                    } while (c.moveToNext());
                }
            }

            ReadDB.close();
            /*
            String str;
            Toast.makeText(getApplicationContext(),ingredList.size(), Toast.LENGTH_LONG).show();
            for(int i=0;i<ingredList.size();i++)
            {
                HashMap<String,String> data2 = ingredList.get(i);
                str=data2.get("ingredient")+" "+data2.get("amount");
                editText.append(str);
            }*/


            for (int k=0;k<ingred.length;k++)
            {
                if(ingred[k]!=null) {
                    editText.append(ingred[k] + " - " + amount[k] + "\n");
                }
            }

        } catch (SQLiteException se) {
            Toast.makeText(getApplicationContext(),  se.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("",  se.getMessage());
        }

    }
}
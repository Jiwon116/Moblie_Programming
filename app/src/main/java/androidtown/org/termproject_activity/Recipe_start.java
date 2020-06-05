package androidtown.org.termproject_activity;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.text.Spannable;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class Recipe_start extends AppCompatActivity implements TextPlayer, View.OnClickListener {

    private final String dbName = "cook";
    static int step=0;
    String name;
    String[] rec=new String[10];
    int[] ind=new int[10];
    private int standbyIndex = 0;
    private int lastPlayIndex = 0;
    int check=0;

    private final Bundle params = new Bundle();
    private TextToSpeech tts;
    private Button playBtn;
    private Button pauseBtn;
    private Button stopBtn;
    private PlayState playState = PlayState.STOP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_start);

        Intent passedIntent = getIntent();
        final String passedName= passedIntent.getStringExtra("name");

        final TimerFragment timerFragment;
        timerFragment = new TimerFragment();
        Button nextB = findViewById(R.id.nextButton);

        initTTS();
        initView();

        SQLiteDatabase ReadDB = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);
        Cursor c = ReadDB.rawQuery("SELECT * FROM recipe WHERE name='"+passedName+"'", null);
        c.moveToFirst();
        step=0;
        name = c.getString(c.getColumnIndex("name"));
        ind[0]=c.getInt(c.getColumnIndex("step"));
        rec[0] = c.getString(c.getColumnIndex("toto"));
        step=0;
        step++;

        EditText nnnn = (EditText)findViewById(R.id.nnn);
        EditText rrrr = (EditText)findViewById(R.id.rrr);

        nnnn.setText("맛있는 "+name+"를 만들어 봅시다!");
        rrrr.setText("[STEP "+ind[0]+"] "+rec[0]);

        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check==1) {
                    getSupportFragmentManager().beginTransaction().remove(timerFragment).commit();
                    check=0;
                }
                readRecipe(timerFragment,passedName);
            }
        });
    }

    public void readRecipe(TimerFragment timerFragment, String passedName){
        try {
            SQLiteDatabase ReadDB = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);

            Cursor c = ReadDB.rawQuery("SELECT * FROM recipe WHERE name='"+passedName+"'", null);

            c.moveToPosition(step);

            if(c.getInt(c.getColumnIndex("timer"))>0)
            {
                getSupportFragmentManager().beginTransaction().add(R.id.container,timerFragment).commit();
                Bundle bundle = new Bundle();
                bundle.putLong("time",c.getInt(c.getColumnIndex("timer")));
                timerFragment.setArguments(bundle);
            }

            ind[step] = c.getInt(c.getColumnIndex("step"));
            rec[step] = c.getString(c.getColumnIndex("toto"));
            EditText rrrr = (EditText)findViewById(R.id.rrr);
            rrrr.setText("[STEP "+ind[step]+"] "+rec[step]);
            step++;

            ReadDB.close();
        } catch (SQLiteException se) {
            Toast.makeText(getApplicationContext(),  se.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("",  se.getMessage());
        }
        startPlay();
        check=1;
    }

    private void initView() {
        playBtn = findViewById(R.id.btn_play);
        pauseBtn = findViewById(R.id.btn_pause);
        stopBtn = findViewById(R.id.btn_stop);

        playBtn.setOnClickListener(this);
        pauseBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);
    }
    private void initTTS() {
        params.putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, null);
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int state) {
                if (state == TextToSpeech.SUCCESS) {
                    tts.setLanguage(Locale.KOREAN);
                } else {
                    showState("TTS 객체 초기화 중 문제가 발생했습니다.");
                }
            }
        });
        tts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
            @Override
            public void onStart(String s) {
            }
            @Override
            public void onDone(String s) {
                clearAll();
            }
            @Override
            public void onError(String s) {
                showState("재생 중 에러가 발생했습니다.");
            }
            @Override
            public void onRangeStart(String utteranceId, int start, int end, int frame) {
                //changeHighlight(standbyIndex + start, standbyIndex + end);
                lastPlayIndex = start;
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_play:
                startPlay();
                break;
            case R.id.btn_pause:
                pausePlay();
                break;
            case R.id.btn_stop:
                stopPlay();
                break;
        }
        showState(playState.getState());
    }
    @Override
    public void startPlay() {
        EditText rrrr1 = (EditText)findViewById(R.id.rrr);
        String content = rrrr1.getText().toString();
        if (playState.isStopping() && !tts.isSpeaking()) {
            startSpeak(content);
        } else if (playState.isWaiting()) {
            standbyIndex += lastPlayIndex;
            startSpeak(content.substring(standbyIndex));
        }
        playState = PlayState.PLAY;
    }
    @Override
    public void pausePlay() {
        if (playState.isPlaying()) {
            playState = PlayState.WAIT;
            tts.stop();
        }
    }
    @Override
    public void stopPlay() {
        tts.stop();
        clearAll();
    }
    private void startSpeak(final String text) {
        tts.speak(text, TextToSpeech.QUEUE_ADD, params, text);
    }
    private void clearAll() {
        playState = PlayState.STOP;
        standbyIndex = 0;
        lastPlayIndex = 0;
    }
    private void showState(final String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onPause() {
        if (playState.isPlaying()) {
            pausePlay();
        }
        super.onPause();
    }
    @Override
    protected void onResume() {
        if (playState.isWaiting()) {
            startPlay();
        }
        super.onResume();
    }
    @Override
    protected void onDestroy() {
        tts.stop();
        tts.shutdown();
        super.onDestroy();
    }
}
package androidtown.org.termproject_activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class Speaker extends Fragment {
    ImageView speaker;
    ImageView mute;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_speaker, container, false);

        speaker = rootView.findViewById(R.id.speakerView);
        mute = rootView.findViewById(R.id.muteView);
        //스피커 사진을 누를때 소리 끈 사진으로 사진 전환
        speaker.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                mute.setVisibility(View.VISIBLE);
                speaker.setVisibility(View.INVISIBLE);
            }
        });

        //소리 끈 사진을 누를때 스피커 사진으로 전환
        mute.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mute.setVisibility(View.INVISIBLE);
                speaker.setVisibility(View.VISIBLE);
            }
        });
        return rootView;
    }
}


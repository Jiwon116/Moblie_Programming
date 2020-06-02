package androidtown.org.termproject_activity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentHigh extends Fragment {
    ImageView hv;
    ImageView hv2;
    TextView textView;
    RelativeLayout highContainer;
    ViewGroup rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = (ViewGroup) inflater.inflate(R.layout.high_fragment, container, false);

        hv=rootView.findViewById(R.id.highPicture);
        hv2=rootView.findViewById(R.id.highPicture2);
        textView=rootView.findViewById(R.id.highTextView);
        highContainer = rootView.findViewById(R.id.highContainer);

        //*************추가 창 -> TextView 추가 *****************
/*
        String name = getArguments().getString("name");
        String entity = getArguments().getString("entity");

        //toast 확인
        String item = name + " " + entity;
        Toast.makeText(rootView.getContext(), item, Toast.LENGTH_SHORT).show();

*/
        //Bundle 받아서 읽기
        /*
        Bundle bundle = getArguments();
        if(bundle!=null) {
            String name = bundle.getString("name");
            String entity = bundle.getString("entity");

            //toast 확인
            String item = name + " " + entity;
            Toast.makeText(rootView.getContext(), item, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(rootView.getContext(), "Error!", Toast.LENGTH_SHORT).show();
        }
        */
        //RelativeLayout에 textView 추가
        /*
            TextView view = new TextView(rootView.getContext());
            view.setText(item);
            view.setTextSize(20);
            view.setTextColor(Color.BLACK);fr

            RelativeLayout.LayoutParams rp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            rp.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
            view.setLayoutParams(rp);

            highContainer.addView(view);
*/
        //*************화면 배경 설정 *****************
        //냉장고 위의 바깥사진을 누를때 안으로 사진 전환
        hv.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                hv.setVisibility(View.INVISIBLE);
                hv2.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
            }
        });

        //냉장고 위의 안쪽사진을 누를때 바깥으로 사진 전환
        hv2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                hv2.setVisibility(View.INVISIBLE);
                hv.setVisibility(View.VISIBLE);
                textView.setVisibility(View.INVISIBLE);
            }
        });

        return rootView;
    }
}


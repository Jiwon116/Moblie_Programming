package androidtown.org.termproject_activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class FragmentLow extends Fragment {
    ImageView lv;
    ImageView lv2;
    TextView textView;
    RelativeLayout container;
    ViewGroup rootView;
    public int open =0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.low_fragment,container,false);

        lv=rootView.findViewById(R.id.lowPicture);
        lv2=rootView.findViewById(R.id.lowPicture2);
        textView=rootView.findViewById(R.id.lowTextView);

        //냉장고 아래의 바깥사진을 누를때 안으로 사진 전환
        lv.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                open=1;
                //activity 로 여닫기 알려줌
                ((FragmentLow.OnApplySelectedListener)activity).onCategoryApplySelectedL(open);

                lv.setVisibility(View.INVISIBLE);
                lv2.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
            }
        });

        //냉장고 아래의 안쪽사진을 누를때 바깥으로 사진 전환
        lv2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                open=0;
                //activity 로 여닫기 알려줌
                ((FragmentLow.OnApplySelectedListener)activity).onCategoryApplySelectedL(open);

                lv2.setVisibility(View.INVISIBLE);
                lv.setVisibility(View.VISIBLE);
                textView.setVisibility(View.INVISIBLE);
            }
        });
        return rootView;
    }
    //activity 에 열림 / 닫힘 정보 넘겨주기!!!!!
    public interface OnApplySelectedListener{
        public void onCategoryApplySelectedL( int open);


    }
    private Refrigerator activity;
    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        if(context instanceof Refrigerator) {

            // 사용될 activity 에 context 정보 가져오는 부분

            this.activity = (Refrigerator)context;

        }



    }
}

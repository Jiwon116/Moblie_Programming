package androidtown.org.termproject_activity;


import android.content.Context;
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
    int open = 0; //냉장고가 열렸는지 닫혔는지 확인하는 변수
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = (ViewGroup) inflater.inflate(R.layout.high_fragment,container,false);

        hv=rootView.findViewById(R.id.highPicture);
        hv2=rootView.findViewById(R.id.highPicture2);
        textView=rootView.findViewById(R.id.highTextView);
        highContainer = rootView.findViewById(R.id.highContainer);


        //*************화면 배경 설정 *****************
        //냉장고 위의 바깥사진을 누를때 안으로 사진 전환
        hv.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                open=1;
                //activity 로 여닫기 알려줌
                ((OnApplySelectedListener)activity).onCategoryApplySelectedH(open);

                hv.setVisibility(View.INVISIBLE);
                hv2.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
            }
        });

        //냉장고 위의 안쪽사진을 누를때 바깥으로 사진 전환
        hv2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                open=0;
                //activity 로 여닫기 알려줌
                ((OnApplySelectedListener)activity).onCategoryApplySelectedH(open);
                hv2.setVisibility(View.INVISIBLE);
                hv.setVisibility(View.VISIBLE);
                textView.setVisibility(View.INVISIBLE);
            }
        });

        return rootView;
    }
    //activity 에 열림 / 닫힘 정보 넘겨주기!!!!!
    public interface OnApplySelectedListener{
        public void onCategoryApplySelectedH( int open);


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

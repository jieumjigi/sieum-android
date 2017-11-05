package com.jieumjigi.sieum;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by wjddk on 2017-06-07.
 */

public class PoetFragment extends Fragment {

    ImageView poet_image;
    TextView poet_name;
    TextView poet_ref;
    TextView intro_poet;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.poet_fragment, container, false);
        Util.setGlobalFont(getContext(), view);

//        butterfly.setVisibility(View.GONE);

        poet_image = (ImageView)view.findViewById(R.id.poet_image);
        poet_name = (TextView) view.findViewById(R.id.poet_name);
        poet_ref = (TextView)view.findViewById(R.id.poet_ref);
        intro_poet = (TextView)view.findViewById(R.id.intro_poet);

        setPoetImage(MainActivity.poetName);
        poet_name.setText(MainActivity.poetName);
        poet_ref.setText(MainActivity.linkToBook);
        intro_poet.setText(MainActivity.introPoet);

        return view;
    }

    public void setPoetImage(String poetName){

        if(poetName.contains("경원")){
            poet_image.setImageResource(R.drawable.profile3);
        }else if(poetName.contains("예랑")){
            poet_image.setImageResource(R.drawable.profile4);
        }else if(poetName.contains("은총")){
            poet_image.setImageResource(R.drawable.profile1);
        }else if(poetName.contains("승재")){
            poet_image.setImageResource(R.drawable.profile5);
        }else if(poetName.contains("영하")){
            poet_image.setImageResource(R.drawable.profile2);
        }else if(poetName.contains("예솔")){
            poet_image.setImageResource(R.drawable.profile6);
        }else{

        }

    }
}

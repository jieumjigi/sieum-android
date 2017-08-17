package com.jieumjigi.sieum;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import static com.jieumjigi.sieum.MainActivity.butterfly;

/**
 * Created by wjddk on 2017-06-07.
 */

public class PoemFragment extends Fragment {

    Animation waiting_anim;
    TextView p_title;
    TextView p_author;
    TextView p_content;
    TextView slash;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        waiting_anim = AnimationUtils.loadAnimation(this.getActivity(),R.anim.waiting_anim);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.poem_fragment, container, false);
        Util.setGlobalFont(getContext(), view);

        p_title = (TextView)view.findViewById(R.id.p_title);
        p_author = (TextView)view.findViewById(R.id.p_author);
        p_content = (TextView)view.findViewById(R.id.p_content);
        slash = (TextView)view.findViewById(R.id.slash);
        butterfly = (ImageView)view.findViewById(R.id.butterfly);
        butterfly.startAnimation(waiting_anim);


        Handler settingHandler = new Handler();
        settingHandler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                p_title.setText(MainActivity.title);
                p_author.setText(MainActivity.poetName);
                p_content.setText(MainActivity.contents);
                slash.setVisibility(View.VISIBLE);
                butterfly.animate().cancel();
                butterfly.setVisibility(View.GONE);
            }
        }, 4000);


        return view;
    }
}

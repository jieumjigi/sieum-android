package com.jieumjigi.sieum;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by wjddk on 2017-06-09.
 */

public class AbbrevFragment extends Fragment {

    TextView poem_abbrev;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.abbrev_fragment, container, false);
        Util.setGlobalFont(getContext(), view);

//        butterfly.setVisibility(View.GONE);

        poem_abbrev = (TextView)view.findViewById(R.id.poem_abbrev);

        Handler settingHandler = new Handler();
        settingHandler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                poem_abbrev.setText(MainActivity.question);
            }
        }, 2000);


        return view;
    }
}

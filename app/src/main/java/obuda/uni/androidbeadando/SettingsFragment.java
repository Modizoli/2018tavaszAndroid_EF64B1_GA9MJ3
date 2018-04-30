package obuda.uni.androidbeadando;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.pm.ActivityInfo;
import android.content.pm.ComponentInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

public class SettingsFragment extends Fragment {

    private View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v=View.inflate( getContext(),R.layout.settingview,null);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final CheckBox landscapecheck=v.findViewById(R.id.landscapemode);

        /*final Button[] settingbuttons=new Button[2];
        settingbuttons[0]=v.findViewById(R.id.apply);
        settingbuttons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(landscapecheck.isChecked()){
                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    for (Button b:settingbuttons
                            ) {
                        b.setVisibility(View.INVISIBLE);
                    }
                    landscapecheck.setVisibility(View.INVISIBLE);
                }
                if(!landscapecheck.isChecked()) {
                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

                    for (Button b:settingbuttons
                            ) {
                        b.setVisibility(View.INVISIBLE);
                    }
                    landscapecheck.setVisibility(View.INVISIBLE);
                }
            }
        });
        settingbuttons[1]=v.findViewById(R.id.cancel);
        settingbuttons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });*/
        Button applybutton=v.findViewById(R.id.apply);
        Button cancelbutton=v.findViewById(R.id.cancel);

        final android.support.v4.app.FragmentManager fm=getFragmentManager();
        //final FragmentTransaction ft=fm.beginTransaction();

        applybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(landscapecheck.isChecked()) {
                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    fm.popBackStackImmediate();
                }
                else {
                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    fm.popBackStackImmediate();
                }
            }
        });

        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.popBackStackImmediate();
            }
        });
    }
}

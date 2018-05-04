package obuda.uni.androidbeadando;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MenuFragment extends Fragment {
    private View v;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=View.inflate( getContext(),R.layout.menuview,null);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button startbutton=v.findViewById(R.id.start);
        Button settingbutton=v.findViewById(R.id.settings);
        Button exitbutton=v.findViewById(R.id.exit);

        FragmentManager fm=getFragmentManager();
        final FragmentTransaction ft=fm.beginTransaction();

        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadGameFragment(ft);

            }
        });
        settingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadSettingsFragment(ft);
            }
        });

        exitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    private void LoadSettingsFragment(FragmentTransaction ft)
    {
        ft.addToBackStack(this.getClass().toString());
        ft.replace(R.id.fragment_container,new SettingsFragment());
        ft.commit();
    }

    private void LoadGameFragment(FragmentTransaction ft){
        ft.addToBackStack(this.getClass().toString());
        ft.replace(R.id.fragment_container,new GameFragment());
        ft.commit();
    }

    private void LoadHighScoreFragment(FragmentTransaction ft){
        ft.addToBackStack(this.getClass().toString());
        ft.replace(R.id.fragment_container,new HighScoreFragment());
        ft.commit();
    }
}

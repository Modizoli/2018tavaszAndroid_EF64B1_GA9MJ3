package obuda.uni.androidbeadando;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class GameFragment  extends Fragment {

    private View v;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=View.inflate( getContext(),R.layout.gameview,null);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setCars();

    }

    private void setCars()
    {
        ConstraintLayout cl=v.findViewById(R.id.map);

        for(int i=0;i<5;i++){
            AppCompatImageView imv=new AppCompatImageView(getContext());
            imv.setLayoutParams(new ViewGroup.LayoutParams(80,120));
            imv.setMaxHeight(120);
            imv.setMaxWidth(80);
            imv.setX(i*100);
            imv.setY(i*100);
            imv.setImageResource(R.drawable.car_purple_front);

            cl.addView(imv);
        }
    }
}
package obuda.uni.androidbeadando;

import android.content.Context;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
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
    private Logic logic;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=View.inflate( getContext(),R.layout.gameview,null);

        SensorManager sm = ( SensorManager ) getActivity().getSystemService( Context.SENSOR_SERVICE );
        logic = new Logic( sm );

        DrawingCanvas dc = ( DrawingCanvas ) v.findViewById( R.id.canvas );

        logic.view = dc;
        dc.logic = logic;

        logic.crashsound = MediaPlayer.create(logic.view.getContext(), R.raw.crash);

        logic.setPaused( false );
        logic.start();

        return v;
    }
}
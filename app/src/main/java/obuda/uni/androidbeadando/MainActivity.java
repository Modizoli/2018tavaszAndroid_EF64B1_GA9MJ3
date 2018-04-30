package obuda.uni.androidbeadando;

import android.hardware.SensorManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Logic logic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sm = (SensorManager)getSystemService( SENSOR_SERVICE );
        logic = new Logic( sm );
    }

    @Override
    protected void onResume() {
        super.onResume();
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        LoadMenuFragment(ft);
    }

    private void LoadMenuFragment(FragmentTransaction ft) {
        ft.replace(R.id.fragment_container,new MenuFragment());
        ft.commit();
    }


}

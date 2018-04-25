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

        setMenuButtons();
    }

    private void setMenuButtons(){
        FragmentManager fm=getSupportFragmentManager();
        final FragmentTransaction ft=fm.beginTransaction();
        final Button[] menubuttons=new Button[3];

        menubuttons[0]=findViewById(R.id.start);
        menubuttons[0].setText("Start");
        menubuttons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Button b:menubuttons
                        ) {
                    b.setVisibility(View.INVISIBLE);
                }

                LoadGameFragment(ft);
            }
        });

        menubuttons[1]=findViewById(R.id.settings);
        menubuttons[1].setText("Settings");
        menubuttons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Button b:menubuttons
                        ) {
                    b.setVisibility(View.INVISIBLE);
                }

                LoadSettingsFragment(ft);
            }
        });

        menubuttons[2]=findViewById(R.id.exit);
        menubuttons[2].setText("Exit");
        menubuttons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }

    private void LoadSettingsFragment(FragmentTransaction ft)
    {
        ft.addToBackStack("MainActivity");
        ft.replace(R.id.fragment_container,new SettingsFragment());
        ft.commit();
    }

    private void LoadGameFragment(FragmentTransaction ft){
        ft.addToBackStack("MainActivity");
        ft.replace(R.id.fragment_container,new GameFragment());
        ft.commit();
    }
}

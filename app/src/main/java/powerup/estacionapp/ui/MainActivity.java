package powerup.estacionapp.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;

import powerup.estacionapp.R;
import powerup.estacionapp.ui.MVP.Presenter;

public class MainActivity extends AppCompatActivity {

    private int frameID = R.layout.fragment_parking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        FrameLayout frame = new FrameLayout(this);
        frame.setId(frameID);
        setContentView(frame, new ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT));

        if (savedInstanceState == null) {
            Fragment newFragment = new ParkingFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(frameID, newFragment).commit();
        }
    }

    private Activity getActivity() {
        return this;
    }
}

package powerup.estacionapp.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import powerup.estacionapp.R;
import powerup.estacionapp.model.Parking;
import powerup.estacionapp.ui.Adapters.ParkingAdapter;
import powerup.estacionapp.ui.MVP.ParkingMVP;
import powerup.estacionapp.ui.MVP.Presenter;

public class MainActivity extends AppCompatActivity implements ParkingMVP.ParkingView {

    private Presenter presenter;
    private ParkingAdapter parkingAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new Presenter(this, this);
        parkingAdapter = new ParkingAdapter();
        recyclerView = (RecyclerView) findViewById(R.id.parking_list);
        recyclerView.setAdapter(parkingAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.isActivityReady();
    }

    @Override
    public void tryGetParkingsFromRaw() {
        InputStream raw = getResources().openRawResource(R.raw.parkings);
        Reader reader = new BufferedReader(new InputStreamReader(raw));
        presenter.listParkingFromReader(reader);
    }

    @Override
    public void addParkingsToAdapter(List<Parking> parkingsList) {
        parkingAdapter.add(parkingsList);
        parkingAdapter.notifyDataSetChanged();
    }

    @Override
    public void showParkings() {

    }

    @Override
    public void ShowError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}

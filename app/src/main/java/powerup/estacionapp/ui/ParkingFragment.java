package powerup.estacionapp.ui;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ParkingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ParkingFragment extends Fragment implements ParkingMVP.ParkingView {

    private Presenter presenter;
    private ParkingAdapter parkingAdapter;

    private RecyclerView rvParkingList;

    public ParkingFragment() {
        // Required empty public constructor
    }

    public static ParkingFragment newInstance() {
        ParkingFragment fragment = new ParkingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new  Presenter(this, this);
        parkingAdapter = new ParkingAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_parking, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvParkingList = (RecyclerView) getActivity().findViewById(R.id.parking_list);
        rvParkingList.setAdapter(parkingAdapter);
        rvParkingList.setLayoutManager(new LinearLayoutManager(getActivity()));
        presenter.isActivityReady();
    }

    public void addParkingsToAdapter(List<Parking> parkings) {
        parkingAdapter.add(parkings);
        parkingAdapter.notifyDataSetChanged();
    }

    @Override
    public void tryGetParkingsFromRaw() {
        InputStream raw = getResources().openRawResource(R.raw.parkings);

        Reader is = new BufferedReader(new InputStreamReader(raw));
        presenter.listParkingFromReader(is);
    }

    @Override
    public void showParkings() {

    }

    @Override
    public void ShowError(String error) {

    }
}

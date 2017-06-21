package powerup.estacionapp.ui.MVP;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import powerup.estacionapp.model.Parking;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by auti_ on 5/3/2017.
 */

public class Presenter implements ParkingMVP.ParkingPresenter{

    private final Object parkingsReposiroty;
    private final ParkingMVP.ParkingView parkingsView;
    private Model parkingModel;

    public Presenter(Object repository, ParkingMVP.ParkingView mvpView) {
        parkingsReposiroty = checkNotNull(repository);
        parkingsView = checkNotNull(mvpView);
        initPresenter();
    }

    private void initPresenter() {
        parkingModel = new Model();
    }

    @Override
    public void listParkingFromReader(Reader rawReader) {
        List<Parking> parkingList = new ArrayList<>();
        parkingList = Model.getParkingList(rawReader);
        parkingsView.addParkingsToAdapter(parkingList);
    }

    @Override
    public void isActivityReady() {
        parkingsView.tryGetParkingsFromRaw();
    }

    @Override
    public void getParkings() {

    }

    @Override
    public void setFavorite() {

    }
}

package powerup.estacionapp.ui.MVP;

import java.io.Reader;
import java.util.List;

import powerup.estacionapp.model.Parking;

/**
 * Created by auti_ on 5/3/2017.
 */

public interface ParkingMVP {
    interface ParkingView {
        void tryGetParkingsFromRaw();
        void addParkingsToAdapter(List<Parking> parkingsList);
        void showParkings();
        void ShowError(String error);

    }

    interface ParkingPresenter {
        void listParkingFromReader(Reader rawReader);
        void isActivityReady();
        void getParkings();
        void setFavorite();
    }
}

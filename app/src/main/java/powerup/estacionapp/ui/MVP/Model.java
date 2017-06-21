package powerup.estacionapp.ui.MVP;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import powerup.estacionapp.model.Parking;

/**
 * Created by auti_ on 5/3/2017.
 */

public class Model {

    public static List<Parking> getParkingList(String jsonString) {
        Gson gson = new Gson();
        Type typeOfT = new TypeToken<List<Parking>>(){}.getType();
        List<Parking> response = (List<Parking>) gson.fromJson(jsonString, typeOfT);
        return response;
    }

    public static List<Parking> getParkingList(Reader jsonRaw) {
        Gson gson = new Gson();
        Type typeOfT = new TypeToken<ArrayList<Parking>>(){}.getType();
        List<Parking> response = (List<Parking>) gson.fromJson(jsonRaw, typeOfT);
        return response;
    }
}

package powerup.estacionapp.ui.MVP;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
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

class Model {
    private static List<Parking> list = new ArrayList<>();

    public static List<Parking> getParkingList(String jsonString) {
        Gson gson = new Gson();
        Type typeOfT = new TypeToken<List<Parking>>(){}.getType();
        return (List<Parking>) gson.fromJson(jsonString, typeOfT);
    }

    static List<Parking> getParkingList(Reader jsonRaw) {
        Gson gson = new Gson();
        Type typeOfT = new TypeToken<ArrayList<Parking>>(){}.getType();
        return (List<Parking>) gson.fromJson(jsonRaw, typeOfT);
    }

    static List<Parking> getParkingsList() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference table = ref.child("estacionamientos");
        Query queryTable = table.orderByKey();

        queryTable.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list = getParkingList(dataSnapshot.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return list;
    }
}

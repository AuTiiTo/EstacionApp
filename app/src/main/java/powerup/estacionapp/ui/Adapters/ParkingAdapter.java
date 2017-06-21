package powerup.estacionapp.ui.Adapters;

import android.content.Context;
import android.location.Location;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import powerup.estacionapp.R;
import powerup.estacionapp.model.Parking;
import powerup.estacionapp.repository.Constant;

/**
 * Created by auti_ on 16/5/2017.
 */

public class ParkingAdapter extends RecyclerView.Adapter<ParkingAdapter.ParkingViewHolder> {

    //TODO: paso 3

    private List<Parking> parkings;

    public ParkingAdapter() {
        this.parkings = new ArrayList<>();
    }

    @Override
    public ParkingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.parking, null);
        ParkingViewHolder parkingViewHolding = new ParkingViewHolder(itemLayoutView);
        return parkingViewHolding;
    }

    @Override
    public void onBindViewHolder(ParkingViewHolder holder, int position) {
        final Parking item = parkings.get(position);
        Context mContext = holder.itemView.getContext();

        final String direccion = item.getStreet()
                .concat(" ")
                .concat(String.valueOf(item.getNumber()));
        holder.nombre.setText(String.valueOf(direccion));
        float metersTo = item.getDistance();
        String distance = "";
        if (metersTo > 1000) {
            metersTo /= 1000.00;
            distance = String.format(Locale.US, "%.2f", metersTo).concat(" kmts");
        } else {
            distance = String.format(Locale.US, "%.2f", metersTo).concat(" mts");
        }

        holder.distancia.setText(distance);
        if (item.getCorner() != null && !item.getCorner().isEmpty()) {
            String corner = "\nEsq. ".concat(item.getCorner());
            holder.direccion.setText(corner);
        } else {
            holder.direccion.setText("");
        }
        holder.precio.setText(mContext.getString(R.string.price_hour, String.valueOf(item.getCost_per_hour())));
        int availability = itemAvailability(item.getAvailable_places(), item.getTotal_places());
        switch (availability) {
            case Constant.HIGH_AVAILABILITY:
                holder.disponibilidad.setText(mContext.getString(R.string.high_availability));
                holder.disponibilidad.setTextColor(mContext.getResources().getColor(R.color.high));
                break;
            case Constant.MEDIUM_AVAILABILITY:
                holder.disponibilidad.setText(mContext.getString(R.string.medium_availability));
                holder.disponibilidad.setTextColor(mContext.getResources().getColor(R.color.medium));
                break;
            case Constant.LOW_AVAILABILITY:
                holder.disponibilidad.setText(mContext.getString(R.string.low_availability));
                holder.disponibilidad.setTextColor(mContext.getResources().getColor(R.color.low));
                break;
            default:
                holder.disponibilidad.setText(mContext.getString(R.string.no_availability));
                holder.disponibilidad.setTextColor(mContext.getResources().getColor(R.color.no));
        }
        if (item.isFavorite()) {
            holder.fav.setImageDrawable(mContext.getDrawable(android.R.drawable.star_big_on));
        } else {
            holder.fav.setImageDrawable(mContext.getDrawable(android.R.drawable.star_big_off));
        }
//        setClickListener(holder, item, parkingDB);
    }

    @Override
    public int getItemCount() {
        return parkings.size();
    }

    public void add(List<Parking> parkingList) {
        parkings = parkingList;
    }

    public int itemAvailability(int available, int total) {
        if (total > 0 && available > 0) {
            if ((available * 100) / total > 50)
                return Constant.HIGH_AVAILABILITY;
            else if ((available * 100) / total > 20)
                return Constant.MEDIUM_AVAILABILITY;
            else
                return Constant.LOW_AVAILABILITY;
        }
        return Constant.NO_AVAILABILITY;
    }

    public float itemDistance(Location mobileLocation, Parking item) {
        Location itemLocation = new Location("");
        itemLocation.setLatitude(item.getLatitude());
        itemLocation.setLongitude(item.getLongitude());
        float distanceTo = 0;
        try {
            distanceTo = mobileLocation.distanceTo(itemLocation);
        } catch (Exception exp) {
            distanceTo = 0;
        }
        return distanceTo;
    }

    class ParkingViewHolder extends RecyclerView.ViewHolder {

        public TextView nombre;
        public TextView direccion;
        public TextView distancia;
        public TextView precio;
        public TextView disponibilidad;
        public ImageView map;
        public ImageView fav;
        public ImageView call;

        public ParkingViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            nombre = (TextView) itemLayoutView.findViewById(R.id.tv_parking_name);
            direccion = (TextView) itemLayoutView.findViewById(R.id.tv_parking_direction);
            distancia = (TextView) itemLayoutView.findViewById(R.id.tv_parking_distance);
            precio = (TextView) itemLayoutView.findViewById(R.id.tv_parking_price);
            disponibilidad = (TextView) itemLayoutView.findViewById(R.id.tv_parking_availability);
            //Icons
            map = (ImageView) itemLayoutView.findViewById(R.id.iv_view_in_map);
            fav = (ImageView) itemLayoutView.findViewById(R.id.iv_fav);
        }
    }
}

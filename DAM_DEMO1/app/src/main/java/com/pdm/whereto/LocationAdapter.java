package com.pdm.whereto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    private List<Location> _locations;
    private AppCompatActivity _activity;

    public LocationAdapter(List<Location> locations, AppCompatActivity activity) {

        _locations = locations;
        _activity = activity;
    }

    public void addItem(Location location) {

        _locations.add(location);
        notifyItemInserted(_locations.size()-1);
    }

    protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected CardView _cardView;
        protected TextView _locationNameView;
        protected TextView _locationAddressView;

        public ViewHolder(View v) {

            super(v);
            v.setOnClickListener(this);
            _cardView = (CardView) v.findViewById(R.id.cardView);
            _locationNameView = (TextView) v.findViewById(R.id.location_name);
            _locationAddressView = (TextView) v.findViewById(R.id.location_address);
        }

        @Override
        public void onClick(View v) {

            Location location = _locations.get(getAdapterPosition());
            Intent intent = new Intent(_activity, MapsActivity.class);
            intent.putExtra("name", location.get_name());
            intent.putExtra("lat", location.get_lat().doubleValue());
            intent.putExtra("lng", location.get_lng().doubleValue());
            _activity.startActivity(intent);
        }
    }

    @Override
    public LocationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Location location = _locations.get(position);
        LocationType locationType = LocationType.getLocationTypeByName(location.get_type());

        //TODO this is not working!!!!Y U NO WORK?!?!
        if (locationType != null)
            holder._cardView.setCardBackgroundColor(locationType.get_color());
        //Log.e("asdf", "color : " + locationType.get_color());

        holder._locationNameView.setText(location.get_name());
        holder._locationAddressView.setText(location.get_address());
    }

    @Override
    public int getItemCount() {

        return _locations.size();
    }
}
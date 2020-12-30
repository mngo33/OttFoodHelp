package com.example.kuriustry2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button button;
    Map<Double, Double> coordinates = new LinkedHashMap<>();
    ArrayList<String> fridgeNames = new ArrayList<>();
    ArrayList<String> addresses = new ArrayList<>();
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        button = (Button) findViewById(R.id.btn_menu);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenuActivity();
            }

        });

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                while (items.hasNext()) {
                    DataSnapshot item = items.next();

                    Log.d("success", "Address" + item.child("Address").getValue().toString());
                    Log.d("success", "Name" + item.getKey());
                    Log.d("success", "Lat" + item.child("Lat").getValue().toString());
                    Log.d("success", "Lng" + item.child("Lng").getValue().toString());

                    double lat = item.child("Lat").getValue(Double.class);
                    double lng = item.child("Lng").getValue(Double.class);
                    String address = item.child("Address").getValue(String.class);
                    String fridgeName = item.getKey();

                    coordinates.put(lat, lng);
                    fridgeNames.add(fridgeName);
                    addresses.add(address);

                    Log.d("success", "Coordinates: " + coordinates.toString());
                    Log.d("success", "Names: " + fridgeNames.toString());
                    Log.d("success", "Address: " + addresses.toString());
                }

                Iterator iterator1 = coordinates.entrySet().iterator();
                Iterator iterator2 = fridgeNames.iterator();
                Iterator iterator3 = addresses.iterator();

                while (iterator1.hasNext() && iterator2.hasNext() && iterator3.hasNext()) {
                    Map.Entry mapElement = (Map.Entry) iterator1.next();
                    LatLng newFridge = new LatLng((double) mapElement.getKey(), (double) mapElement.getValue());
                    Marker m = mMap.addMarker(new MarkerOptions().icon(bitmapDescriptorFromVector(getApplicationContext(), R.drawable.ic_fridge_map_marker)).position(newFridge).title(iterator2.next().toString()).snippet(iterator3.next().toString()));
                }
                coordinates.clear();
                fridgeNames.clear();
                addresses.clear();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("success", "Database error");
            }
        });
    }

    private void openMenuActivity() {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("string",fridgeNames);
        startActivity(intent);
        //overridePendingTransition(R.anim,slide_in_right,R.anim.slide_out_left);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        String[] name = {"Kanata Food Cupboard (Legget)", "Kanata Food Cupboard (Young)", "Lifecentre Foodbank", "Community Compassion Centre Food Bank", "Dalhousie Food Cupboard", "Partage Vanier", "Stittsville Food Bank", "Britannia Woods Food Pantry"};
        String[] address = {"340 Legget Dr, Kanata, ON", "20 Young Rd, Kanata, ON", "2675 Innes Rd unit 1, Gloucester, ON", "1825 St Joseph Blvd, Orléans, ON", "211 Bronson Ave #107, Ottawa, ON", "161 Marier Ave, Vanier, ON", "1631 MAIN ST, Stittsville, ON", "115 Ritchie St #9, Ottawa, ON"};
        Double[] lat = {45.342114134967105, 45.29759771782124, 45.43315531215865, 45.4626271278109, 45.413592193874614, 45.4400787617956, 45.25456168500734, 45.358062401217914};
        Double[] lng = {-75.9098476216874, -75.89396057448384, -75.56220541495972, -75.5461157303016, -75.70650010146716, -75.66495123030217, -75.91545518612845, -75.80088911681118};
        String[] hours = {"Mon-Fri: 9AM to 12PM", "Mon-Fri: 9AM to 12PM", "Thu: 9AM to 2PM, 5PM to 8PM", "1st & 3rd Tue: 1PM to 3PM, 2nd & 4th Sat: 10AM to 12PM", "Wed & Thu: 10:30AM to 1PM", "Mon-Fri: 8:30AM to 4:30PM", "COVID-19 - Irregular, call for an appointment", "Tues, Thu & Fri: 11AM to 3PM"};
        String[] number = {"(613) 355-9843", "(613) 355-9843", "(613) 834-0900", "(613) 837-3555", "(613) 230-3982", "(613) 747-2839", "(613) 831-0451", "(613) 820-0853"};
        String[] postal = {"K2K 1Y6", "K2L 1W1", "K1B 5N5", "K1C 7C6", "K1R 6H5", "K1L 5R8", "K2S 1B8", "K2B 6E8"};
        String[] website = {"http://www.kanatafoodcupboard.ca/", "http://www.kanatafoodcupboard.ca/", "https://www.lifecentre.org/make-a-difference/lifecentre-food-bank/", "https://cpcorleans.ca/foodbank/", "https://www.dalhousiefoodcupboard.ca/", "https://www.cscvanier.com/en/communtity/food-bank", "https://www.stittsvillefoodbank.ca/", "https://britanniawoods.com/food-bank/"};

        int i = 0;
        String id = null;

        HashMap<String, String> markerMap = new HashMap<String, String>();

        LatLng ottawa = new LatLng(45.45025499017344, -75.72999532029837);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ottawa, 10));

        for (i = 0; i < 8; i++) {
            LatLng temp = new LatLng(lat[i], lng[i]);
            Marker m = mMap.addMarker(new MarkerOptions()
                    .icon(bitmapDescriptorFromVector(getApplicationContext(), R.drawable.ic_food_bank_map_marker))
                    .position(temp)
                    .title(name[i])
                    .snippet(address[i]));

            id = m.getId();
            markerMap.put(id, name[i]);
        }

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker marker) {

                String m = markerMap.get(marker.getId());

                if (m != null) {
                    for (int i = 0; i < 8; i++) {
                        if (m.equals(name[i])) {
                            View row = getLayoutInflater().inflate(R.layout.custom_info_window, null);
                            TextView foodbank = (TextView) row.findViewById(R.id.foodbank);
                            TextView street = (TextView) row.findViewById(R.id.streetAddress);
                            TextView code = (TextView) row.findViewById(R.id.postalcode);
                            TextView hour = (TextView) row.findViewById(R.id.hours);
                            TextView phone = (TextView) row.findViewById(R.id.number);
                            foodbank.setText(name[i]);
                            street.setText(address[i]);
                            code.setText(postal[i]);
                            hour.setText(hours[i]);
                            phone.setText(number[i]);

                            return row;
                        }
                    }
                    return null;
                }
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

                return null;
            }
        });

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                String m = markerMap.get(marker.getId());

                for (int i = 0; i < 8; i++) {
                    if (m.equals(name[i])) {
                        gotoUrl(website[i]);
                        return;
                    }
                }
            }

            private void gotoUrl(String s) {
                Uri uri = Uri.parse(s);
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });
    }

    public void register(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivityForResult(intent, 1);
    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        coordinates.clear();
        fridgeNames.clear();
        addresses.clear();

        LatLng addressLatLng = null;
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                if (requestCode == 1) {
                    if (data != null) {
                        addressLatLng = data.getExtras().getParcelable("LatLng");
                    }
                }
                Log.d("success", addressLatLng.toString());
            }
        }
    }
}
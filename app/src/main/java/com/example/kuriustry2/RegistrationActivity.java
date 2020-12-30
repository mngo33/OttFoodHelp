package com.example.kuriustry2;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class RegistrationActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public EditText fridgeName, addressName;
    private Button btnRegister;
    private TextView warnName, warnAddress, newTxt;
    public static String fridgeString, addressString;
    String[] arr = {"FULL", "HALF-FULL", "EMPTY"};
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        initViews();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                initRegister();

                LatLng addressLatLng = getLocationFromAddress(getApplicationContext(), addressString);


                HashMap<String, Object> map = new HashMap<>();
                map.clear();
                map.put("Lat", addressLatLng.latitude);
                map.put("Lng", addressLatLng.longitude);
                map.put("Address", addressString);

                int select = random.nextInt(arr.length);
                map.put("Inventory", arr[select]);

                FirebaseDatabase.getInstance().getReference().child(fridgeString).setValue(map);

                Intent intent = new Intent();
                intent.putExtra("LatLng", addressLatLng);
                setResult(RESULT_OK, intent);
                openMapsActivity();
            }
        });
    }

    private void openMapsActivity() {
        Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);
    }

    private void initRegister() {
        Log.d(TAG, "initRegister: Started");

        if (validateData()) {
            fridgeString = fridgeName.getText().toString();
            addressString = addressName.getText().toString();
        }
    }

    private boolean validateData() {
        Log.d(TAG, "initRegister: Started");
        if (fridgeName.getText().toString().equals("")) {
            warnName.setVisibility(View.VISIBLE);
            warnName.setText("Enter the fridge name");
            return false;
        }

        if (addressName.getText().toString().equals("")) {
            warnAddress.setVisibility(View.VISIBLE);
            warnAddress.setText("Enter the address");
            return false;
        }

        return true;
    }

    private void initViews() {
        Log.d(TAG, "initViews: Started");

        fridgeName = findViewById(R.id.fridgeName);
        addressName = findViewById(R.id.addressName);

        btnRegister = findViewById(R.id.btnRegister);

        newTxt = findViewById(R.id.newTxt);
    }

    public LatLng getLocationFromAddress(Context context, String strAddress) {
        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);

            p1 = new LatLng(location.getLatitude(), location.getLongitude());
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("fail", "Failed to decode address");
        }
        return p1;


    }
}
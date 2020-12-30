package com.example.kuriustry2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class InventoryActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        listView = findViewById(R.id.listView);

        ArrayList<String> list = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.list_item, list);
        listView.setAdapter(adapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference.orderByKey();

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Information info = snapshot.getValue(Information.class);
                    String txt = info.getAddress() + ": " + info.getInventory();
                    list.add(txt);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
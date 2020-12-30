package com.example.kuriustry2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button inventoryButton;
    private Button fridgeButton;
    private Button recipeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        inventoryButton = (Button) findViewById(R.id.btn_inventory);
        inventoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInventoryActivity();
            }

        });

        fridgeButton = (Button) findViewById(R.id.btn_NewFridge);
        fridgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFridgeActivity();
            }
        });

        recipeButton = (Button) findViewById(R.id.btn_recipes);
        recipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRecipeActivity();
            }
        });

    }

    private void openRecipeActivity() {
        Intent intent = new Intent(this, RecipeActivity.class);
        startActivity(intent);
    }

    private void openFridgeActivity() {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    private void openInventoryActivity() {
        Intent intent = new Intent(this, InventoryActivity.class);
        startActivity(intent);
    }
}
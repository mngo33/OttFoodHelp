package com.example.kuriustry2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RecipeActivity extends AppCompatActivity {

    private Button fortyfive;
    private Button thirtyone;
    private Button twentysix;
    private Button pesto;
    private Button chicken;
    private Button honey;
    private Button lemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        fortyfive = (Button) findViewById(R.id.fortyfive);
        fortyfive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.bonappetit.com/gallery/cheap-recipes");
            }

        });

        thirtyone = (Button) findViewById(R.id.thirtyone);
        thirtyone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.countryliving.com/food-drinks/g4287/cheap-dinner-ideas/");
            }

        });

        twentysix = (Button) findViewById(R.id.twentysix);
        twentysix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.thesimpledollar.com/save-money/20-favorite-dirt-cheap-meals/");
            }

        });

        pesto = (Button) findViewById(R.id.pesto);
        pesto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.bonappetit.com/story/rent-week-pesto-roasted-vegetables");
            }

        });

        chicken = (Button) findViewById(R.id.chickenThighs);
        chicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.tasteofhome.com/recipes/teriyaki-chicken-thighs/");
            }

        });

        honey = (Button) findViewById(R.id.honey);
        honey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.delish.com/cooking/recipe-ideas/recipes/a55762/honey-garlic-glazed-salmon-recipe/");
            }

        });

        lemon = (Button) findViewById(R.id.lemon);
        lemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.brit.co/love-and-lemons-cookbook/");
            }

        });
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}
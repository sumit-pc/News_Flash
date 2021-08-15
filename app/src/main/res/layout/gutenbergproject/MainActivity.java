package com.sumit.gutenbergproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView fiction = findViewById(R.id.fictionType);
        CardView drama = findViewById(R.id.dramaType);
        CardView humor = findViewById(R.id.humorType);
        CardView politics = findViewById(R.id.politicsType);
        CardView philosophy = findViewById(R.id.philosophyType);
        CardView history = findViewById(R.id.historyType);
        CardView adventure = findViewById(R.id.adventureType);


        fiction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewBooksActivity.class);
                intent.putExtra("typeCategory", "Fiction");
                startActivity(intent);
            }
        });

        drama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewBooksActivity.class);
                intent.putExtra("typeCategory", "Drama");
                startActivity(intent);
            }
        });

        humor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewBooksActivity.class);
                intent.putExtra("typeCategory", "Humor");
                startActivity(intent);
            }
        });

        politics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewBooksActivity.class);
                intent.putExtra("typeCategory", "Politics");
                startActivity(intent);
            }
        });

        philosophy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewBooksActivity.class);
                intent.putExtra("typeCategory", "Philosophy");
                startActivity(intent);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewBooksActivity.class);
                intent.putExtra("typeCategory", "History");
                startActivity(intent);
            }
        });

        adventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewBooksActivity.class);
                intent.putExtra("typeCategory", "Adventure");
                startActivity(intent);
            }
        });

    }
}

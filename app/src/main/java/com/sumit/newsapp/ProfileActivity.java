package com.sumit.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class ProfileActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    TextView name, gid, email;
    ImageView imageUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        name = findViewById(R.id.custName);
        gid = findViewById(R.id.gid);
        email = findViewById(R.id.email_id);
        imageUrl = findViewById(R.id.editIV);

        gid.setText("Google ID -"+intent.getStringExtra("id23"));
        name.setText("Welcome "+intent.getStringExtra("name"));
        email.setText("Email -"+intent.getStringExtra("email"));
        if(!TextUtils.isEmpty(intent.getStringExtra("imageUrl"))){
            try{
                Glide.with(this).load(intent.getStringExtra("imageUrl")).into(imageUrl);
            }catch (NullPointerException e){
                Toast.makeText(getApplicationContext(),"image not found",Toast.LENGTH_LONG).show();
            }
        }


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
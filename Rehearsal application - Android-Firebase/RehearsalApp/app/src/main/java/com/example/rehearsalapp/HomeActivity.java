package com.example.rehearsalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {

    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    TextView welcome;
    ProgressBar progressBar;

    String name;
    String email;
    String  bbid;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.notesOption){
            Intent intent =  new Intent(HomeActivity.this, NotesActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId() == R.id.attendanceOption){
            Intent intent =  new Intent(HomeActivity.this, AttendanceActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId() == R.id.moneyOption){
            Intent intent =  new Intent(HomeActivity.this, MoneyActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId() == R.id.sheetsOption){
            Intent intent =  new Intent(HomeActivity.this, SheetsActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId() == R.id.listenOption){
            Intent intent =  new Intent(HomeActivity.this, ListenActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId() == R.id.tripsOption){
            Intent intent =  new Intent(HomeActivity.this, TripsActivity.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.aboutOption){
            Intent intent =  new Intent(HomeActivity.this, AboutActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId() == R.id.logoutOption){
            FirebaseAuth.getInstance().signOut();
            Intent intent =  new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        progressBar = findViewById(R.id.progressBarHome);
        progressBar.setVisibility(View.VISIBLE);

        welcome = findViewById(R.id.welcomeText);

        FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressBar.setVisibility(View.GONE);
                bbid = dataSnapshot.child("bbid").getValue().toString();
                name = dataSnapshot.child("name").getValue().toString();
                email = dataSnapshot.child("email").getValue().toString();
                String welcomeMessage = "Welcome to " + bbid + " Brass Band!";

                welcome.setText(welcomeMessage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressBar.setVisibility(View.GONE);
            }
        });



    }
}
package com.example.rehearsalapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NotesActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private NotesAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    DatabaseReference databaseReference;

    ArrayList<NotesItem> notesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        databaseReference = FirebaseDatabase.getInstance().getReference("Notes");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                notesList.clear();
                for (DataSnapshot notesSnapshot: dataSnapshot.getChildren()){
                    NotesItem notesItem = notesSnapshot.getValue(NotesItem.class);
                    notesList.add(notesItem);
                    mAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        mRecyclerView = findViewById(R.id.reciclerViewNotes);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new NotesAdapter(notesList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

        registerForContextMenu(mRecyclerView);


        mAdapter.setOnClickInterface(new NotesAdapter.OnClickInterface(){
            @Override
            public void onDeleteClick(int position){
                notesList.remove(position);
                databaseReference.removeValue();
                databaseReference.setValue(notesList);
                mAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_notes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.addNewNoteOption) {
            Intent intent = new Intent(NotesActivity.this, NewNoteActivity.class);
            startActivityForResult(intent, 12345);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == 12345) {
            if (data.hasExtra("title") && data.hasExtra("text")) {
                String newTitle = data.getStringExtra("title");
                String newText = data.getStringExtra("text");
                if(!newTitle.equals("") || !newText.equals("")) {
                    NotesItem notesItem = new NotesItem(newTitle, newText);
                    notesList.add(notesItem);
                    databaseReference.child(Integer.toString(notesList.size())).setValue(notesItem);
                    mAdapter.notifyDataSetChanged();
                }

            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == 1){
            mAdapter.notifyDataSetChanged();
        }
        return super.onContextItemSelected(item);
    }

}
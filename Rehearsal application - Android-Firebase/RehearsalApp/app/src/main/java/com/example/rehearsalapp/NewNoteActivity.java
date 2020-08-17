package com.example.rehearsalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewNoteActivity extends AppCompatActivity {

    EditText newTitle, newText;
    Button newButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        newTitle = findViewById(R.id.newTitle);
        newText = findViewById(R.id.newText);
        newButton = findViewById(R.id.newButton);

        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = newTitle.getText().toString();
                String text = newText.getText().toString();

                Intent intent = new Intent(NewNoteActivity.this, NotesActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("text", text);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }


}
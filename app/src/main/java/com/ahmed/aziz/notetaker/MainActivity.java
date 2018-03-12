package com.ahmed.aziz.notetaker;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private Spinner categoriesSpinner;
    private Button addNewButton;
    private ListView notesList;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Database db = new Database(this);
        SQLiteDatabase database = db.getWritableDatabase();

        categoriesSpinner = findViewById(R.id.categoriesId);
        ArrayList<String> categories = new ArrayList<String>(Arrays.asList("Task","To Do", "Home Work"));
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_view, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriesSpinner.setAdapter(adapter);



        final ArrayList<String> notesTitles = new ArrayList<String>(Arrays.asList("Myme","Mymy"));
        final ArrayList<String> notesDetails = new ArrayList<String>(Arrays.asList("Myme","Mymy"));
        notesList = findViewById(R.id.notesListId);

        final ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, R.layout.notes_list_view, R.id.listviewTextId, notesTitles);
        notesList.setAdapter(listAdapter);


        addNewButton = findViewById(R.id.addNewId);
        addNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
                final View addFrame = getLayoutInflater().inflate(R.layout.add_new, null);
                dialogBuilder.setView(addFrame);
                final AlertDialog dialog = dialogBuilder.create();
                dialog.show();

                Button addButton = addFrame.findViewById(R.id.addId);
                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditText title = addFrame.findViewById(R.id.addTitleId);
                        String titleText = title.getText().toString();
                        EditText details = addFrame.findViewById(R.id.addDetailsId);
                        String detailsText = title.getText().toString();

                        notesTitles.add(titleText);
                        notesDetails.add(detailsText);
                        listAdapter.notifyDataSetChanged();

                        dialog.cancel();

                    }
                });


                Button cancelButton = addFrame.findViewById(R.id.cancelId);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
            }
        });



    }
}

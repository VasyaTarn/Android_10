package com.example.android_10;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.LauncherActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_10.DB.DatabaseManager;
import com.example.android_10.model.ListModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseManager dbManager;
    private RecyclerView recyclerView;
    private ListAdapter listAdapter;
    private List<ListModel> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager = new DatabaseManager(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();
        listAdapter = new ListAdapter(listItems, this::showEditDialog, this::showDeleteDialog);
        recyclerView.setAdapter(listAdapter);

        loadLists();

        findViewById(R.id.addButton).setOnClickListener(view -> {
            long newId = dbManager.insertList("New List", System.currentTimeMillis(), "New description");
            loadLists();
        });
    }

    private void loadLists() {
        listItems.clear();
        Cursor cursor = dbManager.getAllLists();
        while (cursor.moveToNext()) {
            @SuppressLint("Range")
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            @SuppressLint("Range")
            String name = cursor.getString(cursor.getColumnIndex("name"));
            @SuppressLint("Range")
            long date = cursor.getLong(cursor.getColumnIndex("date"));
            @SuppressLint("Range")
            String description = cursor.getString(cursor.getColumnIndex("description"));

            listItems.add(new ListModel(id, name, date, description));
        }
        cursor.close();
        listAdapter.notifyDataSetChanged();
    }

    private void showEditDialog(ListModel item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit List");

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_edit, null);
        EditText nameEditText = view.findViewById(R.id.nameEditText);
        EditText descriptionEditText = view.findViewById(R.id.descriptionEditText);

        nameEditText.setText(item.getName());
        descriptionEditText.setText(item.getDescription());

        builder.setView(view);

        builder.setPositiveButton("Save", (dialog, which) -> {
            String newName = nameEditText.getText().toString();
            String newDescription = descriptionEditText.getText().toString();
            dbManager.updateList(item.getId(), newName, System.currentTimeMillis(), newDescription);
            loadLists();
        });

        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    private void showDeleteDialog(ListModel item) {
        new AlertDialog.Builder(this)
                .setTitle("Delete List")
                .setMessage("Are you sure you want to delete this list?")
                .setPositiveButton("Delete", (dialog, which) -> {
                    dbManager.deleteList(item.getId());
                    loadLists();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    @Override
    protected void onDestroy() {
        dbManager.close();
        super.onDestroy();
    }
}
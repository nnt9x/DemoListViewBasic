package com.example.demolistviewbasic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvTodos;

    // Data
    private List<String> dataSource;
    // Adapter
    private ArrayAdapter<String> myAdapter;

    // MyDialog
    private TodoDialog todoDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Find by id
        lvTodos = findViewById(R.id.lvTodos);
        // Fake du lieu
        dataSource = new ArrayList<>();


        // Tao Adapter
        myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataSource);

        // Set Adapter cho listview
        lvTodos.setAdapter(myAdapter);

        // Xu ly khi click vao itemview
        lvTodos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, dataSource.get(i).toUpperCase(), Toast.LENGTH_SHORT).show();

            }
        });

        // Nhan giu de xoa
        lvTodos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Thong bao truoc
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Thong bao")
                        .setMessage("Ban co muon xoa?")
                        .setPositiveButton("Dong y", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int a) {
                                dataSource.remove(i);
                                myAdapter.notifyDataSetChanged();
                            }
                        })
                        .create().show();

                return false;
            }
        });
    }

    public void showDialog(View view) {
        //
        if(todoDialog == null){
            todoDialog = new TodoDialog(this) {
                @Override
                protected void saveTodo(String todo) {
                   // Them vao dataSource
                    dataSource.add(todo);
                    // Thong bao du lieu -> render
                    myAdapter.notifyDataSetChanged();
                }
            };
        }
        todoDialog.show();
    }
}
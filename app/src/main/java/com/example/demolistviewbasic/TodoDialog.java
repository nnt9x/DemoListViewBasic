package com.example.demolistviewbasic;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

public abstract class TodoDialog extends Dialog {
    public TodoDialog(@NonNull Context context) {
        super(context);
    }

    protected abstract void saveTodo(String todo);

    private EditText edtInput;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_item);
        edtInput = findViewById(R.id.edtDialogInput);
        btnAdd = findViewById(R.id.btnSave);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = edtInput.getText().toString();
                if(input.isEmpty()){
                    edtInput.setError("Error");
                    return;
                }
                saveTodo(input);
                edtInput.setText("");
                dismiss();
            }
        });

    }
}

package com.example.jiho_project_alltech;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

public class CustomDialog extends Dialog implements View.OnClickListener{
    private Button dialog_button_insert, dialog_button_cancel;
    private EditText dialog_editText_title, dialog_editText_content;
    private Context context;
    private CustomDialogListener customDialogListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);

        setContentView(R.layout.custom_dialog);

        dialog_button_cancel = findViewById(R.id.dialog_button_cancel);
        dialog_button_insert = findViewById(R.id.dialog_button_insert);
        dialog_editText_title = findViewById(R.id.dialog_editText_title);
        dialog_editText_content = findViewById(R.id.dialog_editText_content);

        dialog_button_insert.setOnClickListener(this);
        dialog_button_cancel.setOnClickListener(this);
    }

    public CustomDialog(@NonNull @NotNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_button_insert:
                String title = dialog_editText_title.getText().toString();
                String content = dialog_editText_content.getText().toString();
                customDialogListener.onInsertClicked(title, content);
                dismiss();
                break;
            case R.id.dialog_button_cancel:
                cancel();
                break;
        }
    }

    interface CustomDialogListener {
        void onInsertClicked(String title, String content);
        void onCancelClicked();
    }
    public void setDialogListener (CustomDialogListener customDialogListener) {
        this.customDialogListener = customDialogListener;
    }
}

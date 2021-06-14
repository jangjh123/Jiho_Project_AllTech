package com.example.jiho_project_alltech;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Glide, Thread, Notification, Intent, RecyclerView + Adapter, Custom Dialog
    // (Dialog with Actions), Spinner, SharedPreferences, Retrofit2, Clickable Item of RecyclerView,
    // SQLiteDatabase, TextView, Kinds of Layout, Button, EditText, ViewPager, Fragment
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private Button button;
    private ImageView imageView;
    private static final int REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_image_upLoad = new Intent(Intent.ACTION_PICK);
                intent_image_upLoad.setType("image/*");
                intent_image_upLoad.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent_image_upLoad, REQUEST_CODE);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( requestCode == REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                try {
                    Uri uri = data.getData();
                    Glide.with(MainActivity.this)
                            .load(uri)
                            .into(imageView);



                    NewRunnable newRunnable = new NewRunnable();
                    Thread th = new Thread(newRunnable);
                    th.start();
                    createNotification();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void initView() {
        button = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);
    }
    class NewRunnable implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default");

        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("Sample");
        builder.setContentText("Sample Detail");

        NotificationManager notificationManager = (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(new NotificationChannel("default", "Standard Channel", NotificationManager.IMPORTANCE_DEFAULT));
        }
        notificationManager.notify(1, builder.build());
    }
}
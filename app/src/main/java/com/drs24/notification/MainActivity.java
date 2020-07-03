package com.drs24.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private static final int NOTIFICATION_ID = 0;

    private long[] vibrate = {0};
    private Notification notification;
    private NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.send_notify_btn);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String description = "test channel for notification";

            NotificationChannel channel = new NotificationChannel("uploadData", "testChannel", NotificationManager.IMPORTANCE_LOW);
            channel.setDescription(description);

            //震動
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            Objects.requireNonNull(v).vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));

            NotificationManager noMgr = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            Objects.requireNonNull(noMgr).createNotificationChannel(channel);
        }

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"uploadData")
                .setSmallIcon(R.mipmap.icon_app)
                .setContentTitle("title") //標題
                .setContentText("text........") //內容
                .setWhen(System.currentTimeMillis()) //設置發生時間
                .setDefaults(Notification.DEFAULT_ALL)//使用所有默認值，比如聲音，震動，閃屏等等
                .setVibrate(vibrate)
                .setPriority(Notification.PRIORITY_DEFAULT);



        final NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationManagerCompat.notify(100,builder.build());
            }
        });
    }

//    private Notification notification_method(String title,String text) {
//        Log.d("Debug","notification");
//
//        //建構notification物件，1.設定標題、2.設定訊息、3.設定時間、4.設定小圖示
//        return notification = new Notification.Builder(this)
//                .setContentTitle(title)
//                .setContentText(text)
//                .setWhen(System.currentTimeMillis())
//                .setSmallIcon(R.drawable.ic_launcher_background)
//                .build();
//    }
}

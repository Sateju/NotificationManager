package jjtelechea.netmind.com.notificationmanager;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    NotificationCompat.Builder mBuilder;
    NotificationManager mNotificationManager;
    PendingIntent mPendingIntent;
    RemoteViews mRemoteViews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonCustomNotification = (Button) findViewById(R.id.button_custom_notification);
        Button buttonNotification = (Button) findViewById(R.id.button_notification);
        buttonCustomNotification.setOnClickListener(this);
        buttonNotification.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.button_custom_notification:
                createCustomNotification();
                break;
            case R.id.button_notification:
                createNotification();
                break;
        }
    }

    private void createNotification(){
        mBuilder = (android.support.v7.app.NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Notification")
                .setContentText("This is a simple text for my notification")
                .setAutoCancel(true);

        Intent notificationIntent = new Intent(this, MainActivity.class);
        mPendingIntent = PendingIntent
                .getActivity(this,1,notificationIntent,PendingIntent.FLAG_CANCEL_CURRENT);


        mNotificationManager = (NotificationManager) this.getSystemService(Service.NOTIFICATION_SERVICE);
        mBuilder.setContentIntent(mPendingIntent);
        mNotificationManager.notify(2,mBuilder.build());
    }

    private void createCustomNotification(){

        mRemoteViews = new RemoteViews(this.getPackageName(),R.layout.custom_notification);


        mRemoteViews.setTextViewText(R.id.txtViewTitle,getResources().getText(R.string.a_title));
        mRemoteViews.setTextViewText(R.id.txtViewBody,getResources().getText(R.string.a_body));
        mRemoteViews.setTextViewText(R.id.txtViewFootnote,getResources().getText(R.string.a_footnote));
        mRemoteViews.setImageViewResource(R.id.imageViewNotification,R.drawable.messenger_icon);

        Intent notificationIntent = new Intent(this, MainActivity.class);
        mPendingIntent = PendingIntent
                .getActivity(this,1,notificationIntent,PendingIntent.FLAG_CANCEL_CURRENT);

        mBuilder = (android.support.v7.app.NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true);

        mNotificationManager = (NotificationManager) this.getSystemService(Service.NOTIFICATION_SERVICE);

        mBuilder.setContent(mRemoteViews);
        mBuilder.setContentIntent(mPendingIntent);
        mNotificationManager.notify(2,mBuilder.build());
    }
}

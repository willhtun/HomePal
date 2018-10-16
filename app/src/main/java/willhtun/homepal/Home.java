package willhtun.homepal;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static android.content.Context.NOTIFICATION_SERVICE;

public class Home extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    CalendarHelper mCalendarHelper;

    int rentDaysLeftUntil = 1;
    int internetDaysLeftUntil = 1;
    int electricityDaysLeftUntil = 1;
    int waterDaysLeftUntil = 1;
    int gasDaysLeftUntil = 1;
    int trashDaysLeftUntil = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mDatabaseHelper = new DatabaseHelper(this);
        mCalendarHelper = new CalendarHelper(mDatabaseHelper);

        loadDueDates();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDueDates();
    }

    public void open_Rent(View view) {
        Intent intent_rent = new Intent(this, Rent.class);
        startActivity(intent_rent);
    }

    public void open_Internet(View view) {
        Intent intent_internet = new Intent(this, Internet.class);
        startActivity(intent_internet);
    }

    public void open_Electricity(View view) {
        Intent intent_electricity = new Intent(this, Electricity.class);
        startActivity(intent_electricity);
    }

    public void open_Water(View view) {
        Intent intent_water = new Intent(this, Water.class);
        startActivity(intent_water);
    }

    public void open_Gas(View view) {
        Intent intent_gas = new Intent(this, Gas.class);
        startActivity(intent_gas);
    }

    public void open_Trash(View view) {
        Intent intent_trash = new Intent(this, Trash.class);
        startActivity(intent_trash);
    }

    public void open_Groceries(View view) {
        String id = "id_product";
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // The user-visible name of the channel.
            CharSequence name = "Product";
            // The user-visible description of the channel.
            String description = "Notifications regarding our products";
            int importance = NotificationManager.IMPORTANCE_MAX;
            NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
            // Configure the notification channel.
            mChannel.setDescription(description);
            mChannel.enableLights(true);
            // Sets the notification light color for notifications posted to this
            // channel, if the device supports this feature.
            mChannel.setLightColor(Color.RED);
            notificationManager.createNotificationChannel(mChannel);
        }

        Intent intent1 = new Intent(getApplicationContext(), Home.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 123, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(),"id_product")
                .setSmallIcon(R.drawable.icon_gas) //your app icon
                .setBadgeIconType(R.drawable.ic_border_greendiamond) //your app icon
                .setChannelId(id)
                .setContentTitle("title")
                .setAutoCancel(true).setContentIntent(pendingIntent)
                .setNumber(1)
                .setColor(255)
                .setContentText("text")
                .setWhen(System.currentTimeMillis());
        notificationManager.notify(1, notificationBuilder.build());
    }

    public void open_Settings(View view) {
        Intent intent_settings = new Intent(this, Settings.class);
        startActivity(intent_settings);
    }

    private void loadDueDates() {

        rentDaysLeftUntil = mCalendarHelper.getDaysLeftUntil("rent");
        internetDaysLeftUntil = mCalendarHelper.getDaysLeftUntil("internet");
        electricityDaysLeftUntil = mCalendarHelper.getDaysLeftUntil("electricity");
        waterDaysLeftUntil = mCalendarHelper.getDaysLeftUntil("water");
        gasDaysLeftUntil = mCalendarHelper.getDaysLeftUntil("gas");
        trashDaysLeftUntil = mCalendarHelper.getDaysLeftUntil("trash");

        ((TextView) findViewById(R.id.rent_diamondText)).setText(String.valueOf(rentDaysLeftUntil));
        ((TextView) findViewById(R.id.internet_diamondText)).setText(String.valueOf(internetDaysLeftUntil));
        ((TextView) findViewById(R.id.electricity_diamondText)).setText(String.valueOf(electricityDaysLeftUntil));
        ((TextView) findViewById(R.id.water_diamondText)).setText(String.valueOf(waterDaysLeftUntil));
        ((TextView) findViewById(R.id.gas_diamondText)).setText(String.valueOf(gasDaysLeftUntil));
        ((TextView) findViewById(R.id.trash_diamondText)).setText(String.valueOf(trashDaysLeftUntil));

        if (rentDaysLeftUntil <= 3)
            ((ImageView) findViewById(R.id.rent_greyDiamonds)).setImageResource(R.drawable.home_optionbuttons_red);
        else if (rentDaysLeftUntil <= 7)
            ((ImageView) findViewById(R.id.rent_greyDiamonds)).setImageResource(R.drawable.home_optionbuttons_orange);
        else
            ((ImageView) findViewById(R.id.rent_greyDiamonds)).setImageResource(R.drawable.home_optionbuttons);

        if (internetDaysLeftUntil <= 3)
            ((ImageView) findViewById(R.id.internet_greyDiamonds)).setImageResource(R.drawable.home_optionbuttons_red);
        else if (internetDaysLeftUntil <= 7)
            ((ImageView) findViewById(R.id.internet_greyDiamonds)).setImageResource(R.drawable.home_optionbuttons_orange);
        else
            ((ImageView) findViewById(R.id.internet_greyDiamonds)).setImageResource(R.drawable.home_optionbuttons);

        if (electricityDaysLeftUntil <= 3)
            ((ImageView) findViewById(R.id.electricity_greyDiamonds)).setImageResource(R.drawable.home_optionbuttons_red);
        else if (electricityDaysLeftUntil <= 7)
            ((ImageView) findViewById(R.id.electricity_greyDiamonds)).setImageResource(R.drawable.home_optionbuttons_orange);
        else
            ((ImageView) findViewById(R.id.electricity_greyDiamonds)).setImageResource(R.drawable.home_optionbuttons);

        if (waterDaysLeftUntil <= 3)
            ((ImageView) findViewById(R.id.water_greyDiamonds)).setImageResource(R.drawable.home_optionbuttons_red);
        else if (waterDaysLeftUntil <= 7)
            ((ImageView) findViewById(R.id.water_greyDiamonds)).setImageResource(R.drawable.home_optionbuttons_orange);
        else
            ((ImageView) findViewById(R.id.water_greyDiamonds)).setImageResource(R.drawable.home_optionbuttons);

        if (gasDaysLeftUntil <= 3)
            ((ImageView) findViewById(R.id.gas_greyDiamonds)).setImageResource(R.drawable.home_optionbuttons_red);
        else if (gasDaysLeftUntil <= 7)
            ((ImageView) findViewById(R.id.gas_greyDiamonds)).setImageResource(R.drawable.home_optionbuttons_orange);
        else
            ((ImageView) findViewById(R.id.gas_greyDiamonds)).setImageResource(R.drawable.home_optionbuttons);

        if (trashDaysLeftUntil <= 3)
            ((ImageView) findViewById(R.id.trash_greyDiamonds)).setImageResource(R.drawable.home_optionbuttons_red);
        else if (trashDaysLeftUntil <= 7)
            ((ImageView) findViewById(R.id.trash_greyDiamonds)).setImageResource(R.drawable.home_optionbuttons_orange);
        else
            ((ImageView) findViewById(R.id.trash_greyDiamonds)).setImageResource(R.drawable.home_optionbuttons);
    }
}

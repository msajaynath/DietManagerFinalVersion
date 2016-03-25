package dietmanager.com.dietmanager;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Entity.Notify;

public class NotificationService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public NotificationService(String name) {
        super(name);
    }

    public NotificationService()
    {
        super("NotificationService");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //Toast.makeText(this, "Service hit!", Toast.LENGTH_LONG).show();

        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        Date d = new Date();
        CharSequence s  = DateFormat.format("MMMMdyyyy ", d.getTime());
        if(hour>=8&&hour<=10)
        {
            List<Notify> notify=Notify.findWithQuery(Notify.class,"select * from Notify where date='"+s+"' and period='b'");
            if(notify.size()==0)
            {
                Notify("Diet Manager","Hey its time to take your breakfast for the day.Tap to view diet.","b");
                new Notify(s+"","b",true).save();

            }
        }

        else if(hour>=12&&hour<=14)
        {
            List<Notify> notify=Notify.findWithQuery(Notify.class,"select * from Notify where date='"+s+"' and period='l'");
            if(notify.size()==0)
            {
                Notify("Diet Manager","Hey its time to take your lunch for the day.Tap to view diet.","l");
                new Notify(s+"","l",true).save();

            }
        }

        if(hour>=19&&hour<=21)
        {
            List<Notify> notify=Notify.findWithQuery(Notify.class,"select * from Notify where date='"+s+"' and period='d'");
            if(notify.size()==0)
            {
                Notify("Diet Manager","Hey its time to take your dinner for the day.Tap to view diet.","d");
                new Notify(s+"","d",true).save();

            }
        }


        scheduleNextUpdate();
    }


    private void Notify(String notificationTitle, String notificationMessage,String type){
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        @SuppressWarnings("deprecation")

        Intent notificationIntent = new Intent(this,DietActivity.class);
        notificationIntent.putExtra("notifytype",type);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);



        Notification.Builder builder = new Notification.Builder(NotificationService.this);
        builder.setSmallIcon(R.drawable. my_diet)
                .setContentTitle(notificationTitle)
                .setContentText(notificationMessage)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);
        Notification notification = builder.getNotification();
        notificationManager.notify(R.drawable.notification_template_icon_bg, notification);
    }


    private void scheduleNextUpdate()
    {
        Intent intent = new Intent(this, this.getClass());
        PendingIntent pendingIntent =
                PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // The update frequency should often be user configurable.  This is not.

        long currentTimeMillis = System.currentTimeMillis();
        long nextUpdateTimeMillis = currentTimeMillis + 5000;
        Time nextUpdateTime = new Time();
        nextUpdateTime.set(nextUpdateTimeMillis);

        if (nextUpdateTime.hour < 8 || nextUpdateTime.hour >= 18)
        {
            nextUpdateTime.hour = 8;
            nextUpdateTime.minute = 0;
            nextUpdateTime.second = 0;
            nextUpdateTimeMillis = nextUpdateTime.toMillis(false) + DateUtils.DAY_IN_MILLIS;
        }
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, nextUpdateTimeMillis, pendingIntent);
    }
}

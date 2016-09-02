package hackday.timecardman;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.rey.material.widget.TextView;

import hackday.timecardman.data.repositories.TimeEntryRepository;
import hackday.timecardman.domain.TimeBalanceCalculator;
import hackday.timecardman.presentation.views.HomeController;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by guilherme.silva on 26/08/2016.
 */
public class TimeCardAppWidgetProvider extends AppWidgetProvider {

    private static final String MyOnClick = "MyOnClick";
    private TextView mTxtView;


    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.timecard_appwidget);

        views.setOnClickPendingIntent(R.id.button2, getPendingSelfIntent(context, MyOnClick));
        ComponentName thisWidget = new ComponentName(context, TimeCardAppWidgetProvider.class);

        appWidgetManager.updateAppWidget(thisWidget, views);

    }

    protected PendingIntent getPendingSelfIntent(Context context, String action) {
        // if we brodcast than definetly we have to define ONRECEIVE
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (MyOnClick.equals(intent.getAction())){
           updateAPorraToda();
        }
    }

    private void updateAPorraToda() {

    }



}
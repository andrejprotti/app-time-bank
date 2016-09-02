package hackday.timecardman.data.repositories;

import org.joda.time.DateTime;

import java.sql.Time;

import hackday.timecardman.data.resources.TimeEntry;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by protti on 26/08/16.
 */
public class TimeEntryRepository {

    public void insertDate(Realm mRealm) {
        mRealm.beginTransaction();
        TimeEntry timeEntry = mRealm.createObject(TimeEntry.class);

        timeEntry.setId(mRealm.where(TimeEntry.class).max("id").intValue() + 1);
        timeEntry.setTime(DateTime.now().toDate());

        mRealm.commitTransaction();
    }

    public RealmResults<TimeEntry> getAll(Realm mRealm) {
        return mRealm.where(TimeEntry.class).findAll();
    }

}


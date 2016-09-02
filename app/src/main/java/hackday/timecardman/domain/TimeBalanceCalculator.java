package hackday.timecardman.domain;

import org.joda.time.DateTime;

import hackday.timecardman.data.repositories.TimeEntryRepository;
import hackday.timecardman.data.resources.TimeEntry;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by protti on 26/08/16.
 */
public class TimeBalanceCalculator {

    private static final int DUAS_HORAS = 840;

    public String updateBalance(Realm mRealm) {
        TimeEntryRepository timeEntryRepository = new TimeEntryRepository();

        RealmResults<TimeEntry> results = timeEntryRepository.getAll(mRealm);

        int saldo = 0;

        for (TimeEntry t : results) {
            DateTime dateTime = new DateTime(t.getTime());
            if (dateTime.getMinuteOfDay() < DUAS_HORAS) {
                saldo -= dateTime.getMinuteOfDay();
            } else {
                saldo += dateTime.getMinuteOfDay();
                saldo -= 9 * 60;
            }
        }

        int saldoHoras = saldo / 60;

        int saldoMinutos = saldo % 60;

        return String.valueOf(saldoHoras) + "h" + String.valueOf(saldoMinutos) + "m";
    }


}

package hackday.timecardman.presentation.views;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bluelinelabs.conductor.Controller;
import com.rey.material.widget.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hackday.timecardman.R;
import hackday.timecardman.data.repositories.TimeEntryRepository;
import hackday.timecardman.domain.TimeBalanceCalculator;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by protti on 26/08/16.
 */
public class HomeController extends Controller {

    private TimeEntryRepository timeEntryRepository;
    private TimeBalanceCalculator timeBalanceCalculator;

    private Realm mRealm;

    @BindView(R.id.fab)
    FloatingActionButton mFab;

    @BindView(R.id.time_balance)
    TextView mTimeBalance;

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflater.inflate(R.layout.home_view, container, false);

        ButterKnife.bind(this, view);
        configRealm();

        setupData();

        return view;
    }

    private void setupData() {
        timeEntryRepository = new TimeEntryRepository();
        timeBalanceCalculator = new TimeBalanceCalculator();
    }

    @OnClick(R.id.fab)
    public void onFabClick() {
        confirmDialog();
    }

    private void confirmDialog() {

        new AlertDialog.Builder(getActivity())
                .setTitle("Bater ponto")
                .setMessage("Tem certeza?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        saveData();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    private void saveData() {
        timeEntryRepository.insertDate(mRealm);
        mTimeBalance.setText(timeBalanceCalculator.updateBalance(mRealm));
    }


    private void configRealm() {
        RealmConfiguration config = new RealmConfiguration.Builder(getActivity())
                .deleteRealmIfMigrationNeeded()
                .build();
        mRealm = Realm.getInstance(config);
    }


}

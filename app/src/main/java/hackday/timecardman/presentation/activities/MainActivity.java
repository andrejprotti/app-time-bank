package hackday.timecardman.presentation.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;

import hackday.timecardman.R;
import hackday.timecardman.presentation.views.HomeController;

/**
 * Created by protti on 26/08/16.
 */
public class MainActivity  extends AppCompatActivity {

    private Router router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ViewGroup container = (ViewGroup)findViewById(R.id.controller_container);

        router = Conductor.attachRouter(this, container, savedInstanceState);

        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(new HomeController()));
        }
    }

    @Override
    public void onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed();
        }
    }

}

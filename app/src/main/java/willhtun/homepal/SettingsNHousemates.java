package willhtun.homepal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SettingsNHousemates extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_n_housemates);
        findViewById(R.id.settings_deleteHousemates_back).setVisibility(View.GONE);
    }

    public void resumeFrag() {
        findViewById(R.id.settings_deleteHousemates_back).setVisibility(View.GONE);
        findViewById(R.id.settings_deleteHousemates).setVisibility(View.VISIBLE);

        findViewById(R.id.settings_deleteHousemates_icon1).setVisibility(View.INVISIBLE);
        findViewById(R.id.settings_deleteHousemates_icon2).setVisibility(View.INVISIBLE);
        findViewById(R.id.settings_deleteHousemates_icon3).setVisibility(View.INVISIBLE);
        findViewById(R.id.settings_deleteHousemates_icon4).setVisibility(View.INVISIBLE);
        findViewById(R.id.settings_deleteHousemates_icon5).setVisibility(View.INVISIBLE);

        getSupportFragmentManager().popBackStack();
    }

    public void open_HousematesDelete(View view) {
        findViewById(R.id.settings_deleteHousemates_back).setVisibility(View.VISIBLE);
        findViewById(R.id.settings_deleteHousemates).setVisibility(View.GONE);

        findViewById(R.id.settings_deleteHousemates_icon1).setVisibility(View.VISIBLE);
        findViewById(R.id.settings_deleteHousemates_icon2).setVisibility(View.VISIBLE);
        findViewById(R.id.settings_deleteHousemates_icon3).setVisibility(View.VISIBLE);
        findViewById(R.id.settings_deleteHousemates_icon4).setVisibility(View.VISIBLE);
        findViewById(R.id.settings_deleteHousemates_icon5).setVisibility(View.VISIBLE);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.settings_window, new SettingsHousematesDelete_Frag(), "FRAG_HM_D")
                .addToBackStack(null)
                .commit();
    }

    public void close_HousematesDelete(View view) {
        findViewById(R.id.settings_deleteHousemates_back).setVisibility(View.GONE);
        findViewById(R.id.settings_deleteHousemates).setVisibility(View.VISIBLE);

        findViewById(R.id.settings_deleteHousemates_icon1).setVisibility(View.INVISIBLE);
        findViewById(R.id.settings_deleteHousemates_icon2).setVisibility(View.INVISIBLE);
        findViewById(R.id.settings_deleteHousemates_icon3).setVisibility(View.INVISIBLE);
        findViewById(R.id.settings_deleteHousemates_icon4).setVisibility(View.INVISIBLE);
        findViewById(R.id.settings_deleteHousemates_icon5).setVisibility(View.INVISIBLE);

        getSupportFragmentManager().popBackStack();

        ((SettingsHousemates_Frag) getSupportFragmentManager().findFragmentByTag("FRAG_HM")).refreshHousemates();
    }
}

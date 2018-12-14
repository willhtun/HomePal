package willhtun.homepal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SettingsNCustomBills extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_n_custom_bills);
        findViewById(R.id.settings_deleteCustoms_back).setVisibility(View.GONE);
    }

    public void resumeFrag() {
        findViewById(R.id.settings_deleteCustoms_back).setVisibility(View.GONE);
        findViewById(R.id.settings_deleteCustoms).setVisibility(View.VISIBLE);

        findViewById(R.id.settings_deleteCustoms_icon1).setVisibility(View.INVISIBLE);
        findViewById(R.id.settings_deleteCustoms_icon2).setVisibility(View.INVISIBLE);
        findViewById(R.id.settings_deleteCustoms_icon3).setVisibility(View.INVISIBLE);
        findViewById(R.id.settings_deleteCustoms_icon4).setVisibility(View.INVISIBLE);
        findViewById(R.id.settings_deleteCustoms_icon5).setVisibility(View.INVISIBLE);

        getSupportFragmentManager().popBackStack();
    }

    public void open_CustomsDelete(View view) {
        findViewById(R.id.settings_deleteCustoms_back).setVisibility(View.VISIBLE);
        findViewById(R.id.settings_deleteCustoms).setVisibility(View.GONE);

        findViewById(R.id.settings_deleteCustoms_icon1).setVisibility(View.VISIBLE);
        findViewById(R.id.settings_deleteCustoms_icon2).setVisibility(View.VISIBLE);
        findViewById(R.id.settings_deleteCustoms_icon3).setVisibility(View.VISIBLE);
        findViewById(R.id.settings_deleteCustoms_icon4).setVisibility(View.VISIBLE);
        findViewById(R.id.settings_deleteCustoms_icon5).setVisibility(View.VISIBLE);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.settings_window, new SettingsCustomBillsDelete_Frag())
                .addToBackStack(null)
                .commit();
    }

    public void close_CustomsDelete(View view) {
        findViewById(R.id.settings_deleteCustoms_back).setVisibility(View.GONE);
        findViewById(R.id.settings_deleteCustoms).setVisibility(View.VISIBLE);

        findViewById(R.id.settings_deleteCustoms_icon1).setVisibility(View.INVISIBLE);
        findViewById(R.id.settings_deleteCustoms_icon2).setVisibility(View.INVISIBLE);
        findViewById(R.id.settings_deleteCustoms_icon3).setVisibility(View.INVISIBLE);
        findViewById(R.id.settings_deleteCustoms_icon4).setVisibility(View.INVISIBLE);
        findViewById(R.id.settings_deleteCustoms_icon5).setVisibility(View.INVISIBLE);

        getSupportFragmentManager().popBackStack();
    }
}

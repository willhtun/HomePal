package willhtun.homepal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SettingsNAddBills extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_n_add_bills);
    }

    public void goToActivityCustomBills() {
        Intent intent_opencustombills = new Intent(this, SettingsNCustomBills.class);
        startActivity(intent_opencustombills);
    }
}

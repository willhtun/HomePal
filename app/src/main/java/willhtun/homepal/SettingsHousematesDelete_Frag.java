package willhtun.homepal;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class SettingsHousematesDelete_Frag extends PreferenceFragmentCompat {

    private SharedPreferences prefs;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState,
                                    String rootKey) {
        setPreferencesFromResource(R.xml.pref_housemates_delete, rootKey);

        findPreference("name_preference_housemates1_delete").setIconSpaceReserved(false);
        findPreference("name_preference_housemates2_delete").setIconSpaceReserved(false);
        findPreference("name_preference_housemates3_delete").setIconSpaceReserved(false);
        findPreference("name_preference_housemates4_delete").setIconSpaceReserved(false);
        findPreference("name_preference_housemates5_delete").setIconSpaceReserved(false);

        findPreference("name_preference_housemates1_delete").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                prefs.edit().putBoolean("housemate1_On", false).putString("name_preference_housemates1", "").apply();
                return true;
            }
        });
        findPreference("name_preference_housemates2_delete").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                prefs.edit().putBoolean("housemate2_On", false).putString("name_preference_housemates2", "").apply();
                return true;
            }
        });
        findPreference("name_preference_housemates3_delete").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                prefs.edit().putBoolean("housemate3_On", false).putString("name_preference_housemates3", "").apply();
                return true;
            }
        });
        findPreference("name_preference_housemates4_delete").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                prefs.edit().putBoolean("housemate4_On", false).putString("name_preference_housemates4", "").apply();
                return true;
            }
        });
        findPreference("name_preference_housemates5_delete").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                prefs.edit().putBoolean("housemate5_On", false).putString("name_preference_housemates5", "").apply();
                return true;
            }
        });

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        prefs.registerOnSharedPreferenceChangeListener(listener);

        refreshHousemates();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((SettingsNHousemates) getActivity()).resumeFrag();
    }

    SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
            refreshHousemates();
        }
    };

    private void refreshHousemates() {// not empty
        findPreference("name_preference_housemates1_delete").setTitle(prefs.getString("name_preference_housemates1", "Error"));
        findPreference("name_preference_housemates2_delete").setTitle(prefs.getString("name_preference_housemates2", "Error"));
        findPreference("name_preference_housemates3_delete").setTitle(prefs.getString("name_preference_housemates3", "Error"));
        findPreference("name_preference_housemates4_delete").setTitle(prefs.getString("name_preference_housemates4", "Error"));
        findPreference("name_preference_housemates5_delete").setTitle(prefs.getString("name_preference_housemates5", "Error"));

        findPreference("name_preference_housemates1_delete").setShouldDisableView(false);
        findPreference("name_preference_housemates2_delete").setShouldDisableView(false);
        findPreference("name_preference_housemates3_delete").setShouldDisableView(false);
        findPreference("name_preference_housemates4_delete").setShouldDisableView(false);
        findPreference("name_preference_housemates5_delete").setShouldDisableView(false);

        if (!prefs.getBoolean("housemate1_On", false)) {
            findPreference("name_preference_housemates1_delete").setTitle("Tap to add housemate");
            findPreference("name_preference_housemates1_delete").setSummary("");
            findPreference("name_preference_housemates1_delete").setShouldDisableView(true);
            findPreference("name_preference_housemates1_delete").setEnabled(false);
        }
        if (!prefs.getBoolean("housemate2_On", false)) {
            findPreference("name_preference_housemates2_delete").setTitle("Tap to add housemate");
            findPreference("name_preference_housemates2_delete").setSummary("");
            findPreference("name_preference_housemates2_delete").setShouldDisableView(true);
            findPreference("name_preference_housemates2_delete").setEnabled(false);
        }
        if (!prefs.getBoolean("housemate3_On", false)) {
            findPreference("name_preference_housemates3_delete").setTitle("Tap to add housemate");
            findPreference("name_preference_housemates3_delete").setSummary("");
            findPreference("name_preference_housemates3_delete").setShouldDisableView(true);
            findPreference("name_preference_housemates3_delete").setEnabled(false);
        }
        if (!prefs.getBoolean("housemate4_On", false)) {
            findPreference("name_preference_housemates4_delete").setTitle("Tap to add housemate");
            findPreference("name_preference_housemates4_delete").setSummary("");
            findPreference("name_preference_housemates4_delete").setShouldDisableView(true);
            findPreference("name_preference_housemates4_delete").setEnabled(false);
        }
        if (!prefs.getBoolean("housemate5_On", false)) {
            findPreference("name_preference_housemates5_delete").setTitle("Tap to add housemate");
            findPreference("name_preference_housemates5_delete").setSummary("");
            findPreference("name_preference_housemates5_delete").setShouldDisableView(true);
            findPreference("name_preference_housemates5_delete").setEnabled(false);
        }
    }
}

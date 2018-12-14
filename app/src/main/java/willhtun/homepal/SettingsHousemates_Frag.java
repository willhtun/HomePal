package willhtun.homepal;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.preference.PreferenceFragmentCompat;

public class SettingsHousemates_Frag extends PreferenceFragmentCompat {

    private SharedPreferences prefs;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState,
                                    String rootKey) {
        setPreferencesFromResource(R.xml.pref_housemates, rootKey);

        findPreference("name_preference_housemates1").setIconSpaceReserved(false);
        findPreference("name_preference_housemates2").setIconSpaceReserved(false);
        findPreference("name_preference_housemates3").setIconSpaceReserved(false);
        findPreference("name_preference_housemates4").setIconSpaceReserved(false);
        findPreference("name_preference_housemates5").setIconSpaceReserved(false);

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        prefs.registerOnSharedPreferenceChangeListener(listener);

        refreshHousemates();
    }

    SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
            refreshHousemates();
            refreshAliveStatus(key);
        }
    };

    private void refreshAliveStatus(String k) {
        switch (k) {
            case "name_preference_housemates1":
                prefs.edit().putBoolean("housemate1_On", true).apply();
                break;
            case "name_preference_housemates2":
                prefs.edit().putBoolean("housemate2_On", true).apply();
                break;
            case "name_preference_housemates3":
                prefs.edit().putBoolean("housemate3_On", true).apply();
                break;
            case "name_preference_housemates4":
                prefs.edit().putBoolean("housemate4_On", true).apply();
                break;
            case "name_preference_housemates5":
                prefs.edit().putBoolean("housemate5_On", true).apply();
                break;
            default:
                break;
        }
    }

    private void refreshHousemates() {// not empty
        findPreference("name_preference_housemates1").setTitle(prefs.getString("name_preference_housemates1", "Error"));
        findPreference("name_preference_housemates2").setTitle(prefs.getString("name_preference_housemates2", "Error"));
        findPreference("name_preference_housemates3").setTitle(prefs.getString("name_preference_housemates3", "Error"));
        findPreference("name_preference_housemates4").setTitle(prefs.getString("name_preference_housemates4", "Error"));
        findPreference("name_preference_housemates5").setTitle(prefs.getString("name_preference_housemates5", "Error"));

        if (prefs.getString("name_preference_housemates1", "").equals("")) {
            prefs.edit().putBoolean("housemate1_On", false).apply();
        }
        if (prefs.getString("name_preference_housemates2", "").equals("")) {
            prefs.edit().putBoolean("housemate2_On", false).apply();
        }
        if (prefs.getString("name_preference_housemates3", "").equals("")) {
            prefs.edit().putBoolean("housemate3_On", false).apply();
        }
        if (prefs.getString("name_preference_housemates4", "").equals("")) {
            prefs.edit().putBoolean("housemate4_On", false).apply();
        }
        if (prefs.getString("name_preference_housemates5", "").equals("")) {
            prefs.edit().putBoolean("housemate5_On", false).apply();
        }
        
        if (!prefs.getBoolean("housemate1_On", false)) {
            findPreference("name_preference_housemates1").setTitle("Tap to add housemate");
            findPreference("name_preference_housemates1").setSummary("");
        }
        if (!prefs.getBoolean("housemate2_On", false)) {
            findPreference("name_preference_housemates2").setTitle("Tap to add housemate");
            findPreference("name_preference_housemates2").setSummary("");
        }
        if (!prefs.getBoolean("housemate3_On", false)) {
            findPreference("name_preference_housemates3").setTitle("Tap to add housemate");
            findPreference("name_preference_housemates3").setSummary("");
        }
        if (!prefs.getBoolean("housemate4_On", false)) {
            findPreference("name_preference_housemates4").setTitle("Tap to add housemate");
            findPreference("name_preference_housemates4").setSummary("");
        }
        if (!prefs.getBoolean("housemate5_On", false)) {
            findPreference("name_preference_housemates5").setTitle("Tap to add housemate");
            findPreference("name_preference_housemates5").setSummary("");
        }
    }
}

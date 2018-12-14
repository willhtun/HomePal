package willhtun.homepal;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.View;

public class SettingsCustomBills_Frag extends PreferenceFragmentCompat {

    private SharedPreferences prefs;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState,
                                    String rootKey) {
        setPreferencesFromResource(R.xml.pref_custombills, rootKey);

        findPreference("name_preference_bills_custom1").setIconSpaceReserved(false);
        findPreference("name_preference_bills_custom2").setIconSpaceReserved(false);
        findPreference("name_preference_bills_custom3").setIconSpaceReserved(false);
        findPreference("name_preference_bills_custom4").setIconSpaceReserved(false);
        findPreference("name_preference_bills_custom5").setIconSpaceReserved(false);

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        prefs.registerOnSharedPreferenceChangeListener(listener);

        refreshBills();
    }

    SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
            refreshBills();
            refreshAliveStatus(key);
        }
    };

    private void refreshAliveStatus(String k) {
        switch (k) {
            case "name_preference_bills_custom1":
                prefs.edit().putBoolean("custombill1_On", true).apply();
                break;
            case "name_preference_bills_custom2":
                prefs.edit().putBoolean("custombill2_On", true).apply();
                break;
            case "name_preference_bills_custom3":
                prefs.edit().putBoolean("custombill3_On", true).apply();
                break;
            case "name_preference_bills_custom4":
                prefs.edit().putBoolean("custombill4_On", true).apply();
                break;
            case "name_preference_bills_custom5":
                prefs.edit().putBoolean("custombill5_On", true).apply();
                break;
            default:
                break;
        }
    }

    private void refreshBills() {// not empty
        findPreference("name_preference_bills_custom1").setTitle(prefs.getString("name_preference_bills_custom1", "Error"));
        findPreference("name_preference_bills_custom2").setTitle(prefs.getString("name_preference_bills_custom2", "Error"));
        findPreference("name_preference_bills_custom3").setTitle(prefs.getString("name_preference_bills_custom3", "Error"));
        findPreference("name_preference_bills_custom4").setTitle(prefs.getString("name_preference_bills_custom4", "Error"));
        findPreference("name_preference_bills_custom5").setTitle(prefs.getString("name_preference_bills_custom5", "Error"));

        if (prefs.getString("name_preference_bills_custom1", "").equals("")) {
            prefs.edit().putBoolean("custombill1_On", false).apply();
        }
        if (prefs.getString("name_preference_bills_custom2", "").equals("")) {
            prefs.edit().putBoolean("custombill2_On", false).apply();
        }
        if (prefs.getString("name_preference_bills_custom3", "").equals("")) {
            prefs.edit().putBoolean("custombille3_On", false).apply();
        }
        if (prefs.getString("name_preference_bills_custom4", "").equals("")) {
            prefs.edit().putBoolean("custombill4_On", false).apply();
        }
        if (prefs.getString("name_preference_bills_custom5", "").equals("")) {
            prefs.edit().putBoolean("custombill5_On", false).apply();
        }

        if (!prefs.getBoolean("custombill1_On", false)) {
            findPreference("name_preference_bills_custom1").setTitle("Tap to add custom bill");
            findPreference("name_preference_bills_custom1").setSummary("");
        }
        if (!prefs.getBoolean("custombill2_On", false)) {
            findPreference("name_preference_bills_custom2").setTitle("Tap to add custom bill");
            findPreference("name_preference_bills_custom2").setSummary("");
        }
        if (!prefs.getBoolean("custombille3_On", false)) {
            findPreference("name_preference_bills_custom3").setTitle("Tap to add custom bill");
            findPreference("name_preference_bills_custom3").setSummary("");
        }
        if (!prefs.getBoolean("custombill4_On", false)) {
            findPreference("name_preference_bills_custom4").setTitle("Tap to add custom bill");
            findPreference("name_preference_bills_custom4").setSummary("");
        }
        if (!prefs.getBoolean("custombill5_On", false)) {
            findPreference("name_preference_bills_custom5").setTitle("Tap to add custom bill");
            findPreference("name_preference_bills_custom5").setSummary("");
        }
    }
}

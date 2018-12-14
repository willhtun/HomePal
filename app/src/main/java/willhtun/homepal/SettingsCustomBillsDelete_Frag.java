package willhtun.homepal;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.View;

public class SettingsCustomBillsDelete_Frag extends PreferenceFragmentCompat {

    private SharedPreferences prefs;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState,
                                    String rootKey) {
        setPreferencesFromResource(R.xml.pref_custombills_delete, rootKey);

        findPreference("name_preference_bills_custom1_delete").setIconSpaceReserved(false);
        findPreference("name_preference_bills_custom2_delete").setIconSpaceReserved(false);
        findPreference("name_preference_bills_custom3_delete").setIconSpaceReserved(false);
        findPreference("name_preference_bills_custom4_delete").setIconSpaceReserved(false);
        findPreference("name_preference_bills_custom5_delete").setIconSpaceReserved(false);

        findPreference("name_preference_bills_custom1_delete").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                prefs.edit().putBoolean("custombill1_On", false).putString("name_preference_bills_custom1", "").apply();
                return true;
            }
        });
        findPreference("name_preference_bills_custom2_delete").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                prefs.edit().putBoolean("custombill2_On", false).putString("name_preference_bills_custom2", "").apply();
                return true;
            }
        });
        findPreference("name_preference_bills_custom3_delete").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                prefs.edit().putBoolean("custombill3_On", false).putString("name_preference_bills_custom3", "").apply();
                return true;
            }
        });
        findPreference("name_preference_bills_custom4_delete").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                prefs.edit().putBoolean("custombill4_On", false).putString("name_preference_bills_custom4", "").apply();
                return true;
            }
        });
        findPreference("name_preference_bills_custom5_delete").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                prefs.edit().putBoolean("custombill5_On", false).putString("name_preference_bills_custom5", "").apply();
                return true;
            }
        });

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        prefs.registerOnSharedPreferenceChangeListener(listener);

        refreshBills();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((SettingsNCustomBills) getActivity()).resumeFrag();
    }

    SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
            refreshBills();
        }
    };

    private void refreshBills() {// not empty
        findPreference("name_preference_bills_custom1_delete").setTitle(prefs.getString("name_preference_bills_custom1", "Error"));
        findPreference("name_preference_bills_custom2_delete").setTitle(prefs.getString("name_preference_bills_custom2", "Error"));
        findPreference("name_preference_bills_custom3_delete").setTitle(prefs.getString("name_preference_bills_custom3", "Error"));
        findPreference("name_preference_bills_custom4_delete").setTitle(prefs.getString("name_preference_bills_custom4", "Error"));
        findPreference("name_preference_bills_custom5_delete").setTitle(prefs.getString("name_preference_bills_custom5", "Error"));

        findPreference("name_preference_bills_custom1_delete").setShouldDisableView(false);
        findPreference("name_preference_bills_custom2_delete").setShouldDisableView(false);
        findPreference("name_preference_bills_custom3_delete").setShouldDisableView(false);
        findPreference("name_preference_bills_custom4_delete").setShouldDisableView(false);
        findPreference("name_preference_bills_custom5_delete").setShouldDisableView(false);

        if (!prefs.getBoolean("custombill1_On", false)) {
            findPreference("name_preference_bills_custom1_delete").setTitle("Tap to add custom bill");
            findPreference("name_preference_bills_custom1_delete").setSummary("");
            findPreference("name_preference_bills_custom1_delete").setShouldDisableView(true);
            findPreference("name_preference_bills_custom1_delete").setEnabled(false);
        }
        if (!prefs.getBoolean("custombill2_On", false)) {
            findPreference("name_preference_bills_custom2_delete").setTitle("Tap to add custom bill");
            findPreference("name_preference_bills_custom2_delete").setSummary("");
            findPreference("name_preference_bills_custom2_delete").setShouldDisableView(true);
            findPreference("name_preference_bills_custom2_delete").setEnabled(false);
        }
        if (!prefs.getBoolean("custombill3_On", false)) {
            findPreference("name_preference_bills_custom3_delete").setTitle("Tap to add custom bill");
            findPreference("name_preference_bills_custom3_delete").setSummary("");
            findPreference("name_preference_bills_custom3_delete").setShouldDisableView(true);
            findPreference("name_preference_bills_custom3_delete").setEnabled(false);
        }
        if (!prefs.getBoolean("custombill4_On", false)) {
            findPreference("name_preference_bills_custom4_delete").setTitle("Tap to add custom bill");
            findPreference("name_preference_bills_custom4_delete").setSummary("");
            findPreference("name_preference_bills_custom4_delete").setShouldDisableView(true);
            findPreference("name_preference_bills_custom4_delete").setEnabled(false);
        }
        if (!prefs.getBoolean("custombill5_On", false)) {
            findPreference("name_preference_bills_custom5_delete").setTitle("Tap to add custom bill");
            findPreference("name_preference_bills_custom5_delete").setSummary("");
            findPreference("name_preference_bills_custom5_delete").setShouldDisableView(true);
            findPreference("name_preference_bills_custom5_delete").setEnabled(false);
        }
    }

}

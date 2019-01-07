package willhtun.homepal;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsMain_Frag extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState,
                                    String rootKey) {
        setPreferencesFromResource(R.xml.pref_main, rootKey);

        findPreference("settingsopen_otherbills").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ((SettingsN) getActivity()).goToActivityAddBills();
                return true;
            }
        });

        findPreference("settingsopen_duedatebills").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ((SettingsN) getActivity()).goToActivityDueDates();
                return true;
            }
        });

        findPreference("settingsopen_reminder").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ((SettingsN) getActivity()).show_reminderPicker();
                return true;
            }
        });

        findPreference("settingsopen_billSharing").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ((SettingsN) getActivity()).goToActivityBillSharing();
                return true;
            }
        });

        findPreference("settingsopen_housemates").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ((SettingsN) getActivity()).goToActivityHousemates();
                return true;
            }
        });

        findPreference("settingsopen_otherbills").setIconSpaceReserved(false);
        findPreference("settingsopen_duedatebills").setIconSpaceReserved(false);
        findPreference("settings_notificationOn").setIconSpaceReserved(false);
        findPreference("settingsopen_reminder").setIconSpaceReserved(false);
        findPreference("settingsopen_billSharing").setIconSpaceReserved(false);
        findPreference("settingsopen_housemates").setIconSpaceReserved(false);
        findPreference("settings_feedback").setIconSpaceReserved(false);
        findPreference("settings_version").setIconSpaceReserved(false);
    }
}

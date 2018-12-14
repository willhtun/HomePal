package willhtun.homepal;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsOtherBills_Frag extends PreferenceFragmentCompat {

    private SharedPreferences prefs;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState,
                                    String rootKey) {
        setPreferencesFromResource(R.xml.pref_otherbills, rootKey);

        findPreference("settings_open_custombills").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ((SettingsNAddBills) getActivity()).goToActivityCustomBills();
                return true;
            }
        });

        findPreference("check_box_preference_bills_rent").setIconSpaceReserved(false);
        findPreference("check_box_preference_bills_car").setIconSpaceReserved(false);
        findPreference("check_box_preference_bills_internet").setIconSpaceReserved(false);
        findPreference("check_box_preference_bills_mobile").setIconSpaceReserved(false);
        findPreference("check_box_preference_bills_electricity").setIconSpaceReserved(false);
        findPreference("check_box_preference_bills_water").setIconSpaceReserved(false);
        findPreference("check_box_preference_bills_gas").setIconSpaceReserved(false);
        findPreference("check_box_preference_bills_trash").setIconSpaceReserved(false);
        findPreference("check_box_preference_bills_groceries").setIconSpaceReserved(false);

        /*
        findPreference("check_box_preference_bills_custom1").setIconSpaceReserved(false);
        findPreference("check_box_preference_bills_custom2").setIconSpaceReserved(false);
        findPreference("check_box_preference_bills_custom3").setIconSpaceReserved(false);
        findPreference("check_box_preference_bills_custom4").setIconSpaceReserved(false);
        findPreference("check_box_preference_bills_custom5").setIconSpaceReserved(false);

        findPreference("name_preference_bills_custom1").setIconSpaceReserved(false);
        findPreference("name_preference_bills_custom2").setIconSpaceReserved(false);
        findPreference("name_preference_bills_custom3").setIconSpaceReserved(false);
        findPreference("name_preference_bills_custom4").setIconSpaceReserved(false);
        findPreference("name_preference_bills_custom5").setIconSpaceReserved(false);
*/
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        prefs.registerOnSharedPreferenceChangeListener(listener);

        // refreshNames();

    }


    SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
           // refreshNames();
        }
    };

    private void refreshNames() {
        findPreference("check_box_preference_bills_custom1").setTitle(prefs.getString("name_preference_bills_custom1", "Custom 1"));
        findPreference("check_box_preference_bills_custom2").setTitle(prefs.getString("name_preference_bills_custom2", "Custom 2"));
        findPreference("check_box_preference_bills_custom3").setTitle(prefs.getString("name_preference_bills_custom3", "Custom 3"));
        findPreference("check_box_preference_bills_custom4").setTitle(prefs.getString("name_preference_bills_custom4", "Custom 4"));
        findPreference("check_box_preference_bills_custom5").setTitle(prefs.getString("name_preference_bills_custom5", "Custom 5"));

        if (prefs.getString("name_preference_bills_custom1", "Custom 1").equals(""))
            findPreference("check_box_preference_bills_custom1").setTitle("Custom 1");
        if (prefs.getString("name_preference_bills_custom2", "Custom 2").equals(""))
            findPreference("check_box_preference_bills_custom2").setTitle("Custom 2");
        if (prefs.getString("name_preference_bills_custom3", "Custom 3").equals(""))
            findPreference("check_box_preference_bills_custom3").setTitle("Custom 3");
        if (prefs.getString("name_preference_bills_custom4", "Custom 4").equals(""))
            findPreference("check_box_preference_bills_custom4").setTitle("Custom 4");
        if (prefs.getString("name_preference_bills_custom5", "Custom 5").equals(""))
            findPreference("check_box_preference_bills_custom5").setTitle("Custom 5");
    }


}

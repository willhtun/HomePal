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
public class SettingsDueDateBills_Frag extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState,
                                    String rootKey) {
        setPreferencesFromResource(R.xml.pref_duedatebills, rootKey);

        findPreference("settingsdialog_rent").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ((SettingsNDueDates) getActivity()).openDialog(0);
                return true;
            }
        });
        findPreference("settingsdialog_car").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ((SettingsNDueDates) getActivity()).openDialog(1);
                return true;
            }
        });
        findPreference("settingsdialog_internet").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ((SettingsNDueDates) getActivity()).openDialog(2);
                return true;
            }
        });
        findPreference("settingsdialog_mobile").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ((SettingsNDueDates) getActivity()).openDialog(3);
                return true;
            }
        });
        findPreference("settingsdialog_electricity").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ((SettingsNDueDates) getActivity()).openDialog(4);
                return true;
            }
        });
        findPreference("settingsdialog_water").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ((SettingsNDueDates) getActivity()).openDialog(5);
                return true;
            }
        });
        findPreference("settingsdialog_gas").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ((SettingsNDueDates) getActivity()).openDialog(6);
                return true;
            }
        });
        findPreference("settingsdialog_trash").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ((SettingsNDueDates) getActivity()).openDialog(7);
                return true;
            }
        });
        findPreference("settingsdialog_custom1").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ((SettingsNDueDates) getActivity()).openDialog(8);
                return true;
            }
        });
        findPreference("settingsdialog_custom2").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ((SettingsNDueDates) getActivity()).openDialog(9);
                return true;
            }
        });
        findPreference("settingsdialog_custom3").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ((SettingsNDueDates) getActivity()).openDialog(10);
                return true;
            }
        });
        findPreference("settingsdialog_custom4").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ((SettingsNDueDates) getActivity()).openDialog(11);
                return true;
            }
        });
        findPreference("settingsdialog_custom5").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ((SettingsNDueDates) getActivity()).openDialog(12);
                return true;
            }
        });

        findPreference("settingsdialog_rent").setSummary(((SettingsNDueDates)getActivity()).rentDueDate + " day of each month");
        findPreference("settingsdialog_car").setSummary(((SettingsNDueDates)getActivity()).carDueDate + " day of each month");
        findPreference("settingsdialog_internet").setSummary(((SettingsNDueDates)getActivity()).internetDueDate + " day of each month");
        findPreference("settingsdialog_mobile").setSummary(((SettingsNDueDates)getActivity()).mobileDueDate + " day of each month");
        findPreference("settingsdialog_electricity").setSummary(((SettingsNDueDates)getActivity()).electricityDueDate + " day of each month");
        findPreference("settingsdialog_water").setSummary(((SettingsNDueDates)getActivity()).waterDueDate + " day of each month");
        findPreference("settingsdialog_gas").setSummary(((SettingsNDueDates)getActivity()).gasDueDate + " day of each month");
        findPreference("settingsdialog_trash").setSummary(((SettingsNDueDates)getActivity()).trashDueDate + " day of each month");

        findPreference("settingsdialog_custom1").setSummary(((SettingsNDueDates)getActivity()).custom1DueDate + " day of each month");
        findPreference("settingsdialog_custom2").setSummary(((SettingsNDueDates)getActivity()).custom2DueDate + " day of each month");
        findPreference("settingsdialog_custom3").setSummary(((SettingsNDueDates)getActivity()).custom3DueDate + " day of each month");
        findPreference("settingsdialog_custom4").setSummary(((SettingsNDueDates)getActivity()).custom4DueDate + " day of each month");
        findPreference("settingsdialog_custom5").setSummary(((SettingsNDueDates)getActivity()).custom5DueDate + " day of each month");

        findPreference("settingsdialog_rent").setIconSpaceReserved(false);
        findPreference("settingsdialog_car").setIconSpaceReserved(false);
        findPreference("settingsdialog_internet").setIconSpaceReserved(false);
        findPreference("settingsdialog_mobile").setIconSpaceReserved(false);
        findPreference("settingsdialog_electricity").setIconSpaceReserved(false);
        findPreference("settingsdialog_water").setIconSpaceReserved(false);
        findPreference("settingsdialog_gas").setIconSpaceReserved(false);
        findPreference("settingsdialog_trash").setIconSpaceReserved(false);
        findPreference("settingsdialog_custom1").setIconSpaceReserved(false);
        findPreference("settingsdialog_custom2").setIconSpaceReserved(false);
        findPreference("settingsdialog_custom3").setIconSpaceReserved(false);
        findPreference("settingsdialog_custom4").setIconSpaceReserved(false);
        findPreference("settingsdialog_custom5").setIconSpaceReserved(false);

        loadVisibility();
    }

    public void loadVisibility() {
        boolean empty = true;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        findPreference("settingsdialog_rent").setVisible(false);
        findPreference("settingsdialog_car").setVisible(false);
        findPreference("settingsdialog_internet").setVisible(false);
        findPreference("settingsdialog_mobile").setVisible(false);
        findPreference("settingsdialog_electricity").setVisible(false);
        findPreference("settingsdialog_water").setVisible(false);
        findPreference("settingsdialog_gas").setVisible(false);
        findPreference("settingsdialog_trash").setVisible(false);
        findPreference("settingsdialog_custom1").setVisible(false);
        findPreference("settingsdialog_custom2").setVisible(false);
        findPreference("settingsdialog_custom3").setVisible(false);
        findPreference("settingsdialog_custom4").setVisible(false);
        findPreference("settingsdialog_custom5").setVisible(false);

        if (preferences.getBoolean("check_box_preference_bills_rent", false)) {
            findPreference("settingsdialog_rent").setVisible(true);
            empty = false;
        }
        if (preferences.getBoolean("check_box_preference_bills_car", false)) {
            findPreference("settingsdialog_car").setVisible(true);
            empty = false;
        }
        if (preferences.getBoolean("check_box_preference_bills_internet", false)) {
            findPreference("settingsdialog_internet").setVisible(true);
            empty = false;
        }
        if (preferences.getBoolean("check_box_preference_bills_mobile", false)) {
            findPreference("settingsdialog_mobile").setVisible(true);
            empty = false;
        }
        if (preferences.getBoolean("check_box_preference_bills_electricity", false)) {
            findPreference("settingsdialog_electricity").setVisible(true);
            empty = false;
        }
        if (preferences.getBoolean("check_box_preference_bills_water", false)) {
            findPreference("settingsdialog_water").setVisible(true);
            empty = false;
        }
        if (preferences.getBoolean("check_box_preference_bills_gas", false)) {
            findPreference("settingsdialog_gas").setVisible(true);
            empty = false;
        }
        if (preferences.getBoolean("check_box_preference_bills_trash", false)) {
            findPreference("settingsdialog_trash").setVisible(true);
            empty = false;
        }
        if (preferences.getBoolean("custombill1_On", false)) {
            if (preferences.getString("name_preference_bills_custom1", "Custom 1").equals(""))
                findPreference("settingsdialog_custom1").setTitle("Custom 1");
            else
                findPreference("settingsdialog_custom1").setTitle(preferences.getString("name_preference_bills_custom1", "Custom 1"));
            findPreference("settingsdialog_custom1").setVisible(true);
            empty = false;
        }
        if (preferences.getBoolean("custombill2_On", false)) {
            if (preferences.getString("name_preference_bills_custom2", "Custom 2").equals(""))
                findPreference("settingsdialog_custom2").setTitle("Custom 2");
            else
                findPreference("settingsdialog_custom2").setTitle(preferences.getString("name_preference_bills_custom2", "Custom 2"));
            findPreference("settingsdialog_custom2").setVisible(true);
            empty = false;
        }
        if (preferences.getBoolean("custombill3_On", false)) {
            if (preferences.getString("name_preference_bills_custom3", "Custom 3").equals(""))
                findPreference("settingsdialog_custom3").setTitle("Custom 3");
            else
                findPreference("settingsdialog_custom3").setTitle(preferences.getString("name_preference_bills_custom3", "Custom 3"));
            findPreference("settingsdialog_custom3").setVisible(true);
            empty = false;
        }
        if (preferences.getBoolean("custombill4_On", false)) {
            if (preferences.getString("name_preference_bills_custom4", "Custom 4").equals(""))
                findPreference("settingsdialog_custom4").setTitle("Custom 4");
            else
                findPreference("settingsdialog_custom4").setTitle(preferences.getString("name_preference_bills_custom4", "Custom 4"));
            findPreference("settingsdialog_custom4").setVisible(true);
            empty = false;
        }
        if (preferences.getBoolean("custombill5_On", false)) {
            if (preferences.getString("name_preference_bills_custom5", "Custom 5").equals(""))
                findPreference("settingsdialog_custom5").setTitle("Custom 5");
            else
                findPreference("settingsdialog_custom5").setTitle(preferences.getString("name_preference_bills_custom5", "Custom 5"));

            findPreference("settingsdialog_custom5").setVisible(true);
            empty = false;
        }
    }

}

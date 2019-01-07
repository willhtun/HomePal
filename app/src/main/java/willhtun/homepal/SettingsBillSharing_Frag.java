package willhtun.homepal;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class SettingsBillSharing_Frag extends PreferenceFragmentCompat {

    private SharedPreferences prefs;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState,
                                    String rootKey) {
        setPreferencesFromResource(R.xml.pref_billsharing, rootKey);

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        for (int z = 1; z < 6; z++) {
            if (prefs.getString("name_preference_bills_custom" + z, "").equals(""))
                findPreference("settings_billSharingOn_custom" + z).setTitle("Custom Bill " + z);
            else
                findPreference("settings_billSharingOn_custom" + z).setTitle(prefs.getString("name_preference_bills_custom" + z, "Custom Bill " + z));
        }

        loadVisibility();
    }

    public void loadVisibility() {
        boolean empty = true;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        if (preferences.getBoolean("check_box_preference_bills_rent", false)) {
            findPreference("settings_billSharingOn_rent").setVisible(true);
            empty = false;
        }
        else {
            findPreference("settings_billSharingOn_rent").setVisible(false);
        }
        if (preferences.getBoolean("check_box_preference_bills_car", false)) {
            findPreference("settings_billSharingOn_car").setVisible(true);
            empty = false;
        }
        else {
            findPreference("settings_billSharingOn_car").setVisible(false);
        }
        if (preferences.getBoolean("check_box_preference_bills_internet", false)) {
            findPreference("settings_billSharingOn_internet").setVisible(true);
            empty = false;
        }
        else {
            findPreference("settings_billSharingOn_internet").setVisible(false);
        }
        if (preferences.getBoolean("check_box_preference_bills_mobile", false)) {
            findPreference("settings_billSharingOn_mobile").setVisible(true);
            empty = false;
        }
        else {
            findPreference("settings_billSharingOn_mobile").setVisible(false);
        }
        if (preferences.getBoolean("check_box_preference_bills_electricity", false)) {
            findPreference("settings_billSharingOn_electricity").setVisible(true);
            empty = false;
        }
        else {
            findPreference("settings_billSharingOn_electricity").setVisible(false);
        }
        if (preferences.getBoolean("check_box_preference_bills_water", false)) {
            findPreference("settings_billSharingOn_water").setVisible(true);
            empty = false;
        }
        else {
            findPreference("settings_billSharingOn_water").setVisible(false);
        }
        if (preferences.getBoolean("check_box_preference_bills_gas", false)) {
            findPreference("settings_billSharingOn_gas").setVisible(true);
            empty = false;
        }
        else {
            findPreference("settings_billSharingOn_gas").setVisible(false);
        }
        if (preferences.getBoolean("check_box_preference_bills_trash", false)) {
            findPreference("settings_billSharingOn_trash").setVisible(true);
            empty = false;
        }
        else {
            findPreference("settings_billSharingOn_trash").setVisible(false);
        }
        if (preferences.getBoolean("custombill1_On", false)) {
            if (preferences.getString("name_preference_bills_custom1", "Custom 1").equals(""))
                findPreference("settings_billSharingOn_custom1").setTitle("Custom 1");
            else
                findPreference("settings_billSharingOn_custom1").setTitle(preferences.getString("name_preference_bills_custom1", "Custom 1"));
            findPreference("settings_billSharingOn_custom1").setVisible(true);
            empty = false;
        }
        else {
            findPreference("settings_billSharingOn_custom1").setVisible(false);
        }
        if (preferences.getBoolean("custombill2_On", false)) {
            if (preferences.getString("name_preference_bills_custom2", "Custom 2").equals(""))
                findPreference("settings_billSharingOn_custom2").setTitle("Custom 2");
            else
                findPreference("settings_billSharingOn_custom2").setTitle(preferences.getString("name_preference_bills_custom2", "Custom 2"));
            findPreference("settings_billSharingOn_custom2").setVisible(true);
            empty = false;
        }
        else {
            findPreference("settings_billSharingOn_custom2").setVisible(false);
        }
        if (preferences.getBoolean("custombill3_On", false)) {
            if (preferences.getString("name_preference_bills_custom3", "Custom 3").equals(""))
                findPreference("settings_billSharingOn_custom3").setTitle("Custom 3");
            else
                findPreference("settings_billSharingOn_custom3").setTitle(preferences.getString("name_preference_bills_custom3", "Custom 3"));
            findPreference("settings_billSharingOn_custom3").setVisible(true);
            empty = false;
        }
        else {
            findPreference("settings_billSharingOn_custom3").setVisible(false);
        }
        if (preferences.getBoolean("custombill4_On", false)) {
            if (preferences.getString("name_preference_bills_custom4", "Custom 4").equals(""))
                findPreference("settings_billSharingOn_custom4").setTitle("Custom 4");
            else
                findPreference("settings_billSharingOn_custom4").setTitle(preferences.getString("name_preference_bills_custom4", "Custom 4"));
            findPreference("settings_billSharingOn_custom4").setVisible(true);
            empty = false;
        }
        else {
            findPreference("settings_billSharingOn_custom4").setVisible(false);
        }
        if (preferences.getBoolean("custombill5_On", false)) {
            if (preferences.getString("name_preference_bills_custom5", "Custom 5").equals(""))
                findPreference("settings_billSharingOn_custom5").setTitle("Custom 5");
            else
                findPreference("settings_billSharingOn_custom5").setTitle(preferences.getString("name_preference_bills_custom5", "Custom 5"));

            findPreference("settings_billSharingOn_custom5").setVisible(true);
            empty = false;
        }
        else {
            findPreference("settings_billSharingOn_custom5").setVisible(false);
        }
    }
}

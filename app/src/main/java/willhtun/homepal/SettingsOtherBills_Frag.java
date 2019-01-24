package willhtun.homepal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.support.v4.app.Fragment;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import static android.support.v4.content.ContextCompat.getSystemService;

public class SettingsOtherBills_Frag extends PreferenceFragmentCompat {

    private SharedPreferences prefs;
    private DatabaseHelper mDatabaseHelper;
    private CalendarHelper mCalendarHelper;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState,
                                    String rootKey) {
        setPreferencesFromResource(R.xml.pref_otherbills, rootKey);

        findPreference("check_box_preference_bills_rent").setIconSpaceReserved(false);
        findPreference("check_box_preference_bills_car").setIconSpaceReserved(false);
        findPreference("check_box_preference_bills_internet").setIconSpaceReserved(false);
        findPreference("check_box_preference_bills_mobile").setIconSpaceReserved(false);
        findPreference("check_box_preference_bills_electricity").setIconSpaceReserved(false);
        findPreference("check_box_preference_bills_water").setIconSpaceReserved(false);
        findPreference("check_box_preference_bills_gas").setIconSpaceReserved(false);
        findPreference("check_box_preference_bills_trash").setIconSpaceReserved(false);
        findPreference("check_box_preference_bills_groceries").setIconSpaceReserved(false);

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mDatabaseHelper = new DatabaseHelper(getActivity());
        mCalendarHelper = new CalendarHelper(mDatabaseHelper, getActivity());

        create_deleteConfirmation("rent");
        create_deleteConfirmation("car");
        create_deleteConfirmation("internet");
        create_deleteConfirmation("mobile");
        create_deleteConfirmation("electricity");
        create_deleteConfirmation("water");
        create_deleteConfirmation("gas");
        create_deleteConfirmation("trash");
        create_deleteConfirmation("groceries");

        create_deleteConfirmation_custom("custom1");
        create_deleteConfirmation_custom("custom2");
        create_deleteConfirmation_custom("custom3");
        create_deleteConfirmation_custom("custom4");
        create_deleteConfirmation_custom("custom5");

        for (int z = 1; z < 6; z++) {
            if (prefs.getString("name_preference_bills_custom" + z, "").equals(""))
                findPreference("check_box_preference_bills_custom" + z).setTitle("Custom Bill " + z);
            else
                findPreference("check_box_preference_bills_custom" + z).setTitle(prefs.getString("name_preference_bills_custom" + z, "Custom Bill " + z));
        }
    }

    private void create_deleteConfirmation(final String type) {
        final Preference myPref = (Preference) findPreference("check_box_preference_bills_" + type);
        if (myPref != null) {
            myPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(final Preference preference, Object newValue) {
                    if (newValue instanceof Boolean && ((Boolean) newValue) != prefs.getBoolean("myPref", true)) {
                        final boolean isEnabled = (Boolean) newValue;
                        if (!isEnabled) {
                            android.support.v7.app.AlertDialog.Builder pricePicker_Builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
                            View pricePicker_view = getLayoutInflater().inflate(R.layout.dialog_otherbills_deleteconfirm, null);
                            pricePicker_Builder.setView(pricePicker_view);
                            final android.support.v7.app.AlertDialog pricepickerdialog = pricePicker_Builder.create();

                            (pricePicker_view.findViewById(R.id.deleteconfirm_button)).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    prefs.edit().putBoolean("check_box_preference_bills_" + type, false).commit();
                                    ((android.support.v14.preference.SwitchPreference) findPreference("check_box_preference_bills_" + type)).setChecked(false);
                                    mDatabaseHelper.deleteData_fromHistory(type);
                                    prefs.edit().putBoolean("settings_billSharingOn_" + type, false).apply();
                                    pricepickerdialog.dismiss();
                                }
                            });

                            (pricePicker_view.findViewById(R.id.deletecancel_button)).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    pricepickerdialog.dismiss();
                                }
                            });

                            pricepickerdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            pricepickerdialog.show();

                            return false;
                        }
                    }
                    else {
                        prepTable_empty(type);
                        return false;
                    }
                    return true;
                }
            });
        }
    }

    private void create_deleteConfirmation_custom(final String type) {
        final Preference myPref = (Preference) findPreference("check_box_preference_bills_" + type);
        if (myPref != null) {
            myPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(final Preference preference, Object newValue) {
                    if (newValue instanceof Boolean && ((Boolean) newValue) != prefs.getBoolean("myPref", true)) {
                        final boolean isEnabled = (Boolean) newValue;
                        if (!isEnabled) {
                            android.support.v7.app.AlertDialog.Builder pricePicker_Builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
                            View pricePicker_view = getLayoutInflater().inflate(R.layout.dialog_otherbills_deleteconfirm, null);
                            pricePicker_Builder.setView(pricePicker_view);
                            final android.support.v7.app.AlertDialog pricepickerdialog = pricePicker_Builder.create();

                            (pricePicker_view.findViewById(R.id.deleteconfirm_button)).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    prefs.edit().putBoolean("check_box_preference_bills_" + type, false).apply();
                                    ((android.support.v14.preference.SwitchPreference) findPreference("check_box_preference_bills_" + type)).setChecked(false);

                                    String name = "";
                                    findPreference("check_box_preference_bills_" + type).setTitle("Custom Bill " + type.substring(6,7));
                                    prefs.edit().putBoolean(type.substring(0,6) + "bill" + type.substring(6,7) + "_On", false).apply();
                                    prefs.edit().putBoolean("settings_billSharingOn_" + type, false).apply();
                                    prefs.edit().putString("name_preference_bills_" + type, name).apply();

                                    mDatabaseHelper.deleteData_fromHistory(type);
                                    pricepickerdialog.dismiss();
                                }
                            });

                            (pricePicker_view.findViewById(R.id.deletecancel_button)).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    pricepickerdialog.dismiss();
                                }
                            });

                            pricepickerdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            pricepickerdialog.show();

                            return false;
                        }
                    }
                    else {
                        final android.support.v7.app.AlertDialog.Builder pricePicker_Builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
                        final View pricePicker_view = getLayoutInflater().inflate(R.layout.dialog_custombillnamer, null);
                        pricePicker_Builder.setView(pricePicker_view);
                        final android.support.v7.app.AlertDialog pricepickerdialog = pricePicker_Builder.create();
                        final EditText et_namebox = ((EditText) pricePicker_view.findViewById(R.id.dialog_customname));

                        (pricePicker_view.findViewById(R.id.dialogBtn_customname)).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ((android.support.v14.preference.SwitchPreference) findPreference("check_box_preference_bills_" + type)).setChecked(true);
                                String name = et_namebox.getText().toString();
                                findPreference("check_box_preference_bills_" + type).setTitle(name);
                                prefs.edit().putBoolean(type.substring(0,6) + "bill" + type.substring(6,7) + "_On", true).apply();
                                prefs.edit().putString("name_preference_bills_" + type, name).apply();
                                pricepickerdialog.dismiss();
                            }
                        });

                        pricepickerdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        pricepickerdialog.show();

                        // Auto show keyboard
                        et_namebox.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                InputMethodManager keyboard = (InputMethodManager)
                                        getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                                keyboard.showSoftInput(et_namebox, 0);
                            }
                        },200);

                        return false;
                    }
                    return true;
                }
            });
        }
    }

    private void prepTable_empty(String type) {    // Adds empty rent_10_18 0 0 0 0 0 0 line
        int mon = mCalendarHelper.getCycleMonth(type);
        int yr = mCalendarHelper.getCycleYear(type);
        String monyr = String.valueOf(mon) + "_" + String.valueOf(yr);
        if (!mDatabaseHelper.isEntryExists_fromHistory(type, monyr)) {
            mDatabaseHelper.addData_toHistory(type, monyr, mCalendarHelper.getTodayDate(), 8, 0);
        }
    }

}

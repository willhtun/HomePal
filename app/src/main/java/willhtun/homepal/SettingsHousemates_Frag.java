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

public class SettingsHousemates_Frag extends PreferenceFragmentCompat {

    private SharedPreferences prefs;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState,
                                    String rootKey) {
        setPreferencesFromResource(R.xml.pref_housemates, rootKey);

        findPreference("check_box_preference_housemate1").setIconSpaceReserved(false);
        findPreference("check_box_preference_housemate2").setIconSpaceReserved(false);
        findPreference("check_box_preference_housemate3").setIconSpaceReserved(false);
        findPreference("check_box_preference_housemate4").setIconSpaceReserved(false);
        findPreference("check_box_preference_housemate5").setIconSpaceReserved(false);

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        create_deleteConfirmation_housemate("1");
        create_deleteConfirmation_housemate("2");
        create_deleteConfirmation_housemate("3");
        create_deleteConfirmation_housemate("4");
        create_deleteConfirmation_housemate("5");

        for (int z = 1; z < 6; z++) {
            if ((prefs.getString("name_preference_housemates" + z, "")) .equals("--"))
                findPreference("check_box_preference_housemate" + z).setTitle("Housemate " + z);
            else
                findPreference("check_box_preference_housemate" + z).setTitle(prefs.getString("name_preference_housemates" + z, "Housemate " + z));
        }

    }

    private void create_deleteConfirmation_housemate(final String type) {
        final Preference myPref = (Preference) findPreference("check_box_preference_housemate" + type);
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
                                    prefs.edit().putBoolean("check_box_preference_housemate" + type, false).apply();
                                    ((android.support.v14.preference.SwitchPreference) findPreference("check_box_preference_housemate" + type)).setChecked(false);

                                    String name = "--";
                                    findPreference("check_box_preference_housemate" + type).setTitle("Housemate " + type);
                                    prefs.edit().putBoolean("housemate" + type + "_On", false).apply();
                                    prefs.edit().putString("name_preference_housemates" + type, name).apply();

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
                        final View pricePicker_view = getLayoutInflater().inflate(R.layout.dialog_housematenamer, null);
                        pricePicker_Builder.setView(pricePicker_view);
                        final android.support.v7.app.AlertDialog pricepickerdialog = pricePicker_Builder.create();
                        final EditText et_namebox = ((EditText) pricePicker_view.findViewById(R.id.dialog_customname));

                        (pricePicker_view.findViewById(R.id.housemateconfirm_button)).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ((android.support.v14.preference.SwitchPreference) findPreference("check_box_preference_housemate" + type)).setChecked(true);
                                String name = et_namebox.getText().toString();
                                findPreference("check_box_preference_housemate" + type).setTitle(name);
                                prefs.edit().putBoolean("housemate" + type + "_On", true).apply();
                                prefs.edit().putString("name_preference_housemates" + type, name).apply();
                                pricepickerdialog.dismiss();
                            }
                        });

                        (pricePicker_view.findViewById(R.id.housematecancel_button)).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
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
}

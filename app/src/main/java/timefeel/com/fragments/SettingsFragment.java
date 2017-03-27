package timefeel.com.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import timefeel.com.R;

/**
 * Created by test on 05/03/2017.
 */

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //PreferenceManager.setDefaultValues(getActivity().getApplicationContext(), R.xml.preferences, true);
        addPreferencesFromResource(R.xml.preferences);
    }
}

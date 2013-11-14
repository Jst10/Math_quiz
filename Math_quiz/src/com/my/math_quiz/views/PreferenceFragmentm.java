package com.my.math_quiz.views;

import com.my.math_quiz.R;
import com.my.math_quiz.R.xml;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class PreferenceFragmentm  extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preference_layout);
    }

}

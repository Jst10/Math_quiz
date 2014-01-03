/*

    Copyright 2014 Jože Kulovic

    This file is part of Math-quiz.

    Math-quiz is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Math-quiz is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Math-quiz.  If not, see http://www.gnu.org/licenses

*/
package com.my.math_quiz;

import com.my.math_quiz.views.PreferenceFragmentm;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

public class PreferenceActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new PreferenceFragmentm())
                .commit();
    }
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
//		super.onConfigurationChanged(newConfig);
	}
	@Override
	public void finish() {
		ApplicationClass.settingsChanged();
		super.finish();
	}

    
}

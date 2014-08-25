/*
 * Copyright (C) 2013 The Dirty Unicorns Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.du;

import android.app.ActionBar;
import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.Settings;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceCategory;
import android.provider.Settings.SettingNotFoundException;
import android.util.Log;
import android.view.WindowManagerGlobal;

import com.android.internal.util.slim.DeviceUtils;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.Utils;
import com.android.settings.utils.Helpers;

public class QuickSettings extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {
    private static final String TAG = "QuickSettings";

    private static final String QS_QUICK_ACCESS = "qs_quick_access";
    private static final String QS_QUICK_ACCESS_LINKED = "qs_quick_access_linked";
    private static final String QUICK_SETTINGS_CATEGORY = "quick_settings_category";
    private static final String QUICK_PULLDOWN = "quick_pulldown";
    private static final String SCREENSHOT_TIMEOUT = "screenshot_timeout";

    private CheckBoxPreference mQSQuickAccess;
    private CheckBoxPreference mQSQuickAccess_linked;
    private PreferenceCategory mQuickSettingsCategory;
    private ListPreference mQuickPulldown;
    private ListPreference mScreenshotTimeout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.quick_settings);
        PreferenceScreen prefSet = getPreferenceScreen();

        ActionBar actionBar = getActivity().getActionBar();
        actionBar.setIcon(R.drawable.ic_settings_dirt);

        ContentResolver resolver = getActivity().getContentResolver();

        mQSQuickAccess = (CheckBoxPreference) prefSet.findPreference(QS_QUICK_ACCESS);
        mQSQuickAccess.setChecked((Settings.System.getInt(resolver,
                Settings.System.QS_QUICK_ACCESS, 0) == 1));

        mQSQuickAccess_linked = (CheckBoxPreference) prefSet.findPreference(QS_QUICK_ACCESS_LINKED);
        mQSQuickAccess_linked.setChecked((Settings.System.getInt(resolver,
                Settings.System.QS_QUICK_ACCESS_LINKED, 0) == 1));

        mScreenshotTimeout = (ListPreference) findPreference(SCREENSHOT_TIMEOUT);
        mScreenshotTimeout.setOnPreferenceChangeListener(this);

        mQuickSettingsCategory = (PreferenceCategory) getPreferenceScreen().findPreference(QUICK_SETTINGS_CATEGORY);
        mQuickPulldown = (ListPreference) getPreferenceScreen().findPreference(QUICK_PULLDOWN);
        if (!Utils.isPhone(getActivity())) {
            if(mQuickPulldown != null)
                getPreferenceScreen().removePreference(mQuickPulldown);
                getPreferenceScreen().removePreference((PreferenceCategory) findPreference(QUICK_SETTINGS_CATEGORY));
            } else {
                mQuickPulldown.setOnPreferenceChangeListener(this);
                int quickPulldownValue = Settings.System.getInt(getActivity().getApplicationContext().getContentResolver(),
                        Settings.System.QS_QUICK_PULLDOWN, 0);
                mQuickPulldown.setValue(String.valueOf(quickPulldownValue));
                updatePulldownSummary(quickPulldownValue);
            }
        }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void updatePulldownSummary(int value) {
        Resources res = getResources();
        if (value == 0) {
            /* quick pulldown deactivated */
            mQuickPulldown.setSummary(res.getString(R.string.quick_pulldown_off));
        } else {
            String direction = res.getString(value == 2
                    ? R.string.quick_pulldown_summary_left : R.string.quick_pulldown_summary_right);
            mQuickPulldown.setSummary(res.getString(R.string.quick_pulldown_summary, direction));
        }
    }

    public boolean onPreferenceChange(Preference preference, Object objValue) {
        if (preference == mQuickPulldown) {
            int quickPulldownValue = Integer.valueOf((String) objValue);
            Settings.System.putInt(getActivity().getApplicationContext().getContentResolver(),
                    Settings.System.QS_QUICK_PULLDOWN, quickPulldownValue);
            updatePulldownSummary(quickPulldownValue);
            return true;
        } else if (preference == mScreenshotTimeout) {
            int val = Integer.parseInt((String) objValue);
            Settings.System.putInt(getActivity().getContentResolver(),
                    Settings.System.SCREENSHOT_TOGGLE_DELAY, val);
            return true;
        }
        return false;
    }

    @Override
    public void onPause() {
        super.onPause();
    }



    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        ContentResolver resolver = getActivity().getContentResolver();
        boolean value;

        if (preference == mQSQuickAccess) {
            value = mQSQuickAccess.isChecked();
            Settings.System.putInt(resolver,
                    Settings.System.QS_QUICK_ACCESS, value ? 1 : 0);
            Helpers.restartSystemUI();
        } else if (preference == mQSQuickAccess_linked) {
            value = mQSQuickAccess_linked.isChecked();
            Settings.System.putInt(resolver,
                    Settings.System.QS_QUICK_ACCESS_LINKED, value ? 1 : 0);
           Helpers.restartSystemUI();
        } else {
            // If we didn't handle it, let preferences handle it.
            return super.onPreferenceTreeClick(preferenceScreen, preference);
        }
        return true;
    }
}

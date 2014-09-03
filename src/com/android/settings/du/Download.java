/*
 * Copyright (C) 2014 The Dirty Unicorns Project
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
import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.ContentResolver;
import android.content.res.Resources;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserHandle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.SeekBarPreference;
import android.provider.Settings;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.Utils;

public class Download extends SettingsPreferenceFragment implements OnPreferenceChangeListener {

    Preference mBanksStandardGapps;
    Preference mBanksMinimalGoogleSearchGapps;
    Preference mBanksMinimalGapps;
    Preference mBanksGappsAddons;
    Preference mPAStockGapps;
    Preference mPAMiniGapps;
    Preference mPAMicroGapps;
    Preference mPAFullGapps;
    Preference mTBOGapps;
    Preference mPlayStore;
    Preference mFdroid;
    Preference mXposed;
    Preference mXposedMod;
    Preference mGerrit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.download);

        ActionBar actionBar = getActivity().getActionBar();
        actionBar.setIcon(R.drawable.ic_settings_dirt);

        final ContentResolver resolver = getActivity().getContentResolver();

        mBanksStandardGapps = findPreference("banks_standard_gapps");
        mBanksMinimalGoogleSearchGapps = findPreference("banks_minimal_google_search_gapps");
        mBanksMinimalGapps = findPreference("banks_minimal_gapps");
        mBanksGappsAddons = findPreference("banks_gapps_addons");
        mPAStockGapps = findPreference("pa_stock_gapps");
        mPAMiniGapps = findPreference("pa_mini_gapps");
        mPAMicroGapps = findPreference("pa_micro_gapps");
        mPAFullGapps = findPreference("pa_full_gapps");
        mTBOGapps = findPreference("tbo_gapps");
        mPlayStore = findPreference("playstore");
        mFdroid = findPreference("fdroid");
        mXposed = findPreference("xposed");
        mXposedMod = findPreference("xposed_mod");
        mGerrit = findPreference("gerrit");
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if (preference == mBanksStandardGapps) {
            Uri uri = Uri.parse("http://goo.gl/2U0OPN");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
            return true;
        } else if (preference == mBanksMinimalGoogleSearchGapps) {
            Uri uri = Uri.parse("http://goo.gl/Nz07hc");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
            return true;
        } else if (preference == mBanksMinimalGapps) {
            Uri uri = Uri.parse("http://goo.gl/ZQCb4A");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
            return true;
        } else if (preference == mBanksGappsAddons) {
            Uri uri = Uri.parse("http://goo.gl/r273cg");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
            return true;
        } else if (preference == mPAStockGapps) {
            Uri uri = Uri.parse("http://goo.gl/bgonHf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
            return true;
        } else if (preference == mPAMiniGapps) {
            Uri uri = Uri.parse("http://goo.gl/al1IN2");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
            return true;
        } else if (preference == mPAMicroGapps) {
            Uri uri = Uri.parse("http://goo.gl/rWZga4");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
            return true;
        } else if (preference == mPAFullGapps) {
            Uri uri = Uri.parse("http://goo.gl/qohKa0");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
            return true;
        } else if (preference == mTBOGapps) {
            Uri uri = Uri.parse("http://goo.gl/QHLl7U");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
            return true;
        } else if (preference == mPlayStore) {
            Uri uri = Uri.parse("http://goo.gl/tWWgyJ");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
            return true;
        } else if (preference == mFdroid) {
            Uri uri = Uri.parse("https://f-droid.org/FDroid.apk");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
            return true;
        } else if (preference == mXposed) {
            Uri uri = Uri.parse("http://goo.gl/Mp0dTP");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
            return true;
        } else if (preference == mXposedMod) {
            Uri uri = Uri.parse("http://goo.gl/5J860t");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
            return true;
        } else if (preference == mGerrit) {
            Uri uri = Uri.parse("http://goo.gl/DrtoB0");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
            return true;
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

    public boolean onPreferenceChange(Preference preference, Object value) {
         return true;
    }

    public static class DeviceAdminLockscreenReceiver extends DeviceAdminReceiver {}

}

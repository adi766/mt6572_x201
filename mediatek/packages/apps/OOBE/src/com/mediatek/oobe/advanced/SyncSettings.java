/* Copyright Statement:
 *
 * This software/firmware and related documentation ("MediaTek Software") are
 * protected under relevant copyright laws. The information contained herein is
 * confidential and proprietary to MediaTek Inc. and/or its licensors. Without
 * the prior written permission of MediaTek inc. and/or its licensors, any
 * reproduction, modification, use or disclosure of MediaTek Software, and
 * information contained herein, in whole or in part, shall be strictly
 * prohibited.
 * 
 * MediaTek Inc. (C) 2010. All rights reserved.
 * 
 * BY OPENING THIS FILE, RECEIVER HEREBY UNEQUIVOCALLY ACKNOWLEDGES AND AGREES
 * THAT THE SOFTWARE/FIRMWARE AND ITS DOCUMENTATIONS ("MEDIATEK SOFTWARE")
 * RECEIVED FROM MEDIATEK AND/OR ITS REPRESENTATIVES ARE PROVIDED TO RECEIVER
 * ON AN "AS-IS" BASIS ONLY. MEDIATEK EXPRESSLY DISCLAIMS ANY AND ALL
 * WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NONINFRINGEMENT. NEITHER DOES MEDIATEK PROVIDE ANY WARRANTY WHATSOEVER WITH
 * RESPECT TO THE SOFTWARE OF ANY THIRD PARTY WHICH MAY BE USED BY,
 * INCORPORATED IN, OR SUPPLIED WITH THE MEDIATEK SOFTWARE, AND RECEIVER AGREES
 * TO LOOK ONLY TO SUCH THIRD PARTY FOR ANY WARRANTY CLAIM RELATING THERETO.
 * RECEIVER EXPRESSLY ACKNOWLEDGES THAT IT IS RECEIVER'S SOLE RESPONSIBILITY TO
 * OBTAIN FROM ANY THIRD PARTY ALL PROPER LICENSES CONTAINED IN MEDIATEK
 * SOFTWARE. MEDIATEK SHALL ALSO NOT BE RESPONSIBLE FOR ANY MEDIATEK SOFTWARE
 * RELEASES MADE TO RECEIVER'S SPECIFICATION OR TO CONFORM TO A PARTICULAR
 * STANDARD OR OPEN FORUM. RECEIVER'S SOLE AND EXCLUSIVE REMEDY AND MEDIATEK'S
 * ENTIRE AND CUMULATIVE LIABILITY WITH RESPECT TO THE MEDIATEK SOFTWARE
 * RELEASED HEREUNDER WILL BE, AT MEDIATEK'S OPTION, TO REVISE OR REPLACE THE
 * MEDIATEK SOFTWARE AT ISSUE, OR REFUND ANY SOFTWARE LICENSE FEES OR SERVICE
 * CHARGE PAID BY RECEIVER TO MEDIATEK FOR SUCH MEDIATEK SOFTWARE AT ISSUE.
 *
 * The following software/firmware and/or related documentation ("MediaTek
 * Software") have been modified by MediaTek Inc. All revisions are subject to
 * any receiver's applicable license agreements with MediaTek Inc.
 */

package com.mediatek.oobe.advanced;

import android.content.ContentResolver;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.view.View;

import com.mediatek.oobe.R;
import com.mediatek.oobe.utils.OOBEConstants;
import com.mediatek.oobe.utils.OOBEStepPreferenceActivity;
import com.mediatek.xlog.Xlog;

public class SyncSettings extends OOBEStepPreferenceActivity {
    private static final String TAG = OOBEConstants.TAG;
    private static final String SYNC_SWITCH_PREF = "sync_pref";
    private SwitchPreference mAutoSyncPref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.frame_layout);
        addPreferencesFromResource(R.xml.oobe_sync_settings);
        // initLayout();
        initSpecialLayout(R.string.oobe_account_settings_title, R.string.oobe_account_settings_summary);

    }

    @Override
    protected void initSpecialLayout(int titleRes, int summaryRes) {
        super.initSpecialLayout(R.string.oobe_sync_settings_title, R.string.oobe_sync_settings_summary);
        if (mStepIndex == 1 && mBackBtn != null) {
            mBackBtn.setVisibility(View.VISIBLE);
            mBackBtn.setText(R.string.oobe_btn_text_dismiss);
        }

        mAutoSyncPref = (SwitchPreference) findPreference(SYNC_SWITCH_PREF);
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean masterSyncAutomatically = ContentResolver.getMasterSyncAutomatically();
        mAutoSyncPref.setChecked(masterSyncAutomatically);
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if (preference == mAutoSyncPref && mAutoSyncPref.isChecked()) {
            Xlog.d(TAG, "" + mAutoSyncPref.isChecked());
            ContentResolver.setMasterSyncAutomatically(true);

        } else if (preference == mAutoSyncPref && !mAutoSyncPref.isChecked()) {
            Xlog.d(TAG, "" + mAutoSyncPref.isChecked());
            ContentResolver.setMasterSyncAutomatically(false);
        } else {
            return false;
        }
        return true;

    }

//    @Override
//    public void onNextStep(boolean isNext) {
//        if (!isNext && mStepIndex == 1) {
//            Xlog.d(TAG, getStepSpecialTag() + ">>Dismiss Advanced settings, exit");
//            finishActivityByResultCode(OOBEConstants.RESULT_CODE_FINISH);
//        } else {
//            super.onNextStep(isNext);
//        }
//    }

    @Override
    protected String getStepSpecialTag() {
        return "SyncSettingsActivity";
    }
}

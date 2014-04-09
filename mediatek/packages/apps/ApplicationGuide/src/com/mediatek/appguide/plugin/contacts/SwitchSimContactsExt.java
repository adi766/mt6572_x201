/* Copyright Statement:
 *
 * This software/firmware and related documentation ("MediaTek Software") are
 * protected under relevant copyright laws. The information contained herein
 * is confidential and proprietary to MediaTek Inc. and/or its licensors.
 * Without the prior written permission of MediaTek inc. and/or its licensors,
 * any reproduction, modification, use or disclosure of MediaTek Software,
 * and information contained herein, in whole or in part, shall be strictly prohibited.
 *
 * MediaTek Inc. (C) 2010. All rights reserved.
 *
 * BY OPENING THIS FILE, RECEIVER HEREBY UNEQUIVOCALLY ACKNOWLEDGES AND AGREES
 * THAT THE SOFTWARE/FIRMWARE AND ITS DOCUMENTATIONS ("MEDIATEK SOFTWARE")
 * RECEIVED FROM MEDIATEK AND/OR ITS REPRESENTATIVES ARE PROVIDED TO RECEIVER ON
 * AN "AS-IS" BASIS ONLY. MEDIATEK EXPRESSLY DISCLAIMS ANY AND ALL WARRANTIES,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR NONINFRINGEMENT.
 * NEITHER DOES MEDIATEK PROVIDE ANY WARRANTY WHATSOEVER WITH RESPECT TO THE
 * SOFTWARE OF ANY THIRD PARTY WHICH MAY BE USED BY, INCORPORATED IN, OR
 * SUPPLIED WITH THE MEDIATEK SOFTWARE, AND RECEIVER AGREES TO LOOK ONLY TO SUCH
 * THIRD PARTY FOR ANY WARRANTY CLAIM RELATING THERETO. RECEIVER EXPRESSLY ACKNOWLEDGES
 * THAT IT IS RECEIVER'S SOLE RESPONSIBILITY TO OBTAIN FROM ANY THIRD PARTY ALL PROPER LICENSES
 * CONTAINED IN MEDIATEK SOFTWARE. MEDIATEK SHALL ALSO NOT BE RESPONSIBLE FOR ANY MEDIATEK
 * SOFTWARE RELEASES MADE TO RECEIVER'S SPECIFICATION OR TO CONFORM TO A PARTICULAR
 * STANDARD OR OPEN FORUM. RECEIVER'S SOLE AND EXCLUSIVE REMEDY AND MEDIATEK'S ENTIRE AND
 * CUMULATIVE LIABILITY WITH RESPECT TO THE MEDIATEK SOFTWARE RELEASED HEREUNDER WILL BE,
 * AT MEDIATEK'S OPTION, TO REVISE OR REPLACE THE MEDIATEK SOFTWARE AT ISSUE,
 * OR REFUND ANY SOFTWARE LICENSE FEES OR SERVICE CHARGE PAID BY RECEIVER TO
 * MEDIATEK FOR SUCH MEDIATEK SOFTWARE AT ISSUE.
 *
 * The following software/firmware and/or related documentation ("MediaTek Software")
 * have been modified by MediaTek Inc. All revisions are subject to any receiver's
 * applicable license agreements with MediaTek Inc.
 */
package com.mediatek.appguide.plugin.contacts;

import android.app.Activity;
import android.app.StatusBarManager;
import android.content.Context;
import android.provider.Telephony.SIMInfo;

import com.android.contacts.ext.ContactAccountExtension;
import com.android.contacts.ext.ContactPluginDefault;
import com.mediatek.xlog.Xlog;

import java.util.List;

public class SwitchSimContactsExt extends ContactAccountExtension {
    private static final String TAG = "SwitchSimContactsExt";
    private static final String DIALTACTS = "activities.DialtactsActivity";
    private static final String PEOPLE = "activities.PeopleActivity";
    private static final String PHONE = "PHONE";
    private static final String CONTACTS = "CONTACTS";
    private static final int MULTI_SIM = 2;
    /**
     * Called when the app want to show application guide
     * @param activity: The parent activity
     * @param type: The app type, such as "CONTACTS"
     */
    public void switchSimGuide(Activity activity, String type, String commd) {
        String name = activity.getLocalClassName();
        Xlog.d(TAG, "activity name:" + name + ",commd:" + commd);
        if (! ContactPluginDefault.COMMD_FOR_AppGuideExt.equals(commd)){
            return;
        }
        if (DIALTACTS.equals(name)) {
            type = PHONE;
        } else if (PEOPLE.equals(name)) {
            type = CONTACTS;
        } else {
            return;
        }
        Xlog.d(TAG, "type:" + type);
        List<SIMInfo> simList = SIMInfo.getInsertedSIMList(activity);
        if (simList.size() < MULTI_SIM) {
            Xlog.d(TAG, "sim card number is : " + simList.size());
            return;
        }
        StatusBarManager manager = (StatusBarManager)activity.getSystemService(Context.STATUS_BAR_SERVICE);
        manager.showApplicationGuide(type);
    }

}

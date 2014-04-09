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

package com.mediatek.MediatekDM;

import android.os.Handler;
import android.util.Log;

import com.mediatek.MediatekDM.DmConst.TAG;
import com.mediatek.MediatekDM.data.IDmPersistentValues;
import com.mediatek.MediatekDM.mdm.DownloadDescriptor;
import com.mediatek.MediatekDM.mdm.scomo.MdmScomoDp;
import com.mediatek.MediatekDM.mdm.scomo.MdmScomoDpHandler;
import com.mediatek.MediatekDM.mdm.scomo.ScomoOperationResult;

public class DmScomoDpHandler implements MdmScomoDpHandler {

    private static DmScomoDpHandler mHandler = new DmScomoDpHandler();

    public static DmScomoDpHandler getInstance() {
        return mHandler;
    }

    public boolean confirmDownload(MdmScomoDp dp, DownloadDescriptor dd) {
        Log.i(TAG.Scomo, "confirmDownload");
        Handler h = DmService.getInstance().getScomoHandler();
        h.sendMessage(h.obtainMessage(IDmPersistentValues.MSG_SCOMO_CONFIRM_DOWNLOAD, new Object[] {
                dp, dd
        }));
        return false;
    }

    public boolean confirmInstall(MdmScomoDp dp) {
        Log.i(TAG.Scomo, "confirmInstall");
        Handler h = DmService.getInstance().getScomoHandler();
        h.sendMessage(h.obtainMessage(IDmPersistentValues.MSG_SCOMO_CONFIRM_INSTALL, dp));
        return false;
    }

    public ScomoOperationResult executeInstall(MdmScomoDp dp, String deliveryPkgPath,
            boolean isActive) {
        Log.i(TAG.Scomo, "executeInstall");
        Handler h = DmService.getInstance().getScomoHandler();
        h.sendMessage(h.obtainMessage(IDmPersistentValues.MSG_SCOMO_EXEC_INSTALL, dp));
        return new ScomoOperationResult();
    }

}

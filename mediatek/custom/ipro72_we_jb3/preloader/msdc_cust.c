/*****************************************************************************
*  Copyright Statement:
*  --------------------
*  This software is protected by Copyright and the information contained
*  herein is confidential. The software may not be copied and the information
*  contained herein may not be used or disclosed except with the written
*  permission of MediaTek Inc. (C) 2010
*
*  BY OPENING THIS FILE, BUYER HEREBY UNEQUIVOCALLY ACKNOWLEDGES AND AGREES
*  THAT THE SOFTWARE/FIRMWARE AND ITS DOCUMENTATIONS ("MEDIATEK SOFTWARE")
*  RECEIVED FROM MEDIATEK AND/OR ITS REPRESENTATIVES ARE PROVIDED TO BUYER ON
*  AN "AS-IS" BASIS ONLY. MEDIATEK EXPRESSLY DISCLAIMS ANY AND ALL WARRANTIES,
*  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF
*  MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR NONINFRINGEMENT.
*  NEITHER DOES MEDIATEK PROVIDE ANY WARRANTY WHATSOEVER WITH RESPECT TO THE
*  SOFTWARE OF ANY THIRD PARTY WHICH MAY BE USED BY, INCORPORATED IN, OR
*  SUPPLIED WITH THE MEDIATEK SOFTWARE, AND BUYER AGREES TO LOOK ONLY TO SUCH
*  THIRD PARTY FOR ANY WARRANTY CLAIM RELATING THERETO. MEDIATEK SHALL ALSO
*  NOT BE RESPONSIBLE FOR ANY MEDIATEK SOFTWARE RELEASES MADE TO BUYER'S
*  SPECIFICATION OR TO CONFORM TO A PARTICULAR STANDARD OR OPEN FORUM.
*
*  BUYER'S SOLE AND EXCLUSIVE REMEDY AND MEDIATEK'S ENTIRE AND CUMULATIVE
*  LIABILITY WITH RESPECT TO THE MEDIATEK SOFTWARE RELEASED HEREUNDER WILL BE,
*  AT MEDIATEK'S OPTION, TO REVISE OR REPLACE THE MEDIATEK SOFTWARE AT ISSUE,
*  OR REFUND ANY SOFTWARE LICENSE FEES OR SERVICE CHARGE PAID BY BUYER TO
*  MEDIATEK FOR SUCH MEDIATEK SOFTWARE AT ISSUE.
*
*  THE TRANSACTION CONTEMPLATED HEREUNDER SHALL BE CONSTRUED IN ACCORDANCE
*  WITH THE LAWS OF THE STATE OF CALIFORNIA, USA, EXCLUDING ITS CONFLICT OF
*  LAWS PRINCIPLES.  ANY DISPUTES, CONTROVERSIES OR CLAIMS ARISING THEREOF AND
*  RELATED THERETO SHALL BE SETTLED BY ARBITRATION IN SAN FRANCISCO, CA, UNDER
*  THE RULES OF THE INTERNATIONAL CHAMBER OF COMMERCE (ICC).
*
*****************************************************************************/

#include "msdc.h"

//For DD2-533
u32 msdc_src_clks[] = {133250000, 160000000, 200000000, 178280000, 0, 0, 26000000, 208000000};
//For DD3-633
//u32 msdc_src_clks[] = {0, 132600000, 165750000, 178280000, 189420000, 0, 26000000, 208000000};

//To do: declare multiple msdc_cap or declare msdc_cap as array, so that CTP can use them for multiple MSDC controller

struct msdc_cust msdc_cap = {
    MSDC_CLKSRC_DEFAULT,/* host clock source        */
    MSDC_SMPL_RISING,   /* command latch edge        */
    MSDC_SMPL_RISING,   /* data latch edge       */
    MSDC0_ODC_6MA,     /* clock pad driving         */
    MSDC0_ODC_6MA,     /* command pad driving       */
    MSDC0_ODC_6MA,     /* data pad driving      */
    MSDC0_ODC_6MA,     /* clock pad driving for 1.8v    */
    MSDC0_ODC_6MA,     /* command pad driving   1.8V    */
    MSDC0_ODC_6MA,     /* data pad driving      1.8V    */
    8,                  /* data pins             */
    0,                  /* data address offset       */

    /* hardware capability flags     */
    MSDC_HIGHSPEED|MSDC_DDR
};

#if defined(MMC_MSDC_DRV_CTP) || defined(FEATURE_MMC_MEM_PRESERVE_MODE)
struct msdc_cust msdc_cap_removable = {
    MSDC_CLKSRC_DEFAULT,/* host clock source        */
    MSDC_SMPL_RISING,   /* command latch edge        */
    MSDC_SMPL_RISING,   /* data latch edge       */
    MSDC1_ODC_20MA,    /* clock pad driving         */
    MSDC1_ODC_20MA,    /* command pad driving       */
    MSDC1_ODC_20MA,    /* data pad driving      */
    MSDC1_ODC_20MA,    /* clock pad driving for 1.8v    */
    MSDC1_ODC_20MA,    /* command pad driving   1.8V    */
    MSDC1_ODC_20MA,    /* data pad driving      1.8V    */
    4,          /* data pins             */
    0,          /* data address offset       */

    /* hardware capability flags     */
    MSDC_HIGHSPEED|MSDC_UHS1|MSDC_DDR|MSDC_CD_PIN_EN|MSDC_REMOVABLE
};
#endif


LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional

LOCAL_MODULE := VoiceCommand
LOCAL_SRC_FILES := $(LOCAL_MODULE).apk
LOCAL_MODULE_SUFFIX := $(COMMON_ANDROID_PACKAGE_SUFFIX)
LOCAL_MODULE_CLASS := APPS

LOCAL_CERTIFICATE := platform


LOCAL_PROGUARD_FLAG_FILES := proguard.flags

include $(BUILD_PREBUILT)

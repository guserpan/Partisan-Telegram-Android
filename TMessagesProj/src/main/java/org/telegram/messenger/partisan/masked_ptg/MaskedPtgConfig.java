package org.telegram.messenger.partisan.masked_ptg;

import android.content.Context;
import android.os.Build;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;

import org.telegram.messenger.partisan.masked_ptg.login.LoginScreenFactory;

public class MaskedPtgConfig {
    private static final Integer PRIMARY_COLOR = null;

    public static boolean colorOverride = false;
    public static Integer overridenColor = null;
    private static final IMaskedPasscodeScreenFactory FACTORY = new LoginScreenFactory();

    public static boolean allowNotHiddenNotificationsOverride = false;
    public static Boolean overridenAllowNotHiddenNotifications = false;

    public static boolean allowCallNotificationOverride = false;
    public static Boolean overridenAllowCallNotification = false;

    public static AbstractMaskedPasscodeScreen createScreen(Context context, PasscodeEnteredDelegate delegate, boolean unlockingApp) {
        return FACTORY.createScreen(context, delegate, unlockingApp);
    }

    public static boolean allowAlphaNumericPassword() {
        return FACTORY.allowAlphaNumericPassword();
    }

    public static boolean allowFingerprint() {
        return FACTORY.allowFingerprint();
    }

    public static boolean allowIconShortcuts() {
        return FACTORY.allowIconShortcuts();
    }

    public static boolean allowCallNotification() {
        if (allowCallNotificationOverride)
        {
            return overridenAllowCallNotification;
        }
        return FACTORY.allowCallNotification();
    }

    public static boolean allowNotHiddenNotifications() {
        if (allowNotHiddenNotificationsOverride)
        {
            return overridenAllowNotHiddenNotifications;
        }
        return FACTORY.allowNotHiddenNotifications();
    }

    public static int getDefaultPasscodeType() {
        return FACTORY.getDefaultPasscodeType();
    }

    public static int getPrimaryColor(Context context) {
        if (colorOverride) {
            return overridenColor;
        }
        if (PRIMARY_COLOR != null) {
            return PRIMARY_COLOR;
        } else {
            if (Build.VERSION.SDK_INT >= 21) {
                TypedValue typedValue = new TypedValue();
                ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, android.R.style.Theme_DeviceDefault);
                if (contextThemeWrapper.getTheme().resolveAttribute(android.R.attr.colorAccent, typedValue, true)) {
                    return typedValue.data;
                }
            }
            return FACTORY.getDefaultPrimaryColor();
        }
    }
}

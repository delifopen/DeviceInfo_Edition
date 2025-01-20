
package com.deviceinfo.extras;

import android.content.Context;
import android.nfc.NfcAdapter;

/**
 * EasyNfc Mod Class
 */
public class DeviceNFC {

    /**
     * Is nfc enabled boolean.
     *
     * @return the boolean
     */
    public static String nfcEnabled(Context context) {
        if(isNfcEnabled(context)){
            return "Available";
        }else {
            return "Not available";
        }
    }

    public static boolean isNfcEnabled(Context context) {
        return NfcAdapter.getDefaultAdapter(context).isEnabled();
    }

    /**
     * Is nfc present boolean.
     *
     * @return the boolean
     */
    public static boolean isNfcPresent(Context context) {
        return NfcAdapter.getDefaultAdapter(context) != null;
    }
}

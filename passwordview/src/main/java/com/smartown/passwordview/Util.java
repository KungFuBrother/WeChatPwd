package com.smartown.passwordview;

import android.content.Context;

/**
 * Author:Smartown
 * Date:2017/1/7 20:24
 * Description:
 */
public class Util {

    /**
     * DP转PX
     */
    public static int dpToPx(Context context, float dpSize) {
        return (int) (context.getResources().getDisplayMetrics().density * dpSize);
    }

}

package com.smartown.codeView;

import android.content.Context;
import android.graphics.Paint;

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

    public static float getTextBaseLine(float backgroundTop, float backgroundBottom, Paint paint) {
        final Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return (backgroundTop + backgroundBottom - fontMetrics.bottom - fontMetrics.top) / 2;
    }


}

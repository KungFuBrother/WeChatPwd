package com.smartown.passwordview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author:Smartown
 * Date:2017/1/7 20:12
 * Description:
 */
public class PasswordView extends View {

    //密码长度，默认6位
    private int length;
    //描边颜色，默认#E1E1E1
    private int borderColor;
    //描边宽度，默认1px
    private float borderWidth;
    //分割线颜色，默认#E1E1E1
    private int dividerColor;
    //分割线宽度，默认1px
    private float dividerWidth;
    //密码点颜色，默认#000000
    private int pointColor;
    //密码点半径，默认8dp
    private float pointRadius;

    private float unitWidth;
    private Paint paint;
    private String value = "";
    private Listener listener;

    public PasswordView(Context context) {
        super(context);
        init(null);
    }

    public PasswordView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //根据宽度来计算单元格大小（高度）
        float width = getMeasuredWidth();
        //宽度-左右边宽-中间分割线宽度
        unitWidth = (width - (2 * borderWidth) - ((length - 1) * dividerWidth)) / length;
        setMeasuredDimension((int) width, (int) (unitWidth + (2 * borderWidth)));
    }

    private void init(AttributeSet attrs) {
        paint = new Paint();
        paint.setAntiAlias(true);
        if (attrs == null) {
            length = 6;
            borderColor = Color.parseColor("#E1E1E1");
            borderWidth = 1;
            dividerColor = Color.parseColor("#E1E1E1");
            dividerWidth = 1;
            pointColor = Color.parseColor("#000000");
            pointRadius = Util.dpToPx(getContext(), 8);
        } else {
            TypedArray typedArray = getResources().obtainAttributes(attrs, R.styleable.PasswordView);
            length = typedArray.getInt(R.styleable.PasswordView_length, 6);
            borderColor = typedArray.getColor(R.styleable.PasswordView_borderColor, Color.parseColor("#E1E1E1"));
            borderWidth = typedArray.getDimensionPixelSize(R.styleable.PasswordView_borderWidth, 1);
            dividerColor = typedArray.getColor(R.styleable.PasswordView_dividerColor, Color.parseColor("#E1E1E1"));
            dividerWidth = typedArray.getDimensionPixelSize(R.styleable.PasswordView_dividerWidth, 1);
            pointColor = typedArray.getColor(R.styleable.PasswordView_pointColor, Color.parseColor("#000000"));
            pointRadius = typedArray.getDimensionPixelSize(R.styleable.PasswordView_pointRadius, Util.dpToPx(getContext(), 8));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawDivider(canvas);
        drawBorder(canvas);
        drawPoint(canvas);
    }

    /**
     * 描边
     */
    private void drawBorder(Canvas canvas) {
        if (borderWidth > 0) {
            paint.setColor(borderColor);
            canvas.drawRect(0, 0, getWidth(), borderWidth, paint);
            canvas.drawRect(0, getHeight() - borderWidth, getWidth(), getHeight(), paint);
            canvas.drawRect(0, 0, borderWidth, getHeight(), paint);
            canvas.drawRect(getWidth() - borderWidth, 0, getWidth(), getHeight(), paint);
        }
    }

    /**
     * 画分割线
     */
    private void drawDivider(Canvas canvas) {
        if (dividerWidth > 0) {
            paint.setColor(dividerColor);
            for (int i = 0; i < length - 1; i++) {
                final float left = unitWidth * (i + 1) + dividerWidth * i + borderWidth;
                canvas.drawRect(left, 0, left + dividerWidth, getHeight(), paint);
            }
        }
    }

    /**
     * 画密码点
     */
    private void drawPoint(Canvas canvas) {
        if (pointRadius > 0) {
            paint.setColor(pointColor);
            for (int i = 0; i < value.length(); i++) {
                final float left = unitWidth * i + dividerWidth * i + borderWidth;
                canvas.drawCircle(left + unitWidth / 2, getHeight() / 2, pointRadius, paint);
            }
        }
    }

    public void input(String number) {
        if (value.length() < length) {
            value += number;
            if (listener != null) {
                listener.onValueChanged(value);
                if (value.length() == length) {
                    listener.onComplete(value);
                }
            }
            invalidate();
        }
    }

    public void delete() {
        if (value.length() > 0) {
            value = value.substring(0, value.length() - 1);
            if (listener != null) {
                listener.onValueChanged(value);
            }
            invalidate();
        }
    }

    public String getValue() {
        return value;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {

        public void onValueChanged(String value);

        public void onComplete(String value);

    }

}

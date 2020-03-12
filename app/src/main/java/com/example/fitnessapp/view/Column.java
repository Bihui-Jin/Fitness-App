package com.example.fitnessapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.fitnessapp.R;
import com.example.fitnessapp.utils.DensityUtils;

import androidx.annotation.Nullable;


public class Column extends View {
    private int max = 100;
    private int corner = 40;
    private int bar = 0;
    private int tempBar = 0;
    private int textPadding = 20;
    private Paint paint;
    private int color;

    private Context context;

    public Column(Context context) {
        super(context);
        this.context = context;
    }

    public Column(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initPaint();
    }

    public Column(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        color = this.context.getResources().getColor(R.color.colorPrimary);
        paint.setColor(color);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (bar == 0) {
            paint.setTextSize((float)getWidth() / 2);
            // make a new rect
            RectF newRect = new RectF(0, getHeight() - DensityUtils.pxTodip(this.context, 20), getWidth(), getHeight());
            canvas.drawRoundRect(newRect, DensityUtils.pxTodip(this.context, corner), DensityUtils.pxTodip(this.context, corner), paint);

            canvas.drawText("0",
                    getWidth() * 0.5f - paint.measureText("0") * 0.5f,
                    getHeight() - DensityUtils.pxTodip(this.context, 20) - 2 * DensityUtils.pxTodip(this.context, textPadding),
                    paint);
            return;
        }

        //preventing a long animation time when getting a large value
        int step = bar / 100 + 1;

        if (tempBar < bar - step) {
            tempBar = tempBar + step;
        } else {
            tempBar = bar;
        }
        //rounded corner rect
        String S = tempBar + "";
        //unify the front size
        if (S.length() < 4) {
            paint.setTextSize((float)getWidth() / 2);
        } else {
            paint.setTextSize((float)getWidth() / (S.length() - 1));
        }

        float textH = paint.ascent() + paint.descent();
        float MaxH = getHeight() - textH - 2 * DensityUtils.pxTodip(this.context, textPadding);
        //actual height of rounded corner rect
        float realH = MaxH / max * tempBar;
        // make a new rect
        RectF newRect = new RectF(0, getHeight() - realH, getWidth(), getHeight());
        canvas.drawRoundRect(newRect, DensityUtils.pxTodip(this.context, corner), DensityUtils.pxTodip(this.context, corner), paint);
        //write in the values
        canvas.drawText(S,
                getWidth() * 0.5f - paint.measureText(S) * 0.5f,
                getHeight() - realH - 2 * DensityUtils.pxTodip(this.context, textPadding),
                paint);
        if (tempBar != bar) {
            postInvalidate();
        }
    }

    public void setData(int bar, int max) {
        this.bar = bar;
        tempBar = 0;
        this.max = max;
        postInvalidate();
    }

}

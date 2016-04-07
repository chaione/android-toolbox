package com.moldedbits.android.toolbox.ui.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.DatePicker;
import android.widget.NumberPicker;

import com.moldedbits.android.toolbox.R;

import java.lang.reflect.Field;

/**
 * DatePicker with dividers colored with colorAccent
 */
public class StyledDatePicker extends DatePicker {

    public StyledDatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);

        Class<?> internalRID = null;
        try {
            internalRID = Class.forName("com.android.internal.R$id");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Field month = null;
        try {
            month = internalRID.getField("month");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        NumberPicker npMonth = null;
        try {
            npMonth = (NumberPicker) findViewById(month.getInt(null));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Field day = null;
        try {
            day = internalRID.getField("day");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        NumberPicker npDay = null;
        try {
            npDay = (NumberPicker) findViewById(day.getInt(null));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Field year = null;
        try {
            year = internalRID.getField("year");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        NumberPicker npYear = null;
        try {
            npYear = (NumberPicker) findViewById(year.getInt(null));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Class<?> numberPickerClass = null;
        try {
            numberPickerClass = Class.forName("android.widget.NumberPicker");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Field selectionDivider = null;
        try {
            selectionDivider = numberPickerClass.getDeclaredField("mSelectionDivider");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        TypedValue typedValue = new TypedValue();

        TypedArray a = context.obtainStyledAttributes(typedValue.data, new int[] { R.attr.colorAccent });
        int color = a.getColor(0, 0);
        a.recycle();

        ColorDrawable drawable = new ColorDrawable(color);
        try {
            selectionDivider.setAccessible(true);
            selectionDivider.set(npMonth, drawable);
            selectionDivider.set(npDay, drawable);
            selectionDivider.set(npYear, drawable);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

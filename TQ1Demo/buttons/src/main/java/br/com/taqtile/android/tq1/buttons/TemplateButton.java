package com.taqtile.tq1demo.buttons;

import android.content.Context;
import android.util.AttributeSet;

import br.com.taqtile.android.buttons.CustomButton;

/**
 * Created by taqtile on 6/8/16.
 */
public class TemplateButton extends CustomButton {

    public TemplateButton(Context context) {
        super(context);
    }

    public TemplateButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TemplateButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getDisabledMaskResource() {
        return R.color.color_mask_disabled;
    }

    @Override
    public int getHighlightedMaskResource() {
        return R.color.color_mask_highlighted;
    }

}

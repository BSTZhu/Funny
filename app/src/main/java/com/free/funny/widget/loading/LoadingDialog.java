package com.free.funny.widget.loading;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import com.free.funny.R;

public class LoadingDialog extends Dialog {

    public LoadingDialog(Context context) {
        this(context, R.style.recommend_dialog);
    }

    public LoadingDialog(Context context, int style) {
        super(context, style);
        View contentView = getLayoutInflater().inflate(R.layout.widget_dialog_loading, null);
        setContentView(contentView);
    }
}

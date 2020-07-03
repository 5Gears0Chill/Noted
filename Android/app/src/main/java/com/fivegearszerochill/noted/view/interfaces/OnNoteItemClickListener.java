package com.fivegearszerochill.noted.view.interfaces;

import android.view.View;

public interface OnNoteItemClickListener {
    void onNoteLongPressed(View view, int position);
    void onCloseButtonClicked(View view,int position);
}

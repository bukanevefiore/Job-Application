package com.meka.findajob.Utils;

import androidx.recyclerview.widget.ItemTouchHelper;

public abstract class SwipeHelper extends ItemTouchHelper.SimpleCallback {

    public SwipeHelper(int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
    }
}

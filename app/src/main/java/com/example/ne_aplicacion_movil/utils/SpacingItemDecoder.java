package com.example.ne_aplicacion_movil.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SpacingItemDecoder extends RecyclerView.ItemDecoration {
    private final int verticalSpaceHeight;
    public SpacingItemDecoder(int verticalSpaceHeight){
        this.verticalSpaceHeight=verticalSpaceHeight;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom=verticalSpaceHeight;

    }
}

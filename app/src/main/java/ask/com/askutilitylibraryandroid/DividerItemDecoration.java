package ask.com.askutilitylibraryandroid;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by flatmind on 28/8/17.
 */

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private final Drawable mShadowDrawable;
    private final Rect mShadowPadding = new Rect();
    private final boolean mCastShadowForTransparentBackgroundItem;

    public DividerItemDecoration(@NonNull Drawable shadow) {
        this(shadow, true);
    }

    public DividerItemDecoration(@NonNull Drawable shadow, boolean castShadowForTransparentBackgroundItem) {
        mShadowDrawable = shadow;
        mShadowDrawable.getPadding(mShadowPadding);
        mCastShadowForTransparentBackgroundItem = castShadowForTransparentBackgroundItem;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        final int childCount = parent.getChildCount();

        if (childCount == 0) {
            return;
        }

        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);

            if (!shouldDrawDropShadow(child)) {
                continue;
            }

            final int tx = (int) (ViewCompat.getTranslationX(child) + 0.5f);
            final int ty = (int) (ViewCompat.getTranslationY(child) + 0.5f);

            final int left = child.getLeft() - mShadowPadding.left;
            final int right = child.getRight() + mShadowPadding.right;
            final int top = child.getTop() - mShadowPadding.top;
            final int bottom = child.getBottom() + mShadowPadding.bottom;

            mShadowDrawable.setBounds(left + tx, top + ty, right + tx, bottom + ty);
            mShadowDrawable.draw(c);
        }
    }

    private boolean shouldDrawDropShadow(View child) {
        if (child.getVisibility() != View.VISIBLE) {
            return false;
        }
        if (ViewCompat.getAlpha(child) != 1.0f) {
            return false;
        }

        Drawable background = child.getBackground();
        if (background == null) {
            return false;
        }

        if (!mCastShadowForTransparentBackgroundItem && (background instanceof ColorDrawable)) {
            //noinspection RedundantCast
            if (((ColorDrawable) background).getAlpha() == 0) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0, 0, 0, 0);
    }
}

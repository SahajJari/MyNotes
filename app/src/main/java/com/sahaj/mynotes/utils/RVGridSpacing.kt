package com.example.redbox.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RVGridSpacing(val spanCount: Int, val spacing: Int, val includeEdge: Boolean) :
    RecyclerView.ItemDecoration() {

    // How to implement
    // <RecyclerView>.addItemDecoration(new RVGridSpacing(<spanCount>, <spacing>, true));

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view) // item position
        val column = position % spanCount // item column

        if (includeEdge) {

            outRect.left = spacing - column * spacing / spanCount

            outRect.right = (column + 1) * spacing / spanCount

            if (position < spanCount) { // top edge
                outRect.top = spacing
            }

            outRect.bottom = spacing // item bottom

        } else {

            outRect.left = column * spacing / spanCount
            outRect.right = spacing - (column + 1) * spacing / spanCount

            if (position >= spanCount) {
                outRect.top = spacing // item top
            }

        }

    }

}
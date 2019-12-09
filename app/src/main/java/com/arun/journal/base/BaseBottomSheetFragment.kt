package com.arun.journal.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.arun.journal.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


abstract class BaseBottomSheetFragment<out V : ViewDataBinding, out T : BaseViewModel>(private val openFullScreen: Boolean = true) :
    BottomSheetDialogFragment() {

    private lateinit var mDataBinding: ViewDataBinding
    private lateinit var mViewModel: BaseViewModel

    abstract fun getViewModel(): BaseViewModel

    override fun getTheme(): Int = R.style.BaseBottomSheetDialog

    abstract fun getDataBinding(inflater: LayoutInflater, container: ViewGroup?): ViewDataBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mDataBinding = getDataBinding(inflater, container)
        return mDataBinding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        //set top margin for dialog window
        dialog.window?.setBackgroundDrawable(setTopMargin())
        dialog.setOnShowListener {
            val d = dialog as BottomSheetDialog
            val bottomSheetInternal =
                d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheetInternal?.let {
                //set height for bottom sheet
                if (openFullScreen) {
                    bottomSheetInternal.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
                }
                val behavior = BottomSheetBehavior.from(bottomSheetInternal)
                behavior.skipCollapsed = true
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
                behavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                    override fun onSlide(view: View, state: Float) {

                    }

                    override fun onStateChanged(view: View, state: Int) {
                        if (state == BottomSheetBehavior.STATE_HIDDEN) {
                            onHidden()
                            dismiss()
                        }
                    }
                })
            }
        }
        return dialog
    }

    /**
     * Called when dialog fragment is hidden
     */
    protected open fun onHidden(){
    }

    /**
     * Create InsetDrawable with top spacing
     */
    private fun setTopMargin(): InsetDrawable {
        val back = ColorDrawable(Color.TRANSPARENT)
        return InsetDrawable(back, 0, 100, 0, 0)
    }
}
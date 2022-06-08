package github.dev_playground.jeju_road.presentation.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment

abstract class BaseDialogFragment<VB: ViewDataBinding>(
    @LayoutRes val layoutId: Int
) : DialogFragment() {
    private var _binding: VB? = null
    val binding: VB by lazy { _binding!! }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(
            inflater,
            layoutId,
            container,
            false
        )

        binding {
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    protected fun binding(action: VB.() -> Unit) {
        binding.run(action)
    }
}
package github.dev_playground.jeju_road.presentation.ui.error

import android.app.Dialog
import android.os.Bundle
import android.view.*
import github.dev_playground.jeju_road.presentation.R
import github.dev_playground.jeju_road.presentation.databinding.FragmentDialogErrorBinding
import github.dev_playground.jeju_road.presentation.ui.base.BaseDialogFragment
import github.dev_playground.jeju_road.presentation.ui.list.RestaurantListFragment
import github.dev_playground.jeju_road.presentation.ui.list.RestaurantListFragment.Companion.ERROR_KEY

class ErrorDialogFragment
    : BaseDialogFragment<FragmentDialogErrorBinding>(R.layout.fragment_dialog_error) {

    private val errorText by lazy { requireArguments().getString(ERROR_KEY) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            textViewMessageErrorDialog.text = errorText

            imageViewCloseErrorDialog.setOnClickListener {
                dialog?.dismiss()
            }

            buttonRetryErrorDialog.setOnClickListener {
                if (parentFragmentManager.fragments.isNotEmpty()) {
                    requireActivity().supportFragmentManager
                        .beginTransaction()
                        .replace(
                            R.id.fragmentContainerView_main,
                            RestaurantListFragment.newInstance()
                        )
                        .commit()
                    dialog?.dismiss()
                } else {

                }
            }
        }
    }
}
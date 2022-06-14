//package github.dev_playground.jeju_road.presentation.util.handler
//
//import android.app.Activity
//import android.app.Application
//import android.os.Bundle
//import github.dev_playground.jeju_road.presentation.ui.error.ErrorDialogFragment
//
//class GlobalErrorExceptionHandler(
//    application: Application
//) : Thread.UncaughtExceptionHandler {
//    private var lastActivity: Activity? = null
//    private var activityCount = 0
//
//    init {
//        application.registerActivityLifecycleCallbacks(
//            object : SimpleActivityLifecycleCallbacks() {
//                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
//                    if ()
//                }
//            }
//        )
//    }
//
//    private fun isSkipActivity(activity: Activity) = activity is ErrorDialogFragment
//
//    override fun uncaughtException(t: Thread, e: Throwable) {
//        TODO("Not yet implemented")
//    }
//}
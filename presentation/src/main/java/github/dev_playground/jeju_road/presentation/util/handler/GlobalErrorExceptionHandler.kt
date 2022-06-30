package github.dev_playground.jeju_road.presentation.util.handler

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.os.Process
import github.dev_playground.jeju_road.presentation.ui.error.GlobalErrorActivity
import github.dev_playground.jeju_road.presentation.ui.error.GlobalErrorActivity.Companion.EXTRA_ERROR_TEXT
import github.dev_playground.jeju_road.presentation.ui.error.GlobalErrorActivity.Companion.EXTRA_INTENT
import java.io.PrintWriter
import java.io.StringWriter
import kotlin.system.exitProcess

class GlobalErrorExceptionHandler(
    application: Application
) : Thread.UncaughtExceptionHandler {

    private var lastActivity: Activity? = null
    private var activityCount = 0

    init {
        application.registerActivityLifecycleCallbacks(
            object : SimpleActivityLifecycleCallbacks() {
                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                    if (isSkipActivity(activity)) {
                        return
                    }
                    lastActivity = activity

                    println("onActivityCreated ${lastActivity.toString()}")
                }

                override fun onActivityStarted(activity: Activity) {
                    if (isSkipActivity(activity)) {
                        return
                    }
                    activityCount++
                    lastActivity = activity
                    println("onActivityStarted ${lastActivity.toString()}")
                }

                override fun onActivityStopped(activity: Activity) {
                    if (isSkipActivity(activity)) {
                        return
                    }
                    activityCount--
                    if (activityCount < 0) {
                        lastActivity = null
                    }
                    println("onActivityStopped ${lastActivity.toString()}")
                }
            })
    }

    private fun isSkipActivity(activity: Activity) = activity is GlobalErrorActivity

    override fun uncaughtException(thread: Thread, throwable: Throwable) {
        println("uncaughtException last : ${lastActivity.toString()}")
        lastActivity?.run {
            println("uncaughtException : ${thread.name} ${throwable.message}")
            val stringWriter = StringWriter()
            throwable.printStackTrace(PrintWriter(stringWriter))
            startErrorActivity(this, stringWriter.toString())
        }
        Process.killProcess(Process.myPid())
        exitProcess(-1)
    }

    private fun startErrorActivity(activity: Activity, errorText: String) = activity.run {
        println("startErrorActivity : ${activity.componentName} $errorText ${application.applicationInfo}")
        val errorActivityIntent = Intent(this, GlobalErrorActivity::class.java)
            .apply {
                putExtra(EXTRA_INTENT, intent)
                putExtra(EXTRA_ERROR_TEXT, errorText)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }

        startActivity(errorActivityIntent)
        finish()
    }
}
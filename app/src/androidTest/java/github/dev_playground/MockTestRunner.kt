package github.dev_playground

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import androidx.test.runner.AndroidJUnitRunner

class MockTestRunner : AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?, className: String?, context: Context?)
    : Application {
        return super.newApplication(cl, FakeJejuRoadApp::class.java.name, context)
    }
}
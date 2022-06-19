package github.dev_playground

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class MockTestRunner : AndroidJUnitRunner() {
    @Throws(InstantiationException::class, IllegalAccessException::class, ClassNotFoundException::class)
    override fun newApplication(
        cl: ClassLoader?, className: String?, context: Context?)
    : Application {
        return super.newApplication(cl, FakeJejuRoadApp::class.java.name, context)
    }
}
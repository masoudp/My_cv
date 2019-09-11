package com.mpakbaz.mycv.runner

import com.mpakbaz.mycv.App
import android.app.Application
import android.content.Context
import io.appflate.restmock.android.RESTMockTestRunner


class TestRunner : RESTMockTestRunner() {

    @Throws(InstantiationException::class, IllegalAccessException::class, ClassNotFoundException::class)
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, App::class.java.name, context)
    }

}

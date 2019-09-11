package com.mpakbaz.mycv.features.detail

import com.mpakbaz.mycv.data.model.CVData
import com.mpakbaz.mycv.features.base.MvpView

interface DetailMvpView : MvpView {

    fun showCVData(cvData: CVData?)

    fun showProgress(show: Boolean)

    fun showError(error: Throwable)

}
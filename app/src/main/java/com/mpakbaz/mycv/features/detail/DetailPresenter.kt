package com.mpakbaz.mycv.features.detail

import android.annotation.SuppressLint
import com.mpakbaz.mycv.data.DataManager
import com.mpakbaz.mycv.data.model.CVData
import com.mpakbaz.mycv.data.response.ApiSingleResponse
import com.mpakbaz.mycv.injection.ConfigPersistent
import com.mpakbaz.mycv.features.base.BasePresenter
import com.mpakbaz.mycv.util.rx.scheduler.SchedulerUtils
import javax.inject.Inject

@ConfigPersistent
class DetailPresenter @Inject
constructor(private val mDataManager: DataManager) : BasePresenter<DetailMvpView>() {

    @SuppressLint("CheckResult")
    fun getMyCv() {
        checkViewAttached()
        mvpView?.showProgress(true)
        mDataManager.getMyCv()
                .compose(SchedulerUtils.ioToMain<ApiSingleResponse<CVData>>())
                .subscribe({ result ->
                    mvpView?.showProgress(false)
                    mvpView?.showCVData(result.result)
                }) { throwable ->
                    mvpView?.showProgress(false)
                    mvpView?.showError(throwable)
                }
    }
}

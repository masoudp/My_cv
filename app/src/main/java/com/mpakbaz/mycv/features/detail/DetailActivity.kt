package com.mpakbaz.mycv.features.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.mpakbaz.mycv.R
import com.mpakbaz.mycv.data.model.CVData
import com.mpakbaz.mycv.features.base.BaseActivity
import com.mpakbaz.mycv.features.common.ErrorView
import com.mpakbaz.mycv.features.detail.fragment.BasicInfoFragment
import com.mpakbaz.mycv.features.detail.fragment.BioFragment
import com.mpakbaz.mycv.features.detail.fragment.ListInfoFragment
import com.mpakbaz.mycv.features.detail.fragment.OnFragmentInteractionListener
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*
import timber.log.Timber
import javax.inject.Inject

class DetailActivity : BaseActivity(), DetailMvpView, ErrorView.ErrorListener, OnFragmentInteractionListener, NavigationView.OnNavigationItemSelectedListener {


    @Inject
    lateinit var mDetailPresenter: DetailPresenter




    private lateinit var tabsAdapter: DetailTabsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent().inject(this)
        mDetailPresenter.attachView(this)


        setSupportActionBar(toolbar)
        view_error?.setErrorListener(this)

        initNavDrawer()

        getCV()


    }

    private fun initNavDrawer() {
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.app_name, 0
        )

        drawer_layout?.addDrawerListener(toggle)
        toggle.syncState()
        nav_view?.setNavigationItemSelectedListener(this)
    }

    private fun getCV() {
        mDetailPresenter.getMyCv()
    }

    override val layout: Int
        get() = R.layout.activity_detail

    override fun showCVData(cvData: CVData?) {
        view_error?.visibility = View.GONE
        const_details?.visibility = View.VISIBLE

        setupViewPager(cvData)
    }

    private fun setupViewPager(cvData: CVData?) {
        tabsAdapter = DetailTabsAdapter(supportFragmentManager)
        tabsAdapter.addFragment(BasicInfoFragment.newInstance(cvData), getString(R.string.label_tab_basic_info))
        tabsAdapter.addFragment(ListInfoFragment.newInstance(cvData, ListInfoFragment.Companion.InfoType.SKILL), getString(R.string.label_tab_skills))
        tabsAdapter.addFragment(ListInfoFragment.newInstance(cvData, ListInfoFragment.Companion.InfoType.WORK), getString(R.string.label_tab_work_history))
        tabsAdapter.addFragment(ListInfoFragment.newInstance(cvData, ListInfoFragment.Companion.InfoType.EDUCATION), getString(R.string.label_tab_education_history))
        tabsAdapter.addFragment(BioFragment.newInstance(cvData), getString(R.string.label_tab_about_me))
        vp_details?.adapter = tabsAdapter
        vp_details?.offscreenPageLimit = 5
        changePage(0)
    }

    override fun onFragmentNext(): Boolean {
        return if (vp_details.currentItem < tabsAdapter.count - 1) {
            changePage(vp_details.currentItem + 1)
            true
        } else {
            false
        }
    }

    override fun onFragmentPrev(): Boolean {
        return if (vp_details.currentItem > 0) {
            changePage(vp_details.currentItem - 1)
            true
        } else {
            false
        }
    }

    private fun changePage(index: Int) {
        vp_details?.currentItem = index
        title = tabsAdapter.getPageTitle(index)
    }

    override fun onNavigationItemSelected(menu: MenuItem): Boolean {
        when (menu.itemId) {
            R.id.nav_basic_info -> changePage(0)
            R.id.nav_skills -> changePage(1)
            R.id.nav_works -> changePage(2)
            R.id.nav_education -> changePage(3)
            R.id.nav_about_me -> changePage(4)
            R.id.nav_exit -> finish()
        }

        drawer_layout?.closeDrawer(GravityCompat.START)
        return true
    }

    override fun showProgress(show: Boolean) {
        const_loading?.visibility = if (show) View.VISIBLE else View.GONE
        const_details?.visibility = if (show) View.GONE else View.VISIBLE
    }

    override fun showError(error: Throwable) {
        const_details?.visibility = View.GONE
        view_error?.visibility = View.VISIBLE
        Timber.e(error, "There was a problem retrieving the cv data...")
    }

    override fun onReloadData() {
        mDetailPresenter.getMyCv()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            if (!onFragmentPrev())
                super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDetailPresenter.detachView()
    }

    companion object {

        fun getStartIntent(context: Context): Intent {
            return Intent(context, DetailActivity::class.java)
        }
    }
}
package com.mpakbaz.mycv.features.detail


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class DetailTabsAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private var lstFragments = arrayListOf<Fragment>()
    private var lstTitles = arrayListOf<String>()

    fun getRegisteredFragment(position: Int): Fragment {
        return lstFragments[position]
    }

    override fun getItem(position: Int): Fragment {
        return lstFragments[position]
    }

    fun addFragment(fragment: Fragment, title: String) {
        lstFragments.add(fragment)
        lstTitles.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return lstTitles[position]
    }

    override fun getCount(): Int {
        return lstFragments.size
    }

}

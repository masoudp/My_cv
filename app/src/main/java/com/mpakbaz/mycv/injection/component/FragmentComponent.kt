package com.mpakbaz.mycv.injection.component

import com.mpakbaz.mycv.features.detail.fragment.BasicInfoFragment
import com.mpakbaz.mycv.features.detail.fragment.BioFragment
import com.mpakbaz.mycv.features.detail.fragment.ListInfoFragment
import com.mpakbaz.mycv.injection.PerFragment
import com.mpakbaz.mycv.injection.module.FragmentModule
import dagger.Subcomponent

/**
 * This component inject dependencies to all Fragments across the application
 */
@PerFragment
@Subcomponent(modules = [FragmentModule::class])
interface FragmentComponent {
    fun inject(basicInfoFragment: BasicInfoFragment)
    fun inject(listInfoFragment: ListInfoFragment)
    fun inject(bioFragment: BioFragment)
}
package com.mpakbaz.mycv.features.base

import com.mpakbaz.mycv.App
import com.mpakbaz.mycv.injection.component.ConfigPersistentComponent
import com.mpakbaz.mycv.injection.component.DaggerConfigPersistentComponent
import com.mpakbaz.mycv.injection.component.FragmentComponent
import com.mpakbaz.mycv.injection.module.FragmentModule
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.collection.LongSparseArray
import androidx.fragment.app.Fragment
import timber.log.Timber
import java.util.concurrent.atomic.AtomicLong

/**
 * Abstract Fragment that every other Fragment in this application must implement. It handles
 * creation of Dagger components and makes sure that instances of ConfigPersistentComponent are kept
 * across configuration changes.
 */
abstract class BaseFragment : Fragment() {

    private var mFragmentComponent: FragmentComponent? = null
    private var mFragmentId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create the FragmentComponent and reuses cached ConfigPersistentComponent if this is
        // being called after a configuration change.
        mFragmentId = savedInstanceState?.getLong(KEY_FRAGMENT_ID) ?: NEXT_ID.getAndIncrement()
        var configPersistentComponent: ConfigPersistentComponent? = null
        if (sComponentsArray.get(mFragmentId) == null) {
            Timber.i("Creating new ConfigPersistentComponent id=%d", mFragmentId)
            activity?.let {
                configPersistentComponent = DaggerConfigPersistentComponent.builder()
                        .applicationComponent(App[it].component)
                        .build()
                sComponentsArray.put(mFragmentId, configPersistentComponent)
            }

        } else {
            Timber.i("Reusing ConfigPersistentComponent id=%d", mFragmentId)
            configPersistentComponent = sComponentsArray.get(mFragmentId)
        }
        mFragmentComponent = configPersistentComponent?.fragmentComponent(FragmentModule(this))
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layout, container, false)
    }

    abstract val layout: Int

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(KEY_FRAGMENT_ID, mFragmentId)
    }

    override fun onDestroy() {
        activity?.let {
            if (!it.isChangingConfigurations) {
                Timber.i("Clearing ConfigPersistentComponent id=%d", mFragmentId)
                sComponentsArray.remove(mFragmentId)
            }
        }
        super.onDestroy()
    }

    fun fragmentComponent(): FragmentComponent {
        return mFragmentComponent as FragmentComponent
    }

    companion object {

        private const val KEY_FRAGMENT_ID = "KEY_FRAGMENT_ID"
        private val sComponentsArray = LongSparseArray<ConfigPersistentComponent>()
        private val NEXT_ID = AtomicLong(0)
    }
}

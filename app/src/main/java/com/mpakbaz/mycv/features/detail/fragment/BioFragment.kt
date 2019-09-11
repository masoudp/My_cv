package com.mpakbaz.mycv.features.detail.fragment

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.mpakbaz.mycv.R
import com.mpakbaz.mycv.data.model.CVData
import com.mpakbaz.mycv.features.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_bio.*

private const val ARG_CV_DATA = "CV_DATA"


class BioFragment : BaseFragment() {
    private var cvData: CVData? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cvData = it.getSerializable(ARG_CV_DATA) as CVData?
        }

        fragmentComponent().inject(this)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        tv_bio_name?.text = cvData?.basics?.name
        tv_bio_text?.text = cvData?.basics?.bio
        Glide.with(this)
                .load(cvData?.basics?.picture)
                .into(bio_image)

    }

    override val layout: Int
        get() = R.layout.fragment_bio


    companion object {
        @JvmStatic
        fun newInstance(cvData: CVData?) =
                BioFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(ARG_CV_DATA, cvData)
                    }
                }
    }
}

package com.mpakbaz.mycv.features.detail.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.mpakbaz.mycv.R
import com.mpakbaz.mycv.data.model.CVData
import com.mpakbaz.mycv.features.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_basic_info.*

private const val ARG_CV_DATA = "CV_DATA"


class BasicInfoFragment : BaseFragment() {
    private var cvData: CVData? = null
    private var listener: OnFragmentInteractionListener? = null

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
        tv_name.text = cvData?.basics?.name
        Glide.with(this)
                .load(cvData?.basics?.picture)
                .into(profile_image)
        tv_label.text = cvData?.basics?.label
        tv_location.text = "%s, %s".format(cvData?.basics?.location?.region, cvData?.basics?.location?.countryCode)
        tv_phone.text = getString(R.string.label_phone_prefix, cvData?.basics?.phone)
        tv_mail.text = getString(R.string.label_mail_prefix, cvData?.basics?.email)
        tv_address.text = getString(R.string.label_address_prefix, cvData?.basics?.location?.address)

        btn_view_more.setOnClickListener {
            listener?.onFragmentNext()
        }
    }

    override val layout: Int
        get() = R.layout.fragment_basic_info


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        @JvmStatic
        fun newInstance(cvData: CVData?) =
                BasicInfoFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(ARG_CV_DATA, cvData)
                    }
                }
    }
}

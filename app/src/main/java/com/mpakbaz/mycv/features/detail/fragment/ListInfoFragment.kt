package com.mpakbaz.mycv.features.detail.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.mpakbaz.mycv.R
import com.mpakbaz.mycv.data.model.CVData
import com.mpakbaz.mycv.features.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_list_info.*
import kotlinx.android.synthetic.main.layout_navigation_buttons.*

private const val ARG_CV_DATA = "CV_DATA"
private const val ARG_INFO_TYPE = "INFO_TYPE"


class ListInfoFragment : BaseFragment() {
    private var cvData: CVData? = null
    private var infoType: InfoType? = null
    private var listener: OnFragmentInteractionListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cvData = it.getSerializable(ARG_CV_DATA) as CVData?
            infoType = it.getSerializable(ARG_INFO_TYPE) as InfoType?
        }

        fragmentComponent().inject(this)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        rcv_items?.layoutManager = LinearLayoutManager(activity)
        when (infoType) {
            InfoType.SKILL -> setAdapterSkills(cvData?.skills)
            InfoType.EDUCATION -> setAdapterEducation(cvData?.education)
            InfoType.WORK -> setAdapterWork(cvData?.work)
        }

        btn_next.setOnClickListener { listener?.onFragmentNext() }
        btn_previous.setOnClickListener { listener?.onFragmentPrev() }
    }

    private fun setAdapterWork(work: List<CVData.WorkBean>?) {
        val workAdapter = WorkAdapter()
        workAdapter.setWorks(work)
        rcv_items?.adapter = workAdapter
        workAdapter.notifyDataSetChanged()
    }

    private fun setAdapterEducation(education: List<CVData.EducationBean>?) {
        val educationAdapter = EducationAdapter()
        educationAdapter.setEducations(education)
        rcv_items?.adapter = educationAdapter
        educationAdapter.notifyDataSetChanged()
    }

    private fun setAdapterSkills(skills: List<CVData.SkillsBean>?) {
        val skillAdapter = SkillAdapter()
        skillAdapter.setSkills(skills)
        rcv_items?.adapter = skillAdapter
        skillAdapter.notifyDataSetChanged()
    }

    override val layout: Int
        get() = R.layout.fragment_list_info


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

        enum class InfoType {
            SKILL, EDUCATION, WORK
        }

        @JvmStatic
        fun newInstance(cvData: CVData?, infoType: InfoType) =
                ListInfoFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(ARG_CV_DATA, cvData)
                        putSerializable(ARG_INFO_TYPE, infoType)
                    }
                }
    }
}

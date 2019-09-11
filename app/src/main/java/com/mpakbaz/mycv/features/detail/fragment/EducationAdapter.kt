package com.mpakbaz.mycv.features.detail.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mpakbaz.mycv.R
import com.mpakbaz.mycv.data.model.CVData
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_education.view.*
import javax.inject.Inject

class EducationAdapter @Inject
constructor() : RecyclerView.Adapter<EducationAdapter.EducationViewHolder>() {

    private var educations: List<CVData.EducationBean>? = null

    init {
        educations = emptyList()
    }

    fun setEducations(educations: List<CVData.EducationBean>?) {
        this.educations = educations
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducationViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_education, parent, false)
        return EducationViewHolder(view)
    }

    override fun onBindViewHolder(holder: EducationViewHolder, position: Int) {
        educations?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return educations?.size as Int
    }


    inner class EducationViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer{

        fun bind(education:CVData.EducationBean){
            containerView.tv_instution.text = education.institution
            containerView.tv_study_type.text = education.studyType
            containerView.tv_education_time.text = containerView.context?.getString(R.string.label_work_time)?.format(education.startDate,education.endDate)
            containerView.tv_area.text = education.area

        }

    }
}

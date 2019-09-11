package com.mpakbaz.mycv.features.detail.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mpakbaz.mycv.R
import com.mpakbaz.mycv.data.model.CVData
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_work.view.*
import javax.inject.Inject

class WorkAdapter @Inject
constructor() : RecyclerView.Adapter<WorkAdapter.WorkViewHolder>() {

    private var works: List<CVData.WorkBean>? = null

    init {
        works = emptyList()
    }

    fun setWorks(skills: List<CVData.WorkBean>?) {
        this.works = skills
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_work, parent, false)
        return WorkViewHolder(view)
    }

    override fun onBindViewHolder(holder: WorkViewHolder, position: Int) {
        val work = works?.get(position)
        work?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return works?.size as Int
    }


    inner class WorkViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(work: CVData.WorkBean) {
            containerView.tv_work_title.text = work.position
            containerView.tv_company_name.text = work.company
            containerView.tv_work_time.text = containerView.tv_work_time.context?.getString(R.string.label_work_time)?.format(work.startDate, work.endDate)
            containerView.tv_summary.text = work.summary

        }

    }

}

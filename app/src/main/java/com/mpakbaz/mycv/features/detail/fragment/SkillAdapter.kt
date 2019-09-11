package com.mpakbaz.mycv.features.detail.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mpakbaz.mycv.R
import com.mpakbaz.mycv.data.model.CVData
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_skill.view.*
import javax.inject.Inject

class SkillAdapter @Inject
constructor() : RecyclerView.Adapter<SkillAdapter.SkillViewHolder>() {

    private var skills: List<CVData.SkillsBean>? = null

    init {
        skills = emptyList()
    }

    fun setSkills(skills: List<CVData.SkillsBean>?) {
        this.skills = skills
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_skill, parent, false)
        return SkillViewHolder(view)
    }

    override fun onBindViewHolder(holder: SkillViewHolder, position: Int) {
        val skill = skills?.get(position)
        skill?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return skills?.size as Int
    }


    inner class SkillViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(skill: CVData.SkillsBean) {
            containerView.tv_skill_name.text = skill.name
            containerView.progressBar.progress = skill.level?.toIntOrNull() ?: 0
            containerView.tv_skill_percent.text = "%s %%".format(skill.level)

        }
    }
}

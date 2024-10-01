package com.example.searchjobs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide

// Adapter for the job list
class JobAdapter(val jobs: List<Job>): Adapter<JobPrototype>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobPrototype {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prototype_job, parent, false)
        return JobPrototype(view)
    }

    override fun onBindViewHolder(holder: JobPrototype, position: Int) {
        holder.bind(jobs[position])
    }

    override fun getItemCount(): Int {
        return jobs.size
    }

}

class JobPrototype(itemView: View) : ViewHolder(itemView) {

    val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
    val tvCompany = itemView.findViewById<TextView>(R.id.tvCompany)
    val tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
    val ivLogo = itemView.findViewById<ImageView>(R.id.ivLogo)

    // Bind the job data to the view
    fun bind(job: Job) {
        tvTitle.text = job.title
        tvCompany.text = job.company
        tvDescription.text = job.description

        // Load the image from the URL
        Glide.with(itemView).load(job.companyLogo).into(ivLogo)
    }


}

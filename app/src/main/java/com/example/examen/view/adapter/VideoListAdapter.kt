package com.example.examen.view.adapter

import android.view.View
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.SectionRow
import com.example.examen.R
import com.example.examen.models.Resource
import com.example.examen.models.Video
import com.example.examen.view.viewholder.VideoListViewHolder
import com.skydoves.whatif.whatIfNotNull
import java.util.*

class VideoListAdapter : BaseAdapter() {

  init {
    addSection(ArrayList<Video>())
  }

  fun addVideoList(resource: Resource<List<Video>>) {
    resource.data.whatIfNotNull {
      sections()[0].addAll(it)
      notifyDataSetChanged()
    }
  }

  override fun layout(sectionRow: SectionRow) = R.layout.item_video

  override fun viewHolder(layout: Int, view: View) = VideoListViewHolder(view)
}

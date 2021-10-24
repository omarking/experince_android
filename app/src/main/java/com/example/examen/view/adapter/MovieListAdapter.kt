package com.example.examen.view.adapter

import android.view.View
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.SectionRow
import com.example.examen.R
import com.example.examen.models.Resource
import com.example.examen.models.entity.Movie
import com.example.examen.view.viewholder.MovieListViewHolder
import com.skydoves.whatif.whatIfNotNull
import java.util.*

class MovieListAdapter : BaseAdapter() {

  init {
    addSection(ArrayList<Movie>())
  }

  fun addMovieList(resource: Resource<List<Movie>>) {
    resource.data.whatIfNotNull {
      sections()[0].addAll(it)
      notifyDataSetChanged()
    }
  }

  override fun layout(sectionRow: SectionRow) = R.layout.item_poster

  override fun viewHolder(layout: Int, view: View) = MovieListViewHolder(view)
}

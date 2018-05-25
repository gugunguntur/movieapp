package com.myportfolio.guntur.movieapp.data

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.myportfolio.guntur.movieapp.DetailActivity
import com.myportfolio.guntur.movieapp.R
import com.myportfolio.guntur.movieapp.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_list_card.view.*

class MovieListAdapter(private val list: ArrayList<Movie>,
                       private val context: Context): RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.movie_list_card, parent, false)



        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        holder.bindView(list[position])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var titles = itemView.findViewById<TextView>(R.id.titleId)
        var posters = itemView.findViewById<ImageView>(R.id.imgMovieID)
        var years = itemView.findViewById<TextView>(R.id.yearId)
        var type = itemView.findViewById<TextView>(R.id.typeId)

        fun bindView(movie: Movie) {
            titles.text = movie.title
            years.text = movie.year
            type.text = movie.type

            if (!TextUtils.isEmpty(movie.poster)) {
                Picasso.get()
                        .load(movie.poster)
                        .placeholder(android.R.drawable.ic_menu_report_image)
                        .error(android.R.drawable.ic_menu_report_image)
                        .into(posters)
            }else {
                Picasso.get()
                        .load(R.drawable.food).into(posters)
            }

        }

        init {
            itemView.setOnClickListener {

                val intent = Intent(itemView.context, DetailActivity::class.java)
                itemView.context.startActivity(intent)

            }
        }
    }

}
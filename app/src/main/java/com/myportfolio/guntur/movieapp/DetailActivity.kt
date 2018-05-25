package com.myportfolio.guntur.movieapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.movie_list_card.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val extras = intent.extras
        if (null != extras) {
            val titleMovie = extras.getString("Title")
            val year = extras.getString("Year")

            titleMovieID.text = titleMovie
            descMovieID.text = year
        }

    }
}

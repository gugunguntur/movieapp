package com.myportfolio.guntur.movieapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.myportfolio.guntur.movieapp.data.MovieListAdapter
import com.myportfolio.guntur.movieapp.model.LEFT_LINK
import com.myportfolio.guntur.movieapp.model.Movie
import com.myportfolio.guntur.movieapp.model.QUERY
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.movie_list_card.*
import kotlinx.android.synthetic.main.movie_list_card.view.*
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    var volleyRequest: RequestQueue? = null
    var movieList: ArrayList<Movie>? = null
    var movieAdapter: MovieListAdapter? = null
    var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //var urlString = "http://www.recipepuppy.com/api/?i=onions,garlic&q=omelet&p=3"

        var urls: String?

        var extras = intent.extras
        var title = extras.get("Title")
        //var searchTerm = extras.get("search")

        if (extras != null && !title.equals("")) {

            //construct our url
            var tempUrl = LEFT_LINK+title+QUERY

            urls = tempUrl

        }else {
            urls = "http://www.omdbapi.com/?s=fast&apikey=2ab7f8ec"
        }

        movieList = ArrayList<Movie>()

        volleyRequest = Volley.newRequestQueue(this)

        getMovie(urls)

    }

    fun getMovie(url: String) {
        val recipeRequest = JsonObjectRequest(Request.Method.GET, url,
                Response.Listener {
                    response: JSONObject ->
                    try {

                        val resultArray = response.getJSONArray("Search")

                        for (i in 0..resultArray.length() - 1) {
                            var recipeObj = resultArray.getJSONObject(i)

                            var title = recipeObj.getString("Title")
                            var poster = recipeObj.getString("Poster")
                            var year = recipeObj.getString("Year")
                            var type = recipeObj.getString("Type")

                            Log.d("Search==>>", title)

                            var movie = Movie()
                            movie.title = title
                            movie.poster = poster
                            movie.year = year
                            movie.type = type

                            movieList!!.add(movie)

                            movieAdapter = MovieListAdapter(movieList!!, this)
                            layoutManager = LinearLayoutManager(this)

                            // setup list/recyclerview
                            recyclerViewID.layoutManager = layoutManager
                            recyclerViewID.adapter = movieAdapter
                        }

                        movieAdapter!!.notifyDataSetChanged()

                    }catch (e: JSONException) { e.printStackTrace() }
                },
                Response.ErrorListener {
                    error: VolleyError? ->
                    try {

                    }catch (e: JSONException) { e.printStackTrace() }
                })

        volleyRequest!!.add(recipeRequest)
    }

}

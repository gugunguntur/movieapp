package com.myportfolio.guntur.movieapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        goBtn.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            var titles = titleSearch.text.toString().trim()

            intent.putExtra("Title", titles)

            startActivity(intent)
        }
    }
}

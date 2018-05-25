package com.myportfolio.guntur.movieapp.model

class Movie() {

    var title: String? = null
    var year: String? = null
    var imdbID: String? = null
    var type: String? = null
    var poster: String? = null

    constructor(title: String, year: String, poster: String): this() {

        this.title = title
        this.year = year
        this.imdbID = imdbID
        this.type = type
        this.poster = poster

    }

}
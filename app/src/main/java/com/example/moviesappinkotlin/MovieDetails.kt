package com.example.moviesappinkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.card_movie.view.*

class MovieDetails : AppCompatActivity() {

    /*lateinit var tv_Ratings: TextView
    lateinit var tv_OverView: TextView
    lateinit var tv_Title: TextView
    lateinit var tv_ReleaseDate: TextView
    lateinit var movieImg: ImageView*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        tv_Rating.setText(intent.getStringExtra("rating"))
        tv_movieName.setText(intent.getStringExtra("name"))
        tv_overView.setText(intent.getStringExtra("overview"))
        tv_releaseDate.setText(intent.getStringExtra("date"))

        var imagePath = intent.getStringExtra("image")

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w342${imagePath}")
            .transform(CenterCrop())
            .into(this.moviePoster)

    }
}
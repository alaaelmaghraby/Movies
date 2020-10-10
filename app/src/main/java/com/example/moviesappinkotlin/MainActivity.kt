package com.example.moviesappinkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Callback

import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() , MoviesAdapter.movieListener{
    var currentPageNumber = 1
    lateinit var moviesAdapter: MoviesAdapter
    lateinit var llm: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        moviesAdapter = MoviesAdapter(mutableListOf(),this)
        llm = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,
            false)
        rv_movies.adapter = moviesAdapter
        rv_movies.layoutManager = llm
        getPopularMovies()
        // This is in development branch
    }

    fun getPopularMovies(){
        Log.d("Popular Movies",
            "here" )
        MoviesClient.fetchPopularMovies(
            currentPageNumber,
        ::onPopularMoviesFetched,
        ::onError)
    }

    private fun onError() {
        Toast
            .makeText(this, "Failed to fetch movies", Toast.LENGTH_SHORT)
            .show()
    }

    private fun onPopularMoviesFetched(list: MutableList<Movie>) {
        moviesAdapter.appendMovies(list)
        attachOnScrollListener()
    }

    fun attachOnScrollListener(){
        rv_movies.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItems = llm.itemCount
                val visibleItemsCount = llm.childCount
                val firstVisibleItem = llm.findLastVisibleItemPosition()

                if(firstVisibleItem + visibleItemsCount >= totalItems/2){
                    rv_movies.removeOnScrollListener(this)
                    currentPageNumber++
                    getPopularMovies()
                }
            }
        })
    }

    override fun onClick(m: Movie) {

        var movieName = m.title
        var rating = m.rating
        var overview = m.overview
        var img = m.posterPath
        var date = m.releaseDate

        val intent = Intent(this,MovieDetails::class.java).apply {
            putExtra("name",movieName)
            putExtra("rating",rating.toString())
            putExtra("overview",overview)
            putExtra("image",img)
            putExtra("date",date)
        }
        startActivity(intent)
    }

}
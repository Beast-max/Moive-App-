package com.example.movieapp
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.adapter.LatestRecyclerView
import com.example.myapplication.Response.Movie
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class MainActivity : AppCompatActivity() {

    private lateinit var videoview:YouTubePlayerView

      private lateinit var popularMovie:RecyclerView
      private lateinit var popularMovieAdapter:LatestRecyclerView
      private lateinit var popularMovielayoutmgr:LinearLayoutManager

      private var popularMoviepage = 1

    private lateinit var LatestMovie:RecyclerView
    private lateinit var LatestMovieAdapter:LatestRecyclerView
    private lateinit var LatestMovieLayoutmgr:LinearLayoutManager

    private var latestMoviepage = 1

    private lateinit var Top_ratedMovie:RecyclerView
    private lateinit var Top_ratedMovieAdapter:LatestRecyclerView
    private lateinit var Top_ratedMovieLayoutmgr:LinearLayoutManager

    private var top_ratedMovie = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        popularMovie = findViewById(R.id.popular_movies)
        popularMovieAdapter =
            LatestRecyclerView(mutableListOf()) { movie -> showMovieDetails(movie) }
        popularMovielayoutmgr = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        popularMovie.layoutManager = popularMovielayoutmgr
        popularMovie.adapter = popularMovieAdapter

        LatestMovie = findViewById(R.id.top_rated_movies)
        LatestMovieAdapter =
            LatestRecyclerView(mutableListOf()) { movie -> showMovieDetails(movie) }
        LatestMovieLayoutmgr = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        LatestMovie.layoutManager = LatestMovieLayoutmgr
        LatestMovie.adapter = LatestMovieAdapter

        Top_ratedMovie = findViewById(R.id.upcoming_movies)
        Top_ratedMovieAdapter =
            LatestRecyclerView(mutableListOf()) { movie -> showMovieDetails(movie) }
        Top_ratedMovieLayoutmgr = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        Top_ratedMovie.layoutManager = Top_ratedMovieLayoutmgr
        Top_ratedMovie.adapter = Top_ratedMovieAdapter

        popularMovie()
        latestMovie()
        top_rated()
    }

    private  fun popularMovie()
    {
        Apiservicesclass.getPopularMovies(popularMoviepage,::fatchpopularmoviesuccessful,::onError)
    }

    private fun fatchpopularmoviesuccessful(movie: List<Movie>) {
        popularMovieAdapter.appendMovie(movie)
        attachPopularMoviesOnScrollListener()
    }
    private fun attachPopularMoviesOnScrollListener() {
        popularMovie.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = popularMovielayoutmgr.itemCount
                val visibleItemCount = popularMovielayoutmgr.childCount
                val firstVisibleItem = popularMovielayoutmgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    popularMovie.removeOnScrollListener(this)
                    popularMoviepage++
                    popularMovie()
                }
            }
        })
    }

    private fun latestMovie()
    {
        Apiservicesclass.latestMovie(latestMoviepage,::fatchlatestmovie,::onError)
    }
    private fun top_rated(){
        Apiservicesclass.top_rated(top_ratedMovie,::fatchTopmovie,::onError)
    }

    private fun fatchlatestmovie(list: List<Movie>) {
        LatestMovieAdapter.appendMovie(list)
        attachlatestscrollview()
    }
    private fun fatchTopmovie(list: List<Movie>) {
        Top_ratedMovieAdapter.appendMovie(list)
        attachTopscrollview()
    }

    private fun attachTopscrollview() {
        Top_ratedMovie.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = Top_ratedMovieLayoutmgr.itemCount
                val visibleItemCount = Top_ratedMovieLayoutmgr.childCount
                val firstVisibleItem = Top_ratedMovieLayoutmgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    Top_ratedMovie.removeOnScrollListener(this)
                    top_ratedMovie++
                    top_rated()
                }
            }
        })
    }

    private fun attachlatestscrollview() {
        LatestMovie.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = LatestMovieLayoutmgr.itemCount
                val visibleItemCount = LatestMovieLayoutmgr.childCount
                val firstVisibleItem = LatestMovieLayoutmgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    LatestMovie.removeOnScrollListener(this)
                    latestMoviepage++
                    latestMovie()
                }
            }
        })
    }
    private fun showMovieDetails(movie: Movie) {
        val intent = Intent(this, katypary::class.java)
        intent.putExtra(MOVIE_BACKDROP, movie.backdropPath)
        intent.putExtra(MOVIE_POSTER, movie.posterPath)
        intent.putExtra(MOVIE_TITLE, movie.title)
        intent.putExtra(MOVIE_RATING, movie.rating)
        intent.putExtra(MOVIE_RELEASE_DATE, movie.releaseDate)
        intent.putExtra(MOVIE_OVERVIEW, movie.overview)
        intent.putExtra(VIDEO,movie.video)
        val id = movie.id
        intent.putExtra("extra_movie_id",id)
        startActivity(intent)
    }

    fun onError() {
        Toast.makeText(this, getString(R.string.error_fetch_movies), Toast.LENGTH_SHORT).show()
    }


}



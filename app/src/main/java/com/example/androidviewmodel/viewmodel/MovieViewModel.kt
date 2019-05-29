package et.edu.aait.listdetailfragmentappliction.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import et.edu.aait.listdetailfragmentappliction.data.Movie
import et.edu.aait.listdetailfragmentappliction.repository.MovieRepository
import et.edu.aait.roomdbexample.data.MyDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MovieViewModel(application: Application): AndroidViewModel(application) {

    private val movieRepository: MovieRepository

    init {
        val movieDao = MyDatabase.getDatabase(application).movieDao()
        movieRepository = MovieRepository(movieDao)

    }

    fun getMovieByTitle(title: String?): LiveData<Movie> {
       lateinit var movie: LiveData<Movie>
        viewModelScope.launch(Dispatchers.IO){
             movie = movieRepository.getMovieByTitle(title)
        }

        return movie
    }

    fun addMovie(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        movieRepository.addMovie(movie)
    }

    fun updateMovie(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        movieRepository.updateMovie(movie)
    }

    fun deleteMovie(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        movieRepository.deleteMovie(movie)
    }

}
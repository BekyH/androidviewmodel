package et.edu.aait.listdetailfragmentappliction.repository

import androidx.lifecycle.LiveData
import et.edu.aait.listdetailfragmentappliction.data.Movie
import et.edu.aait.listdetailfragmentappliction.data.MovieDao

class MovieRepository(private val movieDao: MovieDao) {

    fun getMovieByTitle(title: String?): LiveData<Movie> {
        return movieDao.getMovieByTitle(title)
    }

    fun addMovie(movie: Movie) {
        movieDao.insertMovie(movie)
    }

    fun updateMovie(movie: Movie) {
        movieDao.updateMovie(movie)
    }

    fun deleteMovie(movie: Movie) {
        movieDao.deleteMovie(movie)
    }
}
package et.edu.aait.listdetailfragmentappliction.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies WHERE  movie_title = :title")
    fun getMovieByTitle(title: String?): LiveData<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Update
    fun updateMovie(movie: Movie)

    @Delete
    fun deleteMovie(movie: Movie)
}
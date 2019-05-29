package et.edu.aait.listdetailfragmentappliction.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName="movies")
data class Movie(@PrimaryKey @ColumnInfo(name="movie_title") val title:String,
                 @ColumnInfo(name="movie_rating") val rating: String,
                 @ColumnInfo(name="move_description") val description: String): Serializable

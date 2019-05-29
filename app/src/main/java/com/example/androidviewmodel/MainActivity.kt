package et.edu.aait.listdetailfragmentappliction

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import et.edu.aait.listdetailfragmentappliction.ManageMovieFragment.OnManageButtonsClicked
import et.edu.aait.listdetailfragmentappliction.data.Movie
import et.edu.aait.listdetailfragmentappliction.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity(), OnManageButtonsClicked{

    private lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_layout)

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        val manageContentFragment = ManageMovieFragment()

        if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.portrait_frame, manageContentFragment)
                .addToBackStack(null)
                .commit()
        }

        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.land_manage_frame, manageContentFragment)
                .addToBackStack(null)
                .commit()
        }

    }

    override fun onAddButtonClicked(movie: Movie) {
        // insert the content passed to database using viewModel
        movieViewModel.addMovie(movie)

        // create DisplayMovieFragment fragment using its factory method
        val displayMovieFragment = DisplayMovieFragment.newInstance(movie.title)

        // display the fragment on the portrait_frame container in portrait mode
        if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.portrait_frame, displayMovieFragment)
                .addToBackStack(null)
                .commit()
        }

        // display the fragment on the land_display_frame container in landscape mode
        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.land_display_frame, displayMovieFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onUpdateButtonClicked(movie: Movie) {
        // update the content passed to database using viewModel
        movieViewModel.updateMovie(movie)

        // create DisplayMovieFragment fragment using its factory method
        val displayMovieFragment = DisplayMovieFragment.newInstance(movie.title)

        // display the fragment on the portrait_frame container in portrait mode
        if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.portrait_frame, displayMovieFragment)
                .addToBackStack(null)
                .commit()
        }

        // display the fragment on the land_display_frame container in landscape mode
        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.land_display_frame, displayMovieFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDeleteButtonClicked(movie: Movie) {
        movieViewModel.deleteMovie(movie)
    }
}

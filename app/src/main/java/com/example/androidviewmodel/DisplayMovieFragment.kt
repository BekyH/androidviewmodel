package et.edu.aait.listdetailfragmentappliction

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import et.edu.aait.listdetailfragmentappliction.data.Movie
import et.edu.aait.listdetailfragmentappliction.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_display_content.view.*

class DisplayMovieFragment : Fragment() {

    private lateinit var movieViewModel: MovieViewModel

    private lateinit var movieTitleTextView: TextView
    private lateinit var movieRatingTextView: TextView
    private lateinit var movieDescriptionTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_display_content, container, false)

        movieTitleTextView = view.display_one_text
        movieRatingTextView = view.display_two_text
        movieDescriptionTextView = view.display_text_three

        val title = arguments?.getString("movie_title")

        val liveMovie = movieViewModel.getMovieByTitle(title)

        liveMovie.observe(this, Observer {
                movieTitleTextView.text = it.title
                movieRatingTextView.text = it.rating
                movieDescriptionTextView.text = it.description
        })

        return view
    }

    companion object {

        fun newInstance(movieTitle: String): DisplayMovieFragment {
            val displayContentFragment = DisplayMovieFragment()

            val args = Bundle()
            args.putString("movie_title", movieTitle)

            displayContentFragment.arguments = args

            return displayContentFragment
        }
    }

}

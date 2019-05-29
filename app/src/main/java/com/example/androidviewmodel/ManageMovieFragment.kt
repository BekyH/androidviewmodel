package et.edu.aait.listdetailfragmentappliction

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import et.edu.aait.listdetailfragmentappliction.data.Movie
import et.edu.aait.listdetailfragmentappliction.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_manage_content.view.*

class ManageMovieFragment : Fragment() {

    private lateinit var movieViewModel: MovieViewModel

    private lateinit var titleEditText: EditText
    private lateinit var ratingEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var searchTitleText: EditText

    private lateinit var listener: OnManageButtonsClicked

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is OnManageButtonsClicked){
            listener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_manage_content, container, false)

        titleEditText = view.content_one_text
        ratingEditText = view.content_two_text
        descriptionEditText = view.content_three_text
        searchTitleText = view.search_content

        val addButton = view.add_button
        val updateButton = view.update_button
        val deleteButton = view.delete_button
        val searchButton = view.search_button

        addButton.setOnClickListener {
            listener.onAddButtonClicked(readFields())
            clearFields()
        }

        updateButton.setOnClickListener {
            listener.onUpdateButtonClicked(readFields())
            clearFields()
        }

        deleteButton.setOnClickListener {
            listener.onDeleteButtonClicked(readFields())
            clearFields()
        }

        searchButton.setOnClickListener {
            val liveMovie = movieViewModel.getMovieByTitle(searchTitleText.toString())
            liveMovie.observe(this, Observer{
                updateFields(Movie(it.title, it.rating, it.description))
            })
        }

        return view
    }

    private fun updateFields(movie: Movie){
        movie.run{
            titleEditText.setText(movie.title)
            ratingEditText.setText(movie.rating)
            descriptionEditText.setText(movie.description)
        }
    }

    private fun readFields() = Movie(
        titleEditText.text.toString(),
        ratingEditText.text.toString(),
        descriptionEditText.text.toString()

    )

    private fun clearFields() {
        searchTitleText.setText("")
        titleEditText.setText("")
        ratingEditText.setText("")
        descriptionEditText.setText("")
    }

    interface OnManageButtonsClicked{
        fun onAddButtonClicked(movie: Movie)
        fun onUpdateButtonClicked(movie: Movie)
        fun onDeleteButtonClicked(movie: Movie)
    }
}

package com.example.bitfit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AddPageFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_page, container, false)
        val submitButton = view.findViewById<Button>(R.id.submitButton)

        submitButton.setOnClickListener {
            // Log.d("test",exerciseName)
            val exerciseName = view.findViewById<EditText>(R.id.exerciseNameTextView).text.toString()
            val exerciseInfo = view.findViewById<EditText>(R.id.exerciseDescriptionTextView).text.toString()


            //save the event to Database
            lifecycleScope.launch(Dispatchers.IO) {
                (activity?.application as MyApplication).db.exerciseDao().insert(
                    ExerciseEntity(exerciseName, exerciseInfo)
                )
            }
        }

        val deleteAllButton = view.findViewById<Button>(R.id.deleteAllButton)

        //delete everything in database
        deleteAllButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                (activity?.application as MyApplication).db.exerciseDao().deleteAll()
            }
        }

        // Inflate the layout for this fragment
        return view
    }

    companion object {
        fun newInstance(): AddPageFragment {
            return AddPageFragment()
        }
    }
}
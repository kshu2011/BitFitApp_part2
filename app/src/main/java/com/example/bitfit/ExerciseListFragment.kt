package com.example.bitfit

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch



class ExerciseListFragment : Fragment() {

    private val exerciseList = mutableListOf<DisplayExercise>()
    private lateinit var exerciseRecyclerView: RecyclerView
    private lateinit var exerciseAdapter: ExerciseAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Call the new method within onViewCreated




    }

    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_exercise_list, container, false)


        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {
            // Change this statement to store the view in a variable instead of a return statement
            val view = inflater.inflate(R.layout.fragment_exercise_list, container, false)

            // Add these configurations for the recyclerView and to configure the adapter
            val layoutManager = LinearLayoutManager(context)
            exerciseRecyclerView = view.findViewById(R.id.exercise_recycler_view)
            exerciseRecyclerView.layoutManager = layoutManager
            exerciseRecyclerView.setHasFixedSize(true)
            exerciseAdapter = ExerciseAdapter(view.context, exerciseList)
            exerciseRecyclerView.adapter = exerciseAdapter


        lifecycleScope.launch {
            (activity?.application as MyApplication).db.exerciseDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    DisplayExercise(
                        entity.exerciseName,
                        entity.exerciseInfo,
                    )
                }.also { mappedList ->
                    exerciseList.clear()
                    exerciseList.addAll(mappedList)
                    exerciseAdapter.notifyDataSetChanged()
                }
            }
        }

            // Update the return statement to return the inflated view from above
            return view
    }

    companion object {
        fun newInstance(): ExerciseListFragment {
            return ExerciseListFragment()
        }
    }
}
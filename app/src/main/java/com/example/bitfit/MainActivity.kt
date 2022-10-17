package com.example.bitfit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bitfit.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    //lateinit var exerciseList: MutableList<DisplayExercise>
    private val exerciseList = mutableListOf<DisplayExercise>()
    //private var exerciseList: MutableList<DisplayExercise>()


    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager: FragmentManager = supportFragmentManager

        // define your fragments here
        val fragment1: Fragment = ExerciseListFragment()
        val fragment2: Fragment = AddPageFragment()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // handle navigation selection
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.action_main_list -> fragment = fragment1
                R.id.action_add_item -> fragment = fragment2
            }
            fragmentManager.beginTransaction().replace(R.id.rlContainer, fragment).commit()
            true
        }

        // Set default selection
        bottomNavigationView.selectedItemId = R.id.action_main_list

//        binding = ActivityMainBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)
//
//        // Call helper method to swap the FrameLayout with the fragment
//        replaceFragment(ExerciseListFragment())

//        findViewById<Button>(R.id.addButton).setOnClickListener{
//            //now let's make this button start up a 'new' activity...
//            val intent = Intent(this, AddActivity::class.java)
//            //intent.putExtra(ARTICLE_EXTRA, article)
//            this.startActivity(intent)
//
//
//            //originally just used the button to add the information
////            val exerciseName = findViewById<EditText>(R.id.exerciseNameEdit).text.toString()
////            val exerciseInfo = findViewById<EditText>(R.id.exerciseDescriptionEdit).text.toString()
////
////            //save the event to Database
////            lifecycleScope.launch(IO) {
////                (application as MyApplication).db.exerciseDao().insert(
////                    ExerciseEntity(exerciseName, exerciseInfo)
////                )
////            }
//        }

//        //get the recyclerView here
//        val exerciseRv = findViewById<RecyclerView>(R.id.exerciseRv)
//        //get the list of exercise
//      //  exerciseList = ExerciseFetcher.getExercises()
//        //now create adapter and pass the list of exercise
//        val adapter = ExerciseAdapter(this,exerciseList)
//        //attach adapter to the recyclerview
//        exerciseRv.adapter = adapter
//
//
//        lifecycleScope.launch {
//            (application as MyApplication).db.exerciseDao().getAll().collect { databaseList ->
//                databaseList.map { entity ->
//                    DisplayExercise(
//                        entity.exerciseName,
//                        entity.exerciseInfo,
//                    )
//                }.also { mappedList ->
//                    exerciseList.clear()
//                    exerciseList.addAll(mappedList)
//                    adapter.notifyDataSetChanged()
//                }
//            }
//        }
//
//
//
//
//
//        //set layout manager to position the items
//        exerciseRv.layoutManager = LinearLayoutManager(this)
    }

    private fun replaceFragment(articleListFragment: ExerciseListFragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.article_frame_layout, articleListFragment)
        fragmentTransaction.commit()
    }
}
package com.davevarga.userdetailsfirebase.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.davevarga.userdetailsfirebase.R
import com.davevarga.userdetailsfirebase.models.User
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private val userRef = db.collection("users")
    private lateinit var userAdapter: UserRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpRecyclerView()

        addUserButton.setOnClickListener {
            val bottomSheetFragment = NewUserFragment()
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }


    }

    fun setUpRecyclerView() {
        val query = userRef.orderBy("lastName", Query.Direction.ASCENDING)
        val options = FirestoreRecyclerOptions.Builder<User>()
            .setQuery(query, User::class.java)
            .build()
        userAdapter = UserRecyclerAdapter(options)
        recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        userAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        userAdapter.stopListening()
    }
}
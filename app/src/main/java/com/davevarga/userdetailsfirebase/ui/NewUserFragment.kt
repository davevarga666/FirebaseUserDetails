package com.davevarga.userdetailsfirebase.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.davevarga.userdetailsfirebase.R
import com.davevarga.userdetailsfirebase.models.User
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.new_user.*

class NewUserFragment : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.new_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveUserButton.setOnClickListener {
            saveUser()

        }

    }

    fun saveUser() {
        if ((enterFirstName.text!!.isEmpty()) or enterLastName.text!!.isEmpty() or enterAge.text!!.isEmpty()) {
            Toast.makeText(activity, "Required", Toast.LENGTH_SHORT).show()
        } else {
            val newUser = User(
                enterFirstName.text.toString(),
                enterLastName.text.toString(),
                enterAge.text.toString().toInt()
            )
            val userRef = FirebaseFirestore.getInstance()
                .collection("users")
            userRef.add(newUser)
            dismiss()

        }
    }
}
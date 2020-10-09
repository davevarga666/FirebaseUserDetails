package com.davevarga.userdetailsfirebase.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.davevarga.userdetailsfirebase.databinding.UserItemBinding
import com.davevarga.userdetailsfirebase.R
import com.davevarga.userdetailsfirebase.models.User
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class UserRecyclerAdapter(options: FirestoreRecyclerOptions<User>) :
    FirestoreRecyclerAdapter<User, UserRecyclerAdapter.UserViewholder>(options) {

    lateinit var binding: UserItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewholder {

        val inflater = LayoutInflater.from(parent.context)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.user_item,
            parent,
            false
        )

        return UserViewholder(binding.root)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: UserViewholder, position: Int, model: User) {
        return holder.bind(model, binding)
    }

    class UserViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: User, binding: UserItemBinding) {
            binding.userItem = user

        }
    }
}
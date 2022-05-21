package uz.gita.contactfirebase.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import uz.gita.contactfirebase.R
import uz.gita.contactfirebase.db.UserEntity
import uz.gita.contactfirebase.model.User

class UserAdapter() : RecyclerView.Adapter<UserViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<UserEntity>() {
        override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity) =
            oldItem.email == newItem.email


        override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity) =
            oldItem == newItem


    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val userList = differ.currentList
        val username: TextView = holder.itemView.findViewById(R.id.username_id)
        val phone: TextView = holder.itemView.findViewById(R.id.phone_id)
        val profile:ImageView = holder.itemView.findViewById(R.id.profile_image)
        username.text = userList[position].userName
        phone.text = userList[position].phone
        profile.setImageResource(R.drawable.ava_profile)

    }

    override fun getItemCount() = differ.currentList.size
}
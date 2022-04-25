package au.swin.firestoreinitdb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import au.swin.firestoreinitdb.R
import au.swin.firestoreinitdb.models.mUserModel
import au.swin.firestoreinitdb.viewHolder.mUsersViewHolder

class mUserAdapter(private val userList: ArrayList<mUserModel>) : RecyclerView.Adapter<mUsersViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mUsersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.muserviewholder, parent, false)
        return mUsersViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: mUsersViewHolder, position: Int) {
        holder.bind(userList.get(position).mUserName)
    }
}
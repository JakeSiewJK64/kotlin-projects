package au.swin.joekanesiew_core3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MedalistAdapter(private val medalList: ArrayList<Medalist>) : RecyclerView.Adapter<MeadalistViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeadalistViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.medalistviewmodel, parent, false) as View
        return MeadalistViewHolder(view)
    }

    override fun onBindViewHolder(holder: MeadalistViewHolder, position: Int) {
        holder.bind(medalList[position])
    }

    override fun getItemCount(): Int {
        return medalList.size
    }
}
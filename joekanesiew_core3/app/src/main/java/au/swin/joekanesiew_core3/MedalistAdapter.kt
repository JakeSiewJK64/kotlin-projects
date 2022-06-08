package au.swin.joekanesiew_core3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MedalistAdapter(private val medalList: ArrayList<Medalist>) : RecyclerView.Adapter<MedalistViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedalistViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.medalistviewmodel, parent, false) as View
        return MedalistViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedalistViewHolder, position: Int) {
        holder.bind(medalList[position])
    }

    override fun getItemCount(): Int {
        return medalList.size
    }
}
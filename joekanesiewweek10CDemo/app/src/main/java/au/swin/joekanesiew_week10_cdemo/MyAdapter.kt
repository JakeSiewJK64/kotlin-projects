package au.swin.joekanesiew_week10_cdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MyViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val countryName: TextView = view.findViewById(R.id.txt_name)
    private val medalNum: TextView = view.findViewById(R.id.txt_desc)
    fun bind(myWord: MyWord) {
        countryName.text = myWord.countryName
        medalNum.text = myWord.medalNum.toString()

    }
}

class MyAdapter(private val mList: MutableList<MyWord>) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cell = layoutInflater.inflate(R.layout.item_viewmodel, parent, false)
        return MyViewHolder(cell)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(mList[position])
        holder.itemView.setOnClickListener {
            Toast.makeText(

                "You have selected ${myWord.countryName}\nMedals: ${myWord.medalNum}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}
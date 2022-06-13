package au.swin.joekanesiew_customapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter(
    private val recipeList: ArrayList<Recipe>,
    private val fragmentManager: FragmentManager
) : RecyclerView.Adapter<RecipeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recipeviewmodel, parent, false) as View
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipeList[position], fragmentManager)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

}
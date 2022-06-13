package au.swin.joekanesiew_customapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var viewEventButton: Button
    private lateinit var viewRecipesButton: Button
    private lateinit var newRecipeButton: Button

    private fun changeFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.recipeFragmentFrame, fragment)
            commit()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewEventButton = view.findViewById(R.id.viewEventLogButton)
        viewRecipesButton = view.findViewById(R.id.viewRecipeButton)
        newRecipeButton = view.findViewById(R.id.viewRecipeEditorButton)

        viewEventButton.setOnClickListener {
            changeFragment(EventLogFragment())
        }

        viewRecipesButton.setOnClickListener {
            changeFragment(RecipeListFragment())
        }

        newRecipeButton.setOnClickListener {
            changeFragment(NewRecipeFragment())
        }
    }
}
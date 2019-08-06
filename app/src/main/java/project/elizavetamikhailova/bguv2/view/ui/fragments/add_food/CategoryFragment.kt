package project.elizavetamikhailova.bguv2.view.ui.fragments.add_food


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment

import project.elizavetamikhailova.bguv2.databinding.FragmentCategoryBinding
import project.elizavetamikhailova.bguv2.view.adapters.CategoryRecyclerViewAdapter
import project.elizavetamikhailova.bguv2.view.uimodels.Category
import project.elizavetamikhailova.bguv2.view.ui.view_models.add_food.CategoryViewModel
import javax.inject.Inject


class CategoryFragment : DaggerFragment(), CategoryRecyclerViewAdapter.OnItemClickListener {
    override fun onItemClick(category: Category) {
        val action =
            CategoryFragmentDirections.actionCategoryFragmentToProductFragment(category)
        action.category = category
        findNavController().navigate(action)
    }

    private val categoryRecyclerViewAdapter =
        CategoryRecyclerViewAdapter(arrayListOf(), this)

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewDataBinding: FragmentCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentCategoryBinding.inflate(inflater, container, false).apply {
            viewModel = ViewModelProviders.of(this@CategoryFragment, viewModelFactory)
                .get(CategoryViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel =  ViewModelProviders.of(this, viewModelFactory)
            .get(CategoryViewModel::class.java)
        viewDataBinding.viewModel = viewModel
        viewDataBinding.executePendingBindings()
        viewModel.loadCategories()
        viewDataBinding.recyclerView.layoutManager = LinearLayoutManager(activity)
        viewDataBinding.recyclerView.adapter = categoryRecyclerViewAdapter
        viewModel.categories.observe(viewLifecycleOwner,
            Observer<List<Category>> { it?.let{ categoryRecyclerViewAdapter.replaceData(it)} })
    }


}

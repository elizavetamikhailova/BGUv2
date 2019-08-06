package project.elizavetamikhailova.bguv2.view.ui.fragments.add_food

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import project.elizavetamikhailova.bguv2.databinding.FragmentUserProductBinding
import project.elizavetamikhailova.bguv2.helpers.add_food.Dayble
import project.elizavetamikhailova.bguv2.view.adapters.UserProductRecyclerViewAdapter
import project.elizavetamikhailova.bguv2.view.ui.view_models.add_food.UserProductsViewModel
import project.elizavetamikhailova.bguv2.view.uimodels.UserProduct
import javax.inject.Inject


class UserProductFragment : DaggerFragment(), UserProductRecyclerViewAdapter.OnItemClickListener {
    override fun onItemClick(userProduct: UserProduct) {

    }

    val userProductsRecyclerViewAdapter = UserProductRecyclerViewAdapter(arrayListOf(), this)

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewDataBinding: FragmentUserProductBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentUserProductBinding.inflate(inflater, container, false).apply {
            viewModel = ViewModelProviders.of(this@UserProductFragment, viewModelFactory)
                .get(UserProductsViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel =  ViewModelProviders.of(this, viewModelFactory)
            .get(UserProductsViewModel::class.java)
        viewDataBinding.viewModel = viewModel
        viewDataBinding.executePendingBindings()
        viewModel.loadUserProducts((activity as? Dayble)!!.getDay())
        viewDataBinding.recyclerView.layoutManager = LinearLayoutManager(activity)
        viewDataBinding.recyclerView.adapter = userProductsRecyclerViewAdapter
        viewModel.userProducts.observe(viewLifecycleOwner,
            Observer<List<UserProduct>> { it?.let{ userProductsRecyclerViewAdapter.replaceData(it)} })
    }

}

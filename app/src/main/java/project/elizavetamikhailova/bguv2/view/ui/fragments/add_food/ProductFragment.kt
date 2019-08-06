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
import project.elizavetamikhailova.bguv2.databinding.FragmentProductBinding
import project.elizavetamikhailova.bguv2.view.adapters.ProductsRecyclerViewAdapter
import project.elizavetamikhailova.bguv2.view.ui.view_models.add_food.ProductViewModel
import project.elizavetamikhailova.bguv2.view.uimodels.Product
import javax.inject.Inject

class ProductFragment : DaggerFragment(), ProductsRecyclerViewAdapter.OnItemClickListener {
    override fun onItemClick(product: Product) {
        val action =
            ProductFragmentDirections.actionProductFragmentToWeightFragment(product)
        action.product = product
        findNavController().navigate(action)
    }

    val productsRecyclerViewAdapter = ProductsRecyclerViewAdapter(arrayListOf(), this)

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewDataBinding: FragmentProductBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentProductBinding.inflate(inflater, container, false).apply {
            viewModel = ViewModelProviders.of(this@ProductFragment, viewModelFactory)
                .get(ProductViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel =  ViewModelProviders.of(this, viewModelFactory)
            .get(ProductViewModel::class.java)
        viewDataBinding.viewModel = viewModel
        viewDataBinding.executePendingBindings()
        val category = ProductFragmentArgs.fromBundle(arguments!!).category
        viewModel.loadProducts(category)
        viewDataBinding.recyclerView.layoutManager = LinearLayoutManager(activity)
        viewDataBinding.recyclerView.adapter = productsRecyclerViewAdapter
        viewModel.products.observe(viewLifecycleOwner,
            Observer<List<Product>> { it?.let{ productsRecyclerViewAdapter.replaceData(it)} })
    }
}

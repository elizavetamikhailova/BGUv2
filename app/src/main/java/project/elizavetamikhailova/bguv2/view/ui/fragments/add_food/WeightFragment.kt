package project.elizavetamikhailova.bguv2.view.ui.fragments.add_food

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import dagger.android.support.DaggerFragment
import project.elizavetamikhailova.bguv2.databinding.FragmentWeightBinding
import project.elizavetamikhailova.bguv2.helpers.add_food.Dayble
import project.elizavetamikhailova.bguv2.view.ui.view_models.add_food.WeightViewModel
import project.elizavetamikhailova.bguv2.view.uimodels.Product
import javax.inject.Inject

class WeightFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewDataBinding: FragmentWeightBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentWeightBinding.inflate(inflater, container, false).apply {
            viewModel = ViewModelProviders.of(this@WeightFragment, viewModelFactory)
                .get(WeightViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel =  ViewModelProviders.of(this, viewModelFactory)
            .get(WeightViewModel::class.java)
        viewDataBinding.viewModel = viewModel
        viewDataBinding.executePendingBindings()
        val product = WeightFragmentArgs.fromBundle(arguments!!).product

        viewModel.product.value = product
        viewModel.currentProduct.set(product)
        viewDataBinding.product = viewModel.product.value


        viewModel.day = (activity as? Dayble)!!.getDay()

        viewModel.product.observe(viewLifecycleOwner, Observer<Product>{
            viewDataBinding.product = viewModel.product.value
        })

        viewModel.isInserted.observe(viewLifecycleOwner,
            Observer<Boolean> { it?.let{ it ->
                if(it){
                    val action = WeightFragmentDirections.actionWeightFragmentToUserProductFragment()
                    findNavController().navigate(action)
                }
            } })
    }

}

package project.elizavetamikhailova.bguv2.view.ui.fragments.statistic


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
import project.elizavetamikhailova.bguv2.databinding.FragmentWeekStatisticBinding
import project.elizavetamikhailova.bguv2.databinding.WeekItemBinding
import project.elizavetamikhailova.bguv2.helpers.add_food.Dayble
import project.elizavetamikhailova.bguv2.helpers.statistic.StatisticComposite
import project.elizavetamikhailova.bguv2.view.adapters.WeekStatisticRVAdapter
import project.elizavetamikhailova.bguv2.view.ui.view_models.add_food.UserProductsViewModel
import project.elizavetamikhailova.bguv2.view.ui.view_models.statistic.WeekStatisticViewModel
import javax.inject.Inject

class WeekStatisticFragment : DaggerFragment() {

    val adapter = WeekStatisticRVAdapter(arrayListOf())

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewDataBinding: FragmentWeekStatisticBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentWeekStatisticBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(WeekStatisticViewModel::class.java)
        viewDataBinding.recyclerView.layoutManager =  LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        viewDataBinding.recyclerView.adapter = adapter
        viewDataBinding.viewModel = viewModel
        viewDataBinding.user = viewModel.user
        viewModel.stat.observe(viewLifecycleOwner,
            Observer<List<StatisticComposite>> { it?.let{
                adapter.replaceData(it)
            } })
        viewModel.loadUserProduct((activity as? Dayble)!!.getDay())
    }


}

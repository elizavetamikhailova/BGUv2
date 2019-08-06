package project.elizavetamikhailova.bguv2.view.ui.fragments.statistic


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_statistic.*
import project.elizavetamikhailova.bguv2.databinding.FragmentStatisticBinding
import project.elizavetamikhailova.bguv2.helpers.add_food.Dayble
import project.elizavetamikhailova.bguv2.view.ui.view_models.statistic.StatisticViewModel
import project.elizavetamikhailova.bguv2.view.uimodels.DayStatistic
import project.elizavetamikhailova.bguv2.view.uimodels.KcalsStatistic
import javax.inject.Inject
import androidx.navigation.fragment.findNavController
import project.elizavetamikhailova.bguv2.R





class StatisticFragment : DaggerFragment(), View.OnClickListener {
    override fun onClick(v: View) {
        Log.i("KKALS", v.id.toString())
        val action =
            when (v.id) {
                R.id.month -> R.id.action_global_monthStatisticFragment
                R.id.week -> R.id.action_global_weekStatisticFragment
                else -> return
            }
        findNavController().navigate(action)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewDataBinding: FragmentStatisticBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentStatisticBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(StatisticViewModel::class.java)
        viewDataBinding.executePendingBindings()

        month.setOnClickListener(this)
        week.setOnClickListener(this)

        viewModel.protsStatistic.observe(viewLifecycleOwner,
            Observer<DayStatistic> {
                it?.let {
                    viewDataBinding.protsStatistic = it
                }
            })

        viewModel.fatsStatistic.observe(viewLifecycleOwner,
            Observer<DayStatistic> {
                it?.let {
                    viewDataBinding.fatsStatistic = it
                }
            })

        viewModel.carbsStatistic.observe(viewLifecycleOwner,
            Observer<DayStatistic> {
                it?.let {
                    viewDataBinding.carbsStatistic = it
                }
            })

        viewModel.kcalsStatistic.observe(viewLifecycleOwner,
            Observer<KcalsStatistic> {
                it?.let {
                    viewDataBinding.kcalsStatistic = it
                }
            })

        viewModel.loadUserProducts((activity as? Dayble)!!.getDay())

    }


}

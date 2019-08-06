package project.elizavetamikhailova.bguv2.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import project.elizavetamikhailova.bguv2.databinding.WeekItemBinding
import project.elizavetamikhailova.bguv2.helpers.statistic.StatisticComposite

class WeekStatisticRVAdapter (var items: List<StatisticComposite>)
    : RecyclerView.Adapter<WeekStatisticRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = WeekItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bind(items[position])

    override fun getItemCount(): Int = items.size


    fun replaceData(list: List<StatisticComposite>) {
        items = list
        notifyDataSetChanged()
    }

    class ViewHolder(private var binding: WeekItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(statisticComposite: StatisticComposite) {
            binding.apply {
                protsStatistic = statisticComposite.protsStatistic
                fatsStatistic = statisticComposite.fatsStatistic
                carbsStatistic = statisticComposite.carbsStatistic
                kcalsStatistic = statisticComposite.kcalsStatistic
                //day =
                executePendingBindings()
            }

        }
    }

}
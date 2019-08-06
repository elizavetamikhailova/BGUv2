package project.elizavetamikhailova.bguv2.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import project.elizavetamikhailova.bguv2.databinding.UserProductBinding
import project.elizavetamikhailova.bguv2.view.uimodels.Category
import project.elizavetamikhailova.bguv2.view.uimodels.UserProduct

class UserProductRecyclerViewAdapter (var items: List<UserProduct>,
                                      var listener: OnItemClickListener)
    : RecyclerView.Adapter<UserProductRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = UserProductBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bind(items[position], listener)

    override fun getItemCount(): Int = items.size

    interface OnItemClickListener {
        fun onItemClick(userProduct: UserProduct)
    }

    fun replaceData(list: List<UserProduct>) {
        items = list
        notifyDataSetChanged()
    }

    class ViewHolder(private var binding: UserProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(userProduct: UserProduct, listener: OnItemClickListener?) {
            binding.product = userProduct
            if (listener != null) {
                binding.root.setOnClickListener { listener.onItemClick(userProduct) }
            }

            binding.executePendingBindings()
        }
    }

}
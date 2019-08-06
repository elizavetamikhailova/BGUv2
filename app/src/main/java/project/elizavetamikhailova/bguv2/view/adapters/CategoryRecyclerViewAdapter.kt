package project.elizavetamikhailova.bguv2.view.adapters

import androidx.recyclerview.widget.RecyclerView


import android.view.LayoutInflater
import android.view.ViewGroup
import project.elizavetamikhailova.bguv2.databinding.CategoryBinding
import project.elizavetamikhailova.bguv2.view.uimodels.Category


class CategoryRecyclerViewAdapter(var items: List<Category>,
                                  var listener: OnItemClickListener)
    : RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CategoryBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bind(items[position], listener)

    override fun getItemCount(): Int = items.size

    interface OnItemClickListener {
        fun onItemClick(category: Category)
    }

    fun replaceData(list: List<Category>) {
        items = list
        notifyDataSetChanged()
    }

    class ViewHolder(private var binding: CategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category, listener: OnItemClickListener?) {
            binding.category = category
            if (listener != null) {
                binding.root.setOnClickListener { listener.onItemClick(category) }
            }

            binding.executePendingBindings()
        }
    }

}
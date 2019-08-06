package project.elizavetamikhailova.bguv2.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import project.elizavetamikhailova.bguv2.databinding.ProductBinding
import project.elizavetamikhailova.bguv2.view.uimodels.Product

class ProductsRecyclerViewAdapter (var items: List<Product>,
                                   var listener: OnItemClickListener)
    : RecyclerView.Adapter<ProductsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ProductBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bind(items[position], listener)

    override fun getItemCount(): Int = items.size

    interface OnItemClickListener {
        fun onItemClick(product: Product)
    }

    fun replaceData(list: List<Product>) {
        items = list
        notifyDataSetChanged()
    }

    class ViewHolder(private var binding: ProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product, listener: OnItemClickListener?) {
            binding.product = product
            if (listener != null) {
                binding.root.setOnClickListener { listener.onItemClick(product) }
            }

            binding.executePendingBindings()
        }
    }
}
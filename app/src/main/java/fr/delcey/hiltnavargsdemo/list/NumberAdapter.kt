package fr.delcey.hiltnavargsdemo.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fr.delcey.hiltnavargsdemo.R
import fr.delcey.hiltnavargsdemo.data.IndexedNumber

class NumberAdapter(
    private val listener: (numberId: Int) -> Unit
) : ListAdapter<IndexedNumber, NumberAdapter.NumberViewHolder>(NumberDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NumberViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.main_item, parent, false))

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class NumberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView = itemView.findViewById<TextView>(R.id.main_item_tv)
        private val cardView = itemView.findViewById<CardView>(R.id.main_item_cv)

        fun bind(item: IndexedNumber, listener: (index: Int) -> Unit) {
            textView.text = item.value
            cardView.setOnClickListener {
                listener.invoke(item.numberId)
            }
        }
    }

    object NumberDiffCallback : DiffUtil.ItemCallback<IndexedNumber>() {
        override fun areItemsTheSame(oldItem: IndexedNumber, newItem: IndexedNumber): Boolean {
            return oldItem.numberId == newItem.numberId
        }

        override fun areContentsTheSame(oldItem: IndexedNumber, newItem: IndexedNumber): Boolean {
            return oldItem == newItem
        }
    }
}

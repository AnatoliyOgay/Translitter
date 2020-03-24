package kz.app.anatoliy.converter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*
import kz.app.anatoliy.common.database.entities.Item

class VocabularyAdapter(val callback: Callback): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val itemList = mutableListOf<Item>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
    override fun getItemCount(): Int = itemList.size
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).bind(itemList[position])
    }
    inner class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private var cyrillText = itemView.tw_cyrillic
        private var latinText = itemView.tw_latinic
        fun bind(item: Item){
            cyrillText.text = item.entered
            latinText.text = item.translated
            itemView.button_delete.setOnClickListener{
                if (adapterPosition != RecyclerView.NO_POSITION)
                    callback.onClick(itemList[adapterPosition])
            }
        }
    }
    interface Callback{
        fun onClick(item: Item)
    }

    fun addList(items: List<Item>){
        itemList.clear()
        itemList.addAll(items)
        notifyDataSetChanged()
    }
    fun remove(item: Item){
        itemList.remove(item)
        notifyDataSetChanged()
    }
    fun clear(){
        itemList.clear()
        notifyDataSetChanged()
    }
}


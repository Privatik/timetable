package com.io.fizmat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.io.fizmat.R
import com.io.fizmat.navigation.Navigation
import com.io.fizmat.util.UtilToast
import com.io.fizmat.worktotime.DayOfWeek
import com.io.fizmat.xlsreader.model.Day
import com.io.fizmat.xlsreader.model.Group

class AdapterGroup(val listGroup: List<Group>) : RecyclerView.Adapter<AdapterGroup.ItemRecycleView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRecycleView
     = ItemRecycleView(LayoutInflater.from(parent.context).inflate(R.layout.itemrecycleview_button,parent,false))

    override fun getItemCount(): Int = listGroup.size

    override fun onBindViewHolder(holder: ItemRecycleView, position: Int) {
       holder.textButton(listGroup.get(position))
    }

    class ItemRecycleView(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener
    {
        val buttom = itemView.findViewById<Button>(R.id.button)
        lateinit var group: Group

        init {
            buttom.setOnClickListener(this)
        }

        fun textButton(group: Group) {
         buttom.text = group.nameGroup
            this.group = group
       }

        override fun onClick(v: View?) {
                DayOfWeek.dayofWeek()
                Navigation.showDialogTimeTable(group)
        }
    }
}
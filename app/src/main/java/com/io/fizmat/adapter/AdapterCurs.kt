package com.io.fizmat.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.io.fizmat.R
import com.io.fizmat.navigation.Navigation
import com.io.fizmat.worktotime.CurrentOccupation
import com.io.fizmat.xlsreader.model.Curs
import com.io.fizmat.xlsreader.model.Group

class AdapterCurs(val list:List<Curs>) : RecyclerView.Adapter<AdapterCurs.ItemRecycleView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRecycleView
     = ItemRecycleView(LayoutInflater.from(parent.context).inflate(R.layout.itemrecycleview_button,parent,false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ItemRecycleView, position: Int) {
        holder.textButton(list.get(position))
    }

    class ItemRecycleView(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener
    {
        val buttom = itemView.findViewById<Button>(R.id.button)
        lateinit var listGroup: List<Group>

        init {
            buttom.setOnClickListener(this)
        }

        fun textButton(curs : Curs) {
            if (curs.cursTitle.contains("2 курс"))
                buttom.text = " " + curs.cursTitle
            else
                buttom.text = curs.cursTitle

            listGroup = curs.groupList
       }

        override fun onClick(v: View?) {
            Log.d("Curs","${buttom.text}")
            if (buttom.text.equals(" маг 1-2 курс") && (CurrentOccupation.shirt == 1 || CurrentOccupation.shirt == 3))
                CurrentOccupation.shirt = 2
            else if (buttom.text.equals("ИнОб") && (CurrentOccupation.shirt == 2 || CurrentOccupation.shirt == 1))
                CurrentOccupation.shirt = 3
            else if (CurrentOccupation.shirt == 2 || CurrentOccupation.shirt == 3)
                CurrentOccupation.shirt = 1

            Navigation.showFragmetnGroup(listGroup)
        }
    }
}
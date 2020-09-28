package com.io.fizmat.view.fragment

import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.io.fizmat.R
import com.io.fizmat.adapter.AdapterGroup
import com.io.fizmat.xlsreader.model.Group

class FragmentGroup : FragmentMain() {

    var listGroup: List<Group> = arrayListOf()

    override fun backImage(back: ImageButton)
    {
        back.setImageResource(R.drawable.ic_arrow_back_black_24dp)
    }

    override fun workRecycltView(recyclerView: RecyclerView?) {
        recyclerView?.adapter = AdapterGroup(listGroup)
        super.workRecycltView(recyclerView)
    }

    override fun daggerInit(arguments: Bundle?) {
        if (arguments != null) {
            listGroup = arguments.getSerializable("groups") as List<Group>
        }
    }
}
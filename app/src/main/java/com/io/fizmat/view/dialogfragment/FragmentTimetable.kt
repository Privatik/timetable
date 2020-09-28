package com.io.fizmat.view.dialogfragment

import android.content.DialogInterface
import com.io.fizmat.R

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.io.fizmat.adapter.AdapterTabDialog
import com.io.fizmat.navigation.Navigation
import com.io.fizmat.util.UtilToast
import com.io.fizmat.worktotime.DayOfWeek
import com.io.fizmat.xlsreader.model.Day
import com.io.fizmat.xlsreader.model.Group
import java.lang.Exception

class FragmentTimetable: DialogFragment(), View.OnClickListener {

    lateinit var group: Group

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            group = arguments!!.getSerializable("days") as Group
           setStyle(STYLE_NORMAL,R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

            val view = inflater.inflate(R.layout.fragment_timetable, container, false)
            dialog?.window!!.attributes.windowAnimations = R.style.DialogAnimation
        dialog?.window!!.setBackgroundDrawableResource(android.R.color.transparent)


            val back = view.findViewById<ImageButton>(R.id.back)
            val tab = view.findViewById<TabLayout>(R.id.days)
            val curs = view.findViewById<ImageButton>(R.id.curs)
            val viewpager = view.findViewById<ViewPager>(R.id.occupation)
            val groupTextView = view.findViewById<TextView>(R.id.group)

            groupTextView.text = group.nameGroup

            viewpager.adapter = AdapterTabDialog(childFragmentManager, group.dayList)
            tab.setupWithViewPager(viewpager)

            val days = arrayListOf("Пн", "Вт", "Ср", "Чт", "Пт", "Сб")
            for (i in 0 until tab.tabCount) {
                val tabItem = tab.getTabAt(i)
                tabItem?.setCustomView(R.layout.tabitem)
                val textView = tabItem?.customView?.findViewById<TextView>(R.id.day)
                textView?.text = days.get(i)
                if (i == DayOfWeek.day) {
                    textView?.textSize = 25f
                }

            }

            initialTabLayout(tab)
                Log.d("DayOfWeekUpdate","instal")
                viewpager.currentItem = DayOfWeek.day

            back.setOnClickListener(this)
            curs.setOnClickListener(this)

        return view
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        Navigation.showDialog = false
    }

    override fun onStart() {
        super.onStart()
        if (dialog != null)
        {
           dialog!!.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        }
    }


    override fun onClick(v: View?) {
        Log.d("Click","Dialog")
        val id = v?.id
        if (id == R.id.back)
            dialog?.cancel()
        else if (id == R.id.curs)
        {
            dialog?.cancel()
            Navigation.showFragmentCurs()
        }
    }

    private fun initialTabLayout(tab: TabLayout)
    {

        tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener
        {
            var count = 0
            override fun onTabReselected(p0: TabLayout.Tab?) {
                Log.d("Tab","onTabReselected ${p0?.position}")
                if (p0?.position == 5) {
                    count++
                    if (count == 2)
                        UtilToast.show("Ещё 1 раз")
                    if (count == 3)
                    {
                        count = 0
                        Navigation.showInstagram()
                    }
                }
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
                Log.d("Tab","onTabUnselected ${p0?.position}")
                textInit(true,p0)
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                Log.d("Tab","onTabSelected ${p0?.position}")
                textInit(false,p0)
                if (count > 0) count = 0
            }

            private fun textInit(bool: Boolean,p0: TabLayout.Tab?)
            {
                val textView =  p0?.customView?.findViewById<TextView>(R.id.day)
               if (bool) textView?.textSize = 17f
               else textView?.textSize = 25f
            }
        })
    }
}
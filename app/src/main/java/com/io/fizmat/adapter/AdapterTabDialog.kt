package com.io.fizmat.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.io.fizmat.view.fragment.FragmentViewPager
import com.io.fizmat.xlsreader.model.Day

class AdapterTabDialog(fm : FragmentManager,val listDay : List<Day>) : FragmentPagerAdapter(fm){

    override fun getItem(position: Int): Fragment = FragmentViewPager(listDay[position],position)

    override fun getCount(): Int = 6



}
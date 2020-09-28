package com.io.fizmat.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.io.fizmat.R
import com.io.fizmat.util.UtilToast
import com.io.fizmat.view.dialogfragment.FragmentInstagram
import com.io.fizmat.view.dialogfragment.FragmentTimetable
import com.io.fizmat.view.fragment.FragmentCurs
import com.io.fizmat.view.fragment.FragmentGroup
import com.io.fizmat.xlsreader.model.Day
import com.io.fizmat.xlsreader.model.Group
import java.io.Serializable
import java.lang.Exception


object Navigation {

    private lateinit var activity : AppCompatActivity
    private var fragmentGroupOpen = false
    private val content = R.id.maincontent
    var showDialog = false

    fun start(activity: AppCompatActivity)
    {
        this.activity = activity
        Navigation.activity.supportFragmentManager.beginTransaction()
            .add(content,FragmentCurs())
            .commit()
    }

    fun showFragmetnGroup(listGroup: List<Group>)
    {
        val bundle = Bundle()
        bundle.putSerializable("groups",listGroup as Serializable)
        val fragment = FragmentGroup()
        fragment.arguments = bundle

       activity.supportFragmentManager.beginTransaction()
           .setCustomAnimations(R.animator.slide_back_curs,R.animator.slide_start_curs)
           .replace(content, fragment)
           .commit()

        if (!fragmentGroupOpen) fragmentGroupOpen = true
    }

    fun showFragmentCurs()
    {
        activity.supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.animator.slide_back_group,R.animator.slide_start_group)
            .replace(content,FragmentCurs())
            .commit()

        if (fragmentGroupOpen) fragmentGroupOpen = false
    }

    fun showDialogTimeTable(group: Group)
    {
            if (!showDialog) {
                showDialog = true
                val bundle = Bundle()
                bundle.putSerializable("days", group as Serializable)
                val timeTable = FragmentTimetable()
                timeTable.arguments = bundle
                timeTable.show(activity.supportFragmentManager.beginTransaction(), "timetable")
            }
    }

    fun showInstagram()
    {
        val instagram = FragmentInstagram()
        instagram.show(activity.supportFragmentManager.beginTransaction(), "instagram")
    }

    fun getFragmentGroupOpen():Boolean = fragmentGroupOpen
}
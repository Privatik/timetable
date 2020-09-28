package com.io.fizmat.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.io.fizmat.R
import com.io.fizmat.navigation.Navigation
import com.io.fizmat.util.UtilToast
import com.io.fizmat.view.fragment.FragmentCurs
import kotlinx.android.synthetic.main.activitymain.*
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activitymain)
        UtilToast.activity = this
      //  val xlsReader = XLSReaderBRSU.newInstance(URL("http://www.brsu.by/sites/default/files/phys/06_proekt.xls").openStream())
            Navigation.start(this)
    }

    override fun onBackPressed() {
        if (!Navigation.getFragmentGroupOpen()) {
            super.onBackPressed()
        }
        else {
            Navigation.showFragmentCurs()
        }
    }
}
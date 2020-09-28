package com.io.fizmat.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.io.fizmat.R

open class FragmentMain : Fragment() , View.OnClickListener{

  //  @Inject lateinit var listCurs: List<Curs>

    override fun onCreate(savedInstanceState: Bundle?) {
        daggerInit(arguments)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_fragment_with_button,container,false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycleview)
        val back = view.findViewById<ImageButton>(R.id.back)

        back.setOnClickListener(this)

        Log.d("TAG","onCreateView")
        backImage(back)
        workRecycltView(recyclerView)

        return view
    }

    protected open fun workRecycltView(recyclerView: RecyclerView?) {
           recyclerView?.layoutManager = LinearLayoutManager(activity?.baseContext,LinearLayoutManager.VERTICAL,false)
           recyclerView?.setHasFixedSize(true)
    }

    protected open fun backImage(back: ImageButton) {

    }

    protected open fun daggerInit(arguments: Bundle?) {

    }

    override fun onClick(v: View?) {
        Log.d("Click","Screen")
        val id = v?.id
        if (id == R.id.back)
            activity?.onBackPressed()
    }

}
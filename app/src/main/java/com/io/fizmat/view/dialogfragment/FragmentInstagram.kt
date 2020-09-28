package com.io.fizmat.view.dialogfragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.DialogFragment
import com.io.fizmat.R
import java.net.URL

class FragmentInstagram: DialogFragment(), View.OnClickListener  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_instagram,container,false)
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.emptyfon)

        val imageButton = view.findViewById<ImageButton>(R.id.inst)

        imageButton.setOnClickListener(this)
        return view
    }

    override fun onClick(v: View?) {
        val id = v!!.id
        if (id == R.id.inst)
        {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/utf_32/")))
        }
    }

    override fun onStart() {
        super.onStart()
        if (dialog != null)
        {
            dialog!!.window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }
}
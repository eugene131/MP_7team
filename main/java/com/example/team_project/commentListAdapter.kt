package com.example.team_project

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import java.nio.file.Files.size

class commentListAdapter (val context: Context, val coment_list: ArrayList<coment_list>) : BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        /* LayoutInflater는 item을 Adapter에서 사용할 View로 부풀려주는(inflate) 역할을 한다. */
        val view: View = LayoutInflater.from(context).inflate(R.layout.comment_list, null)

        /* 위에서 생성된 view를 res-layout-main_lv_item.xml 파일의 각 View와 연결하는 과정이다. */

        val coment = view.findViewById<TextView>(R.id.coment)
        val coment_year = view.findViewById<TextView>(R.id.ComentYearTV)
        val coment_name = view.findViewById<TextView>(R.id.ComentnameTv)

        /* ArrayList<Dog>의 변수 dog의 이미지와 데이터를 ImageView와 TextView에 담는다. */
        val coment_list = coment_list[position]
        val resourceId = context.resources.getIdentifier(coment_list.name, "drawable", context.packageName)

        coment.text = coment_list.coment
        coment_year.text = coment_list.year
        coment_name.text = coment_list.name

        return view
    }

    override fun getItem(position: Int): Any {
        return coment_list[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return coment_list.size
    }
}
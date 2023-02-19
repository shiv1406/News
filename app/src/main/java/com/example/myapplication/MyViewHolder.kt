package com.example.myapplication

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var imageView: ImageView
    var textView1:TextView
    var channelName:TextView
    var time:TextView
    var ll:LinearLayout

    init {
        imageView = itemView.findViewById(R.id.imageView)
        textView1=itemView.findViewById(R.id.textView1)
        channelName=itemView.findViewById(R.id.channelName)
        time=itemView.findViewById(R.id.time)
        ll=itemView.findViewById(R.id.ll)
    }
}

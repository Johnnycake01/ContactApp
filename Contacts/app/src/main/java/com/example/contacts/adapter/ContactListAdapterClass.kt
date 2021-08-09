package com.example.contacts.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.R
import com.example.contacts.dataClass.ContactContent
import com.example.contacts.inteface.OnItemClick

class ContactListAdapterClass(var items:List<ContactContent>, var listener: OnItemClick):
    RecyclerView.Adapter<ContactListAdapterClass.ViewHolder>() {
inner class ViewHolder(someItemView: View) :RecyclerView.ViewHolder(someItemView),
 View.OnClickListener {
    val image = someItemView.findViewById<ImageView>(R.id.image_res)
    var text = someItemView.findViewById<TextView>(R.id.textu)
    init {
        someItemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val position:Int = adapterPosition
        if (position != RecyclerView.NO_POSITION){
            listener.itemClickes(position)
        }

    }

}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val viewing = LayoutInflater.from(parent.context).
      inflate(R.layout.contact_list_layout,parent,false)
        return ViewHolder(viewing)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentPosition = items[position]

        holder.image.setImageResource(currentPosition.image)
        holder.text.text = currentPosition.name
    }

    override fun getItemCount(): Int {
        return items.size
    }

}


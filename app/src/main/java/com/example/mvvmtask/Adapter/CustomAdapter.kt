package com.example.retrfoit
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmtask.Data.Data
import com.example.mvvmtask.R

class CustomAdapter(var data:ArrayList<Data>, var productsFragment: View) : RecyclerView.Adapter<CustomAdapter.ViewHolder>(){
    class ViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView){

        val name : TextView = itemView.findViewById(R.id.name)
        val quantity : TextView = itemView.findViewById(R.id.quantity)
        val image : ImageView = itemView.findViewById(R.id.image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_view,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var list = data[position]
        holder.name.text = "Name : ${list.name}"
        holder.quantity.text = "quantity : ${list.quantity}"
        Glide.with(productsFragment).load(list.image).into(holder.image)
    }

    override fun getItemCount(): Int {
        return  data.size
    }
}
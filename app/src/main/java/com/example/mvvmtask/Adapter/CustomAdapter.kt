package com.example.retrfoit
import android.app.Notification
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmtask.Data.ProductsData
import com.example.mvvmtask.R
import com.example.mvvmtask.UI.ProductsFragmentDirections

class CustomAdapter(var data:ArrayList<ProductsData>, var productsFragment: View) : RecyclerView.Adapter<CustomAdapter.ViewHolder>(){
    class ViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView){

        val name : TextView = itemView.findViewById(R.id.name)
        val description : TextView = itemView.findViewById(R.id.description)
        val image : ImageView = itemView.findViewById(R.id.image)
        val button: Button = itemView.findViewById(R.id.btn)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_view,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var list = data[position]
        holder.name.text = "  Name : ${list.name}"
        holder.description.text = "Description : ${list.description}"
        Glide.with(productsFragment).load(list.image_url).into(holder.image)
        holder.button.setOnClickListener {
            var action = ProductsFragmentDirections.proToDetail(list.name,list.image_url,list.description)
            Navigation.findNavController(productsFragment).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return  data.size
    }
}
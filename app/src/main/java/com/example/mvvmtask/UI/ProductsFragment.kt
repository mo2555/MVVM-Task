package com.example.mvvmtask.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmtask.R
import com.example.mvvmtask.ViewModel.MyViewModel
import com.example.retrfoit.CustomAdapter

class ProductsFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: CustomAdapter
    var myViewModel : MyViewModel = MyViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        makeView(view)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_, container, false)
    }

   fun makeView(view: View) {
       myViewModel.getProducts()
       myViewModel.getProduct().observe(viewLifecycleOwner, Observer {
           recyclerView = view.findViewById(R.id.recyclerView)
           adapter = CustomAdapter(it,view)
           recyclerView.layoutManager  = GridLayoutManager(view.context,2)
           recyclerView.adapter = adapter
       })

       myViewModel.error_message.observe(viewLifecycleOwner , Observer {
           Toast.makeText(view.context , it , Toast.LENGTH_SHORT).show()
       })
   }
}
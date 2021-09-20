package com.example.mvvmtask.UI

import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mvvmtask.R
import com.example.mvvmtask.R.*
import com.example.mvvmtask.ViewModel.MyViewModel
import com.example.retrfoit.CustomAdapter

class ProductsFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: CustomAdapter
    lateinit var myViewModel : MyViewModel
    lateinit var swipe:SwipeRefreshLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        makeView(view)
        swipe = view.findViewById(R.id.swipe)
        swipe.setOnRefreshListener {
            makeView(view)
            swipe.isRefreshing = false
        }

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_, container, false)
    }

   fun makeView(view: View) {
       myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
       myViewModel.getProducts()
       myViewModel.getProduct().observe(viewLifecycleOwner, Observer {
           recyclerView = view.findViewById(R.id.recyclerView)
           adapter = CustomAdapter(it,view)
           recyclerView.layoutManager  = LinearLayoutManager(view.context)
           recyclerView.adapter = adapter
       })

       myViewModel.error_message.observe(viewLifecycleOwner , Observer {
           Toast.makeText(view.context , it , Toast.LENGTH_SHORT).show()
       })
   }
}
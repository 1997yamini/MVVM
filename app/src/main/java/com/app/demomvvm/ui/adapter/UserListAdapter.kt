package com.app.demomvvm.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.demomvvm.R
import com.app.demomvvm.data.model.response.UserData
import com.app.demomvvm.databinding.InnerRecyclerBinding

import java.util.*

class UserListAdapter(
    context: Context,
    private val list: ArrayList<UserData>,
    //private val listener: ServiceRequestListener? = null,
  //  private val type: BookingType? = null
) :
    RecyclerView.Adapter<UserListAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: InnerRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserData) {
        binding.userData=item


        }

    }

    fun deleteOrder(item: UserData) {
        var index = -1
        (0 until list.size).forEach Loop@{ i ->
            if (list[i].Name == item.Name) {
                index = i
                return@Loop
            }
        }
        if (index >= 0) {
            list.removeAt(index)
            notifyItemRemoved(index)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<InnerRecyclerBinding>(
            LayoutInflater.from(parent.context),
            R.layout.inner_recycler, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])



    }

    override fun getItemCount() = list.size

}
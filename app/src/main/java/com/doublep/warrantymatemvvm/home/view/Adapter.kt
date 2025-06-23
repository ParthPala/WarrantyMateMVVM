package com.doublep.warrantymatemvvm.home.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.doublep.warrantymatemvvm.R
import com.doublep.warrantymatemvvm.base.BaseActivity
import com.doublep.warrantymatemvvm.db.data.HomeEntity

class Adapter (private val list: List<HomeEntity>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.child_list,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.productName.text = item.itemName
        holder.productType.text = item.itemType
        holder.productPic.setImageResource(item.itemPic)
        holder.productExpiry.text = item.itemExpiryDate
        holder.productName.setOnClickListener {
//            (holder.productName.context as BaseActivity).setFragToBaseView(WarrantyDetails())
        }
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productPic: ImageView = itemView.findViewById(R.id.iv_product)
        val productName: TextView = itemView.findViewById(R.id.tv_productName)
        val productType: TextView = itemView.findViewById(R.id.tv_productType)
        val productExpiry: TextView = itemView.findViewById(R.id.tv_productExpiryDetails)
    }
}
package com.example.developnetworktask.view.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.developnetworktask.databinding.ItemProductsBinding
import com.example.developnetworktask.pojo.Products

class AllProductsAdapter(var context: Context, var listner: OnClickListener) :
    RecyclerView.Adapter<AllProductsAdapter.ViewHolder>() {
    var productList: ArrayList<Products> = arrayListOf()
    private var limit = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = productList[position]
        holder.binding.apply {
            when (limit) {
                1 -> {
                    productTitleTxt.text = "${String.format("%.2f", currentItem.price?.toDouble())} LE"

                    priceTxt.text = currentItem.title?.trim()


                }
                else -> {
                    productTitleTxt.text = currentItem.title?.trim()

                    priceTxt.text =
                        "${String.format("%.2f", currentItem.price?.toDouble())} LE"
                }
            }

            descTxt.text = currentItem.description

        }
        holder.itemView.setOnClickListener {
            listner.onClick(currentItem)
        }
    }

    override fun getItemCount(): Int = productList.count()


    @SuppressLint("NotifyDataSetChanged")
    fun setData(productList: ArrayList<Products>, limit: Int) {
        this.productList = productList
        this.limit = limit
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemProductsBinding) : RecyclerView.ViewHolder(binding.root)

}
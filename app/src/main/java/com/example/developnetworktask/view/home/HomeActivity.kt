package com.example.developnetworktask.view.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.developnetworktask.R
import com.example.developnetworktask.databinding.ActivityHomeBinding
import com.example.developnetworktask.model.local_source.sharedPrefs.SharedPrefs
//import com.example.developnetworktask.model.local_source.sharedPrefs.SharedPrefs
import com.example.developnetworktask.model.remote_source.ProductRemoteSource
import com.example.developnetworktask.model.repository.product.ProductRepo
import com.example.developnetworktask.network.MyConnectivityManager
import com.example.developnetworktask.pojo.Products
import com.example.developnetworktask.view.home.adapter.AllProductsAdapter
import com.example.developnetworktask.view.home.adapter.OnClickListener
import com.example.developnetworktask.view.home.view_model.HomeVMFactory
import com.example.developnetworktask.view.home.view_model.HomeViewModel

class HomeActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeVM: HomeViewModel
    private lateinit var homeVMFactory: HomeVMFactory
    private lateinit var allProductsAdapter: AllProductsAdapter
    private var dialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getInit()

        val check = MyConnectivityManager.checkForInternet(this)

        binding.loadingProgressBar.visibility = View.VISIBLE

        if (check) {
            binding.networkLayout.noNetwork.visibility = View.INVISIBLE
            binding.mainLayout.visibility = View.VISIBLE
            homeVM.getProduct()
        } else {
            binding.networkLayout.noNetwork.visibility = View.VISIBLE
            binding.mainLayout.visibility = View.INVISIBLE

        }

    }

    override fun onResume() {
        super.onResume()
        observeHome()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item != null)
            when (item.itemId) {
                R.id.logout -> {
                    SharedPrefs.getInstance(this).setState(0)
                }
            }

        return true
    }

    private fun observeHome() {
        homeVM.allProducts.observe(this) {
            Log.d("limit", it.limit.toString())
            var limit = 0
            if (it.products.size != 0) {
                binding.loadingProgressBar.visibility = View.INVISIBLE

                when {
                    it.limit!!.toInt() > 50 -> {
                        limit = 1
                    }
                }
                allProductsAdapter.setData(it.products, limit)
            }
        }
    }

    private fun getInit() {
        homeVMFactory = HomeVMFactory(
            ProductRepo.getInstance(ProductRemoteSource.getInstance(), this),
        )
        homeVM = ViewModelProvider(this, homeVMFactory)[HomeViewModel::class.java]

        allProductsAdapter = AllProductsAdapter(this, this)
        binding.recycleViewHome.adapter = allProductsAdapter
    }

    override fun onClick(product: Products) {
        showDialog(product)
    }

    @SuppressLint("SetTextI18n")
    private fun showDialog(product: Products) {
        val builder = AlertDialog.Builder(this)
        val dialogLayout: View = layoutInflater.inflate(R.layout.dialog_product_details, null)
        builder.setView(dialogLayout)
        val productImage = dialogLayout.findViewById<ImageView>(R.id.image_product)
        val productPrice = dialogLayout.findViewById<TextView>(R.id.price_txt)
        val productTitle = dialogLayout.findViewById<TextView>(R.id.product_title_txt)
        val productDesc = dialogLayout.findViewById<TextView>(R.id.desc_txt)

        productTitle.text = product.title
        productPrice.text = "${String.format("%.2f", product.price?.toDouble())} LE"
        productDesc.text = product.description
        Glide.with(this)
            .load(product.images[0])
            .into(productImage)

        dialog = builder.create()
        dialog!!.show()
    }

}
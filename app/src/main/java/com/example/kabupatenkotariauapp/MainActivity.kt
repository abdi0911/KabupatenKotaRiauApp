package com.example.kabupatenkotariauapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kabupatenkotariauapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var title: String = "Mode List View"
    private var listData: ArrayList<KabKota> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Menambahkan Toolbar jika diperlukan
        setSupportActionBar(binding.toolbar)

        setActionBarTitle(title)
        listData.addAll(KabKotaData.listDataKabKota)
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_list -> {
                title = "Mode List View"
                showRecyclerList()
            }
            R.id.action_grid -> {
                title = "Mode Grid View"
                showRecyclerGrid()
            }
            R.id.action_cardview -> {
                title = "Mode Card View"
                showRecyclerCardView()
            }
        }
        setActionBarTitle(title)
    }

    private fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }
    private fun showRecyclerList() {
        binding.rvKabKota.layoutManager = LinearLayoutManager(this)
        val listKabKotaAdapter = ListKabKotaAdapter(listData)
        binding.rvKabKota.adapter = listKabKotaAdapter
        listKabKotaAdapter.setOnItemClickCallback(object : ListKabKotaAdapter.OnItemClickCallback {
            override fun onItemClicked(data: KabKota) {
                showDataKabKota(data)
            }
        })
    }
    private fun showRecyclerGrid() {
        binding.rvKabKota.layoutManager = GridLayoutManager(this, 2)
        val gridKabKotaAdapter = GridKabKotaAdapter(listData)
        binding.rvKabKota.adapter = gridKabKotaAdapter
        gridKabKotaAdapter.setOnItemClickCallback(object : GridKabKotaAdapter.OnItemClickCallback{
            override fun onItemClicked(data: KabKota) {
                showDataKabKota(data)
            }
        })
    }
    private fun showRecyclerCardView() {
        binding.rvKabKota.layoutManager = LinearLayoutManager(this)
        val cardKabKotaAdapter = CardKabKotaAdapter(listData)
        binding.rvKabKota.adapter = cardKabKotaAdapter
        cardKabKotaAdapter.setOnItemClickCallback(object : CardKabKotaAdapter.OnItemClickCallback{
            override fun onItemClicked(data: KabKota) {
                showDataKabKota(data)
            }
        })
    }
    private fun showDataKabKota(data: KabKota){
        val moveWithObjectIntent = Intent(this@MainActivity, DetailKabKotaActivity::class.java)
        moveWithObjectIntent.putExtra(DetailKabKotaActivity.EXTRA_KAB_KOTA, data)
        startActivity(moveWithObjectIntent)
    }
}

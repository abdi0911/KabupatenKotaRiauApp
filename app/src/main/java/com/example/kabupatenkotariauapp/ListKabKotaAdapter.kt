package com.example.kabupatenkotariauapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.kabupatenkotariauapp.databinding.ItemListKabkotaBinding

class ListKabKotaAdapter(private val listKabKota: ArrayList<KabKota>) :
    RecyclerView.Adapter<ListKabKotaAdapter.ListViewHolder>() {

        private lateinit var onltemClickCallback: OnItemClickCallback

        fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
            this.onltemClickCallback = onItemClickCallback
        }
    interface OnItemClickCallback {
        fun onItemClicked(data: KabKota)
    }

    // Fungsi untuk membuat ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemListKabkotaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)  // Mengembalikan ViewHolder dengan binding
    }

    // Fungsi untuk mengikat data ke dalam ViewHolder
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        // Mengambil objek KabKota dari list berdasarkan posisi
        val (gambar, kabupaten_kota, pusat_pemerintahan) = listKabKota[position]

        // Memuat gambar menggunakan Glide dengan ukuran 55x55
        Glide.with(holder.binding.imgItemPhoto.context)
            .load(gambar)  // Menggunakan gambar dari objek KabKota
            .apply(RequestOptions().override(55, 55))  // Set ukuran gambar
            .into(holder.binding.imgItemPhoto)  // Memasukkan gambar ke ImageView

        // Mengisi data ke dalam TextView
        holder.binding.tvItemName.text = kabupaten_kota  // Nama Kabupaten/Kota
        holder.binding.tvItemDetail.text = "Pusat Pemerintahan : $pusat_pemerintahan"  // Menampilkan pusat pemerintahan
        holder.itemView.setOnClickListener {
            onltemClickCallback.onItemClicked(listKabKota[holder.adapterPosition])
        }
    }

    // Fungsi untuk mendapatkan jumlah item di dalam RecyclerView
    override fun getItemCount(): Int = listKabKota.size

    // ViewHolder untuk item dalam RecyclerView
    inner class ListViewHolder(val binding: ItemListKabkotaBinding) :
        RecyclerView.ViewHolder(binding.root)
}

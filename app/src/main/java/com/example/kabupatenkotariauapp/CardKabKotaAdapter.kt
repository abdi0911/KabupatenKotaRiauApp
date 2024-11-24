package com.example.kabupatenkotariauapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kabupatenkotariauapp.databinding.ItemCardKabkotaBinding

class CardKabKotaAdapter(private val listKabKota: ArrayList<KabKota>) :
    RecyclerView.Adapter<CardKabKotaAdapter.CardViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: KabKota)
    }

    // Membuat ViewHolder baru untuk setiap item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = ItemCardKabkotaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CardViewHolder(binding)
    }

    // Menghubungkan data dengan ViewHolder
    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val kabKota = listKabKota[position]

        // Menampilkan gambar menggunakan Glide
        Glide.with(holder.itemView.context)
            .load(kabKota.gambar)
            .into(holder.binding.imgItemPhoto)

        // Mengatur teks untuk nama kabupaten/kota dan detail
        holder.binding.tvItemName.text = kabKota.kabupaten_kota
        holder.binding.tvItemDetail.text = "Pusat Pemerintahan:\n${kabKota.pusat_pemerintahan}"

        // Memberikan aksi pada tombol "Share"
        holder.binding.btnSetShare.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Bagikan data: ${kabKota.kabupaten_kota}",
                Toast.LENGTH_SHORT
            ).show()
        }
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listKabKota[holder.adapterPosition])
        }
    }

    // Menentukan jumlah item yang ada di dalam daftar
    override fun getItemCount(): Int = listKabKota.size

    // Kelas untuk menyimpan referensi elemen-elemen dalam layout item
    inner class CardViewHolder(val binding: ItemCardKabkotaBinding) :
        RecyclerView.ViewHolder(binding.root)
}

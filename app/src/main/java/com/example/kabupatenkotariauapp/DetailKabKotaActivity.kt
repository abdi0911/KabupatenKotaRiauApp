package com.example.kabupatenkotariauapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.kabupatenkotariauapp.databinding.ActivityDetailKabKotaBinding

class DetailKabKotaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailKabKotaBinding

    companion object {
        const val EXTRA_KAB_KOTA = "extra_kab_kota"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Mengatur binding layout
        binding = ActivityDetailKabKotaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Menampilkan tombol "back" pada action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Mendapatkan data yang dikirim melalui Intent
        val dataKabKota = intent.getParcelableExtra<KabKota>(EXTRA_KAB_KOTA)

        dataKabKota?.let { kabKota ->
            // Memuat gambar menggunakan Glide
            Glide.with(applicationContext)
                .load(kabKota.gambar)
                .into(binding.imgItemPhoto)

            // Mengisi data ke dalam layout
            binding.tvNama.text = kabKota.kabupaten_kota
            binding.tvPusatPemerintahan.text = kabKota.pusat_pemerintahan
            binding.tvBupatiWalikota.text = kabKota.bupati_walikota
            binding.tvLuasWilayah.text = kabKota.luas_wilayah.toString()
            binding.tvJumlahPenduduk.text = kabKota.jumlah_penduduk.toString()
            binding.tvJumlahKecamatan.text = kabKota.jumlah_kecamatan.toString()
            binding.tvJumlahKelurahan.text = kabKota.jumlah_kelurahan.toString()
            binding.tvJumlahDesa.text = kabKota.jumlah_desa.toString()

            // Mengatur klik tombol untuk melihat peta wilayah
            binding.btnViewPeta.setOnClickListener {
                val showViewPeta = Intent(this@DetailKabKotaActivity, ViewPetaActivity::class.java)
                showViewPeta.putExtra(ViewPetaActivity.EXTRA_URL_PETA, kabKota.url_peta_wilayah)
                startActivity(showViewPeta)
            }
        }
    }
}

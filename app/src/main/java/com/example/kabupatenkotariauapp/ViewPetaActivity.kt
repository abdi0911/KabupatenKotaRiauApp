package com.example.kabupatenkotariauapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.kabupatenkotariauapp.databinding.ActivityViewPetaBinding
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

class ViewPetaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPetaBinding

    companion object {
        const val EXTRA_URL_PETA = "extra_url_peta"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Mengatur binding layout
        binding = ActivityViewPetaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mendapatkan URL Peta dari Intent
        val urlPeta = intent.getStringExtra(EXTRA_URL_PETA)

        // Cek jika URL Peta ada
        if (urlPeta != null) {
            // Gunakan GlideToVectorYou jika URL adalah untuk gambar vektor (SVG)
            GlideToVectorYou
                .init()
                .with(this)
                .requestBuilder
                .load(urlPeta)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.baseline_image_24) // Placeholder saat gambar dimuat
                        .error(R.drawable.baseline_broken_image_24) // Gambar error jika gagal dimuat
                        .fitCenter() // Sesuaikan gambar di tengah ImageView
                )
                .into(binding.imgItemPhoto)
        } else {
            // Jika URL Peta tidak ada, tampilkan gambar placeholder atau penanganan error lainnya
            binding.imgItemPhoto.setImageResource(R.drawable.baseline_broken_image_24)
        }
    }
}

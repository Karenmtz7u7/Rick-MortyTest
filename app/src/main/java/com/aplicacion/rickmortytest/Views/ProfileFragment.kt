package com.aplicacion.rickmortytest.Views


import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.aplicacion.rickmortytest.R
import com.aplicacion.rickmortytest.databinding.FragmentProfileBinding
import com.bumptech.glide.Glide


class ProfileFragment : Fragment(R.layout.fragment_profile) {
lateinit var binding : FragmentProfileBinding

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.likedinBtn.setOnClickListener {
            linkedInGo()
        }
        binding.gitHubBtn.setOnClickListener { gitHubGo() }

        Glide.with(this).load(R.drawable.desarrollo).into(binding.gifImage)


        return binding.root
    }

    fun linkedInGo(){
        val uri = Uri.parse("https://www.linkedin.com/in/ana-karen-mart%C3%ADnez-jim%C3%A9nez-30b588253")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    fun gitHubGo(){
        val uri = Uri.parse("https://github.com/Karenmtz7u7")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}
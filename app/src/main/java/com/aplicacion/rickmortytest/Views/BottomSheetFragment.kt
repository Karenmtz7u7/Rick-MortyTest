package com.aplicacion.rickmortytest.Views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import com.aplicacion.rickmortytest.Models.Characters
import com.aplicacion.rickmortytest.R
import com.aplicacion.rickmortytest.databinding.ActivityMainBinding
import com.aplicacion.rickmortytest.databinding.FragmentBottomSheetBinding
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*


class BottomSheetFragment(private val character: Characters) : BottomSheetDialogFragment() {
    lateinit var binding: FragmentBottomSheetBinding
    val bottomSheetDialog = BottomSheetDialog(this@BottomSheetFragment.requireContext())
    val bottomSheetView = layoutInflater.inflate(R.layout.fragment_bottom_sheet, null)
    var buttonBack : Button?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentBottomSheetBinding.inflate(layoutInflater)

        bind(character)

        buttonBack?.setOnClickListener {
            dismiss()
        }

        return binding.root
    }



    private fun bind(character : Characters) {
        binding.nameCharactertxt.text = character.name
        binding.statusCharactertxt.text = character.status
        binding.specieCharactertxt.text = character.species
        binding.genderCharactertxt.text = character.gender

        Glide.with(this).load(character.image).into(binding.imageCharacter)

        val colorStatus = when (character.status.toLowerCase(Locale.getDefault())) {
            "alive" -> R.color.green_700
            "dead" -> R.color.orange_200
            else -> R.color.orange_200
        }
        binding.statusTxt.setTextColor(ContextCompat.getColor(this.requireContext(), colorStatus))
        binding.statusCharactertxt.setTextColor(ContextCompat.getColor(this.requireContext(), colorStatus))

        val genderSymbol = when(character.gender.toLowerCase(Locale.getDefault())){
            "male" -> R.drawable.ic_male_24
            "female" -> R.drawable.ic_female_24
            else -> R.drawable.ic_male_24
        }
        binding.genderImage.setImageResource(genderSymbol)

        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.show()

    }



}



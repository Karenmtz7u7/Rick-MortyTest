package com.aplicacion.rickmortytest.Views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aplicacion.rickmortytest.Activities.Adapters.CharactersAdapter
import com.aplicacion.rickmortytest.Models.Characters
import com.aplicacion.rickmortytest.R
import com.aplicacion.rickmortytest.Service.ApiService
import com.aplicacion.rickmortytest.Service.httpClient
import com.aplicacion.rickmortytest.databinding.FragmentHomeBinding
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.*
import org.w3c.dom.Text
import java.util.*


class HomeFragment : Fragment(R.layout.fragment_home) {
    lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: CharactersAdapter
    private lateinit var characters: List<Characters>
    private lateinit var recyclerView: RecyclerView



    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        recyclerView = binding.recycleViewCharacters
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = CharactersAdapter(requireContext(), emptyList()){ character ->
            showCharacterDetail(character)
        }
         recyclerView.adapter = adapter

        corroutine()

        return binding.root
    }







    private fun showCharacterDetail(character: Characters) {
        val bottomSheetDialog =  BottomSheetDialog(requireContext())
        val view = layoutInflater.inflate( R.layout.fragment_bottom_sheet, null)

        bottomSheetDialog.setContentView(view)

        val imageCharacter = view.findViewById<ImageView>(R.id.imageCharacter)
        val nameCharacter = view.findViewById<TextView>(R.id.nameCharactertxt)
        val statusCharacter = view.findViewById<TextView>(R.id.statusCharactertxt)
        val statusTXT = view?.findViewById<TextView>(R.id.statusTxt)
        val especieCharacter = view.findViewById<TextView>(R.id.specieCharactertxt)
        val genderCharacter = view.findViewById<TextView>(R.id.genderCharactertxt)
        val genderImage = view.findViewById<ImageView>(R.id.genderImage)
        val buttonBack = view.findViewById<Button>(R.id.backBtn)

        buttonBack.setOnClickListener {
            bottomSheetDialog.dismiss()
        }

        Glide.with(this).load(character.image).into(imageCharacter)
        nameCharacter.text = character.name

        statusCharacter.text = character.status

        val colorStatus = when (character.status.toLowerCase(Locale.getDefault())) {
            "alive" -> R.color.green_700
            "dead" -> R.color.orange_200
            else -> R.color.orange_200
        }
        statusTXT?.setTextColor(ContextCompat.getColor(requireContext(), colorStatus))
        statusCharacter?.setTextColor(ContextCompat.getColor(requireContext(), colorStatus))
        especieCharacter.text = character.species
        genderCharacter.text = character.gender
        val genderSymbol = when(character.gender.toLowerCase(Locale.getDefault())){
            "male" -> R.drawable.ic_male_24
            "female" -> R.drawable.ic_female_24
            else -> R.drawable.ic_male_24
        }
        genderImage.setImageResource(genderSymbol)

        bottomSheetDialog.show()
    }


    private fun corroutine() {

            GlobalScope.launch {
                characters = fetchCharacters()
                withContext(Dispatchers.Main) {
                    adapter.characters = characters
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

private suspend fun fetchCharacters():
        List<Characters> = withContext(Dispatchers.IO) {

    try {
        val response = httpClient.ApiRick.getCharacters()
        response.body()?.results
    } catch (e : Exception){

        emptyList<Characters>()

    } as List<Characters>







}










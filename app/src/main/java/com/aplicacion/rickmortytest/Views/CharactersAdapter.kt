package com.aplicacion.rickmortytest.Activities.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.aplicacion.rickmortytest.Models.Characters
import com.aplicacion.rickmortytest.R
import com.aplicacion.rickmortytest.Views.BottomSheetFragment
import com.bumptech.glide.Glide

import java.util.*


class CharactersAdapter(
    private val context: Context,
    var characters: List<Characters>,
    private val onCharacterClick: (Characters) -> Unit,

    ): RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_characters, parent, false)
        return CharactersViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val characters = characters[position]
        holder.bind(characters)
        holder.itemView.setOnClickListener { onCharacterClick(characters) }

    }

    override fun getItemCount() = characters.size



  inner class CharactersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

      private val nametxt = itemView.findViewById<TextView>(R.id.nameCharacter)
       private val statustxt = itemView.findViewById<TextView>(R.id.StatusCharacter)
       private val imagetxt = itemView.findViewById<ImageView>(R.id.charactersImage)
       private val txtStatus=   itemView.findViewById<TextView>(R.id.statustxt)
       private val gender = itemView.findViewById<ImageView>(R.id.charactersgender)


        fun bind(character: Characters) {
            nametxt.text = character.name
            statustxt.text = character.status
            Glide.with(itemView.context).load(character.image).into(imagetxt)

            val colorStatus = when (character.status.toLowerCase(Locale.getDefault())) {
                "alive" -> R.color.green_700
                "dead" -> R.color.orange_200
                else -> R.color.orange_200
            }
            txtStatus.setTextColor(ContextCompat.getColor(context, colorStatus))
            statustxt.setTextColor(ContextCompat.getColor(context, colorStatus))

            val genderIcon = when (character.gender.toLowerCase(Locale.getDefault())) {
                "male" -> R.drawable.ic_male_24
                "female" -> R.drawable.ic_female_24
                else -> R.drawable.ic_male_24
            }
            gender.setImageResource(genderIcon)
        }
        }
}
package com.sayempro.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView

class CountriesAdapter(
    var countryListWithFlagAndDetails: List<Map<String, Any>>, var context: Context
) : RecyclerView.Adapter<CountriesAdapter.CountryViewHolder>() {
    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //here we define all the parameters that will be used in the card

        var textViewCountryName: TextView = itemView.findViewById(R.id.textViewCountryName)
        var textViewCountryDescription: TextView = itemView.findViewById(R.id.textViewDetail)
        var imageViewCountryImage: CircleImageView =
            itemView.findViewById(R.id.imageViewCountryImage)
        var cardView: CardView = itemView.findViewById(R.id.cardView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        //create a view inflater, set what i want to show, then return the view holder
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.card_design, parent, false)
        return CountryViewHolder(view)

    }

    override fun getItemCount(): Int {
        return countryListWithFlagAndDetails.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        //sets data to each recyclerview item, the cards content in this case
        holder.imageViewCountryImage.setImageResource(countryListWithFlagAndDetails[position]["icon"] as Int)
        holder.textViewCountryName.text = countryListWithFlagAndDetails[position]["name"].toString()
        holder.textViewCountryDescription.text =
            countryListWithFlagAndDetails[position]["desc"].toString()
        holder.cardView.setOnClickListener {
            Toast.makeText(
                context,
                "You clicked ${countryListWithFlagAndDetails[position]["name"].toString()}",
                Toast.LENGTH_LONG
            ).show()
        }

    }
}
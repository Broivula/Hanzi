package com.example.hanzi_training_app


import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_layout.view.*

class MainAdapter(var cardList: List<Card>?, var supportFragmentManager: FragmentManager): RecyclerView.Adapter<CustomViewHolder>(){



    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val card = cardList?.get(position)
        Log.d("ADAPTER", "building!")
        if (cardList != null){
            holder.view.symbol_text.text = card?.symbol
            if(card?.pinyin_revealed!!) holder.view.pinyin_text.text = card?.chinese_word else holder.view.pinyin_text.text = "?????"
            if(card.translation_revealed) holder.view.translation_text_view.text = card?.translation else holder.view.translation_text_view.text = "????"


            holder.view.symbol_text.setAutoSizeTextTypeWithDefaults(AUTO_SIZE_TEXT_TYPE_UNIFORM)
        }

        holder.view.setOnClickListener { view ->
          AnimationController.changeCardFragment(position, supportFragmentManager, R.anim.anim_scale_up, android.R.anim.fade_out)
        }

        holder.view.setOnLongClickListener { view ->
            // user holds down the card, reveal the text and reload UI
            DatabaseManager.cardList?.get(position)?.pinyin_revealed = true
            DatabaseManager.cardList?.get(position)?.translation_revealed = true
            Log.d("TOUCH", "detecting long clikc on symbol ${holder.view.symbol_text.text}")
            DatabaseManager.recyclerView?.adapter?.notifyDataSetChanged()
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int {
        return cardList?.count() ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val element = layoutInflater.inflate(R.layout.card_layout, parent, false)
        DatabaseManager.recyclerView = parent.findViewById(R.id.recycler_view_cardlist)
        return CustomViewHolder(element)
    }

    private fun checkSymbolFontSize(symbol: String): Float{
        Log.d("ADAPTER", "returning ${symbol.count().toFloat()}")
        return symbol.count().toFloat()
    }


}

class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view)
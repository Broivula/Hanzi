package com.example.hanzi_training_app

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

object DatabaseManager {
    private lateinit var dataBase: CardDatabase
    var recyclerView: RecyclerView? = null
    var cardList: List<Card>? = null

    fun initDatabase(context: Context){
        dataBase = CardDatabase.get(context)
        cardList =  dataBase.cardDao().getAll()
    }

    fun addCardToDatabase(card: Card){
        dataBase.cardDao().insert(card)
    }

    fun getCardsFromDatabase(): List<Card>?{
        return cardList
    }
}
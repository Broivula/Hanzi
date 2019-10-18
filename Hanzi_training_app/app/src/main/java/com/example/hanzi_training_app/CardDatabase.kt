package com.example.hanzi_training_app

import android.content.Context
import androidx.room.*

@Entity
data class Card(
    @PrimaryKey(autoGenerate = true)
    val uid: Long,
    var translation: String,
    var chinese_word: String,
    var symbol: String,
    var pinyin_revealed : Boolean,
    var translation_revealed : Boolean
)

@Dao
interface CardDao{
    @Query("SELECT * FROM Card")
    fun getAll(): List<Card>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(card: Card)

    @Update
    fun update(card: Card)
}

@Database(entities = [(Card::class)], version = 1)
abstract class CardDatabase : RoomDatabase(){
    abstract fun cardDao(): CardDao
    companion object{
        private var sInstance: CardDatabase? = null
        @Synchronized
        fun get(context: Context): CardDatabase{
            if(sInstance == null){
                sInstance=
                    Room.databaseBuilder(context.applicationContext, CardDatabase::class.java, "cardDB").allowMainThreadQueries().build()
            }
            return sInstance!!
        }
    }
}
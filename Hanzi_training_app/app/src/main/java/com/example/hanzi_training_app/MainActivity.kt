package com.example.hanzi_training_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import androidx.core.view.MotionEventCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFunction()
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        toolbar.overflowIcon = getDrawable(R.drawable.ic_more_horiz_white_24dp)

        fab.setOnClickListener { view ->
            val intent = Intent(this, AddCardActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            R.id.action_hide_card_info -> {
                hideCards()
                true
            }
            R.id.action_restart ->{
                restartCards()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun restartCards(){
        Log.d("MAIN", "restarting cards..")

    }

    private fun hideCards(){
        Log.d("MAIN", "hiding the pinyin and translation")
        DatabaseManager.cardList?.forEach {card -> card.translation_revealed = false; card.pinyin_revealed = false }
        DatabaseManager.recyclerView?.adapter?.notifyDataSetChanged()
    }


    private fun initFunction(){
        DatabaseManager.initDatabase(this)
    }
}

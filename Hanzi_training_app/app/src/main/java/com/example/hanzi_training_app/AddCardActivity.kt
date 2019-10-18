package com.example.hanzi_training_app

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatDialogFragment
import kotlinx.android.synthetic.main.activity_add_card.*
import org.jetbrains.anko.contentView
import java.io.IOException

class AddCardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_card)

        add_card_button.setOnClickListener{view ->
            createNewCard()
        }

    }

    private fun createNewCard(){
        Log.d("CARD", "Creating a new card..")
        val newCard = Card(0,
            translation = translation_text_view.text.toString(),
            chinese_word = word_text_view.text.toString(),
            symbol = symbol_text_view.text.toString(),
            pinyin_revealed = false,
            translation_revealed = false
            )
        DatabaseManager.addCardToDatabase(newCard)
        hideKeyBoard()
        displayDialog()
        clearTextFields()

    }

    private fun displayDialog(){
        val d = CardCreatedDialog()
        d.show(supportFragmentManager, "card created")
    }

    private fun hideKeyBoard(){
        try {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus.windowToken, 0)
        }catch (e: IOException){
            Log.d("ADD_CARD_ACT", "crashed when hiding keyboard, error msg: ${e.toString()}")
        }
    }

    private fun clearTextFields(){
        symbol_text_view.text.clear()
        word_text_view.text.clear()
        translation_text_view.text.clear()
    }
}

class CardCreatedDialog() : AppCompatDialogFragment(){
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(R.string.card_created_title)
            .setMessage(R.string.card_created_message)
            .setPositiveButton("return", DialogInterface.OnClickListener { _, _ ->

            })
        return builder.create()
    }
}

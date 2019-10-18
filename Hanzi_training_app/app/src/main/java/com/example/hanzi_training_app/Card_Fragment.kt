package com.example.hanzi_training_app


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MotionEventCompat
import kotlinx.android.synthetic.main.fragment_card_.*

/**
 * A simple [Fragment] subclass.
 */
class Card_Fragment(var index: Int) : Fragment() {

    private var startX : Float = 0.0f
    private var startY : Float = 0.0f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this

        return inflater.inflate(R.layout.fragment_card_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view?.setOnTouchListener(fragmentOnTouchListener)
        val card = DatabaseManager.cardList?.get(index)
        card_layout_symbol_text_view.text = card?.symbol
        if(card?.pinyin_revealed!!)card_layout_pinyin_text_view.text = card?.chinese_word else card_layout_pinyin_text_view.text = "????"
        if(card.translation_revealed)card_layout_translation_text_view.text = card?.translation else card_layout_translation_text_view.text = "????"
    }

    private val fragmentOnTouchListener : View.OnTouchListener = object : View.OnTouchListener {

        override fun onTouch(view: View?, event: MotionEvent?): Boolean {
            val action: Int = MotionEventCompat.getActionMasked(event)
           when(action){
                MotionEvent.ACTION_DOWN -> {
                    startX = event!!.x
                    startY = event!!.y
                }
                MotionEvent.ACTION_MOVE -> determineMoveDir(event!!)
            }
            return true
        }
    }


    fun determineMoveDir(event: MotionEvent){
        val xDir = startX - event.x
        val yDir = startY - event.y
        when{
            xDir > 255 ->{
                //swipe left -> next card
                AnimationController.changeCardFragment(checkIfOutOfBounds(index + 1), fragmentManager!!, R.anim.slide_in_right, R.anim.slide_out_left)
                }
            xDir < -255 -> {
                //swipe right -> previous card
                AnimationController.changeCardFragment(checkIfOutOfBounds(index - 1), fragmentManager!!, R.anim.slide_in_left, R.anim.slide_out_right)
            }
            yDir > 295 -> {
                // swipe up, go back
                AnimationController.returnOriginalFragment(fragmentManager!!, android.R.anim.fade_in, R.anim.anim_slide_out_up)
            }
            yDir < -295 -> {
                // swipe down, go back with different animation
                AnimationController.returnOriginalFragment(fragmentManager!!, android.R.anim.fade_in, R.anim.anim_slide_out_down)
            }
        }
    }

    fun checkIfOutOfBounds(index: Int) : Int{

        return when{
            index < 0 -> (DatabaseManager.cardList!!.count() - 1)
            index == DatabaseManager.cardList!!.count() -> 0
            else -> index
        }
    }

}

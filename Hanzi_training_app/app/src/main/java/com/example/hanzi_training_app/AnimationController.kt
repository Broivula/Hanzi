package com.example.hanzi_training_app

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object AnimationController : AppCompatActivity() {


    fun returnOriginalFragment(supportFragmentManager: FragmentManager, enterAnimation: Int, exitAnimation: Int){
        val fManager = supportFragmentManager
        val fTransaction = fManager.beginTransaction()
        val fragment: Fragment = CardListFragments()
        fTransaction.setCustomAnimations(enterAnimation, exitAnimation)
        fTransaction.replace(R.id.fragment_container_main, fragment)
        fTransaction.commit()
    }

    fun changeCardFragment(index: Int, supportFragmentManager: FragmentManager, animationEnter: Int, animationExit : Int){
        val fManager = supportFragmentManager
        val fTransaction = fManager.beginTransaction()
        val fragment: Fragment = Card_Fragment(index)
        fTransaction.setCustomAnimations(animationEnter, animationExit)
        fTransaction.replace(R.id.fragment_container_main, fragment)
        fTransaction.commit()
    }


}
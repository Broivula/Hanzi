package com.example.hanzi_training_app


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_card_list_fragments.*

/**
 * A simple [Fragment] subclass.
 */
class CardListFragments : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("FRAGMENT", "created")
        return inflater.inflate(R.layout.fragment_card_list_fragments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("FRAGMENT", "adapter is  = ${recycler_view_cardlist.adapter}")
        if(recycler_view_cardlist.adapter == null){
            recycler_view_cardlist.layoutManager = GridLayoutManager(context, 3)
            recycler_view_cardlist.adapter = MainAdapter(DatabaseManager.getCardsFromDatabase(), fragmentManager!!)
        }else{
            recycler_view_cardlist.adapter?.notifyDataSetChanged()
        }

    }

    override fun onResume() {
        super.onResume()
        Log.d("FRAGMENT", "onresume called")

        recycler_view_cardlist.adapter?.notifyDataSetChanged()
    }

}

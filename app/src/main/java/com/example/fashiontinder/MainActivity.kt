package com.example.fashiontinder

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.google.firebase.database.FirebaseDatabase
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.SwipeDirection
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private var progressBar: ProgressBar? = null
    private var adapter: CardAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()
        reload()
    }

    private fun setup() {
        progressBar = findViewById(R.id.activity_main_progress_bar)

        activity_main_card_stack_view.setCardEventListener(object : CardStackView.CardEventListener {
            override fun onCardDragging(percentX: Float, percentY: Float) {
                Log.d("CardStackView", "onCardDragging")
            }

            override fun onCardSwiped(direction: SwipeDirection) {
                Log.d("CardStackView", "onCardSwiped: " + direction.toString())
                Log.d("CardStackView", "topIndex: " + activity_main_card_stack_view?.topIndex)
                val item = adapter?.getItem(activity_main_card_stack_view?.topIndex ?: 0)
                Log.d("CardStackView", item.toString())
                // TODO DELETE. We want finite stack
                if (activity_main_card_stack_view?.topIndex == adapter?.count?.minus(5)) {
                    Log.d("CardStackView", "Paginate: " + activity_main_card_stack_view?.topIndex)
                    paginate()
                }
            }

            override fun onCardReversed() {
                Log.d("CardStackView", "onCardReversed")
            }

            override fun onCardMovedToOrigin() {
                Log.d("CardStackView", "onCardMovedToOrigin")
            }

            override fun onCardClicked(index: Int) {
                Log.d("CardStackView", "onCardClicked: $index")
            }
        })
    }

    private fun paginate() {
        activity_main_card_stack_view?.setPaginationReserved()
        adapter?.addAll(createFakeCArds())
        adapter?.notifyDataSetChanged()
    }
    private fun reload() {
        activity_main_card_stack_view?.visibility = View.GONE
        progressBar?.visibility = View.VISIBLE
        Handler().postDelayed({
            adapter = createCardAdapter()
            activity_main_card_stack_view?.setAdapter(adapter)
            activity_main_card_stack_view?.visibility = View.VISIBLE
            progressBar?.visibility = View.GONE
        }, 1000)
    }

    private fun createFakeCArds(): List<Card> {
        val cards = ArrayList<Card>()
        val string = resources.openRawResource(R.raw.she_new).bufferedReader().use { it.readText() }
        val jsonArray = JSONArray(string)
        for ( i in 0 until jsonArray.length()){
            with(jsonArray.getJSONObject(i)){
                cards.add(Card(this.getString("name"), this.getString("id"), this.getString("image")))
            }
        }

        return cards.distinctBy { it.subtitle }
    }

    private fun createCardAdapter(): CardAdapter {
        val adapter = CardAdapter(applicationContext)
        adapter.addAll(createFakeCArds())
        return adapter
    }
}

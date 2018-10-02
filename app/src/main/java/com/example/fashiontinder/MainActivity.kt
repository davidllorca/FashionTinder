package com.example.fashiontinder


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.SwipeDirection
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity(), CardAdapter.Listener {
    override fun onClick(product: Product?) {
        val intent = Intent(this, CardInfoActivity::class.java)
        intent.putExtra("product", product)
        startActivity(intent)
    }

    private var progressBar: ProgressBar? = null
    private var adapter: CardAdapter? = null

    private var  reference : DatabaseReference = FirebaseDatabase.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()

        readDB()
    }

    private fun readDB(){
      reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                val response = dataSnapshot.getValue(Response::class.java)
                reload(ArrayList(response!!.response!!.values))
                reference.removeEventListener(this)

                Log.d("DataBase", "Value is: "+response!!.response)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("DataBase", "Failed to read value.", error.toException())
            }
        })
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


                if(direction == SwipeDirection.Right){
                    updateLike(item!!.id, item.likes)
                }else if(direction == SwipeDirection.Left){
                    updateDisLike(item!!.id, item.dislikes)
                }


                // TODO DELETE. We want finite stack
                if (activity_main_card_stack_view?.topIndex == adapter?.count?.minus(5)) {
                    Log.d("CardStackView", "Paginate: " + activity_main_card_stack_view?.topIndex)
                    readDB()
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

   /* private fun paginate() {
        activity_main_card_stack_view?.setPaginationReserved()
       // adapter?.addAll(createFakeCArds())
        adapter?.notifyDataSetChanged()
    }*/

    private fun updateLike(productId: String?, likes: Int?) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        reference.child("response").child(productId.toString()).child("likes").setValue(likes!!.plus(1))
    }

    private fun updateDisLike(productId: String?, dislikes: Int?) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        reference.child("response").child(productId.toString()).child("dislikes").setValue(dislikes!!.plus(1))
    }

    private fun reload(listProducts : ArrayList<Product>?) {
        activity_main_card_stack_view?.visibility = View.GONE
        progressBar?.visibility = View.VISIBLE
        Handler().postDelayed({
            adapter = createCardAdapter(listProducts)
            activity_main_card_stack_view?.setAdapter(adapter)
            activity_main_card_stack_view?.visibility = View.VISIBLE
            progressBar?.visibility = View.GONE
        }, 1000)
    }

   /* private fun createFakeCArds(): List<Card> {
        val cards = ArrayList<Card>()
        val string = resources.openRawResource(R.raw.she_new).bufferedReader().use { it.readText() }
        val jsonArray = JSONArray(string)
        for ( i in 0 until jsonArray.length()){
            with(jsonArray.getJSONObject(i)){
                cards.add(Card(this.getString("name"), this.getString("id"), this.getString("image")))
            }
        }

        return cards.distinctBy { it.subtitle }
    }*/

    private fun createCardAdapter(listProducts: ArrayList<Product>?): CardAdapter {
        val adapter = CardAdapter(applicationContext, this)
        adapter.addAll(listProducts)
        return adapter
    }
}

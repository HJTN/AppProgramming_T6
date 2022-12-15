package com.example.kioskupgrade

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.kioskupgrade.com.example.kioskupgrade.TutorialViewRenderer
import com.example.kioskupgrade.databinding.ActivityOrderBinding
import com.example.kioskupgrade.databinding.ActivityPaymentBinding
import com.example.kioskupgrade.databinding.OrderItemBinding
import com.example.kioskupgrade.databinding.PaymentItemBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.DecimalFormat

//결제화면 제어
var items = ArrayList<String>()
var prices = ArrayList<Int>()
var numbs = ArrayList<Int>()

class paymentActivity : AppCompatActivity() {
    lateinit var database : DatabaseReference
    val decimalFormat = DecimalFormat("#,###")
    var adapter: PaymentAdapter? = null

    var itemKeys: ArrayList<String>? = null

    var currentStock = mutableMapOf<String, Map<String, Int>>()
    var sumprice = 0
    lateinit var tutorial_renderer : TutorialViewRenderer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        items = intent.getStringArrayListExtra("items") as ArrayList<String>
        prices = intent.getIntegerArrayListExtra("prices") as ArrayList<Int>
        numbs = intent.getIntegerArrayListExtra("numbs") as ArrayList<Int>
        sumprice= intent.getIntExtra("price", 0)
        binding.totalPrice.text = decimalFormat.format(sumprice)+" 원"

        itemKeys = intent.getStringArrayListExtra("keys")
        database = Firebase.database.reference
        Log.d("Data Check", itemKeys?.size.toString())
        for (k in 0..itemKeys!!.size-1) {
            var tempItem = mutableMapOf<String, Int>()

            database.child(itemKeys!![k]).addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    tempItem.put("num",snapshot.child("num").value.toString().toInt() - numbs[k])
                    tempItem.put("rescent_sale",snapshot.child("rescent_sale").value.toString().toInt() + numbs[k])
                    tempItem.put("today_sale",snapshot.child("today_sale").value.toString().toInt() + numbs[k])
                    tempItem.put("twoweek_sale",snapshot.child("twoweek_sale").value.toString().toInt() + numbs[k])
                    tempItem.put("onemonth_sale",snapshot.child("onemonth_sale").value.toString().toInt() + numbs[k])
                }
                override fun onCancelled(error: DatabaseError) {
                    Log.d("Error",error.message)
                }
            })
            currentStock.put(itemKeys!![k], tempItem)
        }

        adapter = PaymentAdapter(this, items)

        // 어댑터 설정
        binding.listView.adapter = adapter

        binding.packing.setOnClickListener {
            Toast.makeText(this,"포장을 눌렀습니다",Toast.LENGTH_SHORT).show();
            if(CrossActivityInfo.isTutorial)
                tutorial_renderer.MovePhase()
        }

        binding.inShop.setOnClickListener {
            Toast.makeText(this,"매장에서 식사를 눌렀습니다",Toast.LENGTH_SHORT).show();
            if(CrossActivityInfo.isTutorial)
                tutorial_renderer.MovePhase()
        }

        //하단 4개의 결제버튼을 눌렀을시에 발생하는 이벤트의 헨들러

        val eventHandler =	object	:	DialogInterface.OnClickListener {
            override	fun	onClick(dialog:	DialogInterface?, which:	Int)	{
                //긍정 버튼을 누른다면(결제완료)-> finsh activty 이동->어플 종료후 다시 실행
                if(which	==	DialogInterface.BUTTON_POSITIVE)	{
                    Log.d("Data Check", currentStock.toString())
                    for (i in currentStock) {
                        Log.d("Data Check",i.value.toString())
                        Firebase.database.reference.child(i.key).updateChildren(i.value)
                    }

                    val intent = Intent(applicationContext, FinishActivity::class.java)
                    startActivity(intent)
                    //부정 버튼을 누른다면-> ALERT DIALOG DISSMISS
                }else	if(which	==	DialogInterface.BUTTON_NEGATIVE)	{
                    Log.d("ALERTDLG",	"결제를 취소하였습니다 다시 시도해주세요")
                    val player: MediaPlayer = MediaPlayer.create(this@paymentActivity,R.raw.cancel)
                    player.start()
                }
            }
        }

        binding.creditCard.setOnClickListener {
            Toast.makeText(this,"신용카드로 결제",Toast.LENGTH_SHORT).show();
            val builder = AlertDialog.Builder(this@paymentActivity)

            builder.setPositiveButton("결제완료",eventHandler)
            builder.setNegativeButton("취소", eventHandler)

            val inflater = layoutInflater
            val dialoglayout: View = inflater.inflate(R.layout.dialog_credit, null)

            builder.setView(dialoglayout)
            builder.show()

            if(CrossActivityInfo.isTutorial){
                tutorial_renderer.MovePhase()

                val mp = MediaPlayer.create(this, R.raw.choose_card)
                mp.start()
            }
        }

        binding.cash.setOnClickListener {
            Toast.makeText(this,"현금으로 결제",Toast.LENGTH_SHORT).show();
            val builder = AlertDialog.Builder(this@paymentActivity)

            builder.setPositiveButton("결제완료",eventHandler)
            builder.setNegativeButton("취소", eventHandler)

            val inflater = layoutInflater
            val dialoglayout: View = inflater.inflate(R.layout.dialog_cash, null)

            builder.setView(dialoglayout)
            builder.show()

            if(CrossActivityInfo.isTutorial){
                tutorial_renderer.MovePhase()

                val mp = MediaPlayer.create(this, R.raw.choose_cash)
                mp.start()
            }
        }

        binding.toss.setOnClickListener {
            Toast.makeText(this,"토스로 결제",Toast.LENGTH_SHORT).show();
            val builder = AlertDialog.Builder(this@paymentActivity)

            builder.setPositiveButton("결제완료",eventHandler)
            builder.setNegativeButton("취소", eventHandler)

            val inflater = layoutInflater
            val dialoglayout: View = inflater.inflate(R.layout.dialog_toss, null)

            builder.setView(dialoglayout)
            builder.show()

            if(CrossActivityInfo.isTutorial){
                tutorial_renderer.MovePhase()

                val mp = MediaPlayer.create(this, R.raw.choose_toss)
                mp.start()
            }
        }

        binding.etc.setOnClickListener {
            Toast.makeText(this,"기타결제수단으로 결제",Toast.LENGTH_SHORT).show();
            val builder = AlertDialog.Builder(this@paymentActivity)

            builder.setPositiveButton("결제완료",eventHandler)
            builder.setNegativeButton("취소", eventHandler)

            val inflater = layoutInflater
            val dialoglayout: View = inflater.inflate(R.layout.dialog_etc, null)

            builder.setView(dialoglayout)
            builder.show()

            if(CrossActivityInfo.isTutorial){
                tutorial_renderer.MovePhase()

                val mp = MediaPlayer.create(this, R.raw.choose_etc)
                mp.start()
            }
        }

        if(CrossActivityInfo.isTutorial){
            SetTutorialView(binding)
        }
    }

    fun SetTutorialView(binding : ActivityPaymentBinding){
        val panels : MutableList<ImageView> = mutableListOf()
        panels.add(binding.tutorialPanel1)
        panels.add(binding.tutorialPanel2)
        panels.add(binding.tutorialPanel3)
        panels.add(binding.tutorialPanel4)

        tutorial_renderer = TutorialViewRenderer(this.applicationContext, panels, binding.tutorialText, binding.tutorialNextButton)
//        tutorial_renderer.Highlight(0.05f, 0.6f, 0.97f, 1f)
//        tutorial_renderer.SetText("",0.01f, 1.35f, 30f)
//        tutorial_renderer.SetNextButton(0.1f, 2f)

        tutorial_renderer.StartFrom_PaymentPhase()
        tutorial_renderer.StartTutorial()
    }
}

class PaymentAdapter(val context: Context, val order : ArrayList<String>) : BaseAdapter() {
    override fun getCount(): Int {
        return order.size
    }

    override fun getItem(position: Int): Any {
        return order[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
        val binding2 = PaymentItemBinding.inflate(LayoutInflater.from(context))
        val view:View = binding2.root

        val decimalFormat = DecimalFormat("#,###")

        val menu = binding2.tvMenu
        val num = binding2.tvNum
        val price = binding2.tvPrice

        menu.text = items[position]
        num.text = "x " + numbs[position].toString()
        price.text = decimalFormat.format(prices[position]) + " 원"

        return view
    }

}

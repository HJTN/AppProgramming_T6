package com.example.kioskupgrade

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kioskupgrade.adapter.SelectedMenu
import com.example.kioskupgrade.adapter.SelectedMenuAdapter
import com.example.kioskupgrade.adapter.StockAdapter
import com.example.kioskupgrade.databinding.ActivityKioskmainBinding
import com.google.android.material.tabs.TabLayout
import org.w3c.dom.Text


class KioskmainActivity : AppCompatActivity() {
    lateinit var items : ArrayList<MenuItem>
    lateinit var menuGRV: GridView
    lateinit var selectedItems : ArrayList<SelectedMenu>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityKioskmainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //상단 탭
        val tabLayout = binding.tabs

        val tab1: TabLayout.Tab = tabLayout.newTab()
        tab1.text = "전체"
        tabLayout.addTab(tab1)

        val tab2: TabLayout.Tab = tabLayout.newTab()
        tab2.text = "버거"
        tabLayout.addTab(tab2)

        val tab3: TabLayout.Tab = tabLayout.newTab()
        tab3.text = "음료"
        tabLayout.addTab(tab3)

        val tab4: TabLayout.Tab = tabLayout.newTab()
        tab4.text = "사이드 메뉴"
        tabLayout.addTab(tab4)

        //메뉴 구성 및 grid view 객체 생성
        menuGRV = binding.menuGrid
        items = ArrayList<MenuItem>()

        for (i in 1..10) {
            items.add(MenuItem("싸이버거", 5000))
        }
        val menuAdapter = MenuAdapter(items, this@KioskmainActivity)
        menuGRV.adapter = menuAdapter

        menuGRV.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            // inside on click method we are simply displaying
            // a toast message with course name.
            Toast.makeText(
                applicationContext, items[position].name + " selected",
                Toast.LENGTH_SHORT
            ).show()
        }

        //장바구니 리사이클러뷰
        selectedItems = ArrayList<SelectedMenu>()
        selectedItems.add(SelectedMenu("싸이버거", 400, 1))
        selectedItems.add(SelectedMenu("싸이버거", 400, 1))

        binding.recyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        binding.recyclerView.adapter = SelectedMenuAdapter(selectedItems)

        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(binding.root.context,
                LinearLayoutManager.VERTICAL)
        )
    }
}

data class MenuItem (
    var name : String,
    var price : Int
)

class MenuAdapter(
    private val items: ArrayList<MenuItem>,
    private val context: Context
) : BaseAdapter()  {
    private var layoutInflater: LayoutInflater? = null

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView

        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        if (convertView == null) {
            convertView = layoutInflater!!.inflate(R.layout.menu_item, null)
            var icon = convertView!!.findViewById<ImageView>(R.id.menu_icon)
            var name = convertView!!.findViewById<TextView>(R.id.menu_name)
            var price = convertView!!.findViewById<TextView>(R.id.menu_price)

            name.setText(items[position].name)
            price.setText(items[position].price.toString())
        }

        return convertView
    }
}
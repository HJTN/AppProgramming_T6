import com.example.kioskupgrade.databinding.OrderItemBinding
import com.example.kioskupgrade.databinding.MenuItemBinding
import com.example.kioskupgrade.fragment.BeverageOrderFragment
import com.example.kioskupgrade.fragment.HamburgerOrderFragment
import com.example.kioskupgrade.fragment.SidemenuOrderFragment
import com.example.teamprogect.fragment.RecommendOrderFragment
import com.google.android.material.tabs.TabLayout
import java.text.DecimalFormat

//주문 화면 관리
var orderList = ArrayList<String>() // 주문 메뉴명
var priceList = ArrayList<Int>() // 주문 메뉴당 가격
var subPriceList = ArrayList<Int>() // 주문 메뉴 하나당 가격(개수 수정시 참고 위해 저장)
var numList = ArrayList<Int>()  // 주문 메뉴당 개수
var stocks = ArrayList<String>()
var adapter: OrderAdapter? = null

var totalPrice : Int = 0
lateinit var priceText: TextView

class SubActivity : AppCompatActivity() {
    lateinit var fragmentManager: FragmentManager
    lateinit var transaction: FragmentTransaction
    var listView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tabLayout = binding.tabs

        val tab1: TabLayout.Tab = tabLayout.newTab()
        tab1.text = "추천 메뉴"
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

        fragmentManager = supportFragmentManager
        transaction = fragmentManager.beginTransaction()
        transaction.add(binding.tabContent.id, RecommendOrderFragment())
        transaction.commit()

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val transaction = supportFragmentManager.beginTransaction()

                when(tab?.text) {
                    "추천 메뉴" -> transaction.replace(binding.tabContent.id, RecommendOrderFragment())
                    "버거" -> transaction.replace(binding.tabContent.id, HamburgerOrderFragment())
                    "음료" -> transaction.replace(binding.tabContent.id, BeverageOrderFragment())
                    "사이드 메뉴" -> transaction.replace(binding.tabContent.id, SidemenuOrderFragment())
                }
                transaction.commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Log.d("TabButton", "onTabUnselected...")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Log.d("TabButton", "onTabReselected...")
            }
        })

        totalPrice = 0

        priceText = findViewById(R.id.sum)

        orderList.clear()
        priceList.clear()
        numList.clear()
        subPriceList.clear()

        adapter = OrderAdapter(this, orderList)

        // 어댑터 설정
        listView = findViewById(R.id.listView) as ListView?
        listView!!.adapter = adapter

        adapter?.notifyDataSetChanged();

        val next1Button: Button = findViewById(R.id.purchase)
        next1Button.setOnClickListener {
            val intent = Intent(applicationContext, paymentActivity::class.java)

            // 다음액티비티인 payment activity에 어레이리스트 전달
            intent.putStringArrayListExtra("items",orderList)
            intent.putStringArrayListExtra("keys", stocks)
            intent.putExtra("price", totalPrice)
            startActivity(intent)
        }

        if(CrossActivityInfo.isTutorial){
            SetTutorialView(binding)
        }
    }

    fun SetTutorialView(binding : ActivityOrderBinding){
        val panels : MutableList<ImageView> = mutableListOf()
        panels.add(binding.tutorialPanel1)
        panels.add(binding.tutorialPanel2)
        panels.add(binding.tutorialPanel3)
        panels.add(binding.tutorialPanel4)

        val tutorial_renderer = TutorialViewRenderer(this.applicationContext, panels, binding.tutorialText, binding.tutorialNextButton)
        //tutorial_renderer.Highlight(0.45f, 0.73f, 1f, 1f)
        //tutorial_renderer.SetText("",0.01f, 1.35f, 30f)
        //tutorial_renderer.SetNextButton(0.1f, 2f)
        tutorial_renderer.StartTutorial()
    }
}

class OrderAdapter(val context: Context, val order : ArrayList<String>) : BaseAdapter() {
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
        val binding2 = OrderItemBinding.inflate(LayoutInflater.from(context))
        val view:View = binding2.root

        val decimalFormat = DecimalFormat("#,###")

        val menu = binding2.tvMenu
        val num = binding2.tvNum
        val price = binding2.tvPrice

        menu.text = orderList[position]
        num.text = numList[position].toString()
        price.text = decimalFormat.format(priceList[position]) + " 원"

        binding2.btnMinus.setOnClickListener {
            if (numList[position] == 1) {
                numList.removeAt(position)
                priceList.removeAt(position)
                orderList.removeAt(position)
                totalPrice -= subPriceList[position]
                subPriceList.removeAt(position)

                priceText.text = "가격: " + decimalFormat.format(totalPrice) + " 원"
                adapter?.notifyDataSetChanged()
                Log.d("order", "minus: num=${numList.size.toString()} , order=${orderList.size.toString()}" +
                        " , price=${priceList.size.toString()} , subprice=${subPriceList.size.toString()}" )
            }
            else {
                numList[position] -= 1
                priceList[position] -= subPriceList[position]
                totalPrice -= subPriceList[position]

                priceText.text = "가격: " + decimalFormat.format(totalPrice) + " 원"
                adapter?.notifyDataSetChanged()
                Log.d("order", "minus: num=${numList.size.toString()} , order=${orderList.size.toString()}" +
                        " , price=${priceList.size.toString()} , subprice=${subPriceList.size.toString()}" )
            }
        }
        binding2.btnPlus.setOnClickListener {
            numList[position] += 1
            priceList[position] += subPriceList[position]
            totalPrice += subPriceList[position]

            priceText.text = "가격: " + decimalFormat.format(totalPrice) + " 원"
            adapter?.notifyDataSetChanged()
            Log.d("order", "plus: num=${numList.size.toString()} , order=${orderList.size.toString()}" +
                    " , price=${priceList.size.toString()} , subprice=${subPriceList.size.toString()}" )
        }
        return view
    }

}

class MenuViewHolder(val binding: MenuItemBinding): RecyclerView.ViewHolder(binding.root)

class MenuAdapter(val dataSet: MutableList<Item>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MenuViewHolder(MenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("RecyclerView", "onBindViewHolder(): $position")
        val binding = (holder as MenuViewHolder).binding
        val decimalFormat = DecimalFormat("#,###")

        binding.itemName.text = dataSet[position].name
        binding.itemPrice.text = decimalFormat.format(dataSet[position].price) + " 원"
        binding.itemImg.setImageResource(dataSet[position].img)

        binding.itemButton.setOnClickListener(View.OnClickListener {
            stocks.add(dataSet[position].key)
            if (orderList.contains(dataSet[position].getName())) {
                val idx = orderList.indexOf(dataSet[position].getName())
                numList[idx] += 1
                priceList[idx] += dataSet[position].getPrice()
            }
            else {
                orderList.add(dataSet[position].getName())
                numList.add(1)
                priceList.add(dataSet[position].getPrice())
                subPriceList.add(dataSet[position].getPrice())
            }

            totalPrice = totalPrice + dataSet[position].price
            priceText.text = "가격: " + decimalFormat.format(totalPrice) + " 원"
            adapter?.notifyDataSetChanged()
            Log.d("order", "new: num=${numList.size.toString()} , order=${orderList.size.toString()}" +
                    " , price=${priceList.size.toString()} , subprice=${subPriceList.size.toString()}" )
            Toast.makeText(binding.root.context, "${binding.itemName.text} Clicked", Toast.LENGTH_SHORT).show()
        })
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}

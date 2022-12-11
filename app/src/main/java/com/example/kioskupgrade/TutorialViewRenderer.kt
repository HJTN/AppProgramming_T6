package com.example.kioskupgrade.com.example.kioskupgrade

import android.content.Context
import android.media.MediaPlayer
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.kioskupgrade.R

//튜토리얼 화면 렌더링을 위한 클래스. 사용하는 쪽에서 application context와 4개의 반투명 ImageView를 넘겨주며 생성한다.
class TutorialViewRenderer(val context: Context,
                           val panels : MutableList<ImageView>, val tv : TextView, val btn : Button) {

    lateinit var mp : MediaPlayer

    val params = arrayOf(

        arrayOf(0f, 0f, 0f, 0f,
            "주문 시작하기", 0.23f, 0.5f, 40f,
            0.37f, 0.8f,
            R.raw.intro),

        arrayOf(0f, 0.06f, 1f, 0.13f,
        "상단 탭 버튼의\n종류를 선택해주세요", 0.07f, 0.7f, 40f,
        0.37f, 1f,
            R.raw.tab4),

        arrayOf(0f, 0.06f, 1f, 0.7f,
            "메뉴를 담아주세요",0.4f, 1.3f, 30f,
            0.67f, 1.42f,
            R.raw.tabselect),

        arrayOf(0f, 0.06f, 1f, 1f,
            "",0.01f, 1.35f, 30f,
            0.1f, 2f,
            R.raw.productselect),

        arrayOf(0f, 0.06f, 1f, 0.55f,
            "",0.01f, 1.35f, 30f,
            0.1f, 2f,
            R.raw.choose_serve),

        arrayOf(0f, 0.55f, 1f, 1f,
            "",0.01f, 1.35f, 30f,
            0.1f, 2f,
            R.raw.paymentmethodchoose))

    var phase = 0
    val maxphase = 5

    fun StartTutorial(){
        Highlight(params[phase][0] as Float, params[phase][1] as Float
        ,params[phase][2] as Float,params[phase][3] as Float)

        SetText(params[phase][4] as String, params[phase][5] as Float,params[phase][6] as Float,params[phase][7] as Float)

        SetNextButton(params[phase][8] as Float, params[phase][9] as Float)

        if(::mp.isInitialized){
            mp.pause()
            mp.release()
        }
        mp = MediaPlayer.create(context, params[phase][10] as Int)
        mp.start()
    }

    fun StartFrom_PaymentPhase(){
        PassPhase_to(4)
    }

    fun PassPhase_to(p : Int){
        phase = p
    }

    fun MovePhase(){
        phase += 1
        Release()
        if(phase <= maxphase){
            StartTutorial()
        }
    }

    //원하는 영역 이외의 부분을 터치불가, 어둡게 만든다.
    //lx_ratio, ly_ratio 에는 원하는 영역의 왼쪽 위 좌표를 화면상의 비율로 표현해 넘겨주면 된다. 예를들어 화면중앙은 0.5f가 된다.
    fun Highlight(lx_ratio : Float, ly_ratio : Float,
                  rx_ratio : Float, ry_ratio : Float){
        val display = context?.resources?.displayMetrics
        val deviceWidth : Int = display?.widthPixels!!
        val deviceHeight : Int = display?.heightPixels!!

        val lx : Float = deviceWidth.times(lx_ratio); val ly: Float = deviceHeight.times(ly_ratio)
        val rx : Float = deviceWidth.times(rx_ratio); val ry: Float = deviceHeight.times(ry_ratio)

        val panel1 = panels[0]
        val panel2 = panels[1]
        val panel3 = panels[2]
        val panel4 = panels[3]

        panel1.visibility = View.VISIBLE
        SetImagePos(panel1, 0f, 0f, lx.toInt(), ry.toInt())

        panel2.visibility = View.VISIBLE
        SetImagePos(panel2, 0f, ry, rx.toInt(), deviceHeight - ry.toInt())

        panel3.visibility = View.VISIBLE
        SetImagePos(panel3, rx, ly, deviceWidth - lx.toInt(), deviceHeight - ly.toInt())

        panel4.visibility = View.VISIBLE
        SetImagePos(panel4, lx, 0f, deviceWidth - lx.toInt(), ly.toInt())

    }

    //Hightlight 함수를 위한 보조함수
    fun SetImagePos(img : ImageView, x:Float, y:Float, w:Int, h:Int){
        img.x = x; img.y = y;
        img.getLayoutParams().width = w
        img.getLayoutParams().height = h
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        img.requestLayout()
    }

    //안내 메세지의 내용과 위치를 조정한다.
    fun SetText(str : String, x_ratio:Float, y_ratio:Float, size : Float){
        val display = context?.resources?.displayMetrics
        val deviceWidth : Int = display?.widthPixels!!
        val deviceHeight : Int = display?.heightPixels!!

        tv.visibility = View.VISIBLE
        tv.text = str
        tv.x = deviceWidth.times(x_ratio); tv.y = deviceWidth.times(y_ratio)
        tv.textSize = size
        tv.requestLayout()
    }

    fun SetNextButton(x_ratio:Float, y_ratio:Float){
        val display = context?.resources?.displayMetrics
        val deviceWidth : Int = display?.widthPixels!!
        val deviceHeight : Int = display?.heightPixels!!

        btn.visibility = View.VISIBLE
        btn.x = deviceWidth.times(x_ratio); btn.y = deviceWidth.times(y_ratio)
        btn.setOnClickListener({ MovePhase() })
        btn.requestLayout()
    }

    //하이라이트 상태를 해제시킨다.
    fun Release(){
        for(i in 0..3){
            panels[i].visibility = View.INVISIBLE
            panels[i].requestLayout()
        }
        tv.visibility = View.INVISIBLE
        tv.requestLayout()

        btn.visibility = View.INVISIBLE
        btn.requestLayout()
    }
}

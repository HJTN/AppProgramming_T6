package com.example.kioskupgrade

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView

//튜토리얼 화면 렌더링을 위한 클래스. 사용하는 쪽에서 application context와 4개의 반투명 ImageView를 넘겨주며 생성한다.
class TutorialViewRenderer(val context: Context, val panels : MutableList<ImageView>, val tv : TextView) {

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

    //하이라이트 상태를 해제시킨다.
    fun Release(){
        for(i in 0..3){
            panels[i].visibility = View.INVISIBLE
            panels[i].requestLayout()
        }
        tv.visibility = View.INVISIBLE
        tv.requestLayout()
    }
}
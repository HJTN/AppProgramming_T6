package com.example.kioskupgrade.DTO

class Material {
    var name: String = ""
    var img: String = ""
    var num: Int = 0

    constructor()

    constructor(name:String, img:String, num:Int) {
        this.name = name
        this.img = img
        this.num = num
    }

    @JvmName("getName1")
    fun getName(): String {
        return this.name
    }

    @JvmName("setName1")
    fun setName(name:String) {
        this.name = name
    }

    @JvmName("getImg1")
    fun getImg(): String {
        return this.img
    }

    @JvmName("setImg1")
    fun setImg(img: String) {
        this.img = img
    }

    @JvmName("getNum1")
    fun getNum(): Int {
        return this.num
    }

    @JvmName("setNum1")
    fun setNum(num:Int) {
        this.num = num
    }
}
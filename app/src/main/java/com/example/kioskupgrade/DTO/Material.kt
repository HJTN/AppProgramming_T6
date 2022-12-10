package com.example.kioskupgrade.DTO

class Material {
    var key: String = ""
    var name: String = ""
    var img: Int = 0
    var num: Int = 0

    constructor()

    constructor(key:String, name:String, img:Int, num:Int) {
        this.key = key
        this.name = name
        this.img = img
        this.num = num
    }

    @JvmName("getKey1")
    fun getKey(): String {
        return this.key
    }

    @JvmName("setKey1")
    fun setKey(key: String) {
        this.key = key
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
    fun getImg(): Int {
        return this.img
    }

    @JvmName("setImg1")
    fun setImg(img: Int) {
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
package com.example.kioskupgrade.DTO

// item 데이터 저장 위한 클래스

class Item {
    var key = ""
    var name = ""
    var price: Int = 0
    var img: Int = 0
    var num: Int = 0

    constructor()

    constructor(key: String, name: String, price: Int, img: Int, num: Int) {
        this.key = key
        this.name = name
        this.price = price
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
    fun setName(name: String) {
        this.name = name
    }

    @JvmName("getPrice1")
    fun getPrice(): Int {
        return this.price
    }

    @JvmName("setPrice1")
    fun setPrice(price: Int) {
        this.price = price
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
    fun setNum(num: Int) {
        this.num = num
    }
}
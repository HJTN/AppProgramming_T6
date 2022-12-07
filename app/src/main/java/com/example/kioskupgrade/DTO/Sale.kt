package com.example.kioskupgrade.DTO

class Sale {
    var name: String = ""
    var img: String = ""
    var num: Int = 0
    var account: Int = 0

    constructor()

    constructor(name:String, img:String, num:Int, account:Int) {
        this.name = name
        this.img = img
        this.num = num
        this.account = account
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

    @JvmName("getAccount1")
    fun getAccount(): Int {
        return this.account
    }

    @JvmName("setAccount1")
    fun setAccount(account:Int) {
        this.account = account
    }
}
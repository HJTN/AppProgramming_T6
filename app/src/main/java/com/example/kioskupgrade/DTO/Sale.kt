package com.example.kioskupgrade.DTO

class Sale {
    var name: String = ""
    var img_path: String = ""
    var num: Int = 0
    var account: Int = 0

    constructor()

    constructor(name:String, img_path:String, num:Int, account:Int) {
        this.name = name
        this.img_path = img_path
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

    fun getImgPath(): String {
        return this.img_path
    }

    fun setImgPath(img_path: String) {
        this.img_path = img_path
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
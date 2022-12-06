package com.example.kioskupgrade.DTO

class Material {
    var root: String = ""
    var name: String = ""
    var imgPath: String = ""
    var num: Int = 0

    constructor()

    constructor(root:String, name:String, imgPath:String, num:Int) {
        this.root = root
        this.name = name
        this.imgPath = imgPath
        this.num = num
    }

    @JvmName("getRoot1")
    fun getRoot(): String {
        return this.root
    }

    @JvmName("setRoot1")
    fun setRoot(key:String) {
        this.root = key
    }

    @JvmName("getName1")
    fun getName(): String {
        return this.name
    }

    @JvmName("setName1")
    fun setName(name:String) {
        this.name = name
    }

    @JvmName("getImgPath1")
    fun getImgPath(): String {
        return this.imgPath
    }

    @JvmName("setImgPath1")
    fun setImgPath(img_path: String) {
        this.imgPath = img_path
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
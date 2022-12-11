package com.example.kioskupgrade.`interface`

import com.example.kioskupgrade.DTO.Material
import com.example.kioskupgrade.DTO.Sale
import java.text.DecimalFormat

class CalcInterface {
    lateinit var dataSet: MutableList<Sale>

    constructor()

    constructor(dataSet: MutableList<Sale>) {
        this.dataSet = dataSet
    }

    @JvmName("getDataSet1")
    fun getDataSet(): MutableList<Sale> {
        return dataSet
    }

    @JvmName("setDataSet1")
    fun setDataSet(dataSet: MutableList<Sale>) {
        this.dataSet = dataSet
    }

    fun getTotalSale(): String {
        var total = 0
        val decimalFormat = DecimalFormat("#,###")
        for (data in dataSet) {
            total += data.num
        }
        return decimalFormat.format(total)
    }

    fun getTotalAccount(): String {
        var total = 0
        val decimalFormat = DecimalFormat("#,###")
        for (data in dataSet) {
            total += data.num * data.account
        }
        return decimalFormat.format(total)
    }

    fun getPopular(): Sale {
        var target = Sale()
        var mostNum = 0

        for (data in dataSet) {
            if (mostNum < data.num)
                target = data
        }

        return target
    }
}
package com.example.qlnongsan.mode

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class ChiTietDonHang(
    var idDonHang: String = "",
    var idNongSan: String = "",
    var soLuong: Int? = null,
    var gia: Double? = null
) : RealmObject()
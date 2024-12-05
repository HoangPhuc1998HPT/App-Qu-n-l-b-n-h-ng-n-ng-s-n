package com.example.qlnongsan.mode

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class DonHang(
    @PrimaryKey var idDonHang: String = "",
    var idKhachHang: String = "",
    var idNongSan: String = "",
    var slNongSan: Int = 0,
    var gia: Double = 0.0,
    var tongGia: Double = 0.0,
    var ngDh: Date = Date()
) : RealmObject()


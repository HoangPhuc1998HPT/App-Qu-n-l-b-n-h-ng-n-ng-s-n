package com.example.qlnongsan.mode

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class NongSan(
    @PrimaryKey var idNongSan: String = "",
    var tenNongSan: String = "",
    var moTaNongSan: String = "",
    var dvt: String = "",
    var gia: Double = 0.0,
    var slTonKho: Int = 0,
    var ngTao: Date = Date(),
    var ngCapNhat: Date? = null
) : RealmObject()
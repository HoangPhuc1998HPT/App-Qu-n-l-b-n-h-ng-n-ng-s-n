package com.example.qlnongsan.mode

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class KhachHang(
    @PrimaryKey var idKhachHang: String = "",
    var ho: String = "",
    var ten: String = "",
    var email: String = "",
    var sdt: String = "",
    var diaChi: String = "",
    var ngayTao: Date = Date()
) : RealmObject()

package com.example.qlnongsan.mode

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class NhanVien(
    @PrimaryKey var idNhanVien: String = "",
    var ho: String = "",
    var ten: String = "",
    var email: String = "",
    var sdt: String = "",
    var vaiTro: String = "",
    var ngayTao: Date = Date()
) : RealmObject()

package com.example.qlnongsan

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.qlnongsan.mode.NhanVien
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import java.util.*

class TaoNhanVienActivity : AppCompatActivity() {
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_taonhanvien)

        val configNhanVien = RealmConfiguration.Builder()
            .name("NHANVIEN.realm")
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .allowWritesOnUiThread(true)
            .build()
        realm = Realm.getInstance(configNhanVien)

        findViewById<Button>(R.id.btnTaoNhanVien).setOnClickListener {
            taoNhanVien()
        }
    }

    private fun taoNhanVien() {
        val ho = findViewById<EditText>(R.id.editHo).text.toString()
        val ten = findViewById<EditText>(R.id.editTen).text.toString()
        val email = findViewById<EditText>(R.id.editEmail).text.toString()
        val sdt = findViewById<EditText>(R.id.editSDT).text.toString()
        val vaiTro = findViewById<EditText>(R.id.editVaiTro).text.toString()

        val validVaiTro = listOf("quan ly", "ban hang", "giao hang", "thu ngan", "ke toan")
        if (ho.isEmpty() || ten.isEmpty() || email.isEmpty() || sdt.isEmpty() || vaiTro.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            return
        } else if (!validVaiTro.contains(vaiTro.toLowerCase())) {
            Toast.makeText(this, "Vai trò không hợp lệ", Toast.LENGTH_SHORT).show()
            return
        }

        val lastEmployee = realm.where<NhanVien>().sort("idNhanVien", io.realm.Sort.DESCENDING).findFirst()
        val newId = if (lastEmployee != null) {
            val lastId = lastEmployee.idNhanVien.substring(1).toInt()
            "F%03d".format(lastId + 1)
        } else {
            "F001"
        }

        realm.executeTransaction { r ->
            val nhanVien = r.createObject<NhanVien>(newId)
            nhanVien.ho = ho
            nhanVien.ten = ten
            nhanVien.email = email
            nhanVien.sdt = sdt
            nhanVien.vaiTro = vaiTro
            nhanVien.ngayTao = Date()
        }

        Toast.makeText(this, "Nhân viên đã được tạo", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}

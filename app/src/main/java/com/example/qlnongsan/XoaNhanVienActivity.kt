package com.example.qlnongsan

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.qlnongsan.mode.NhanVien
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.where

class XoaNhanVienActivity : AppCompatActivity() {
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xoanhanvien)

        // Use NHANVIEN.realm configuration
        val configNhanVien = RealmConfiguration.Builder()
            .name("NHANVIEN.realm")
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .allowWritesOnUiThread(true)
            .build()
        realm = Realm.getInstance(configNhanVien)

        findViewById<Button>(R.id.btnXoaNhanVien).setOnClickListener {
            xoaNhanVien()
        }
    }

    private fun xoaNhanVien() {
        val idNhanVien = findViewById<EditText>(R.id.editIdNhanVien).text.toString()

        if (idNhanVien.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập ID Nhân Viên", Toast.LENGTH_SHORT).show()
            return
        }

        realm.executeTransaction { r ->
            val result = r.where<NhanVien>().equalTo("idNhanVien", idNhanVien).findFirst()
            if (result != null) {
                result.deleteFromRealm()
                Toast.makeText(this, "Nhân viên đã được xóa", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Không tìm thấy Nhân Viên", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}

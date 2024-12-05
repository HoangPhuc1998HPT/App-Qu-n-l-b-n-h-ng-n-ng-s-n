package com.example.qlnongsan

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.qlnongsan.mode.NongSan
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.where
import java.util.*

class CapNhatNongSanActivity : AppCompatActivity() {
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_capnhatnongsan)

        // Use TTNONGSAN.realm configuration
        val configNongSan = RealmConfiguration.Builder()
            .name("TTNONGSAN.realm")
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .allowWritesOnUiThread(true)
            .build()
        realm = Realm.getInstance(configNongSan)

        findViewById<Button>(R.id.btnCapNhatNongSan).setOnClickListener {
            capNhatNongSan()
        }
    }

    private fun capNhatNongSan() {
        val idNongSan = findViewById<EditText>(R.id.editIdNongSan).text.toString()
        val gia = findViewById<EditText>(R.id.editGia).text.toString().toDoubleOrNull()
        val slTonKho = findViewById<EditText>(R.id.editSLTonKho).text.toString().toIntOrNull()

        if (idNongSan.isEmpty() || gia == null || slTonKho == null) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            return
        }

        realm.executeTransaction { r ->
            val nongSan = r.where<NongSan>().equalTo("idNongSan", idNongSan).findFirst()
            if (nongSan != null) {
                nongSan.gia = gia
                nongSan.slTonKho = slTonKho
                nongSan.ngCapNhat = Date()
                Toast.makeText(this, "Nông sản đã được cập nhật", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Không tìm thấy Nông sản", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}

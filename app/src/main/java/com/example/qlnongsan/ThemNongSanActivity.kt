package com.example.qlnongsan

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.qlnongsan.mode.NongSan
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import java.util.*

class ThemNongSanActivity : AppCompatActivity() {
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_themnongsan)

        // Use TTNONGSAN.realm configuration
        val configTTNongSan = RealmConfiguration.Builder()
            .name("TTNONGSAN.realm")
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .allowWritesOnUiThread(true)
            .build()
        realm = Realm.getInstance(configTTNongSan)

        findViewById<Button>(R.id.btnThemNongSan).setOnClickListener {
            themNongSan()
        }
    }

    private fun themNongSan() {
        val tenNongSan = findViewById<EditText>(R.id.editTenNongSan).text.toString()
        val moTaNongSan = findViewById<EditText>(R.id.editMoTaNongSan).text.toString()
        val dvt = findViewById<EditText>(R.id.editDVT).text.toString()
        val gia = findViewById<EditText>(R.id.editGia).text.toString().toDoubleOrNull()
        val slTonKho = findViewById<EditText>(R.id.editSLTonKho).text.toString().toIntOrNull()

        if (tenNongSan.isEmpty() || moTaNongSan.isEmpty() || dvt.isEmpty() || gia == null || slTonKho == null) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            return
        }

        val lastNongSan = realm.where<NongSan>().sort("idNongSan", io.realm.Sort.DESCENDING).findFirst()
        val newId = if (lastNongSan != null) {
            val lastId = lastNongSan.idNongSan.substring(2).toInt()
            "NS%03d".format(lastId + 1)
        } else {
            "NS001"
        }

        realm.executeTransaction { r ->
            val nongSan = r.createObject<NongSan>(newId)
            nongSan.tenNongSan = tenNongSan
            nongSan.moTaNongSan = moTaNongSan
            nongSan.dvt = dvt
            nongSan.gia = gia
            nongSan.slTonKho = slTonKho
            nongSan.ngTao = Date()
            nongSan.ngCapNhat = null
        }

        Toast.makeText(this, "Nông sản đã được thêm", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}

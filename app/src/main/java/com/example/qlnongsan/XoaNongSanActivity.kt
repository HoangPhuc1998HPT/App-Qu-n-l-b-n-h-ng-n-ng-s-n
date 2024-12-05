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

class XoaNongSanActivity : AppCompatActivity() {
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xoanongsan)

        // Use TTNONGSAN.realm configuration
        val configNongSan = RealmConfiguration.Builder()
            .name("TTNONGSAN.realm")
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .allowWritesOnUiThread(true)
            .build()
        realm = Realm.getInstance(configNongSan)

        findViewById<Button>(R.id.btnXoaNongSan).setOnClickListener {
            xoaNongSan()
        }
    }

    private fun xoaNongSan() {
        val idNongSan = findViewById<EditText>(R.id.editIdNongSan).text.toString()

        if (idNongSan.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập ID Nông Sản", Toast.LENGTH_SHORT).show()
            return
        }

        realm.executeTransaction { r ->
            val result = r.where<NongSan>().equalTo("idNongSan", idNongSan).findFirst()
            if (result != null) {
                result.deleteFromRealm()
                Toast.makeText(this, "Nông sản đã được xóa", Toast.LENGTH_SHORT).show()
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

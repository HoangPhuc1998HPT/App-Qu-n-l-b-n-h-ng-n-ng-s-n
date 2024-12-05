package com.example.qlnongsan

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class NongSanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nongsan)

        findViewById<Button>(R.id.btnThemNongSan).setOnClickListener {
            startActivity(Intent(this, ThemNongSanActivity::class.java))
        }

        findViewById<Button>(R.id.btnCapNhatNongSan).setOnClickListener {
            startActivity(Intent(this, CapNhatNongSanActivity::class.java))
        }

        findViewById<Button>(R.id.btnXoaNongSan).setOnClickListener {
            startActivity(Intent(this, XoaNongSanActivity::class.java))
        }

        findViewById<Button>(R.id.btnThongTinNongSan).setOnClickListener {
            startActivity(Intent(this, ThongTinNongSanActivity::class.java))
        }
    }
}

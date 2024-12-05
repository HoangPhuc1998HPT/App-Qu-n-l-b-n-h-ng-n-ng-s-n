package com.example.qlnongsan

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.qlnongsan.databinding.ActivityMainBinding
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.types.TypedRealmObject
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Thong tin khach hang

        binding.btnKhachHang.setOnClickListener {
            startActivity(Intent(this, KhachHangActivity::class.java))
        }
        // Thong tin nhan vien
        binding.btnThongTinNhanVien.setOnClickListener {
            startActivity(Intent(this, NhanVienActivity::class.java))
        }

        binding.btnThongTinNongSan.setOnClickListener {
            startActivity(Intent(this, NongSanActivity::class.java))
        }

        binding.btnTaoDonHang.setOnClickListener {
            startActivity(Intent(this, TaoDonHangActivity::class.java))
        }

        binding.btnThongTinDonHang.setOnClickListener {
            startActivity(Intent(this, ThongTinDonHangActivity::class.java))
        }



    }
}
   // fun create(schema: Set<KClass<out TypedRealmObject>>) {}

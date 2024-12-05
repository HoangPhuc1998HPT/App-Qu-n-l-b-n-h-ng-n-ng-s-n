package com.example.qlnongsan

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.qlnongsan.mode.NongSan
import io.realm.Realm
import io.realm.kotlin.where

class NongSanDonHangAdapter(private val context: Context, private val realm: Realm, private val updateTongGiaCallback: () -> Unit) :
    RecyclerView.Adapter<NongSanDonHangAdapter.NongSanViewHolder>() {

    private val items = mutableListOf<NongSanDonHangItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NongSanViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_nongsan_donhang, parent, false)
        return NongSanViewHolder(view)
    }

    override fun onBindViewHolder(holder: NongSanViewHolder, position: Int) {
        val item = items[position]
        holder.editIdNongSan.setText(item.idNongSan)
        holder.editSLNongSan.setText(item.soLuong?.toString())
        holder.textViewGia.text = item.gia?.let { "$it ngàn đồng" } ?: ""

        holder.editIdNongSan.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val idNongSan = s.toString()
                if (idNongSan.isNotEmpty()) {
                    val nongSan = realm.where<NongSan>().equalTo("idNongSan", idNongSan).findFirst()
                    if (nongSan != null) {
                        holder.textViewGia.text = "${nongSan.gia} ngàn đồng"
                        item.gia = nongSan.gia
                    } else {
                        holder.textViewGia.text = ""
                        item.gia = null
                    }
                }
                item.idNongSan = idNongSan
                updateTongGiaCallback()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        holder.editSLNongSan.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val soLuong = s.toString().toIntOrNull()
                item.soLuong = soLuong
                updateTongGiaCallback()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItem() {
        items.add(NongSanDonHangItem())
        notifyItemInserted(items.size - 1)
    }

    fun getItems(): List<NongSanDonHangItem> {
        return items
    }

    inner class NongSanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val editIdNongSan: EditText = itemView.findViewById(R.id.editIdNongSan)
        val editSLNongSan: EditText = itemView.findViewById(R.id.editSLNongSan)
        val textViewGia: TextView = itemView.findViewById(R.id.textViewGia)
    }
}

data class NongSanDonHangItem(
    var idNongSan: String = "",
    var soLuong: Int? = null,
    var gia: Double? = null
)

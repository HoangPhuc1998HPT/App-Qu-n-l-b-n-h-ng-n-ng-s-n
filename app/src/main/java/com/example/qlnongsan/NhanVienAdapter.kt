package com.example.qlnongsan

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.qlnongsan.mode.NhanVien
import io.realm.RealmResults

class NhanVienAdapter(private val context: Context, private val nhanViens: RealmResults<NhanVien>)
    : RecyclerView.Adapter<NhanVienAdapter.NhanVienViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NhanVienViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_nhanvien, parent, false)
        return NhanVienViewHolder(view)
    }

    override fun onBindViewHolder(holder: NhanVienViewHolder, position: Int) {
        val nhanVien = nhanViens[position]
        holder.textViewId.text = nhanVien?.idNhanVien
        holder.textViewHoTen.text = "${nhanVien?.ho} ${nhanVien?.ten}"
        holder.textViewVaiTro.text = nhanVien?.vaiTro
    }

    override fun getItemCount(): Int {
        return nhanViens.size
    }

    inner class NhanVienViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewId: TextView = itemView.findViewById(R.id.textViewId)
        val textViewHoTen: TextView = itemView.findViewById(R.id.textViewHoTen)
        val textViewVaiTro: TextView = itemView.findViewById(R.id.textViewVaiTro)
    }
}

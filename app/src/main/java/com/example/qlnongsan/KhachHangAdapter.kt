package com.example.qlnongsan

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.qlnongsan.mode.KhachHang
import io.realm.RealmResults

class KhachHangAdapter(private val context: Context, private val khachHangs: RealmResults<KhachHang>)
    : RecyclerView.Adapter<KhachHangAdapter.KhachHangViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KhachHangViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_khachhang, parent, false)
        return KhachHangViewHolder(view)
    }

    override fun onBindViewHolder(holder: KhachHangViewHolder, position: Int) {
        val khachHang = khachHangs[position]
        holder.textViewId.text = khachHang?.idKhachHang
        holder.textViewHoTen.text = "${khachHang?.ho} ${khachHang?.ten}"
    }

    override fun getItemCount(): Int {
        return khachHangs.size
    }

    inner class KhachHangViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewId: TextView = itemView.findViewById(R.id.textViewId)
        val textViewHoTen: TextView = itemView.findViewById(R.id.textViewHoTen)
    }
}

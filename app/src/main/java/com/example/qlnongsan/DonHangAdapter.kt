package com.example.qlnongsan

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.qlnongsan.mode.DonHang
import io.realm.RealmResults

class DonHangAdapter(private val context: Context, private val donHangs: RealmResults<DonHang>)
    : RecyclerView.Adapter<DonHangAdapter.DonHangViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonHangViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_donhang, parent, false)
        return DonHangViewHolder(view)
    }

    override fun onBindViewHolder(holder: DonHangViewHolder, position: Int) {
        val donHang = donHangs[position]
        holder.textViewIdDonHang.text = donHang?.idDonHang
        holder.textViewTongGia.text = donHang?.tongGia.toString()
        holder.textViewNgDh.text = donHang?.ngDh.toString()
    }

    override fun getItemCount(): Int {
        return donHangs.size
    }

    inner class DonHangViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewIdDonHang: TextView = itemView.findViewById(R.id.textViewIdDonHang)
        val textViewTongGia: TextView = itemView.findViewById(R.id.textViewTongGia)
        val textViewNgDh: TextView = itemView.findViewById(R.id.textViewNgDh)
    }
}

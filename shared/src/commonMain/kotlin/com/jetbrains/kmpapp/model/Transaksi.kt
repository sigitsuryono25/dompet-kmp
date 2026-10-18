package com.jetbrains.kmpapp.model

import androidx.compose.runtime.Immutable

// Data Dummy (Bab 5: Sungai Data yang Mengalir)
data class Transaksi(val id: Int, val judul: String, val nominal: String)

val daftarTransaksiDummy = listOf(
    Transaksi(1, "Nasi Padang Lauk Rendang", "- Rp 35.000"),
    Transaksi(2, "Kopi Senja Anak Indie", "- Rp 25.000"),
    Transaksi(3, "Gaji Buta", "+ Rp 5.000.000"),
    Transaksi(4, "Netflix & Chill", "- Rp 150.000"),
)

// Bab 15: baju zirah @Immutable untuk optimasi Recomposition
@Immutable
data class TransaksiUIState(
    val id: Int,
    val judul: String,
    val nominal: String
)

val daftarTransaksiUI = daftarTransaksiDummy.map {
    TransaksiUIState(id = it.id, judul = it.judul, nominal = it.nominal)
}

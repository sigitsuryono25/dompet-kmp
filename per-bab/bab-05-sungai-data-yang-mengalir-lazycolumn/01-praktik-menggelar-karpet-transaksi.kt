// ==========================================================
//  Bab 5 — SUNGAI DATA YANG MENGALIR (LazyColumn)
//  VOLUME 2 — MULAI BEROTOT "MENGHIDUPKAN SANG DOMPET"
//  Bagian: Praktik: Menggelar Karpet Transaksi
//  Kode ini disalin apa adanya dari buku "Maha Kitab Sakti".
// ==========================================================

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable

// Data Dummy
data class Transaksi(val id: Int, val judul: String, val nominal: String)

val daftarTransaksiDummy = listOf(
    Transaksi(1, "Nasi Padang Lauk Rendang", "- Rp 35.000"),
    Transaksi(2, "Kopi Senja Anak Indie", "- Rp 25.000"),
    Transaksi(3, "Gaji Buta", "+ Rp 5.000.000"),
    Transaksi(4, "Netflix & Chill", "- Rp 150.000"),
    // Bayangkan ada 100 data di sini...
)

@Composable
fun RiwayatTransaksiList(daftar: List<Transaksi>) {
    // LazyColumn adalah sang RecyclerView masa depan
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(top = 16.dp)
    ) {
        // Keajaiban terjadi di sini. Tak ada adapter!
        items(daftar) { item ->
            // Kita panggil TransaksiCard yang kita buat di Bab 3
            TransaksiCard(judul = item.judul, nominal = item.nominal)
            Spacer(modifier = Modifier.height(8.dp)) // Jarak antar kartu
        }
    }
}

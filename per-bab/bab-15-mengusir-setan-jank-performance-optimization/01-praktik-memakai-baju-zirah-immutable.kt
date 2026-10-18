// ==========================================================
//  Bab 15 — MENGUSIR SETAN *JANK* (PERFORMANCE OPTIMIZATION)
//  VOLUME 4 — PENDEKAR TURUN GUNUNG "MENYENTUH LOGAM BERDARAH"
//  Bagian: Praktik: Memakai Baju Zirah @Immutable
//  Kode ini disalin apa adanya dari buku "Maha Kitab Sakti".
// ==========================================================

import androidx.compose.runtime.Immutable

// TAMBAHKAN @Immutable.
// Ini janji ke Compose bahwa kalau objek ini dibikin,
// nilainya nggak bakal berubah diem-diem.
@Immutable
data class TransaksiUIState(
    val id: Int,
    val judul: String,
    val nominal: String
)

// Gunakan 'derivedStateOf' agar Recomposition hanya terjadi
// jika hasil perhitungannya BERBEDA.
// Jangan taruh kalkulasi berat (seperti filter list)
// langsung di dalam fungsi Compose!
@Composable
fun LayarTransaksi(daftarTransaksi: List<TransaksiUIState>) {
    // Misal kita cuma mau nampilin yang nominalnya lebih dari 100 ribu
    val transaksiMahal by remember(daftarTransaksi) {
        derivedStateOf {
            // Logika filter ringan
            daftarTransaksi.filter { it.nominal.length > 5 }
        }
    }

    LazyColumn {
        items(transaksiMahal) { item ->
            TransaksiCard(item.judul, item.nominal)
        }
    }
}

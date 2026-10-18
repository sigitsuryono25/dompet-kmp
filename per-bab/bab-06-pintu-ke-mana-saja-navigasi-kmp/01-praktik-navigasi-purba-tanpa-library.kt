// ==========================================================
//  Bab 6 — PINTU KE MANA SAJA (NAVIGASI KMP)
//  VOLUME 2 — MULAI BEROTOT "MENGHIDUPKAN SANG DOMPET"
//  Bagian: Praktik: Navigasi Purba (Tanpa Library)
//  Kode ini disalin apa adanya dari buku "Maha Kitab Sakti".
// ==========================================================

import androidx.compose.runtime.*

// Enum sebagai kompas arah kita
enum class Layar { HOME, TAMBAH_TRANSAKSI }

@Composable
fun AppNavigator() {
    // Layar mana yang sedang aktif saat ini?
    var layarAktif by remember { mutableStateOf(Layar.HOME) }

    // Buka pintu sesuai state!
    when (layarAktif) {
        Layar.HOME -> LayarUtama(
            // Lempar fungsi sebagai jembatan untuk pindah layar
            keLayarTambah = { layarAktif = Layar.TAMBAH_TRANSAKSI }
        )
        Layar.TAMBAH_TRANSAKSI -> LayarTambahData(
            kembaliKeHome = { layarAktif = Layar.HOME }
        )
    }
}

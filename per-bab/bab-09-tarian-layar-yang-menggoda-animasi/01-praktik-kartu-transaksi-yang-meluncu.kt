// ==========================================================
//  Bab 9 — TARIAN LAYAR YANG MENGGODA (ANIMASI)
//  VOLUME 3 — SENI & SIHIR "MENEMBUS AWAN, MENGUKIR BESI"
//  Bagian: Praktik: Kartu Transaksi yang Meluncur Anggun
//  Kode ini disalin apa adanya dari buku "Maha Kitab Sakti".
// ==========================================================

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import kotlinx.coroutines.delay

@Composable
fun AnimatedTransaksiCard(judul: String, nominal: String) {
    // State untuk memicu animasi
    var isVisible by remember { mutableStateOf(false) }

    // Efek samping: Setelah komponen dipanggil, ubah state jadi true
    LaunchedEffect(Unit) {
        delay(100) // Beri sedikit jeda biar efeknya terasa
        isVisible = true
    }

    // Sihir animasi Compose!
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            initialOffsetY = { 100 } // Muncul dari bawah (100 pixel)
        ) + fadeIn(animationSpec = tween(500)) // Durasi 500ms
    ) {
        // Panggil kartu lama kita dari Bab 3
        TransaksiCard(judul, nominal)
    }
}

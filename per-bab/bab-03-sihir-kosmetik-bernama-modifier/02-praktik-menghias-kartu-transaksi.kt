// ==========================================================
//  Bab 3 — SIHIR KOSMETIK BERNAMA MODIFIER
//  VOLUME 1 — BABAT ALAS "DOMPET KEMBANG KEMPIS"
//  Bagian: Praktik: Menghias Kartu Transaksi
//  Kode ini disalin apa adanya dari buku "Maha Kitab Sakti".
// ==========================================================

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color

@Composable
fun TransaksiCard(judul: String, nominal: String) {
    // Box menumpuk warna background dan konten
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            // Bayangan jatuh lebih dulu
            .shadow(4.dp, shape = RoundedCornerShape(12.dp))
            .background(Color.White) // Kanvas diputihkan
            .padding(16.dp) // Ruang nafas diberikan
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(judul, fontSize = 16.sp, fontWeight = FontWeight.Medium)
            // Darah merah pengeluaran
            Text(
                nominal,
                color = Color(0xFFE53935),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

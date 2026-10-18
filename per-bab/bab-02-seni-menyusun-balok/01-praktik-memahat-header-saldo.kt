// ==========================================================
//  Bab 2 — SENI MENYUSUN BALOK
//  VOLUME 1 — BABAT ALAS "DOMPET KEMBANG KEMPIS"
//  Bagian: Praktik: Memahat Header Saldo
//  Kode ini disalin apa adanya dari buku "Maha Kitab Sakti".
// ==========================================================

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DompetApp() {
    MaterialTheme {
        // Layar utama dibungkus Column (atas ke bawah)
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderSaldo()
        }
    }
}

@Composable
fun HeaderSaldo() {
    // Row: Meletakkan Teks Saldo dan Tombol berhadapan kiri-kanan
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text("Sisa Saldo Bulan Ini", color = MaterialTheme.colors.secondary)
            Text(
                text = "Rp 1.500.000",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Button(onClick = { println("Tombol Tambah Ditekan!") }) {
            Text("+ Tambah")
        }
    }
}

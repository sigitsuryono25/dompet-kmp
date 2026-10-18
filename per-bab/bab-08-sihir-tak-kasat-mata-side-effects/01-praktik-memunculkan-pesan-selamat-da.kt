// ==========================================================
//  Bab 8 — SIHIR TAK KASAT MATA (SIDE EFFECTS)
//  VOLUME 2 — MULAI BEROTOT "MENGHIDUPKAN SANG DOMPET"
//  Bagian: Praktik: Memunculkan Pesan Selamat Datang
//  Kode ini disalin apa adanya dari buku "Maha Kitab Sakti".
// ==========================================================

import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay

@Composable
fun LayarUtama(keLayarTambah: () -> Unit) {

    // LaunchedEffect dengan key Unit (true) artinya hanya dijalankan SEKALI
    // saat komponen ini pertama kali digambar di layar.
    LaunchedEffect(Unit) {
        println("Memanggil API untuk mengambil data saldo...")
        delay(1000) // Pura-puranya loading jaringan 1 detik
        println("Data saldo berhasil diambil!")
    }

    Column(modifier = Modifier.padding(16.dp)) {
        HeaderSaldo()
        Button(onClick = keLayarTambah) { Text("Tambah Pengeluaran") }
        RiwayatTransaksiList(daftarTransaksiDummy)
    }
}

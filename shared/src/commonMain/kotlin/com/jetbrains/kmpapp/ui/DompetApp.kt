package com.jetbrains.kmpapp.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetbrains.kmpapp.model.Transaksi
import com.jetbrains.kmpapp.model.TransaksiUIState
import com.jetbrains.kmpapp.model.daftarTransaksiDummy
import kotlinx.coroutines.delay

// ======================================================================
// Bab 2: Denyut Nadi — pintu masuk aplikasi + Header Saldo
// ======================================================================

@Composable
fun DompetApp() {
    MaterialTheme {
        AppNavigator()
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

// ======================================================================
// Bab 3: Sihir Kosmetik — Kartu Transaksi
// ======================================================================

@Composable
fun TransaksiCard(judul: String, nominal: String) {
    // Box menumpuk warna background dan konten
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .shadow(4.dp, shape = RoundedCornerShape(12.dp)) // Bayangan jatuh lebih dulu
            .background(Color.White) // Kanvas diputihkan
            .padding(16.dp) // Ruang nafas diberikan
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(judul, fontSize = 16.sp, fontWeight = FontWeight.Medium)
            Text(nominal, color = Color(0xFFE53935), fontWeight = FontWeight.Bold) // Darah merah pengeluaran
        }
    }
}

// ======================================================================
// Bab 5: Sungai Data — Riwayat dengan LazyColumn
// ======================================================================

@Composable
fun RiwayatTransaksiList(daftar: List<Transaksi>) {
    // LazyColumn adalah sang RecyclerView masa depan
    LazyColumn(modifier = Modifier.fillMaxSize().padding(top = 16.dp)) {
        // Keajaiban terjadi di sini. Tak ada adapter!
        items(daftar, key = { it.id }) { item ->
            // Kita panggil TransaksiCard yang kita buat di Bab 3
            TransaksiCard(judul = item.judul, nominal = item.nominal)
            Spacer(modifier = Modifier.height(8.dp)) // Jarak antar kartu
        }
    }
}

// ======================================================================
// Bab 6: Navigasi Purba (tanpa library) + Bab 8: Layar Utama
// ======================================================================

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

@Composable
fun LayarUtama(keLayarTambah: () -> Unit) {

    // LaunchedEffect dengan key Unit (true) artinya hanya dijalankan SEKALI
    // saat komponen ini pertama kali digambar di layar.
    LaunchedEffect(Unit) {
        println("Memanggil API untuk mengambil data saldo...")
        delay(1000) // Pura-puranya loading jaringan 1 detik
        println("Data saldo berhasil diambil!")
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        HeaderSaldo()
        Button(onClick = keLayarTambah, modifier = Modifier.padding(top = 16.dp)) {
            Text("Tambah Pengeluaran")
        }
        RiwayatTransaksiList(daftarTransaksiDummy)
    }
}

// ======================================================================
// Bab 4 + Bab 7: State & State Hoisting — Form Input
// ======================================================================

@Composable
fun LayarTambahData(kembaliKeHome: () -> Unit) {
    // State ditarik ke atas (Hoisting) ke level layar
    var inputUser by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Kita lemparkan state ke bawah
        InputPengeluaran(
            nominal = inputUser,
            onNominalBerubah = { nilaiBaru -> inputUser = nilaiBaru } // Event mengalir ke atas
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            println("Menyimpan data: $inputUser")
            kembaliKeHome()
        }) {
            Text("Simpan & Kembali")
        }
    }
}

// Komponen Input (stateless). Teks di atas adalah cermin sejati dari state.
@Composable
fun InputPengeluaran(nominal: String, onNominalBerubah: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 32.dp)) {
        Text(
            text = "Bakal keluar duit: Rp $nominal",
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = nominal,
            onValueChange = onNominalBerubah, // Oper tugas ke atasan
            label = { Text("Masukkan Nominal") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

// ======================================================================
// Bab 14: Melukis di Atas Kanvas — Pie Chart tanpa library
// ======================================================================

@Composable
fun PieChartPengeluaran() {
    // Canvas adalah kanvas Dewata. Kita gambar manual pakai koordinat.
    Canvas(modifier = Modifier.size(200.dp)) {
        // Sudut mulai gambar
        var sudutMulai = 0f

        // Data pengeluaran fiktif (misal: 60% Makanan, 40% Transport)
        val porsiMakanan = 216f // 60% dari 360 derajat
        val porsiTransport = 144f // 40% dari 360 derajat

        // 1. Gambar porsi Makanan (Merah)
        drawArc(
            color = Color.Red,
            startAngle = sudutMulai,
            sweepAngle = porsiMakanan,
            useCenter = false, // Biar jadi Donut Chart, bukan Pie padat
            style = Stroke(width = 40f),
            size = Size(size.width, size.height)
        )

        sudutMulai += porsiMakanan

        // 2. Gambar porsi Transport (Biru)
        drawArc(
            color = Color.Blue,
            startAngle = sudutMulai,
            sweepAngle = porsiTransport,
            useCenter = false,
            style = Stroke(width = 40f),
            size = Size(size.width, size.height)
        )
    }
}

// ======================================================================
// Bab 9: Tarian Layar yang Menggoda — Kartu Transaksi yang Meluncur
// ======================================================================

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

// ======================================================================
// Bab 15: Mengusir Setan Jank — derivedStateOf + @Immutable
// ======================================================================

@Composable
fun LayarTransaksi(daftarTransaksi: List<TransaksiUIState>) {
    // Misal kita cuma mau nampilin yang nominalnya lebih dari 100 ribu
    val transaksiMahal by remember(daftarTransaksi) {
        derivedStateOf {
            daftarTransaksi.filter { it.nominal.length > 5 } // Logika filter ringan
        }
    }

    LazyColumn(modifier = Modifier.fillMaxSize().padding(top = 16.dp)) {
        items(transaksiMahal, key = { it.id }) { item ->
            TransaksiCard(item.judul, item.nominal)
        }
    }
}

@file:OptIn(ExperimentalComposeUiApi::class)

package com.jetbrains.kmpapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.ImageComposeScene
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.jetbrains.kmpapp.model.daftarTransaksiUI
import com.jetbrains.kmpapp.ui.AnimatedTransaksiCard
import com.jetbrains.kmpapp.ui.HeaderSaldo
import com.jetbrains.kmpapp.ui.InputPengeluaran
import com.jetbrains.kmpapp.ui.LayarTransaksi
import com.jetbrains.kmpapp.ui.LayarUtama
import com.jetbrains.kmpapp.ui.PieChartPengeluaran
import com.jetbrains.kmpapp.ui.TransaksiCard
import org.jetbrains.skia.EncodedImageFormat
import java.io.File

// Warna status bar (tema terang bawaan MaterialTheme).
private val BG_TERANG = Color.White
private val FG_TERANG = Color(0xFF1A1A1A)

/**
 * Merender tiap layar/komponen DompetApp (dibungkus bingkai ponsel) menjadi PNG
 * memakai ImageComposeScene (Skia off-screen) — tanpa emulator atau jendela.
 *
 * Hanya yang benar-benar dibangun di dalam buku yang dirender:
 * Layar Utama, Layar Tambah Data, Pie Chart, Header Saldo, Kartu Transaksi,
 * Kartu Beranimasi, dan Daftar Terfilter.
 *
 * Jalankan: ./gradlew :desktopApp:screenshots
 */
fun main(args: Array<String>) {
    System.setProperty("skiko.renderApi", "SOFTWARE")
    val dir = File(args.firstOrNull() ?: "screenshots").apply { mkdirs() }

    val density = Density(2f)
    val w = ((SCREEN_W_DP + 2 * BEZEL_DP + 2 * MARGIN_DP) * 2f).toInt()
    val h = ((STATUS_DP + SCREEN_H_DP + 2 * BEZEL_DP + 2 * MARGIN_DP) * 2f).toInt()

    // 01 — Layar Utama (Bab 2/5/6/8): HeaderSaldo + tombol + daftar transaksi
    render(dir, "01-beranda.png", w, h, density, BG_TERANG, FG_TERANG) {
        MaterialTheme { LayarUtama(keLayarTambah = {}) }
    }

    // 02 — Layar Tambah Data (Bab 4/7): cermin state "Bakal keluar duit: Rp …"
    render(dir, "02-tambah.png", w, h, density, BG_TERANG, FG_TERANG) {
        MaterialTheme {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                InputPengeluaran(nominal = "25000", onNominalBerubah = {})
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {}) { Text("Simpan & Kembali") }
            }
        }
    }

    // 03 — Pie Chart Pengeluaran (Bab 14)
    render(dir, "03-statistik.png", w, h, density, BG_TERANG, FG_TERANG) {
        MaterialTheme {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                PieChartPengeluaran()
            }
        }
    }

    // 04 — Header Saldo (Bab 2): komponen lepas
    render(dir, "04-header-saldo.png", w, h, density, BG_TERANG, FG_TERANG) {
        MaterialTheme {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) { HeaderSaldo() }
        }
    }

    // 05 — Kartu Transaksi (Bab 3): komponen lepas
    render(dir, "05-kartu-transaksi.png", w, h, density, BG_TERANG, FG_TERANG) {
        MaterialTheme {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                TransaksiCard(judul = "Nasi Padang Lauk Rendang", nominal = "- Rp 35.000")
            }
        }
    }

    // 06 — Kartu Beranimasi (Bab 9): tangkap di tengah animasi (kartu sedang meluncur masuk)
    render(dir, "06-animasi.png", w, h, density, BG_TERANG, FG_TERANG, frames = 22) {
        MaterialTheme {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                AnimatedTransaksiCard(judul = "Nasi Padang Lauk Rendang", nominal = "- Rp 35.000")
            }
        }
    }

    // 07 — Daftar Terfilter (Bab 15): TransaksiUIState + derivedStateOf
    render(dir, "07-transaksi-filter.png", w, h, density, BG_TERANG, FG_TERANG) {
        MaterialTheme {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                LayarTransaksi(daftarTransaksiUI)
            }
        }
    }

    println("Selesai. Gambar disimpan di: ${dir.absolutePath}")
}

private fun render(
    dir: File,
    name: String,
    w: Int,
    h: Int,
    density: Density,
    bg: Color,
    fg: Color,
    frames: Int = 1,
    content: @Composable () -> Unit,
) {
    val scene = ImageComposeScene(width = w, height = h, density = density) {
        DeviceFrame(bg, fg) { content() }
    }
    try {
        var image = scene.render(0L)
        var nanos = 0L
        repeat(frames - 1) {
            // Majukan jam frame; beri jeda riil supaya efek delay()/animasi sempat jalan.
            nanos += 16_000_000L
            Thread.sleep(16)
            image = scene.render(nanos)
        }
        val bytes = image.encodeToData(EncodedImageFormat.PNG)!!.bytes
        File(dir, name).writeBytes(bytes)
        println("  • $name")
    } finally {
        scene.close()
    }
}

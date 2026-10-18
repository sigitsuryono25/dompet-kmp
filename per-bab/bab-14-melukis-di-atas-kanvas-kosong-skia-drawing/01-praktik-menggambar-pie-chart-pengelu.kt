// ==========================================================
//  Bab 14 — MELUKIS DI ATAS KANVAS KOSONG (SKIA DRAWING)
//  VOLUME 4 — PENDEKAR TURUN GUNUNG "MENYENTUH LOGAM BERDARAH"
//  Bagian: Praktik: Menggambar Pie Chart Pengeluaran Tanpa Library
//  Kode ini disalin apa adanya dari buku "Maha Kitab Sakti".
// ==========================================================

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

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

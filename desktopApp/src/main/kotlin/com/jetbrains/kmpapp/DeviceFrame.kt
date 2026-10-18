package com.jetbrains.kmpapp

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Ukuran (dalam dp) — dipakai juga oleh screenshot.kt untuk menghitung kanvas.
const val SCREEN_W_DP = 390f
const val SCREEN_H_DP = 844f
const val STATUS_DP = 46f
const val MARGIN_DP = 10f      // ruang transparan di luar bodi ponsel
const val BEZEL_DP = 14f       // tebal bingkai logam di sekitar layar

private val WARNA_BODI = Color(0xFF121416)

/** Membungkus layar aplikasi dengan bodi ponsel membulat + status bar. */
@Composable
fun DeviceFrame(latarStatus: Color, warnaStatus: Color, content: @Composable () -> Unit) {
    // Latar luar dibiarkan transparan; yang tampak hanya bentuk bodi ponsel.
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        // Bodi ponsel (membulat)
        Box(
            modifier = Modifier
                .padding(MARGIN_DP.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(60.dp))
                .background(WARNA_BODI),
        ) {
            // Layar (membulat sedikit lebih kecil dari bodi)
            Column(
                modifier = Modifier
                    .padding(BEZEL_DP.dp)
                    .fillMaxSize()
                    .clip(RoundedCornerShape(48.dp))
                    .background(latarStatus),
            ) {
                StatusBar(latarStatus, warnaStatus)
                Box(modifier = Modifier.weight(1f)) { content() }
            }
        }
    }
}

@Composable
private fun StatusBar(latar: Color, warna: Color) {
    Box(modifier = Modifier.fillMaxWidth().height(STATUS_DP.dp).background(latar)) {
        Row(
            modifier = Modifier.fillMaxSize().padding(horizontal = 26.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text("09:41", color = warna, fontWeight = FontWeight.Bold, fontSize = 15.sp)
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                SinyalIkon(warna)
                WifiIkon(warna)
                BateraiIkon(warna)
            }
        }
        // "Dynamic island"
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .width(96.dp)
                .height(26.dp)
                .clip(RoundedCornerShape(50))
                .background(Color(0xFF0A0A0A)),
        )
    }
}

@Composable
private fun SinyalIkon(warna: Color) {
    Canvas(modifier = Modifier.size(width = 18.dp, height = 12.dp)) {
        val n = 4
        val gap = size.width * 0.14f
        val bw = (size.width - gap * (n - 1)) / n
        for (i in 0 until n) {
            val bh = size.height * (0.42f + 0.19f * i)
            drawRoundRect(
                color = warna,
                topLeft = Offset(i * (bw + gap), size.height - bh),
                size = Size(bw, bh),
                cornerRadius = CornerRadius(bw * 0.35f),
            )
        }
    }
}

@Composable
private fun WifiIkon(warna: Color) {
    Canvas(modifier = Modifier.size(16.dp)) {
        val s = size.minDimension
        val cx = size.width / 2
        val cy = size.height * 0.80f
        val stroke = Stroke(width = s * 0.14f, cap = StrokeCap.Round)
        listOf(0.30f, 0.56f, 0.82f).forEach { r ->
            drawArc(
                color = warna,
                startAngle = 218f,
                sweepAngle = 104f,
                useCenter = false,
                topLeft = Offset(cx - s * r, cy - s * r),
                size = Size(2 * s * r, 2 * s * r),
                style = stroke,
            )
        }
        drawCircle(color = warna, radius = s * 0.075f, center = Offset(cx, cy))
    }
}

@Composable
private fun BateraiIkon(warna: Color) {
    Canvas(modifier = Modifier.size(width = 25.dp, height = 12.dp)) {
        val bodyW = size.width * 0.82f
        val bodyH = size.height * 0.62f
        val top = (size.height - bodyH) / 2
        drawRoundRect(
            color = warna,
            topLeft = Offset(0f, top),
            size = Size(bodyW, bodyH),
            cornerRadius = CornerRadius(bodyH * 0.32f),
            style = Stroke(width = bodyH * 0.16f),
        )
        drawRoundRect(
            color = warna,
            topLeft = Offset(bodyW + size.width * 0.03f, top + bodyH * 0.28f),
            size = Size(size.width * 0.09f, bodyH * 0.44f),
            cornerRadius = CornerRadius(2f),
        )
        drawRoundRect(
            color = warna,
            topLeft = Offset(bodyW * 0.14f, top + bodyH * 0.22f),
            size = Size(bodyW * 0.66f, bodyH * 0.56f),
            cornerRadius = CornerRadius(bodyH * 0.22f),
        )
    }
}

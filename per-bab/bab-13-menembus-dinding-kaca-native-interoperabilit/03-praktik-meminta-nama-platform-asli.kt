// ==========================================================
//  Bab 13 — MENEMBUS DINDING KACA (NATIVE INTEROPERABILITY)
//  VOLUME 4 — PENDEKAR TURUN GUNUNG "MENYENTUH LOGAM BERDARAH"
//  Bagian: Praktik: Meminta Nama Platform Asli
//  Kode ini disalin apa adanya dari buku "Maha Kitab Sakti".
// ==========================================================

import android.os.Build

// Kita tepati janji untuk Android!
actual fun getNamaPlatform(): String {
    return "Android ${Build.VERSION.RELEASE} - Si Robot Hijau"
}

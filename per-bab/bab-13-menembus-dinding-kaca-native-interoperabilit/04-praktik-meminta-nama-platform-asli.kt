// ==========================================================
//  Bab 13 — MENEMBUS DINDING KACA (NATIVE INTEROPERABILITY)
//  VOLUME 4 — PENDEKAR TURUN GUNUNG "MENYENTUH LOGAM BERDARAH"
//  Bagian: Praktik: Meminta Nama Platform Asli
//  Kode ini disalin apa adanya dari buku "Maha Kitab Sakti".
// ==========================================================

import platform.UIKit.UIDevice

// Kita tepati janji untuk iOS!
// (Perhatikan kita bisa panggil API iOS asli dari Kotlin!)
actual fun getNamaPlatform(): String {
    return UIDevice.currentDevice.systemName() + " " +
        UIDevice.currentDevice.systemVersion
}

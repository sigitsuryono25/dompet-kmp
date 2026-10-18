// ==========================================================
//  Bab 5 — SUNGAI DATA YANG MENGALIR (LazyColumn)
//  VOLUME 2 — MULAI BEROTOT "MENGHIDUPKAN SANG DOMPET"
//  Bagian: `items` dan Kuncinya (`key`)
//  Kode ini disalin apa adanya dari buku "Maha Kitab Sakti".
// ==========================================================

items(daftar, key = { it.id }) { item ->
    TransaksiCard(judul = item.judul, nominal = item.nominal)
}

// ==========================================================
//  Bab 4 — DENYUT NADI APLIKASI (STATE)
//  VOLUME 1 — BABAT ALAS "DOMPET KEMBANG KEMPIS"
//  Bagian: `by` Itu Apa?
//  Kode ini disalin apa adanya dari buku "Maha Kitab Sakti".
// ==========================================================

// Tanpa 'by'
val state = remember { mutableStateOf("") }
state.value = "baru"

// Dengan 'by' — jauh lebih manis
var teks by remember { mutableStateOf("") }
teks = "baru"

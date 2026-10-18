// ==========================================================
//  Bab 7 — OTAK DI BALIK OTOT (STATE HOISTING)
//  VOLUME 2 — MULAI BEROTOT "MENGHIDUPKAN SANG DOMPET"
//  Bagian: Praktik: Menarik Keluar State Input
//  Kode ini disalin apa adanya dari buku "Maha Kitab Sakti".
// ==========================================================

@Composable
fun LayarTambahData(kembaliKeHome: () -> Unit) {
    // State ditarik ke atas (Hoisting) ke level layar
    var inputUser by remember { mutableStateOf("") }

    Column {
        // Kita lemparkan state ke bawah
        InputPengeluaran(
            nominal = inputUser,
            // Event mengalir ke atas
            onNominalBerubah = { nilaiBaru -> inputUser = nilaiBaru }
        )

        Button(onClick = {
            println("Menyimpan data: $inputUser")
            kembaliKeHome()
        }) {
            Text("Simpan & Kembali")
        }
    }
}

// Komponen Input sekarang jadi bodoh (Stateless). Ia hanya menerima perintah.
@Composable
fun InputPengeluaran(nominal: String, onNominalBerubah: (String) -> Unit) {
    OutlinedTextField(
        value = nominal,
        onValueChange = onNominalBerubah, // Oper tugas ke atasan
        label = { Text("Masukkan Nominal") }
    )
}

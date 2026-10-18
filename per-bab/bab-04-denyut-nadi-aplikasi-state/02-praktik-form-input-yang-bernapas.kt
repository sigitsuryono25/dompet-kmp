// ==========================================================
//  Bab 4 — DENYUT NADI APLIKASI (STATE)
//  VOLUME 1 — BABAT ALAS "DOMPET KEMBANG KEMPIS"
//  Bagian: Praktik: Form Input yang Bernapas
//  Kode ini disalin apa adanya dari buku "Maha Kitab Sakti".
// ==========================================================

import androidx.compose.runtime.*

@Composable
fun InputPengeluaran() {
    // Inilah 'ingatan' si Compose. Perubahan di sini
    // adalah pemicu Recomposition!
    var nominalInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 32.dp)
    ) {
        // Teks ini adalah cermin sejati dari state nominalInput
        Text(
            text = "Bakal keluar duit: Rp $nominalInput",
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = nominalInput,
            onValueChange = { newValue ->
                // Jari pengguna menari, state diperbarui!
                nominalInput = newValue
            },
            label = { Text("Masukkan Nominal") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

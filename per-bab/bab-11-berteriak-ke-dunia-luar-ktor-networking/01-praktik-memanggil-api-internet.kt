// ==========================================================
//  Bab 11 — BERTERIAK KE DUNIA LUAR (KTOR NETWORKING)
//  VOLUME 3 — SENI & SIHIR "MENEMBUS AWAN, MENGUKIR BESI"
//  Bagian: Praktik: Memanggil API Internet
//  Kode ini disalin apa adanya dari buku "Maha Kitab Sakti".
// ==========================================================

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

// Inisialisasi Ktor Client (Letakkan di Repository/ViewModel)
val httpClient = HttpClient {
    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }
}

// Fungsi ajaib menembus awan
suspend fun fetchKursUSD(): String {
    return try {
        // Anggap ini endpoint API rahasia kita
        val response: String = httpClient
            .get("https://api.exchangerate.com/v1/latest")
            .body()
        "Sukses memanggil dunia luar: $response"
    } catch (e: Exception) {
        "Gagal berteriak: ${e.message}"
    }
}

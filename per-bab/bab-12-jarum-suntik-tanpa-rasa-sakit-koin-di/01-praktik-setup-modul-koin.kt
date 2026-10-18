// ==========================================================
//  Bab 12 — JARUM SUNTIK TANPA RASA SAKIT (KOIN DI)
//  VOLUME 3 — SENI & SIHIR "MENEMBUS AWAN, MENGUKIR BESI"
//  Bagian: Praktik: Setup Modul Koin
//  Kode ini disalin apa adanya dari buku "Maha Kitab Sakti".
// ==========================================================

import org.koin.core.context.startKoin
import org.koin.dsl.module

// 1. Bungkus semua komponenmu dalam sebuah Modul
val appModule = module {
    // Single: Objek ini cuma dibuat SATU KALI dan dipakai beramai-ramai
    single { HttpClient() }

    // Factory: Objek baru dibuat tiap kali diminta
    // 'get()' otomatis nyari dependensi yang pas!
    factory { TransaksiRepository(get()) }

    // ViewModel punya penanganan khusus (di Koin versi terbaru)
    // viewModel { DompetViewModel(get()) }
}

// 2. Jalankan ritual Koin (biasanya dipanggil di titik masuk aplikasi:
//    Application class Android / Main.kt iOS)
fun initKoin() {
    startKoin {
        modules(appModule)
    }
}

// ==========================================================
//  Bab 10 — MENGUKIR INGATAN PADA BESI (DATABASE KMP)
//  VOLUME 3 — SENI & SIHIR "MENEMBUS AWAN, MENGUKIR BESI"
//  Bagian: Praktik: Cetak Biru Database (Entity & DAO)
//  Kode ini disalin apa adanya dari buku "Maha Kitab Sakti".
// ==========================================================

import androidx.room.*
import kotlinx.coroutines.flow.Flow

// 1. Entity (Bentuk tabel di database)
@Entity(tableName = "tabel_transaksi")
data class TransaksiEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val judul: String,
    val nominal: Double,
    val jenis: String // "PEMASUKAN" atau "PENGELUARAN"
)

// 2. DAO (Data Access Object) - Mantra untuk ngobrol dengan database
@Dao
interface TransaksiDao {
    @Insert
    suspend fun tambahTransaksi(transaksi: TransaksiEntity)

    // Perhatikan Flow! Ini adalah sungai data reaktif.
    // Kalau ada data baru, UI otomatis ke-update tanpa perlu dipanggil ulang!
    @Query("SELECT * FROM tabel_transaksi ORDER BY id DESC")
    fun ambilSemuaTransaksi(): Flow<List<TransaksiEntity>>
}

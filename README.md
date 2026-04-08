Penjelasan Class-class LK05

* Class Interfaces
    program menggunakan 3 interfaces utama yang berfungsi sebagai "kontrak" yang harus dipatuhi bagi setiap data medis.
    - MedicalRecord : Menjamin setiap data memiliki ID pasien (patientId).
    - Versioned : Menjamin setiap data memiliki label versi (getVersion), yang menjawab masalah ketidakseragaman versi data di rumah sakit.
    - Confidential<T> : Menjamin adanya level keamanan (securityLevel), kemampuan untuk menyembunyikan data sensitif (maskSensitiveData), dan kemampuan untuk       menduplikasi diri sendiri (copy).
      
* Class PatientProfile
  class induk yang memiliki atribut seperti ID pasien, nama pasien dan SSN. serta melakukan override terhadap method. Kelas ini mengimplementasikan dasar MedicalRecord dan Versioned.

* Class PatientProfileV1,PatientProfileV2,PatientProfileV3
  class-class ini merupakan subclass dari class PatientProfile dengan rincian sebagai berikut.
  - V1 hanya data dasar berupa nama, ID, dan SSN (sama seperti class sebelumnya)
  - V2 ada tambahan berupa data riwayat alergi dan nomor darurat
  - V3 menambah data berupa preferensi makanan dan kekayaan bersih
  - Masing-masing kelas mendefinisikan sendiri apa yang dianggap "sensitif" di dalam method maskSensitiveData(). Misalnya, V2 akan menyembunyikan riwayat alergi jika akses tidak cukup, sedangkan V1 hanya menyembunyikan SSN.
 
* Class IntegrationGateway<T>
class ini merupakan class yang digunakan sebagai satu jalur API sesuai yg diminta pada LK05
  - Generic Bounded Type : Menggunakan <T extends MedicalRecord & Versioned & Confidential<T>>. Gateway ini tidak mau menerima sembarang objek, objek tersebut harus punya ID, punya versi, dan punya fitur keamanan.
  - method fetchData berfungsi sebagai -
    - memvalidasi ID pasien apakah sudah sesuai
    - Membandingkan level akses pemanggil dengan level keamanan data. Jika level pemanggil lebih rendah, Gateway memerintahkan data tersebut untuk menyembunyikan bagian sensitifnya tanpa perlu tahu apa isi datanya
    - Menggunakan mockingData.copy(). Ini dilakukan agar data asli di database tidak ikut rusak saat proses masking.
   
* Class SecureResponse<T>
class ini berfungsi untuk membungkus data setelah data tersebut selesai diproses gateway
Tujuan utamanya adalah memberikan informasi tambahan seperti status keberhasilan (success) dan pesan peringatan (warningMessage) jika ada data yang disembunyikan.Pembungkus ini juga menggunakan Generic agar bisa membawa tipe data apapun (V1, V2, atau V3) secara fleksibel.

* class MainSimulation
class main yang berfungsi untuk menjalankan semua program yang telah dibuat dengan spesifikasi sebagai berikut:

  - objek pertama yang dibungkus dengan ProfileV1
    - ID Tidak Ditemukan: Mencoba memanggil "P011" padahal ID Andi adalah "P001". Gateway akan mengembalikan pesan error "Patient not found".
    - Akses Rendah (Level 1): Karena akses (1) < keamanan Andi (2), maka data SSN akan di-masking menjadi [REDACTED].
    - Akses Sempurna (Level 3): Karena akses (3) > keamanan Andi (2), data ditampilkan secara utuh.
      
  - objek kedua yang dibungkus dengan ProfileV2
    - ID Milik Orang Lain: Mencoba mencari ID Andi ("P001") pada gateway milik Susan ("P002"). Ini mensimulasikan perlindungan data agar tidak terjadi tertukar data antar pasien.
    - Akses Menengah (Level 2): Karena akses (2) < keamanan Susan (3), field tambahan seperti Riwayat Alergi dan Nomor Darurat akan otomatis disembunyikan.
    - Akses Penuh (Level 4): Menampilkan semua data Susan termasuk field V2.
      
  - objek ketiga yang dibungkus dengan ProfileV3
    - Ekstensibilitas: Menunjukkan bahwa sistem mampu menangani versi data baru (V3) dengan field seperti makanan dan netWorth tanpa mengubah kode Gateway.
    - Keamanan Tinggi: Menguji level akses tinggi (level 6 dan 7) pada data yang sangat rahasia (security level 7).
   
lalu method static void showResult(SecureResponse<?> res, int level) ini digunakan untuk merapikan output yang akan ditampilkan.

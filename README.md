# Backend DASS-21

## Gambaran Umum
Proyek open source ini diambil dari repositori milik [carlosabdoamaral/dass21](https://github.com/carlosabdoamaral/dass21) yang kami terjemahkan ke dalam bahasa inggris untuk memudahkan pengembangan. Aplikasi ini adalah backend REST API yang dirancang untuk mengelola kuesioner psikologis [DASS-21](https://www.psy.unsw.edu.au/dass/). DASS-21, yang dibuat oleh Peter Lovibond, PhD dari Australia, mengukur berbagai tingkat depresi, kecemasan, dan stres melalui 21 pertanyaan.

Dibangun menggunakan **Java Spring Boot**, API ini menyediakan serangkaian endpoint untuk mengelola data peserta dan respons mereka terhadap kuesioner. Aplikasi ini juga terintegrasi dengan berbagai alat bantu seperti Docker, Grafana, SonarQube, dan Prometheus untuk analisis data, pemantauan, dan penjaminan kualitas kode.

## Fitur Utama
*   **Manajemen Peserta**: Menyediakan operasi CRUD (Create, Read, Update, Delete) lengkap untuk data peserta.
*   **Manajemen Respons**: Menyediakan operasi CRUD untuk setiap set jawaban kuesioner DASS-21.
*   **Alur Kuesioner Terstruktur**: Mendukung alur pengisian kuesioner multi-langkah melalui endpoint yang spesifik.
*   **Visualisasi Data**: Terintegrasi dengan Grafana untuk menampilkan dasbor analitik dari data respons yang terkumpul.
*   **Jaminan Kualitas Kode**: Terhubung dengan SonarQube untuk analisis kualitas kode statis secara berkelanjutan.
*   **Dokumentasi API Otomatis**: Dokumentasi API interaktif tersedia melalui Swagger untuk memudahkan pengujian dan integrasi.

## Struktur Proyek
Kode diatur secara modular mengikuti praktik terbaik Spring Boot untuk kemudahan pemeliharaan. Berikut adalah rincian struktur direktori utama:

| Direktori                                          | Deskripsi                                                                                     |
|----------------------------------------------------|-----------------------------------------------------------------------------------------------|
| `src/main/java/.../controller/`                    | Berisi semua *controller* yang mendefinisikan *endpoint* API.                                 |
| `src/main/java/.../service/`                       | Berisi logika bisnis inti aplikasi, seperti validasi dan pemrosesan data.                     |
| `src/main/java/.../model/`                         | Berisi entitas data JPA yang memetakan ke tabel-tabel di dalam database.                      |
| `src/main/java/.../repository/`                    | Berisi *interface* Spring Data JPA untuk abstraksi dan kemudahan akses ke database.           |
| `src/main/java/.../dto/`                           | Berisi *Data Transfer Objects* (DTO) yang digunakan untuk membentuk request dan response API. |
| `pom.xml`                                          | File konfigurasi Maven yang mengelola semua dependensi dan pengaturan build proyek.           |

---

## Endpoint API

Berikut adalah daftar endpoint yang tersedia, dikelompokkan berdasarkan fungsionalitasnya.

#### 1. Alur Utama Kuesioner (`/requirements`)
*   `POST /requirements/step4` - Menyimpan satu set jawaban lengkap dari kuesioner DASS-21.
*   `GET /requirements/step5` - Mengambil daftar semua respons yang pernah disimpan.
*   `PUT /requirements/step6` - Memperbarui data respons dan data peserta terkait secara bersamaan.
*   `GET /requirements/step7/{id}` - Mencari semua riwayat respons berdasarkan ID peserta.

#### 2. Manajemen Peserta (`/crud/participant`)
*   `GET /crud/participant/find` - Mengambil daftar semua peserta.
*   `GET /crud/participant/find/{id}` - Mengambil detail peserta berdasarkan ID.
*   `POST /crud/participant/save` - Menyimpan data peserta baru.
*   `PUT /crud/participant/update` - Memperbarui informasi peserta yang sudah ada.
*   `DELETE /crud/participant/delete/{id}` - Menghapus data peserta berdasarkan ID.

#### 3. Manajemen Respons (`/crud/response`)
*   `GET /crud/response/find` - Mengambil semua data respons.
*   `GET /crud/response/find/{id}` - Mengambil detail respons berdasarkan ID.
*   `POST /crud/response/save` - Menyimpan data respons baru.
*   `PUT /crud/response/update` - Memperbarui data respons yang sudah ada.
*   `DELETE /crud/response/delete/{id}` - Menghapus data respons berdasarkan ID.

---

## Langkah-langkah Menjalankan

Berikut adalah panduan untuk menyiapkan dan menjalankan server di lingkungan lokal.

**1. Prasyarat**
*   **Java 21**: Pastikan Java Development Kit (JDK) 21 terinstal di sistem Anda.
*   **Docker**: Pastikan Docker sudah terinstal dan berjalan untuk mengelola database dan layanan lainnya.

**2. Setup Proyek**
*   **IDE**: Gunakan Integrated Development Environment (IDE) seperti IntelliJ IDEA, Eclipse, atau Spring Tool Suite untuk pengembangan yang lebih mudah.
*   **Kloning Repositori**: Kloning repositori ini ke mesin lokal Anda.
    ```bash
    git clone https://github.com/shluf/dass21-be
    ```
**3. Jalankan Server**
*   **Via IDE**: Buka proyek di IDE Anda dan klik tombol "Run".
*   **Via Command Line**: Buka terminal di direktori root proyek dan jalankan perintah Maven berikut:
    ```bash
    mvn spring-boot:run
    ```
Server API sekarang akan berjalan dan dapat diakses di **http://localhost:8080**.

---

## Integrasi Tambahan

#### Dokumentasi Postman
Untuk memfasilitasi pengujian, telah disediakan koleksi Postman di `src/main/resources/postman/postman-collection.json`. Anda dapat mengimpor file ini ke Postman untuk mendapatkan semua endpoint yang telah dikonfigurasi.

Dokumentasi online juga tersedia di sini:
[https://documenter.getpostman.com/view/37974053/2sB2x3nDHa](https://documenter.getpostman.com/view/37974053/2sB2x3nDHa)

#### Dasbor Grafana
Proyek ini dilengkapi dengan dasbor Grafana untuk analisis dan visualisasi data.

**Cara Mengakses:**
1.  Buka [http://localhost:3000/](http://localhost:3000/) di browser Anda.

**Hubungkan Sumber Data:**
1.  Klik `Add datasource`.
2.  Pilih `postgresql`.
3.  **Host**: `host.docker.internal:5432`
4.  **Database**: `dass21_db`
5.  **User**: `postgres`
6.  **Password**: `postgres`
7.  Klik `Save & Test` di bagian bawah halaman.

**Impor Dasbor:**
1.  Buka halaman dasbor, klik `New`, lalu `Import`.
2.  Salin seluruh konten JSON dari file `dashboard.json` yang terletak di `src/main/resources/grafana` atau seret file tersebut ke bagian unggahan.
3.  Tempelkan di area teks `Import via dashboard JSON model`.
4.  Klik tombol `Load`.

#### Analisis SonarQube
SonarQube digunakan untuk memantau kualitas kode. Saat proyek dijalankan, SonarQube akan tersedia di [http://localhost:9000/](http://localhost:9000/). Untuk menghasilkan analisis, ikuti langkah-langkah yang disediakan di platform SonarQube.

#### Prometheus
Dalam proyek ini, Prometheus digunakan primarily untuk memungkinkan akses Grafana ke database.

#### Dokumentasi Swagger
Dokumentasi Swagger UI untuk API ini dapat diakses di:
[http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)


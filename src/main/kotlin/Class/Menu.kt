package Class

import java.util.*

class Menu {
    private val mb = ManajemenBuku()
    private val pdp = PDP()

    fun manajemenBuku() {
        val scanner = Scanner(System.`in`)

        println()
        println("=====MANAJEMEN BUKU=====")
        println(
            """
            1. Menambah Buku
            2. Menghapus Buku
            3. Tampilkan Semua Buku
        """.trimIndent()
        )

        print("Pilihan : ")
        val pilih = scanner.nextInt()

        when (pilih) {
            1 -> mb.menambahBuku()
            2 -> mb.menghapusBuku()
            3 -> mb.tampilkanBuku()
            else -> println("TOLONG PILIH NO SESUAI MENU")
        }
    }

    fun pencarianBuku() {
        val scanner = Scanner(System.`in`)

        println()
        println("=====PENCARIAN BUKU=====")
        print("Cari Buku : ")
        val pilih = scanner.nextLine()
        val pencarianBuku: ManajemenBuku = PencarianBuku(pilih)
        pencarianBuku.tampilkanBuku()
    }

    fun peminjamanBuku() {
        val scanner = Scanner(System.`in`)

        println()
        println("=====PEMINJAMAN BUKU=====")
        print("Buku yang akan dipinjam : ")
        val buku = scanner.nextLine()
        print("Tanggal Untuk Mengembalikan : ")
        val tanggal = scanner.nextLine()

        pdp.peminjamanBuku(buku, tanggal)
    }

    fun pengembalianBuku() {
        val scanner = Scanner(System.`in`)

        println()
        println("=====PEMINJAMAN BUKU=====")
        print("Buku yang akan dikembalikan : ")
        val buku = scanner.nextLine()

        pdp.pengembalianBuku(buku)
    }

}
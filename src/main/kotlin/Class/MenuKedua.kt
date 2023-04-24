package Class

import java.util.*

class MenuKedua {

    fun manajemenBuku() {
        val scanner = Scanner(System.`in`)

        println()
        println(
            """
            1. Menambah Buku
            2. Menghapus Buku
            3. Tampilkan Buku
        """.trimIndent()
        )

        print("Pilihan : ")
        val pilih = scanner.nextInt()

        if (pilih == 1) {

        }
        if (pilih == 2) {

        }
        if (pilih == 3) {

        }
        if (pilih > 3 || pilih < 1) {
            println("TOLONG PILIH SESUAI MENU")
        }
    }

    fun pencarianBuku() {
        val scanner = Scanner(System.`in`)

        println()
        print("Cari Buku : ")
        val pilih = scanner.nextInt()
    }

    fun peminjamanBuku() {
        val scanner = Scanner(System.`in`)

        println()
        print("Buku yang akan dipinjam : ")
        val buku = scanner.nextLine()
    }

    fun pengembalianBuku() {
        val scanner = Scanner(System.`in`)

        println()
        print("Buku yang akan dikembalikan : ")
        val buku = scanner.nextLine()
    }

}
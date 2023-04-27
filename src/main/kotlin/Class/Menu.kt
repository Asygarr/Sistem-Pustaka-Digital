package Class

import java.util.*

class Menu {
    val mb = ManajemenBuku()

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
            mb.menambahBuku()
        }
        if (pilih == 2) {
            mb.menghapusBuku()
        }
        if (pilih == 3) {
            mb.tampilkanBuku()
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
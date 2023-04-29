package app

import Class.Menu
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    var back: String
    val menu = Menu()

    do {
        println("=====SELAMAT DATANG DI PUSTAKA DIGITAL=====")
        println("MENU")

        println(
            """
                1. Manajemen Buku
                2. Pencarian Buku
                3. Peminjaman Buku 
                4. Pengembalian Buku
            """.trimIndent()
        )

        print("Pilihan : ")
        val pilih = scanner.nextInt()

        when (pilih) {
            1 -> menu.manajemenBuku()
            2 -> menu.pencarianBuku()
            3 -> menu.peminjamanBuku()
            4 -> menu.pengembalianBuku()
            else -> println("TOLONG PILIH NO SESUAI MENU")
        }

        print("Kemenu utama? y/n : ")
        back = scanner.next()
        println()
    } while (back.equals("y"))


}

package app

import Class.MenuKedua
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    var back: String
    val menuKedua = MenuKedua()

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

        if (pilih == 1) {
            menuKedua.manajemenBuku()
        }
        if (pilih == 2) {
            menuKedua.pencarianBuku()
        }
        if (pilih == 3) {
            menuKedua.peminjamanBuku()
        }
        if (pilih == 4) {
            menuKedua.pengembalianBuku()
        }
        if (pilih > 4 || pilih < 1) {
            println("TOLONG PILIH SESUAI MENU")
        }

        print("Kemenu utama? y/n : ")
        back = scanner.next()
        println()
    } while (back.equals("y"))


}

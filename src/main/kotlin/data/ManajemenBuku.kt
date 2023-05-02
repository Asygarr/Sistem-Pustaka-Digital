package data

import java.sql.SQLException
import java.util.*

open class ManajemenBuku {
    private val input = Scanner(System.`in`)
    private val connector = DB()
    private val conn = connector.connect()

    fun menambahBuku() {
        println()
        print("Masukkan judul buku : ")
        val judul = input.nextLine()
        print("Masukkan penulis buku : ")
        val penulis = input.nextLine()
        print("Masukkan penerbit buku : ")
        val penerbit = input.nextLine()
        print("Masukkan tahun terbit buku : ")
        val tahunTerbit = input.nextInt()
        println()

        if (conn != null) {
            try {
                val statement = conn.createStatement()
                val query = "INSERT INTO buku (judul, penulis, penerbit, tahun_terbit) VALUES ('$judul', '$penulis', '$penerbit', '$tahunTerbit')"
                statement.executeUpdate(query)
                statement.close()
            } catch (e: SQLException) {
                println("Gagal menambahkan data: ${e.message}")
            }
        } else {
            println("Gagal membuat koneksi ke database")
        }
    }

    fun menghapusBuku() {
        println()
        print("Masukkan judul buku yang akan di DELETE : ")
        val buku = input.nextLine()

        if (conn != null) {
            try {
                val statement = conn.createStatement()
                val query = "SELECT * FROM buku WHERE judul = '$buku'"
                val result = statement.executeQuery(query)

                while (result.next()) {
                    val idBuku = result.getInt("id")

                    try {
                        val statementDelete = conn.createStatement()
                        val queryDelete = "DELETE FROM buku WHERE id = $idBuku"
                        statementDelete.executeUpdate(queryDelete)

                        statementDelete.close()
                    } catch (e: SQLException) {
                        println("Gagal membuat koneksi ke database")
                    }
                }

                result.close()
                statement.close()
            } catch (e: SQLException) {
                println("Gagal menghapus data ke database")
            }

        } else {
            println("Gagal membuat koneksi ke database")
        }
    }

    open fun tampilkanBuku() {
        if (conn != null) {
            try {
                val statement = conn.createStatement()
                val query = "SELECT * FROM buku"
                val result = statement.executeQuery(query)


                println("+--------------------------------------+---------------------------------+---------------------------------+-------------+----------+")
                println("| Judul                                | Penulis                         | Penerbit                        |Tahun Terbit |Status    |")
                println("+--------------------------------------+---------------------------------+---------------------------------+-------------+----------+")
                while (result.next()) {
                    val judul = result.getString("judul")
                    val penulis = result.getString("penulis")
                    val penerbit = result.getString("penerbit")
                    val tahunTerbit = result.getInt("tahun_terbit")
                    val status = result.getString("status")

                    println("| %36s | %31s | %31s | %11d | %7s |".format(judul, penulis, penerbit, tahunTerbit, status))
                }
                println("+--------------------------------------+---------------------------------+---------------------------------+-------------+----------+")

                result.close()
                statement.close()
            } catch (e: SQLException) {
                println("Gagal menampilkan data")
            }
        } else {
            println("Gagal membuat koneksi ke database")
        }
    }
}
package data

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class DB {

    private var connection: Connection? = null

    // Fungsi untuk membuat koneksi ke database
    fun connect(): Connection? {
        if (connection != null && !connection!!.isClosed) {
            return connection
        }

        try {
            Class.forName("com.mysql.jdbc.Driver")
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sistem_pustaka_digital", "root", ""
            )
            println("Koneksi berhasil dibuat!")
        } catch (e: SQLException) {
            println("Gagal membuat koneksi: ${e.message}")
        } catch (e: ClassNotFoundException) {
            println("Gagal membuat koneksi: ${e.message}")
        }

        return connection
    }

    // Fungsi untuk menutup koneksi ke database
    fun disconnect() {
        if (connection != null && !connection!!.isClosed) {
            connection!!.close()
            println("Koneksi berhasil ditutup!")
        }
    }

}
package Class

import java.sql.SQLException

class PencarianBuku(private val buku: String): ManajemenBuku() {
    private val connector = DB()
    private val conn = connector.connect()

    override fun tampilkanBuku() {
        if (conn != null) {
            try {
                val statement = conn.createStatement()
                val query = "SELECT * FROM buku WHERE judul = '${this.buku}'"
                val result = statement.executeQuery(query)


                println("+--------------------------------------+---------------------------------+---------------------------------+-------------+----------+")
                println("| Judul                                | Penulis                         | Penerbit                        |Tahun Terbit |Status    |")
                println("+--------------------------------------+---------------------------------+---------------------------------+-------------+----------+")
                while (result.next()) {
                    val judul = result.getString("judul")
                    val penulis = result.getString("penulis")
                    val penerbit = result.getString("penerbit")
                    val tahun_terbit = result.getInt("tahun_terbit")
                    val status = result.getString("status")

                    println("| %36s | %31s | %31s | %11d | %7s |".format(judul, penulis, penerbit, tahun_terbit, status))
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
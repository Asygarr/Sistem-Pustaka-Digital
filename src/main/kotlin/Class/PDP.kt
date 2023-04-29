package Class

import java.sql.SQLException

class PDP() {
    private val connector = DB()
    private val conn = connector.connect()

    fun peminjamanBuku(judul: String, tanggal: String) {
        if (conn != null) {
            try {
                val query = "SELECT * FROM buku WHERE judul = ?"
                val statement = conn.prepareStatement(query)
                statement.setString(1, judul)
                val result = statement.executeQuery()

                while (result.next()) {
                    val id_buku = result.getInt("id")
                    val status = result.getString("status")

                    if (status == "Tersedia") {
                        try {
                            val queryPinjam = "INSERT INTO peminjaman (id_buku, tanggal_pengembalian) VALUES (?, ?)"
                            val psPinjam = conn.prepareStatement(queryPinjam)
                            psPinjam.setInt(1, id_buku)
                            psPinjam.setString(2, tanggal)
                            psPinjam.executeUpdate()

                            val querySetStatus = "UPDATE buku SET status = 'Dipinjam' WHERE id = ?"
                            val psStatus = conn.prepareStatement(querySetStatus)
                            psStatus.setInt(1, id_buku)
                            psStatus.executeUpdate()

                            psPinjam.close()
                            psStatus.close()
                        } catch (e: SQLException) {
                            println("Gagal menambahkan data: ${e.message}")
                        }
                    } else {
                        println("Buku sedang dalam peminjaman, Silahkan pilih buku lain")
                    }
                }

                result.close()
                statement.close()

            } catch (e: SQLException) {
                e.printStackTrace()
            }
        } else {
            println("Gagal membuat koneksi ke database")
        }
    }

    fun pengembalianBuku(buku: String) {
        if (conn != null) {
            try {
                val query = "SELECT * FROM buku WHERE judul = ?"
                val statement = conn.prepareStatement(query)
                statement.setString(1, buku)
                val result = statement.executeQuery()

                while (result.next()) {
                    val id_buku = result.getInt("id")
                    val status = result.getString("status")

                    if (status.equals("Dipinjam")) {
                        try {
                            val queryPinjam = "SELECT * FROM peminjaman WHERE id_buku = ?"
                            val psPinjam = conn.prepareStatement(queryPinjam)
                            psPinjam.setInt(1, id_buku)
                            val resultPinjam = psPinjam.executeQuery()

                            while (resultPinjam.next()) {
                                val id_pinjam = resultPinjam.getInt("id")

                                try {
                                    val queryPengembalian = "INSERT INTO pengembalian (id_peminjaman, tanggal_pengembalian) VALUES (?, CURRENT_DATE)"
                                    val psKembali = conn.prepareStatement(queryPengembalian)
                                    psKembali.setInt(1, id_pinjam)
                                    psKembali.executeUpdate()

                                    val querySetStatus = "UPDATE buku SET status = 'Tersedia' WHERE id = ?"
                                    val psStatus = conn.prepareStatement(querySetStatus)
                                    psStatus.setInt(1, id_buku)
                                    psStatus.executeUpdate()

                                    psKembali.close()
                                    psStatus.close()
                                } catch (e: SQLException) {
                                    println("Gagal menambahkan data: ${e.message}")
                                }
                            }

                            resultPinjam.close()

                        } catch (e: SQLException) {
                            e.printStackTrace()
                        }
                    } else {
                        println("Buku tidak dalam daftar peminjaman")
                    }
                }

                result.close()
                statement.close()

            } catch (e: SQLException) {
                e.printStackTrace()
            }
        } else {
            println("Gagal membuat koneksi ke database")
        }
    }

}
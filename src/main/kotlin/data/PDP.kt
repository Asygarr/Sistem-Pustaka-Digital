package data

import java.sql.SQLException

class PDP {
    private val connector = DB()
    private val conn = connector.connect()

    fun peminjamanBuku(judul: String, tanggal: String) {

        if (conn != null) {
            try {
                val query = "SELECT * FROM buku WHERE judul = ? LIMIT 1"
                val statement = conn.prepareStatement(query)
                statement.setString(1, judul)
                val result = statement.executeQuery()

                while (result.next()) {
                    val idBuku = result.getInt("id")
                    val status = result.getString("status")

                    if (status == "Tersedia") {
                        try {
                            val queryPinjam = "INSERT INTO peminjaman (id_buku, tanggal_pengembalian) VALUES (?, ?)"
                            val psPinjam = conn.prepareStatement(queryPinjam)
                            psPinjam.setInt(1, idBuku)
                            psPinjam.setString(2, tanggal)
                            psPinjam.executeUpdate()

                            val querySetStatus = "UPDATE buku SET status = 'Dipinjam' WHERE id = ?"
                            val psStatus = conn.prepareStatement(querySetStatus)
                            psStatus.setInt(1, idBuku)
                            psStatus.executeUpdate()

                            try {
                                val queryTampil = "SELECT * FROM peminjaman WHERE id_buku = ?"
                                val psTampil = conn.prepareStatement(queryTampil)
                                psTampil.setInt(1, idBuku)
                                val resultTampil = psTampil.executeQuery()

                                while (resultTampil.next()) {
                                    val tanggalPinjam = resultTampil.getString("tanggal_peminjaman")
                                    val tanggalKembali = resultTampil.getString("tanggal_pengembalian")

                                    println("""
                                        
                                        ===BUKU BERHASIL DIPINJAM===
                                        JUDUL : $judul
                                        TANGGAL PEMINJAMAN : $tanggalPinjam
                                        TANGGAL PENGEMBALIAN : $tanggalKembali
                                    """.trimIndent())
                                }

                                psTampil.close()
                                resultTampil.close()

                            } catch (e: SQLException) {
                                e.printStackTrace()
                            }

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
                val query = "SELECT * FROM buku WHERE judul = ? AND status = 'Dipinjam' LIMIT 1"
                val statement = conn.prepareStatement(query)
                statement.setString(1, buku)
                val result = statement.executeQuery()

                while (result.next()) {
                    val idBuku = result.getInt("id")

                    try {
                        val queryPinjam = "SELECT * FROM peminjaman WHERE id_buku = ?"
                        val psPinjam = conn.prepareStatement(queryPinjam)
                        psPinjam.setInt(1, idBuku)
                        val resultPinjam = psPinjam.executeQuery()

                        while (resultPinjam.next()) {
                            val idPinjam = resultPinjam.getInt("id")

                            try {
                                val queryPengembalian = "INSERT INTO pengembalian (id_peminjaman, tanggal_pengembalian) VALUES (?, CURRENT_DATE)"
                                val psKembali = conn.prepareStatement(queryPengembalian)
                                psKembali.setInt(1, idPinjam)
                                psKembali.executeUpdate()

                                val querySetStatus = "UPDATE buku SET status = 'Tersedia' WHERE id = ?"
                                val psStatus = conn.prepareStatement(querySetStatus)
                                psStatus.setInt(1, idBuku)
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
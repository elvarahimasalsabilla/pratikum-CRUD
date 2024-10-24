/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author elva
 */
package app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MahasiswaManager {
    Connection conn = null;
    Statement st = null;

    // Driver dan URL database
    String driver = "com.mysql.cj.jdbc.Driver"; 
    String url = "jdbc:mysql://localhost:3306/db_mahasiswa23131016";

    // Konstruktor
    public MahasiswaManager() {
        try {
            // Load driver dan buat koneksi
            Class.forName(driver);
            conn = DriverManager.getConnection(url, "root", "");
            st = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Metode untuk mendapatkan daftar mahasiswa dari database
    public List<Mahasiswa> getMahasiswa() {
        ResultSet rs = null;
        List<Mahasiswa> mahasiswaList = new ArrayList<>();
        try {
            // Query untuk mengambil data dari tabel tbl_mahasiswa
            rs = st.executeQuery("SELECT * FROM mahasiswa");
            while (rs.next()) {
                Mahasiswa m = new Mahasiswa();
                m.setnobp(rs.getString("NoBP"));
                m.setnama(rs.getString("Nama"));
                m.settmplahir(rs.getString("TmpLahir"));
                m.settgllahir(rs.getString("TglLahir"));
                m.setalamat(rs.getString("Alamat"));
                m.setphone(rs.getString("Phone"));
                m.setasalsekolah(rs.getString("AsalSekolah"));
                mahasiswaList.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mahasiswaList;
    }

    // Metode untuk menambahkan data mahasiswa ke database
    public int Insert(Mahasiswa m) {
        int result = 0;
        try {
            // Query untuk insert data ke tbl_mahasiswa
            String sql = "INSERT INTO mahasiswa (NoBP, Nama, TmpLahir, TglLahir, Alamat, Phone, AsalSekolah) " +
                         "VALUES ('" + m.getnobp() + "', '" + m.getnama() + "', '" + m.gettmplahir() + "', '" + m.gettgllahir() + "', '" + m.getalamat() + "', '" + m.getphone() + "', '" + m.getasalsekolah() + "')";
            result = st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // Metode untuk menghapus data mahasiswa dari database
    public int Delete(Mahasiswa m) {
        int result = 0;
        try {
            // Query untuk delete data dari tbl_mahasiswa berdasarkan NoBP
            String sql = "DELETE FROM mahasiswa WHERE NoBP='" + m.getnobp() + "'";
            result = st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // Metode untuk mengupdate data mahasiswa di database
    public int Update(Mahasiswa m) {
        int result = 0;
        try {
            // Query untuk update data di tbl_mahasiswa berdasarkan NoBP
            String sql = "UPDATE mahasiswa SET " +
                         "Nama='" + m.getnama() + "', " +
                         "TmpLahir='" + m.gettmplahir() + "', " +
                         "TglLahir='" + m.gettgllahir() + "', " +
                         "Alamat='" + m.getalamat() + "', " +
                         "Phone='" + m.getphone() + "', " +
                         "AsalSekolah='" + m.getasalsekolah() + "' " +
                         "WHERE NoBP='" + m.getnobp() + "'";
            result = st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // Metode untuk menutup koneksi ke database
    public void closeConnection() {
        try {
            if (st != null) st.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

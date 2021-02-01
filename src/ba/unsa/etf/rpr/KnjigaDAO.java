package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class KnjigaDAO {
    private static KnjigaDAO instance = null;
    private Connection conn;
        private PreparedStatement pretragaUpit, dodavanjeUpit, noviIdUpit, izmjenaUpit, brisanjeUpit, sveKnjigeUpit;

    public static KnjigaDAO getInstance() throws SQLException {
        if (instance == null) instance = new KnjigaDAO();
        return instance;
    }

    private KnjigaDAO() throws SQLException {
        String url = "jdbc:sqlite:" + System.getProperty("user.home") + "/.knjigeapp/knjige.db";
        conn = DriverManager.getConnection(url);
        try {
            pretragaUpit = conn.prepareStatement("SELECT * FROM knjiga WHERE naslov=? OR autor=?");
        } catch (SQLException e) {
            //znači da tabela knjiga ne postoji
            kreirajBazu(); //napunice bazu deafultnim podacima
            pretragaUpit = conn.prepareStatement("SELECT * FROM knjiga WHERE naslov=? OR autor=?");
            
        }
        dodavanjeUpit = conn.prepareStatement("INSERT INTO knjiga VALUES(?,?,?,?)");
        noviIdUpit = conn.prepareStatement("SELECT MAX(id)+1 FROM knjiga");
        izmjenaUpit = conn.prepareStatement("UPDATE knjiga SET naslov=?, autor=?, isbn=? WHERE id=?");
        brisanjeUpit = conn.prepareStatement("DELETE FROM knjiga WHERE id=?");
        sveKnjigeUpit = conn.prepareStatement("SELECT * FROM knjiga ORDER BY naslov");
        }

    private void kreirajBazu() {
            Scanner ulaz = null;
            try {
                ulaz = new Scanner(new FileInputStream("knjiga.sql"));
                String sqlUpit = "";
                while (ulaz.hasNext()) {
                    sqlUpit += ulaz.nextLine();
                    if ( sqlUpit.length() > 1 && sqlUpit.charAt( sqlUpit.length()-1 ) == ';') {
                        try {
                            Statement stmt = conn.createStatement();
                            stmt.execute(sqlUpit);
                            sqlUpit = "";
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
                ulaz.close();
    } catch (FileNotFoundException e) {
        System.out.println("Ne postoji SQL datoteka… nastavljam sa praznom bazom");
    }
}

    ArrayList<Knjiga> pretraga (String pretraga) {
        ArrayList<Knjiga> rezultat = new ArrayList<>();
        try {
            pretragaUpit.setString(1,pretraga);
            pretragaUpit.setString(2,pretraga);
            ResultSet rs = pretragaUpit.executeQuery();
            while (rs.next()) {
                rezultat.add(new Knjiga(rs.getInt("id"), rs.getString("naslov"), rs.getString("autor"), rs.getString("isbn")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rezultat;
    }

    public static void removeInstance() throws SQLException {
        if (instance == null) return;
        instance.conn.close();
        instance = null;
    }

    public Knjiga dodaj(Knjiga k) {
        try {
            ResultSet rs = noviIdUpit.executeQuery();
            if (rs.next())
                k.setId(rs.getInt(1));
            else
                k.setId(1);

            dodavanjeUpit.setInt(1,k.getId());
            dodavanjeUpit.setString(2, k.getNaslov());
            dodavanjeUpit.setString(3,k.getAutor());
            dodavanjeUpit.setString(4,k.getIsbn());
            dodavanjeUpit.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return k;
    }

    public void izmijeni(Knjiga k) {
        try {
            izmjenaUpit.setInt(4,k.getId());
            izmjenaUpit.setString(1, k.getNaslov());
            izmjenaUpit.setString(2,k.getAutor());
            izmjenaUpit.setString(3,k.getIsbn());
            izmjenaUpit.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void obrisi(Knjiga k) {
        try {
            brisanjeUpit.setInt(1,k.getId());
            brisanjeUpit.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ObservableList<Knjiga> sveKnjige() {
        ObservableList<Knjiga> rezultat = FXCollections.observableArrayList();
        try {
            ResultSet rs = sveKnjigeUpit.executeQuery();
            while (rs.next()) {
                rezultat.add(new Knjiga(rs.getInt("id"), rs.getString("naslov"), rs.getString("autor"), rs.getString("isbn")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rezultat;
    }
}

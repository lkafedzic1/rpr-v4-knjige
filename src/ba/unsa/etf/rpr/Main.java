package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/knjiga.fxml"));
        primaryStage.setTitle("Knjige");
        primaryStage.setScene(new Scene(root, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE));
        primaryStage.show();
    }


    public static void main(String[] args) {

        launch(args);
    }
}

/*
import java.sql.*;
import java.util.Scanner;

public class Main {
    private static KnjigaDAO dao;
    private static Scanner ulaz;


    public static void main(String[] args) throws SQLException {
        dao = KnjigaDAO.getInstance();
        ulaz =  new Scanner(System.in);
        int opcija = 0;
        do {
            System.out.println("Unesite opciju\n1 - pretraga\n2 - unos\n3 - izmjena\n4 - brisanje\n0 - kraj programa\n");
            opcija = ulaz.nextInt();
            if (ulaz.hasNextLine()) ulaz.nextLine(); //čita do entera
            switch (opcija) {
                case 1:
                    pretraga();
                    break;
                case 2:
                    unos();
                    break;
                case 3:
                    izmjena();
                    break;
                case 4:
                    brisanje();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Nepoznata opcija");
            }
        } while (opcija != 0 );
    }

    private static void brisanje() {
        int id;
        System.out.println("Unesite ID knjige koju brišete: ");
        id = ulaz.nextInt();
        Knjiga k = new Knjiga(id, "","","");
        dao.obrisi(k);
    }

    private static void izmjena() {
        int id;
        String naslov, autor, isbn;
        System.out.println("Unesite ID knjige koju mijenjate: ");
        id = ulaz.nextInt();
        if (ulaz.hasNextLine()) ulaz.nextLine(); //čita do entera
        System.out.println("Unesite naslov: ");
        naslov = ulaz.nextLine();
        System.out.println("Unesite autor: ");
        autor = ulaz.nextLine();
        System.out.println("Unesite isbn: ");
        isbn = ulaz.nextLine();
        Knjiga k = new Knjiga(id, naslov, autor, isbn);
        dao.izmijeni(k);
    }

    private static void unos() {
        String naslov, autor, isbn;
        System.out.println("Unesite naslov: ");
        naslov = ulaz.nextLine();
        System.out.println("Unesite autor: ");
        autor = ulaz.nextLine();
        System.out.println("Unesite isbn: ");
        isbn = ulaz.nextLine();
        Knjiga k = new Knjiga(0, naslov, autor, isbn);
        dao.dodaj(k);
    }

    private static void pretraga() {
        System.out.println("Unesite naslov knjige: ");
        Scanner ulaz = new Scanner(System.in);
        String upit = ulaz.nextLine();
        for (Knjiga k : dao.pretraga(upit)) {
            System.out.println("ID: " + k.getId() + ", " + k.getNaslov());
        }
    }
}
*/
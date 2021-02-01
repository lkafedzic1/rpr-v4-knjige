package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class KnjigaController {
    public ListView<Knjiga> listKnjige;    //veza modela i viewa (fxml)
    public TextField fldNaslov;
    public TextField fldAutor;
    public TextField fldIsbn;
    private KnjigaDAO dao;



    @FXML
    public void initialize() throws SQLException {
        dao = KnjigaDAO.getInstance();
        listKnjige.setItems(dao.sveKnjige());
        listKnjige.getSelectionModel().selectedItemProperty().addListener((obs, oldItem, newItem) -> {
            Knjiga oldKnjiga = (Knjiga) oldItem;
            Knjiga newKnjiga = (Knjiga) newItem;
            if (oldItem != null) {
                fldNaslov.textProperty().unbindBidirectional(oldKnjiga.naslovProperty());
                fldAutor.textProperty().unbindBidirectional(oldKnjiga.autorProperty());
                fldIsbn.textProperty().unbindBidirectional(oldKnjiga.isbnProperty() );
                //dao.izmijeni(oldKnjiga);
            }
            if (newItem != null) {
                fldNaslov.textProperty().bindBidirectional(newKnjiga.naslovProperty());
                fldAutor.textProperty().bindBidirectional(newKnjiga.autorProperty());
                fldIsbn.textProperty().bindBidirectional(newKnjiga.isbnProperty() );
            } else {
                fldNaslov.setText("");
                fldAutor.setText("");
                fldIsbn.setText("");
            }
        });
    }

    public void exitAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void okAction(ActionEvent actionEvent) {
        for (Knjiga k : listKnjige.getItems()) {
            dao.izmijeni(k);
        }
    }
}

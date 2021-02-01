package ba.unsa.etf.rpr;

import javafx.beans.property.SimpleStringProperty;

public class Knjiga {
    private int id;
    private SimpleStringProperty naslov, autor, isbn;
    private boolean promijenjena;

    public Knjiga(int id, String naslov, String autor, String isbn) {
        this.id = id;
        this.naslov = new SimpleStringProperty(naslov);
        this.autor = new SimpleStringProperty(autor);
        this.isbn = new SimpleStringProperty(isbn);
        this.promijenjena = false;
    }

    public Knjiga() {
        naslov = new SimpleStringProperty();
        autor = new SimpleStringProperty();
        isbn = new SimpleStringProperty();
        promijenjena = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaslov() {
        return naslov.get();
    }

    public void setNaslov(String naslov) {
        this.naslov.set(naslov);
    }

    public String getAutor() {
        return autor.get();
    }

    public void setAutor(String autor) {
        this.autor.set(autor);
    }

    public String getIsbn() {
        return isbn.get();
    }

    public void setIsbn(String isbn) {
        this.isbn.set(isbn);
    }

    public boolean isPromijenjena() {
        return promijenjena;
    }

    public void setPromijenjena(boolean promijenjena) {
        this.promijenjena = promijenjena;
    }

    public SimpleStringProperty naslovProperty() {
        return naslov;
    }

    public SimpleStringProperty autorProperty() {
        return autor;
    }

    public SimpleStringProperty isbnProperty() {
        return isbn;
    }

    @Override
    public String toString() {
        return autor.get() + ", " + naslov.get();
    }
}

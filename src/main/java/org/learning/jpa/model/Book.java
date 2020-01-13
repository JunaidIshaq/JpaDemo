package org.learning.jpa.model;


import javax.persistence.*;

@Entity
@Table(name = "books")
@NamedQueries({
        @NamedQuery(name = "Book.findByName",
                    query = "select b from Book b where b.name = : name"),
        @NamedQuery(name = "Book.findAll",
                    query = "select b from Book b")
})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name="isbn_number")
    private String isbn;

    @ManyToOne
    @JoinColumn(name="author_id")
    private Author author;

    public Book() {
    }

    public Book(String name,String isbn) {
        this.setName(name);
        this.setIsbn(isbn);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isbn='" + isbn + '\'' +
                ", author=" + author +
                '}';
    }
}

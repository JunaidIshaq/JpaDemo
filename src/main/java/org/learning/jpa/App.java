package org.learning.jpa;

import org.learning.jpa.model.Author;
import org.learning.jpa.model.Book;
import org.learning.jpa.model.User;
import org.learning.jpa.model.UserProfile;
import org.learning.jpa.repository.AuthorRepository;
import org.learning.jpa.repository.BookRepository;
import org.learning.jpa.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class App {

    public static void main(String[] args) {
        // Create our Entity Manager
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("perunit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Create our Repositories
        BookRepository bookRepository = new BookRepository(entityManager);
        AuthorRepository authorRepository = new AuthorRepository(entityManager);
        UserRepository userRepository = new UserRepository(entityManager);

        // Create an User and persisting to the db.
        User user = new User("Junaid");
        user.setEmail("junaidnumcs@gmail.com");
        user.setPassword("root");
        user.addProfile(new UserProfile(25,"M","Green"));

        Optional<User> savedUser = userRepository.save(user);
        savedUser.ifPresent(System.out::println);
        System.out.println("*************Find By ID*************");
        Optional<User> retrievedUser = userRepository.findById(3);
        retrievedUser.ifPresent(System.out::println);
        System.out.println("*************Find By Name************");
        Optional<User> retrievedUser1 = userRepository.findByName("Naveed");
        retrievedUser1.ifPresent(System.out::println);
        System.out.println(retrievedUser1.get().getProfile());
        System.out.println("************Find All**************");
        List<User> users = userRepository.findAll();
        users.forEach(System.out::println);
        System.out.println("***********Find All Named Query**************");
        List<User> users1 = userRepository.findAllNamedQuery();
        users1.forEach(System.out::println);
        System.out.println("**************Find By Name Named Query**************");
        Optional<User> retrievedUser2 = userRepository.findByNameNamedQuery("Salman");
        retrievedUser2.ifPresent(System.out::println);




        // Create an author and add 3 books to his list of books

        Author author = new Author("Author 1");
        author.addBook(new Book("Book 1","1322-987987-9891"));
        author.addBook(new Book("Book 2","1322-987987-9892"));
        author.addBook(new Book("Book 3","1322-987987-9893"));
        Optional<Author> savedAuthor = authorRepository.save(author);
        savedAuthor.ifPresent(System.out::println);
        System.out.println("Saved Author : " + savedAuthor.get());
        // Find all Authors
        List<Author> authors = authorRepository.findAll();
        System.out.println("Authors :");
        authors.forEach(System.out::println);
        // Find author by Name
        Optional<Author> authorByName = authorRepository.findByName("Author 1");
        System.out.println("Searching for an author by Name");
        authorByName.ifPresent(System.out::println);
        // Search for a book by ID
        Optional<Book> foundBook = bookRepository.findById(2);
        foundBook.ifPresent(System.out::println);
        // Search for a book with an invalid ID
        Optional<Book> notFoundBook = bookRepository.findById(99);
        notFoundBook.ifPresent(System.out::println);
        // List All Books
        List<Book> books = bookRepository.findAll();
        System.out.println("Books in Database :");
        books.forEach(System.out::println);
        // Find a book by name
        Optional<Book> queryBook1 = bookRepository.findByName("Book 2");
        System.out.println("Query for Book 2 : ");
        queryBook1.ifPresent(System.out::println);
        // Find a book by name using a named Query
        Optional<Book> queryBook2 = bookRepository.findByNameNamedQuery("Book 3");
        System.out.println("Query for Book 3 : ");
        queryBook2.ifPresent(System.out::println);
        // Add a book to Author 1
        Optional<Author> author1 = authorRepository.findById(2);
        author1.ifPresent(a -> {
            a.addBook(new Book("Book 4", "1322-987987-9894"));
            System.out.println("Save Author : " + authorRepository.save(a));
        });
        entityManager.close();
        entityManagerFactory.close();
    }
}

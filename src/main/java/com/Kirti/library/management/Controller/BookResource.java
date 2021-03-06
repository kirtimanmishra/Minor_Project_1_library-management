package com.Kirti.library.management.Controller;

import com.Kirti.library.management.DataAccessLayer.Book;
import com.Kirti.library.management.DataAccessLayer.BookRepository;
import com.Kirti.library.management.DataAccessLayer.IssuedBooksRepository;
import com.Kirti.library.management.DataAccessLayer.UserRepository;
import com.Kirti.library.management.Util.BookValidator;
import com.Kirti.library.management.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class BookResource {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    IssuedBooksRepository rentRepository;

    @Autowired
    private BookRepository repository;

    BookValidator validator = new BookValidator();

    @GetMapping("/hi")
    public String sayHello(){
        return "Hello Shashi";
    }


    // Find All books
    @GetMapping("/books")
    List<Book> findAll() {
        LOGGER.info("findAll called");
        List<Book> list = new ArrayList<Book>();
        //list = repository.findAll();
        try {
            list = repository.findAll();
            if (list.size() == 0) {
                LOGGER.severe("No book found");
                throw new BookNotFoundException(5);
            }
        }
        catch(BookNotFoundException exc)
        {
            LOGGER.severe(exc.toString());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No Book Found", exc);
        }
        return list;
    }

    @PostMapping("/books")  //return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    Book newBook(@RequestBody Book newBook) {
        if (validator.isValid(newBook))
            return repository.save(newBook);
        else {
            LOGGER.severe("newBook is not valid");
            return null;
        }
    }

    @GetMapping("/book")
    public List<Book> getBooksByAuthorName(@RequestParam("author") String author) {
        return repository.findBooksByAuthor(author);

    }
    @GetMapping(value = "/searchBooksBySubject")
    public List<Book> searchBooksBySubject(@RequestParam("subject") String subject){
        List<Book> books = bookRepository.findBooksBySubject(subject);
        ArrayList<Book> list = new ArrayList<Book>();
        for(Book book : books){
            if(book.getSubject().equals(subject)) list.add(book);
        }
        return list;
    }
}

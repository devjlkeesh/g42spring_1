package dev.jlkeesh.controller;

import dev.jlkeesh.dao.BookDao;
import dev.jlkeesh.dao.BookDao2;
import dev.jlkeesh.domain.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/book")
public class BookController {


    private final BookDao2 bookDao2;

    public BookController(BookDao2 bookDao2) {
        this.bookDao2 = bookDao2;
    }

    @GetMapping
    public String bookPage(Model model) {
        List<Book> books = bookDao2.findAll(1, 10);
        model.addAttribute("books", books);
        return "book/books";
    }

    @GetMapping("/add")
    public String bookAddPage() {
        return "book/add";
    }
    @PostMapping("/add")
    public String bookAdd(@ModelAttribute Book book) {
        bookDao2.save(book);
        return "redirect:/book";
    }

    /*private final BookDao bookDao;

    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @ResponseBody
    @PostMapping
    public String save() {
        Book book = new Book();
        book.setTitle("Go Tools");
        book.setAuthor("Javohir Elmurodov");
        book.setPageCount(200);
        return bookDao.save(book).toString();
    }

    @GetMapping("/all")
    @ResponseBody
    public String list(@RequestParam("size") int size, @RequestParam("page") int page) {
        return bookDao.findAll(page, size).toString();
    }*/

}

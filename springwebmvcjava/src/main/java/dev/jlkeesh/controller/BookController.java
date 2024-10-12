package dev.jlkeesh.controller;

import dev.jlkeesh.BookUpdateDto;
import dev.jlkeesh.dao.BookDao2;
import dev.jlkeesh.domain.Book;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public String bookAddPage(Model model) {
        model.addAttribute("title", "Book Add Page");
        model.addAttribute("book", new Book());
        return "book/add";
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PostMapping("/add")
    public String bookAdd(@ModelAttribute Book book) {
        bookDao2.save(book);
        return "redirect:/book";
    }

    @GetMapping("/delete/{id}")
    public String bookDeletePage(Model model, @PathVariable("id") int id) {
        Book book = bookDao2.findById(id);
        model.addAttribute("book", book);
        return "book/delete";
    }

    @PostMapping("/delete/{id}")
    public String bookDelete(@PathVariable("id") int id) {
        bookDao2.delete(id);
        return "redirect:/book";
    }


    @GetMapping("/update/{id}")
    public String bookUpdatePage(Model model, @PathVariable("id") int id) {
        Book book = bookDao2.findById(id);
        model.addAttribute("title", "Book Update Page");
        model.addAttribute("book", book);
        return "book/add";
    }

    @PostMapping("/update/{id}")
    public String bookUpdate(@PathVariable("id") int id, @ModelAttribute BookUpdateDto dto) {
        Book dbBook = bookDao2.findById(id);//
        dbBook.setTitle(dto.title());
        dbBook.setAuthor(dto.author());
        dbBook.setPageCount(dto.pageCount());
        bookDao2.update(dbBook);
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

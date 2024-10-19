package dev.jlkeesh.controller;

import dev.jlkeesh.dto.BookUpdateDto;
import dev.jlkeesh.dao.BookDao2;
import dev.jlkeesh.domain.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;


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

    //    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PostMapping("/add")
    public String bookAdd(@ModelAttribute Book book,
                          @RequestParam("file") MultipartFile file) throws IOException {
        String url = "C:\\Users\\jlkeesh\\IdeaProjects\\pdp\\g42\\g42spring_1\\springwebmvcjava\\uploads";



        Files.copy(
                file.getInputStream(),
                Path.of(url, UUID.randomUUID() +"."+ StringUtils.getFilenameExtension(file.getOriginalFilename())),
                StandardCopyOption.REPLACE_EXISTING);
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

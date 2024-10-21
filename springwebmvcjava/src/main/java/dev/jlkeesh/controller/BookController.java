package dev.jlkeesh.controller;

import dev.jlkeesh.dao.BookDao2;
import dev.jlkeesh.domain.Book;
import dev.jlkeesh.dto.BookUpdateDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String bookAdd(@Valid @ModelAttribute Book book, BindingResult br, RedirectAttributes attributes) throws IOException {
        if (br.hasErrors()) {
            for (FieldError fieldError : br.getFieldErrors()) {
                attributes.addFlashAttribute("error_"+fieldError.getField(), fieldError.getDefaultMessage());
            }
            return "redirect:/book/add";
        }
        String url = "C:\\Users\\jlkeesh\\IdeaProjects\\pdp\\g42\\g42spring_1\\springwebmvcjava\\uploads";

        MultipartFile file = book.getFile();
        Files.copy(
                file.getInputStream(),
                Path.of(url, UUID.randomUUID() + "." + StringUtils.getFilenameExtension(file.getOriginalFilename())),
                StandardCopyOption.REPLACE_EXISTING);
        bookDao2.save(book);
        return "redirect:/book";
    }

    @GetMapping("/delete/{id}")
    public String bookDeletePage(Model model, @PathVariable("id") int id) {
        Book book = bookDao2.findById(id);
        /*        Book book = null;
        try {
            book = bookDao2.findById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            throw new ResourceNotFoundException("book not found with id:" + id);
        }*/
        model.addAttribute("book", book);
        return "book/delete";
    }

/*    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ModelAndView emptyResult(HttpServletRequest req, EmptyResultDataAccessException e) {
        String requestURI = req.getRequestURI();
        ModelAndView modelAndView = new ModelAndView("/error/404");
        modelAndView.addObject("error_message", "book not found: " + requestURI);
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView internal(Exception e) {
        ModelAndView modelAndView = new ModelAndView("/error/500");
        modelAndView.addObject("error_message", "internal error");
        return modelAndView;
    }*/


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

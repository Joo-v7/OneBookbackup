package com.onebook.frontapi.controller.book;

import com.onebook.frontapi.dto.author.AuthorDTO;
import com.onebook.frontapi.dto.book.BookAuthorDTO;
import com.onebook.frontapi.dto.book.BookDTO;
import com.onebook.frontapi.dto.book.BookSaveDTO;
import com.onebook.frontapi.dto.image.ImageDTO;
import com.onebook.frontapi.dto.publisher.PublisherDTO;
import com.onebook.frontapi.dto.tag.TagResponse;
import com.onebook.frontapi.service.author.AuthorService;
import com.onebook.frontapi.service.book.BookAuthorService;
import com.onebook.frontapi.service.book.BookService;
import com.onebook.frontapi.service.image.ImageService;
import com.onebook.frontapi.service.publisher.PublisherService;
import com.onebook.frontapi.service.tag.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/book")
@Slf4j
public class BookController {
    private final BookService bookService;
    private final BookAuthorService bookAuthorService;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final TagService tagService;
    private final ImageService imageService;

    @GetMapping("/newbooks")
    public String newBooks(Model model, Pageable pageable) {
        Page<BookDTO> bookDTOPage = bookService.newBooks(pageable);
        log.info("page: {}", bookDTOPage);
        model.addAttribute("bookList", bookDTOPage.getContent());

        return "index";
    }


    @GetMapping("/bookDetail")
    public String bookDetail(@RequestParam("bookId") long bookId,
                             @RequestParam(value = "url", required = false) String url,
                             Model model) {
        BookDTO book = bookService.getBook(bookId);
        BookAuthorDTO bookAuthor = bookAuthorService.getBookAuthor(bookId);

        AuthorDTO author = authorService.getAuthor(bookAuthor.getAuthor().getAuthorId());

        log.info("bookId: {}", book.getBookId());
        log.info("bookTitle: {}", book.getTitle());
        if(Objects.isNull(url) || url.isEmpty()) {
            ImageDTO image = imageService.getImage(bookId);
            model.addAttribute("url", image.getUrl());
        }else{
            model.addAttribute("url", url);

        }
        model.addAttribute("book", book);
        model.addAttribute("author", author);

        return "book/bookDetail";
    }

    @GetMapping
    public String registerForm(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                               Model model) {
        log.info("Form    categoryId: {}", categoryId);
        model.addAttribute("categoryId", categoryId);
        return "book/bookRegister";
    }


    @PostMapping("/create")
    public String createBook(@RequestParam("title") String title,
                             @RequestParam("content") String content,
                             @RequestParam("pubdate") String pubdate,
                             @RequestParam("description") String description,
                             @RequestParam("isbn13") String isbn13,
                             @RequestParam("priceSales") Integer priceSales,
                             @RequestParam("price") Integer price,
                             @RequestParam("stock") Integer stock,
                             @RequestParam("authorId") int authorId,
                             @RequestParam("publisherId") Long publisherId,
                             @RequestParam("tagId") Long tagId,
                             @RequestParam("categoriesId") String categoriesId,
                             @RequestPart("image") MultipartFile image,
                             Model model) {
        AuthorDTO author = authorService.getAuthor(authorId);
        PublisherDTO publisher = publisherService.getById(publisherId);
        TagResponse tag = tagService.getTag(tagId);
        log.info("title: {}", title);
        log.info("content: {}", content);
        log.info("pubdate: {}", pubdate);
        log.info("description: {}", description);
        log.info("isbn13: {}", isbn13);
        log.info("priceSales: {}", priceSales);
        log.info("price: {}", price);
        log.info("stock: {}", stock);
        log.info("authorId: {}", author.getAuthorId());
        log.info("publisherId: {}", publisher.getPublisherId());
        log.info("tagId: {}", tag.tagId());
        log.info("categoriesId: {}", categoriesId);
        log.info("image: {}", image.getOriginalFilename());



        BookSaveDTO dto = new BookSaveDTO();
        dto.setTitle(title);
        dto.setContent(content);
        dto.setPubdate(pubdate);  // String을 LocalDate로 변환
        dto.setDescription(description);
        dto.setIsbn13(isbn13);
        dto.setPriceSales(priceSales);
        dto.setPrice(price);
        dto.setStock(stock);
        dto.setAuthor(author);
        dto.setPublisher(publisher);
        dto.setTag(tag);
        dto.setCategoryId(Integer.parseInt(categoriesId));
        log.info("imageName: {}", image.getOriginalFilename());
        bookService.createBook(dto, image);
        return "redirect:/";
    }



//    @PostMapping("/register")
//    public String register(BookSaveDTO dto, MultipartFile image, Model model) {
//        BookDTO book = bookService.createBook(dto, image);
//    }
}

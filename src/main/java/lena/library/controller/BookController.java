package lena.library.controller;


import lena.library.service.BookService;
import lena.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public ModelAndView allBooks() {// метод получения всех книг
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("books");
//        modelAndView.addObject("Lena's Way", book);
//        return modelAndView;
//    }
//    @RequestMapping(value = "/edit", method = RequestMethod.GET)
//    public ModelAndView editPage() { //метод редактирования
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("authors");
//        return modelAndView;
//    }

       // private static Book book;

//        static {
//            book = new Book("Lena's Way",2019,"Путь Лены к программированию", );
//        }

}

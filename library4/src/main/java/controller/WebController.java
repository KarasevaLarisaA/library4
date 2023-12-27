package controller;

import bean.GenresBean;
import bean.BooksBean;
import entity.Book;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet(loadOnStartup = 1, urlPatterns = {"/book", "/genre"})
public class WebController extends HttpServlet {

    @EJB
    GenresBean genresBean;

    @EJB
    BooksBean booksBean;

    @Override
    public void init() {
        var ctx = getServletContext();
        ctx.setAttribute("genres", genresBean.findAll());
        ctx.setAttribute("allBooks", booksBean.findAll());
        ctx.setAttribute("genreBooks", null);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String path = req.getServletPath();
        var ctx = getServletContext();
        ctx.setAttribute("genreBooks", null);
        if (Objects.equals(path, "/genre")) {
            var param = req.getParameter("id");
            List<Book> books = null;
            if (param != null) {
                try {
                    var id = Long.parseLong(param);
                    var genre = genresBean.findById(id);
                    if (genre != null) {
                        books = booksBean.findByGenre(genre.getId());
                    }
                } catch (NumberFormatException ignored) {}
            }
            ctx.setAttribute("genreBooks", books);
            ctx.getRequestDispatcher("/index.jsp").forward(req, resp);
        } else if (Objects.equals(path, "/book")) {
            var param = req.getParameter("id");
            Book book = null;
            if (param != null) {
                try {
                    var id = Long.parseLong(param);
                    book = booksBean.findById(id);
                } catch (NumberFormatException ignored) {}
            }
            ctx.setAttribute("book", book);
            ctx.getRequestDispatcher("/WEB-INF/views" + path + ".jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}

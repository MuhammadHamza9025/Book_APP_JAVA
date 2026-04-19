package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.BookDAO;
import models.Book;

@WebServlet("/getAllBooks")
public class GetAllBooksServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BookDAO dao = new BookDAO();
        ArrayList<Book> books = dao.getAllBooks();

        request.setAttribute("books", books);
        request.getRequestDispatcher("/allBooks.jsp").forward(request, response);
    }
}
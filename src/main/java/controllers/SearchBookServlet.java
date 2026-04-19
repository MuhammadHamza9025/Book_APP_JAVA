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

@WebServlet("/searchBook")
public class SearchBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchQuery = request.getParameter("search");

        BookDAO dao = new BookDAO();
        ArrayList<Book> books;

        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            books = dao.searchBooks(searchQuery); // Assumes this uses LIKE queries
        } else {
            books = dao.getAllBooks();
        }

        request.setAttribute("books", books);
        request.getRequestDispatcher("/allBooks.jsp").forward(request, response);
    }
}
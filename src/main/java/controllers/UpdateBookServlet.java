package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.BookDAO;
import models.Book;

@WebServlet("/updateBook")
public class UpdateBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        
        if (idStr != null && !idStr.trim().isEmpty()) {
            int id = Integer.parseInt(idStr);
            BookDAO dao = new BookDAO();
            Book book = dao.getBookById(id);
            
            request.setAttribute("book", book);
            request.getRequestDispatcher("/editBook.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/getAllBooks");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String date = request.getParameter("date");
        String genres = request.getParameter("genres");
        String characters = request.getParameter("characters");
        String synopsis = request.getParameter("synopsis");

        Book book = new Book(id, title, author, date, genres, characters, synopsis);
        BookDAO dao = new BookDAO();
        dao.updateBook(book); // Assumes updateBook is implemented in your BookDAO

        response.sendRedirect(request.getContextPath() + "/getAllBooks");
    }
}
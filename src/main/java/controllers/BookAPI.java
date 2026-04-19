package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.google.gson.Gson;

import db.BookDAO;
import models.Book;
import models.BookList;

@WebServlet("/BookAPI")
public class BookAPI extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String format = request.getParameter("format");
        if (format == null) format = "json"; // Default to JSON

        String idStr = request.getParameter("id");
        String searchStr = request.getParameter("search");

        BookDAO dao = new BookDAO();
        Object resultData = null;

        if (idStr != null && !idStr.isEmpty()) {
            Book book = dao.getBookById(Integer.parseInt(idStr));
            if (book == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                sendResponse(response, "Book not found", "text");
                return;
            }
            resultData = book;
        } else if (searchStr != null && !searchStr.isEmpty()) {
            ArrayList<Book> books = dao.searchBooks(searchStr);
            resultData = new BookList(books);
        } else {
            ArrayList<Book> books = dao.getAllBooks();
            resultData = new BookList(books);
        }

        sendResponse(response, resultData, format);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String format = request.getParameter("format");
        if (format == null) format = "json";

        String requestBody = getRequestBody(request);
        Book book = parseBookFromBody(requestBody, format);

        if (book != null) {
            BookDAO dao = new BookDAO();
            dao.insertBook(book);
            response.setStatus(HttpServletResponse.SC_CREATED);
            sendResponse(response, "Book successfully created", "text");
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            sendResponse(response, "Invalid request body", "text");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String format = request.getParameter("format");
        if (format == null) format = "json";

        String requestBody = getRequestBody(request);
        Book book = parseBookFromBody(requestBody, format);

        if (book != null) {
            BookDAO dao = new BookDAO();
            dao.updateBook(book);
            response.setStatus(HttpServletResponse.SC_OK);
            sendResponse(response, "Book successfully updated", "text");
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            sendResponse(response, "Invalid request body", "text");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");

        if (idStr != null && !idStr.trim().isEmpty()) {
            BookDAO dao = new BookDAO();
            dao.deleteBook(Integer.parseInt(idStr));
            response.setStatus(HttpServletResponse.SC_OK);
            sendResponse(response, "Book successfully deleted", "text");
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            sendResponse(response, "ID is required for deletion", "text");
        }
    }

    // --- Helper Methods ---

    private void sendResponse(HttpServletResponse response, Object data, String format) throws IOException {
        PrintWriter out = response.getWriter();
        format = format.toLowerCase();

        try {
            if (format.equals("xml")) {
                response.setContentType("application/xml");
                JAXBContext context = JAXBContext.newInstance(Book.class, BookList.class);
                Marshaller marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                
                StringWriter sw = new StringWriter();
                marshaller.marshal(data, sw);
                out.print(sw.toString());

            } else if (format.equals("text")) {
                response.setContentType("text/plain");
                if (data instanceof BookList) {
                    for (Book b : ((BookList) data).getBooks()) {
                        out.println(b.toString());
                    }
                } else {
                    out.println(data.toString());
                }

            } else { // json default
                response.setContentType("application/json");
                // For BookList, unwrap it into the actual List to keep JSON arrays clean instead of an object wrapping an array.
                if (data instanceof BookList) {
                    out.print(gson.toJson(((BookList) data).getBooks()));
                } else {
                    out.print(gson.toJson(data));
                }
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("text/plain");
            out.print("Server Error: " + e.getMessage());
        }
        
        out.flush();
    }

    private String getRequestBody(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    private Book parseBookFromBody(String body, String format) {
        try {
            if (format.equalsIgnoreCase("xml")) {
                JAXBContext context = JAXBContext.newInstance(Book.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                return (Book) unmarshaller.unmarshal(new StringReader(body));
            } else if (format.equalsIgnoreCase("json")) {
                return gson.fromJson(body, Book.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
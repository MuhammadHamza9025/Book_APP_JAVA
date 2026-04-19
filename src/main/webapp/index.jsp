<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="java.util.ArrayList" %>
        <%@ page import="models.Book" %>
            <%@ page import="db.BookDAO" %>
                <!DOCTYPE html>
                <html>

                <head>
                    <meta charset="UTF-8">
                    <title>Book Store</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            margin: 40px;
                        }

                        table {
                            border-collapse: collapse;
                            width: 100%;
                            margin-top: 20px;
                        }

                        th,
                        td {
                            border: 1px solid #dddddd;
                            text-align: left;
                            padding: 8px;
                        }

                        th {
                            background-color: #f2f2f2;
                        }
                    </style>
                </head>

                <body>
                    <h1>Welcome to the Book Application</h1>

                    <form action="index.jsp" method="get">
                        <input type="text" name="search" placeholder="Search for books...">
                        <input type="submit" value="Search">
                    </form>

                    <% BookDAO dao=new BookDAO(); ArrayList<Book> books = new ArrayList<>();

                            String searchQuery = request.getParameter("search");
                            if (searchQuery != null && !searchQuery.trim().isEmpty()) {
                            books = dao.searchBooks(searchQuery);
                            } else {
                            books = dao.getAllBooks();
                            }
                            %>

                            <h2>Book List</h2>
                            <% if(books==null || books.isEmpty()) { %>
                                <p>No books found or unable to connect to the database.</p>
                                <% } else { %>
                                    <table>
                                        <tr>
                                            <th>ID</th>
                                            <th>Title</th>
                                            <th>Author</th>
                                            <th>Genres</th>
                                            <th>Synopsis</th>
                                        </tr>
                                        <% for(Book b : books) { %>
                                            <tr>
                                                <td>
                                                    <%= b.getId() %>
                                                </td>
                                                <td>
                                                    <%= b.getTitle() %>
                                                </td>
                                                <td>
                                                    <%= b.getAuthor() %>
                                                </td>
                                                <td>
                                                    <%= b.getGenres() %>
                                                </td>
                                                <td>
                                                    <%= b.getSynopsis() %>
                                                </td>
                                            </tr>
                                            <% } %>
                                    </table>
                                    <% } %>
                </body>

                </html>
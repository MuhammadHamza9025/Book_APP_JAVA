<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>All Books</title>
            <!-- Bootstrap 5 CSS -->
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        </head>

        <body>
            <div class="container mt-5">
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <h2>Book Manager</h2>
                    <a href="${pageContext.request.contextPath}/addBook" class="btn btn-primary">Add New Book</a>
                </div>

                <div class="card mb-4">
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/searchBook" method="get" class="d-flex">
                            <input class="form-control me-2" type="search" name="search"
                                placeholder="Search books by title, author, or genre..." aria-label="Search"
                                value="<c:out value='${param.search}' />">
                            <button class="btn btn-outline-success" type="submit">Search</button>
                            <c:if test="${not empty param.search}">
                                <a href="${pageContext.request.contextPath}/getAllBooks"
                                    class="btn btn-outline-secondary ms-2">Clear</a>
                            </c:if>
                        </form>
                    </div>
                </div>

                <c:if test="${not empty param.search}">
                    <h5 class="text-muted mb-3">Search results for: "
                        <c:out value="${param.search}" />"
                    </h5>
                </c:if>

                <div class="table-responsive">
                    <table class="table table-striped table-hover align-middle">
                        <thead class="table-dark">
                            <tr>
                                <th>ID</th>
                                <th>Title</th>
                                <th>Author</th>
                                <th>Date</th>
                                <th>Genres</th>
                                <th class="text-center">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="book" items="${books}">
                                <tr>
                                    <td>${book.id}</td>
                                    <td>
                                        <c:out value="${book.title}" />
                                    </td>
                                    <td>
                                        <c:out value="${book.author}" />
                                    </td>
                                    <td>
                                        <c:out value="${book.date}" />
                                    </td>
                                    <td>
                                        <c:out value="${book.genres}" />
                                    </td>
                                    <td class="text-center">
                                        <a href="${pageContext.request.contextPath}/updateBook?id=${book.id}"
                                            class="btn btn-sm btn-warning">Edit</a>
                                        <a href="${pageContext.request.contextPath}/deleteBook?id=${book.id}"
                                            class="btn btn-sm btn-danger ms-1"
                                            onclick="return confirm('Are you sure you want to delete this book?');">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty books}">
                                <tr>
                                    <td colspan="6" class="text-center text-muted">No books found.</td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
            <!-- Bootstrap 5 JS Bundle -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        </body>

        </html>
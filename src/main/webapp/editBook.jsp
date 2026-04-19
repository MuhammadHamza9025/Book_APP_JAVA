<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Edit Book</title>
            <!-- Bootstrap 5 CSS -->
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        </head>

        <body class="bg-light">
            <div class="container mt-5">
                <div class="row justify-content-center">
                    <div class="col-md-8">
                        <div class="card shadow-sm">
                            <div class="card-header bg-warning">
                                <h3 class="card-title mb-0">Edit Book:
                                    <c:out value="${book.title}" />
                                </h3>
                            </div>
                            <div class="card-body">
                                <form action="${pageContext.request.contextPath}/updateBook" method="post">
                                    <!-- Hidden ID Field -->
                                    <input type="hidden" name="id" value="${book.id}">

                                    <div class="row g-3">
                                        <div class="col-md-6 mb-3">
                                            <label for="title" class="form-label fw-bold">Title</label>
                                            <input type="text" class="form-control" id="title" name="title"
                                                value="<c:out value='${book.title}' />" required>
                                        </div>
                                        <div class="col-md-6 mb-3">
                                            <label for="author" class="form-label fw-bold">Author</label>
                                            <input type="text" class="form-control" id="author" name="author"
                                                value="<c:out value='${book.author}' />" required>
                                        </div>
                                        <div class="col-md-6 mb-3">
                                            <label for="date" class="form-label fw-bold">Date Published</label>
                                            <input type="text" class="form-control" id="date" name="date"
                                                value="<c:out value='${book.date}' />">
                                        </div>
                                        <div class="col-md-6 mb-3">
                                            <label for="genres" class="form-label fw-bold">Genres</label>
                                            <input type="text" class="form-control" id="genres" name="genres"
                                                value="<c:out value='${book.genres}' />">
                                        </div>
                                        <div class="col-12 mb-3">
                                            <label for="characters" class="form-label fw-bold">Characters</label>
                                            <input type="text" class="form-control" id="characters" name="characters"
                                                value="<c:out value='${book.characters}' />">
                                        </div>
                                        <div class="col-12 mb-4">
                                            <label for="synopsis" class="form-label fw-bold">Synopsis</label>
                                            <textarea class="form-control" id="synopsis" name="synopsis"
                                                rows="4"><c:out value='${book.synopsis}' /></textarea>
                                        </div>
                                    </div>
                                    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                        <button type="submit" class="btn btn-primary me-md-2">Update Book</button>
                                        <a href="${pageContext.request.contextPath}/getAllBooks"
                                            class="btn btn-secondary">Cancel</a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Bootstrap 5 JS Bundle -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        </body>

        </html>
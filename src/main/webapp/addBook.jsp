<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Book</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card shadow-sm">
                <div class="card-header bg-primary text-white">
                    <h3 class="card-title mb-0">Add a New Book</h3>
                </div>
                <div class="card-body">
                    <form action="${pageContext.request.contextPath}/addBook" method="post">
                        <div class="row g-3">
                            <div class="col-md-6 mb-3">
                                <label for="title" class="form-label fw-bold">Title</label>
                                <input type="text" class="form-control" id="title" name="title" required placeholder="e.g., The Hobbit">
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="author" class="form-label fw-bold">Author</label>
                                <input type="text" class="form-control" id="author" name="author" required placeholder="e.g., J.R.R. Tolkien">
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="date" class="form-label fw-bold">Date Published</label>
                                <input type="text" class="form-control" id="date" name="date" placeholder="e.g., 1937-09-21">
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="genres" class="form-label fw-bold">Genres</label>
                                <input type="text" class="form-control" id="genres" name="genres" placeholder="e.g., Fantasy, Adventure">
                            </div>
                            <div class="col-12 mb-3">
                                <label for="characters" class="form-label fw-bold">Characters</label>
                                <input type="text" class="form-control" id="characters" name="characters" placeholder="e.g., Bilbo Baggins, Gandalf">
                            </div>
                            <div class="col-12 mb-4">
                                <label for="synopsis" class="form-label fw-bold">Synopsis</label>
                                <textarea class="form-control" id="synopsis" name="synopsis" rows="4" placeholder="Brief summary of the book..."></textarea>
                            </div>
                        </div>
                        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                            <a href="${pageContext.request.contextPath}/getAllBooks" class="btn btn-secondary me-md-2">Cancel</a>
                            <button type="submit" class="btn btn-success">Save Book</button>
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
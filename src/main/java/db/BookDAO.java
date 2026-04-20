package db;

import java.sql.*;
import java.util.ArrayList;
import models.Book;

public class BookDAO {

    Book oneBook = null;
    Connection conn = null;
    Statement stmt = null;
    // String user = "amjidamz"; // ← change this
    // String password = "Vudeksoor7"; // ← change this
    // String url = "jdbc:mysql://mudfoot.doc.stu.mmu.ac.uk:6306/" + user;
    // CHANGE THESE 3 LINES
    String user = "root";
    String password = "JXYtjXxrpbSEviMVnNeuHaVOiRRTMMUa";
    String url = "jdbc:mysql://roundhouse.proxy.rlwy.net:20245/railway";

    public BookDAO() {
    }

    private void openConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
        } catch (SQLException se) {
            System.out.println(se);
        }
    }

    private void closeConnection() {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Book getNextBook(ResultSet rs) {
        Book thisBook = null;
        try {
            thisBook = new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("date"),
                    rs.getString("genres"),
                    rs.getString("characters"),
                    rs.getString("synopsis"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return thisBook;
    }

    // ── GET ALL ──────────────────────────────────────────────
    public ArrayList<Book> getAllBooks() {
        ArrayList<Book> allBooks = new ArrayList<>();
        openConnection();
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM books");
            while (rs.next()) {
                allBooks.add(getNextBook(rs));
            }
            stmt.close();
            closeConnection();
        } catch (SQLException se) {
            System.out.println(se);
        }
        return allBooks;
    }

    // ── SEARCH ───────────────────────────────────────────────
    public ArrayList<Book> searchBooks(String searchStr) {
        ArrayList<Book> results = new ArrayList<>();
        openConnection();
        try {
            String sql = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ? OR genres LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            String term = "%" + searchStr + "%";
            ps.setString(1, term);
            ps.setString(2, term);
            ps.setString(3, term);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                results.add(getNextBook(rs));
            }
            ps.close();
            closeConnection();
        } catch (SQLException se) {
            System.out.println(se);
        }
        return results;
    }

    // ── GET BY ID ────────────────────────────────────────────
    public Book getBookById(int id) {
        Book book = null;
        openConnection();
        try {
            String sql = "SELECT * FROM books WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                book = getNextBook(rs);
            }
            ps.close();
            closeConnection();
        } catch (SQLException se) {
            System.out.println(se);
        }
        return book;
    }

    // ── INSERT ───────────────────────────────────────────────
    public int insertBook(Book b) {
        int rows = 0;
        openConnection();
        try {
            String sql = "INSERT INTO books (title, author, date, genres, characters, synopsis) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, b.getTitle());
            ps.setString(2, b.getAuthor());
            ps.setString(3, b.getDate());
            ps.setString(4, b.getGenres());
            ps.setString(5, b.getCharacters());
            ps.setString(6, b.getSynopsis());
            rows = ps.executeUpdate();
            ps.close();
            closeConnection();
        } catch (SQLException se) {
            System.out.println(se);
        }
        return rows;
    }

    // ── UPDATE ───────────────────────────────────────────────
    public int updateBook(Book b) {
        int rows = 0;
        openConnection();
        try {
            String sql = "UPDATE books SET title=?, author=?, date=?, genres=?, characters=?, synopsis=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, b.getTitle());
            ps.setString(2, b.getAuthor());
            ps.setString(3, b.getDate());
            ps.setString(4, b.getGenres());
            ps.setString(5, b.getCharacters());
            ps.setString(6, b.getSynopsis());
            ps.setInt(7, b.getId());
            rows = ps.executeUpdate();
            ps.close();
            closeConnection();
        } catch (SQLException se) {
            System.out.println(se);
        }
        return rows;
    }

    // ── DELETE ───────────────────────────────────────────────
    public int deleteBook(int id) {
        int rows = 0;
        openConnection();
        try {
            String sql = "DELETE FROM books WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rows = ps.executeUpdate();
            ps.close();
            closeConnection();
        } catch (SQLException se) {
            System.out.println(se);
        }
        return rows;
    }
}
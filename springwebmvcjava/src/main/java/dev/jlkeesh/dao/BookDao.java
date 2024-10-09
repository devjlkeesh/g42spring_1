package dev.jlkeesh.dao;

import dev.jlkeesh.domain.Book;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDao {
    private final JdbcTemplate jdbcTemplate;
    private final BeanPropertyRowMapper<Book> rowMapper = BeanPropertyRowMapper.newInstance(Book.class);

    private static String insertQuery = "insert into books (title,author,page_count) values (?,?,?) returning id;";

    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Book save(Book book) {
        return jdbcTemplate.execute(insertQuery, (PreparedStatementCallback<Book>) ps -> {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getPageCount());
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                book.setId(resultSet.getInt("id"));
            }
            return book;
        });
    }

    public List<Book> findAll() {
        return jdbcTemplate.query("select * from books", rowMapper);
    }

    public List<Book> findAll(int page, int size) {
        return jdbcTemplate.query("select * from books limit ? offset ?", rs -> {
            List<Book> books = new ArrayList<>();
            int i = 0;
            while (rs.next()) {
                Book book = rowMapper.mapRow(rs, i);
                i++;
                books.add(book);
            }
            return books;
        }, size, (page - 1) * size);
    }

    public List<Book> findAll2(int page, int size) {
        return jdbcTemplate.query("select * from books limit ? offset ?", rowMapper, size, (page - 1) * size);
    }


}

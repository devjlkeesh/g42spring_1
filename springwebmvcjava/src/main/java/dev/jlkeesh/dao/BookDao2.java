package dev.jlkeesh.dao;

import dev.jlkeesh.domain.Book;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class BookDao2 {
    private final BeanPropertyRowMapper<Book> rowMapper = BeanPropertyRowMapper.newInstance(Book.class);
    private static String insertQuery = "insert into books (title,author,page_count) values (:title,:author,:page_count);";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public BookDao2(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = namedParameterJdbcTemplate;
    }


    public Book save(Book book) {
//        SqlParameterSource params = new BeanPropertySqlParameterSource(book);
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("title", book.getTitle())
                .addValue("author", book.getAuthor())
                .addValue("page_count", book.getPageCount());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(insertQuery, params, keyHolder, new String[]{"id"});
        book.setId(keyHolder.getKey().longValue());
        return book;
    }

    public Book findById(int id) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);
        return jdbcTemplate.queryForObject("select * from books where id = :id", params, rowMapper);
    }

    public List<Book> findAll(int page, int size) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("limit", size)
                .addValue("offset", (page - 1) * size);
        return jdbcTemplate.query("select * from books  limit :limit offset :offset", params, rowMapper);
    }

    public boolean delete(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
        int count = jdbcTemplate.update("delete from books where id = :id", params);
        return count != 0;
    }

    public Book update(Book book) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", book.getId())
                .addValue("title", book.getTitle())
                .addValue("author", book.getAuthor())
                .addValue("page_count", book.getPageCount());
        jdbcTemplate.update("update books set title = :title, author = :author, page_count = :page_count where id = :id", params);
        return book;
    }
}

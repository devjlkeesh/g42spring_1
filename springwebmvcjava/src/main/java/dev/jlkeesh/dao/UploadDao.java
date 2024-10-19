package dev.jlkeesh.dao;

import dev.jlkeesh.domain.Upload;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Optional;

@Repository
public class UploadDao {

    private final JdbcTemplate jdbcTemplate;

    public UploadDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Upload upload) {
        jdbcTemplate.update("insert into uploads(id, original_name,generated_name,download_url, size, mime_type, uploaded_date) values(?,?,?,?,?,?,?)"
                , ps -> {
                    ps.setString(1, upload.getId().toString());
                    ps.setString(2, upload.getOriginalName());
                    ps.setString(3, upload.getGeneratedName());
                    ps.setString(4, upload.getDownloadUrl());
                    ps.setLong(5, upload.getSize());
                    ps.setString(6, upload.getMimeType());
                    ps.setTimestamp(7, Timestamp.valueOf(upload.getUploadDate()));
                });
    }

    public Optional<Upload> findByGeneratedName(String id) {
        Upload upload = jdbcTemplate.queryForObject("select * from uploads where generated_name = ?",
                new Object[]{id},
                BeanPropertyRowMapper.newInstance(Upload.class));
        return Optional.ofNullable(upload);
    }

}

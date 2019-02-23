package sk.upjs.ics.deutsch;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class MySqlWordDao implements WordDao {
    private JdbcTemplate jdbcTemplate;
    
    public MySqlWordDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public List<Word> getAll() {
        String sql = "SELECT * FROM word";
        BeanPropertyRowMapper<Word> mapper = BeanPropertyRowMapper.newInstance(Word.class);
        return jdbcTemplate.query(sql, mapper);
    }
    
    @Override
    public void add(Word word) {
        String sql = "INSERT INTO word VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, null, word.getDeu(), word.getEng());     
    }
    
    @Override
    public void delete(Word word) {
        String sql = "DELETE FROM word WHERE id = ?";
        jdbcTemplate.update(sql, word.getId());
    }

    @Override
    public Word getByRowIndex(int idx) {
        String sql = "SELECT TOP ? EXCEPT TOP ? FROM word";
        BeanPropertyRowMapper<Word> mapper = BeanPropertyRowMapper.newInstance(Word.class);
        return (Word) (jdbcTemplate.query(sql, mapper, idx, idx - 1).get(0));
    }

}

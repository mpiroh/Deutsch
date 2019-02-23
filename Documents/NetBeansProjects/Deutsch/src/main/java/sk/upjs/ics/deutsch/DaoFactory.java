package sk.upjs.ics.deutsch;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public enum DaoFactory {
    INSTANCE;

    private WordDao wordDao;
    private JdbcTemplate jdbcTemplate;
    private MysqlDataSource dataSource;

    public WordDao getWordDao() {
        if (this.wordDao == null) {
            this.wordDao = new MySqlWordDao(getJdbcTemplate());
        }
        return this.wordDao;
    }

    public JdbcTemplate getJdbcTemplate() {
        if (this.jdbcTemplate == null) {
            this.jdbcTemplate = new JdbcTemplate(getDataSource());
        }
        return this.jdbcTemplate;
    }
    
    public MysqlDataSource getDataSource() {
        if (this.dataSource == null) {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setURL("jdbc:mysql://localhost/deutsch");
            dataSource.setUser("deutschUser");
            dataSource.setPassword("deutschPassword");
            this.dataSource = dataSource;
        }
        return this.dataSource;
    }
}

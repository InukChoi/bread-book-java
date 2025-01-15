package global.common;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCp {
    private static HikariDataSource ds;
    static {
        HikariConfig config = new HikariConfig();
        // DB URL 변경하기
        config.setJdbcUrl("jdbc:mysql://localhost:3306/abc");
        config.setUsername("root");
        config.setPassword("qwer1234");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setMinimumIdle(5);
        config.setMaximumPoolSize(10);

        ds = new HikariDataSource(config);
    }
}

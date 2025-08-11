package learn.sqlite;

import java.sql.SQLException;
import java.sql.Statement;

public class SchemaInitializer {
    public static void run() {
        try (final Statement stmt = DatabaseManager.getConnection().createStatement()) {
            stmt.execute("""
                        CREATE TABLE IF NOT EXISTS rfid_card (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            card_uid TEXT NOT NULL UNIQUE,
                            owner_name TEXT
                        )
                    """);
        } catch (SQLException e) {
            throw new RuntimeException("Schema init failed", e);
        }
    }
}

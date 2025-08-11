package learn.sqlite.rfid;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import learn.sqlite.DatabaseManager;

public class RfidRepository {

    public Rfid insert(Rfid rfid) {
        final String sql = """
                    INSERT INTO rfid_card (card_uid, owner_name)
                    VALUES (?, ?)
                """;

        try (final PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, rfid.getCardUid());
            pstmt.setString(2, rfid.getOwnerName());
            pstmt.executeUpdate();

            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) {
                    int id = keys.getInt(1);
                    return new Rfid(id, rfid.getCardUid(), rfid.getOwnerName());
                }
            }

            throw new RuntimeException("Error inserting Rfid: No ID");
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting Rfid", e);
        }
    }

    public Optional<Rfid> findByCardUid(String cardUid) {
        final String sql = """
                    SELECT id, card_uid, owner_name
                    FROM rfid_card
                    WHERE card_uid = ?
                """;
        try (final PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, cardUid);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Rfid(
                            rs.getInt("id"),
                            rs.getString("card_uid"),
                            rs.getString("owner_name")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding Rfid by cardUid", e);
        }
        return Optional.empty();
    }

}

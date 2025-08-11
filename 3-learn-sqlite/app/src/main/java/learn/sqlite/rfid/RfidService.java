package learn.sqlite.rfid;

public class RfidService {

    private final RfidRepository repository;

    public RfidService(RfidRepository repository) {
        this.repository = repository;
    }

    public Rfid save(String cardUid, String ownerName) {
        validate(cardUid);
        return repository.insert(new Rfid(-1, cardUid, ownerName));
    }

    public Rfid findByCardUid(String cardUid) {
        return repository.findByCardUid(cardUid)
                .orElseThrow(() -> new IllegalArgumentException("Not found: RFID cardUid " + cardUid));
    }

    private void validate(String cardUid) {
        if (!cardUid.matches("^[A-Fa-f0-9]{8}$")) {
            throw new IllegalArgumentException("Invalid format: cardUid");
        }
    }
}

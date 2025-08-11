package learn.sqlite.rfid;

public class Rfid {

    private final int id;
    private final String cardUid;
    private final String ownerName;

    public Rfid(int id, String cardUid, String ownerName) {
        this.id = id;
        this.cardUid = cardUid;
        this.ownerName = ownerName;
    }

    public int getId() { return id; }
    public String getCardUid() { return cardUid; }
    public String getOwnerName() { return ownerName; }

}


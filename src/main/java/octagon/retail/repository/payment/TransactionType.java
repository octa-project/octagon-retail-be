package octagon.retail.repository.payment;

public enum TransactionType {

    card("Карт"),
    cash("Бэлэн"),
    account("Данс"),
    nonCash("Бэлэн Бус");

    private final String displayName;

    TransactionType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}

package org.example.Model;
public enum CustomerType {
    НОВЫЙ("Новый"),
    ПОСТОЯННЫЙ("Постоянный"),
    VIP("VIP");

    private final String displayName;

    CustomerType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static CustomerType fromString(String value) {
        for (CustomerType type : CustomerType.values()) {
            if (type.displayName.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Неверный тип покупателя: " + value);
    }
}
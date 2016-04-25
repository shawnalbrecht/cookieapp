package com.kla.simplecrud;

/**
 * Enum for the sort passed in.
 */
public enum CookieSortColumn {
    ID("id"),

    RECIPE("recipe");

    private String column;

    CookieSortColumn(String column) {
        this.column = column;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public static CookieSortColumn getCookieSortColumn(String column) {
        for (CookieSortColumn c : CookieSortColumn.values()) {
            if (c.getColumn().equals(column)) {
                return c;
            }
        }
        return null;
    }
}

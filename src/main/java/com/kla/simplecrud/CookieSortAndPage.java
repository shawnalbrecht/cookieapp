package com.kla.simplecrud;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class CookieSortAndPage {

    private String currentDirection;

    private CookieSortColumn currentSortColumn;

    private CookieSortColumn cookieSortColumn;

    private int currentPage;

    private int totalPages;

    private boolean pageChanged;

    public CookieSortAndPage() {

    }

    public CookieSortAndPage(String currentDirection, CookieSortColumn currentSortColumn, String direction, CookieSortColumn cookieSortColumn, int currentPage, int totalPages) {
        this.currentDirection = currentDirection;
        this.currentSortColumn = currentSortColumn;
        this.cookieSortColumn = cookieSortColumn;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }

    public String getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(String currentDirection) {
        this.currentDirection = currentDirection;
    }

    public CookieSortColumn getCurrentSortColumn() {
        return currentSortColumn;
    }

    public void setCurrentSortColumn(CookieSortColumn currentSortColumn) {
        this.currentSortColumn = currentSortColumn;
    }

    public CookieSortColumn getCookieSortColumn() {
        return cookieSortColumn;
    }

    public void setCookieSortColumn(CookieSortColumn cookieSortColumn) {
        this.cookieSortColumn = cookieSortColumn;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setCurrentPage(String currentPage) {
        try {
            this.currentPage = Integer.parseInt(currentPage);
        } catch (NumberFormatException nfe) {
            this.currentPage = 1;
        }
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isPageChanged() {
        return pageChanged;
    }

    public void setPageChanged(boolean pageChanged) {
        this.pageChanged = pageChanged;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}

package com.kla.simplecrud.service;

import com.kla.simplecrud.CookieSortAndPage;
import com.kla.simplecrud.model.Cookie;
import com.kla.simplecrud.model.CookieType;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Handles creation, retrieval, deletion, and updating of delicious cookies.
 */
public interface CookieService {

    /**
     * Create a cookie in the cookie database. If no cookie recipe description is supplied
     * "No recipe entered!" will be inserted.
     *
     * @param cookie {@link Cookie}
     * @return {@link Cookie} that was created
     */
    Cookie createCookie(Cookie cookie);

    /**
     * Configures sorting and paging for the cookie search.
     */
    void configureSortingAndPaging(CookieSortAndPage cookieSortAndPage);

    /**
     * Retrieves a list of {@link Cookie}s in page objects which allow for page operations.
     *
     * @param {@link CookieSortAndPage} contains all the sorting and paging goodies
     * @return list of {@link Cookie} in {@link Page} objects that have paging options
     */
    Page<Cookie> retrieveCookies(CookieSortAndPage cookieSortAndPage);

    /**
     * Return just one cookie.
     *
     * @return {@link Cookie}
     */
    Cookie retrieveCookie(Long cookieId);

    /**
     * Retrieve a list of cookie types.
     *
     * @return {@link CookieType}
     */
    List<CookieType> retrieveCookieTypes();

    /**
     * Returns the updated cookie object.
     *
     * @param cookie {@link Cookie}
     * @return true if the update was successful false if error occured
     */
    Cookie updateCookie(Cookie cookie);

    /**
     * Deletes the specified cookie via cookieId.
     *
     * @param cookieId unique cookie code model id the cookie
     */
    void deleteCookie(long cookieId);

}

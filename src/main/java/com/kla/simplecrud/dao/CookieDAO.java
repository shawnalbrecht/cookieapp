package com.kla.simplecrud.dao;

import com.kla.simplecrud.model.Cookie;

import java.util.List;

/**
 * Accesses the postgre database containing cookie info.
 */
public interface CookieDAO {
    /**
     * Create a cookie in the cookie database. If no cookie recipe description is supplied
     * "No recipe entered!" will be inserted.
     *
     * @param cookie {@link Cookie}
     * @return 0 if the cookie failed model create, if any non-zero number is returned that is the value
     */
    long createCookie(Cookie cookie);

    /**
     * Retreives a list of all the {@link Cookie}s.
     *
     * @return List of {@link Cookie}
     */
    List<Cookie> retrieveCookies();

    /**
     * Returns the updated cookie object.
     *
     * @param cookie {@link Cookie}
     * @return true if the update was successful false if error occured
     */
    boolean updateCookie(Cookie cookie);

    /**
     * Deletes the specified cookie via cookieId.
     *
     * @param cookieId unique cookie code model id the cookie
     * @return true if the update was successful false if error occured
     */
    boolean deleteCookie(long cookieId);
}

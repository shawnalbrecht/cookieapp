package com.kla.simplecrud.service;

import com.kla.simplecrud.CookieSortAndPage;
import com.kla.simplecrud.CookieSortColumn;
import com.kla.simplecrud.dao.CookieRepository;
import com.kla.simplecrud.dao.CookieTypeRepository;
import com.kla.simplecrud.model.Cookie;
import com.kla.simplecrud.model.CookieType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A simple implementation of {@link CookieService}.
 */
@Service
public class CookieServiceImpl implements CookieService {

    private static int PAGE_SIZE = 5;

    private static CookieSortColumn DEFAULT_SORT_COLUMN = CookieSortColumn.ID;

    private static String DEFAULT_SORT_DIRECTION = "asc";

    @Autowired
    CookieRepository cookieRepository;

    @Autowired
    CookieTypeRepository cookieTypeRepository;

    @Override
    public Cookie createCookie(Cookie cookie) {
        return this.cookieRepository.save(cookie);
    }

    @Override
    public void configureSortingAndPaging(CookieSortAndPage cookieSortAndPage) {

        if (cookieSortAndPage.getCurrentPage() == 0) {
            cookieSortAndPage.setCurrentPage(1);
        }

        if (cookieSortAndPage.getCurrentSortColumn() == null) {
            cookieSortAndPage.setCurrentSortColumn(DEFAULT_SORT_COLUMN);
        }

        if (StringUtils.isEmpty(cookieSortAndPage.getCurrentDirection())) {
            cookieSortAndPage.setCurrentDirection(DEFAULT_SORT_DIRECTION);
        } else {
            if (!cookieSortAndPage.isPageChanged()) {
                if (cookieSortAndPage.getCurrentSortColumn() == cookieSortAndPage.getCookieSortColumn()) {
                    if ("asc".equalsIgnoreCase(cookieSortAndPage.getCurrentDirection())) {
                        cookieSortAndPage.setCurrentDirection("desc");
                    } else {
                        cookieSortAndPage.setCurrentDirection("asc");
                    }
                } else {
                    cookieSortAndPage.setCurrentDirection("asc");
                }
            }
        }

        if (cookieSortAndPage.getCookieSortColumn() != null) {
            cookieSortAndPage.setCurrentSortColumn(cookieSortAndPage.getCookieSortColumn());
        }

    }

    @Override
    @Transactional
    public Page<Cookie> retrieveCookies(CookieSortAndPage cookieSortAndPage) {
        Sort.Direction finalDirection = "ASC".equalsIgnoreCase(cookieSortAndPage.getCurrentDirection()) ? Sort.Direction.ASC: Sort.Direction.DESC;
        return this.cookieRepository.findAll(
                new PageRequest(cookieSortAndPage.getCurrentPage() - 1, PAGE_SIZE,
                        new Sort(finalDirection,
                                cookieSortAndPage.getCurrentSortColumn().getColumn())));
    }

    @Override
    @Transactional
    public Cookie retrieveCookie(Long cookieId) {
        return this.cookieRepository.findOne(cookieId);
    }

    @Override
    public List<CookieType> retrieveCookieTypes() {
        return this.cookieTypeRepository.findAll();
    }

    @Override
    public Cookie updateCookie(Cookie cookie) {

        Set<CookieType> cookieTypeSet = new HashSet<>();

        for (CookieType cookieType : cookie.getCookieTypes()) {
            cookieTypeSet.add(this.cookieTypeRepository.findCookieByTypeCode(cookieType.getType()));
        }
        cookie.setCookieTypes(cookieTypeSet);

        return this.cookieRepository.save(cookie);
    }

    @Override
    public void deleteCookie(long cookieId) {
        this.cookieRepository.delete(cookieId);
    }
}

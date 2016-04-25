package com.kla.simplecrud.controller;

import com.kla.simplecrud.CookieSortAndPage;
import com.kla.simplecrud.CookieSortColumn;
import com.kla.simplecrud.model.Cookie;
import com.kla.simplecrud.model.CookieType;
import com.kla.simplecrud.service.CookieService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class CookieController {

    @Autowired
    CookieService cookieService;

    /**
     * List out all the recipe recipes.
     *
     * @return {@link ModelAndView}
     */
    @RequestMapping("/")
    public ModelAndView loadCookieHomePage(HttpServletRequest request) {

        String currentPageString = request.getParameter("currentPage");
        String sortColumn = request.getParameter("sortColumn");
        String currentSort = request.getParameter("currentSort");
        String currentDirection = request.getParameter("currentDirection");
        String pageChanged = request.getParameter("pageChanged");

        CookieSortAndPage cookieSortAndPage = new CookieSortAndPage();
        cookieSortAndPage.setCookieSortColumn(CookieSortColumn.getCookieSortColumn(sortColumn));
        cookieSortAndPage.setCurrentSortColumn(CookieSortColumn.getCookieSortColumn(currentSort));
        cookieSortAndPage.setCurrentDirection(currentDirection);
        cookieSortAndPage.setCurrentPage(currentPageString);
        cookieSortAndPage.setPageChanged(Boolean.parseBoolean(pageChanged));

        this.cookieService.configureSortingAndPaging(cookieSortAndPage);

        Page<Cookie> cookieList = this.cookieService.retrieveCookies(cookieSortAndPage);
        List<CookieType> cookieTypeList = this.cookieService.retrieveCookieTypes();

        ModelAndView modelAndView = new ModelAndView("cookieHome");

        cookieSortAndPage.setTotalPages(cookieList.getTotalPages());

        modelAndView.addObject("cookieSortAndPage", cookieSortAndPage);
        modelAndView.addObject("cookieList", cookieList.getContent());
        modelAndView.addObject("cookieTypeList", cookieTypeList);

        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addCookie(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("cookieRecipeRow");

        String cookieCommaDelimtedList = request.getParameter("cookieTypes");
        String cookieRecipe = request.getParameter("cookieRecipe");

        Map<String,String> errors = this.validateCookie(cookieCommaDelimtedList, cookieRecipe);

        if (errors.isEmpty()) {

            List<String> cookieList = Arrays.asList(cookieCommaDelimtedList.split("\\s*,\\s*"));
            Set<CookieType> cookieTypeSet = new HashSet<>();

            for (String cookieType : cookieList) {
                cookieTypeSet.add(new CookieType(cookieType));
            }

            Cookie createdCookie = this.cookieService.createCookie(new Cookie(cookieRecipe, cookieTypeSet));

            //created recipe does not return the recipe type description, with some more hibernate tweaks
            // should be able to avoid this call
            Cookie returnCookie = this.cookieService.retrieveCookie(createdCookie.getId());
            List<CookieType> cookieTypeList = this.cookieService.retrieveCookieTypes();

            modelAndView.addObject("cookieRow", returnCookie);
            modelAndView.addObject("cookieTypeList", cookieTypeList);

        }

        modelAndView.addObject("errors", errors);

        return modelAndView;
    }

    @RequestMapping("/update")
    public ModelAndView updateCookie(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("cookieRecipeRow");

        String cookieId = request.getParameter("cookieId");
        String cookieCommaDelimtedList = request.getParameter("cookieTypes");
        String cookieRecipe = request.getParameter("cookieRecipe");

        Map<String,String> errors = this.validateCookie(cookieCommaDelimtedList, cookieRecipe);

        if (errors.isEmpty()) {
            List<String> cookieList = Arrays.asList(cookieCommaDelimtedList.split("\\s*,\\s*"));
            Set<CookieType> cookieTypeSet = new HashSet<>();

            for (String cookieType : cookieList) {
                cookieTypeSet.add(new CookieType(cookieType));
            }

            Cookie cookieToUpdate = new Cookie(Long.parseLong(cookieId), cookieRecipe, cookieTypeSet);

            this.cookieService.updateCookie(cookieToUpdate);
            List<CookieType> cookieTypeList = this.cookieService.retrieveCookieTypes();

            modelAndView.addObject("cookieRow", cookieToUpdate);
            modelAndView.addObject("cookieTypeList", cookieTypeList);
        }

        modelAndView.addObject("errors", errors);

        return modelAndView;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public void deleteCookie(HttpServletRequest request) {
        this.cookieService.deleteCookie(Long.parseLong(request.getParameter("cookieId")));
    }

    private Map<String,String> validateCookie(String cookieTypes, String recipe) {

        Map<String, String> errors = new HashMap<>();

        if (StringUtils.isEmpty(cookieTypes)) {
            errors.put("multiSelectCookieRow", "Please assign the recipe a type.");
        }
        if (StringUtils.isEmpty(recipe)) {
            errors.put("editCookieRecipe", "Please enter a recipe");
        }

        return errors;
    }

    private void makeManyCookies(){
        Set<CookieType> cookieTypeSet = new HashSet<>();
        cookieTypeSet.add(new CookieType("CCHP"));
        cookieTypeSet.add(new CookieType("XMAS"));

        for (int i = 0; i < 20; i++) {
            String cookieRecipe = "Recipe number " + i;

            Cookie createdCookie = this.cookieService.createCookie(new Cookie(cookieRecipe, cookieTypeSet));
        }
    }
}

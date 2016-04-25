<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tr id="cookieRow${cookieRow.id}">
    <td>
        <span>
            ${cookieRow.id}
        </span>
    </td>
    <td>
        <span class="displayCookieRow">
            <c:forEach var="cookieType" items="${cookieRow.cookieTypes}" varStatus="loop">
                <c:out value="${cookieType.description}"/><c:if test="${!loop.last}">,</c:if>
            </c:forEach>
        </span>
        <span class="editCookieRow multiSelectCookieRow">
            <select multiple="multiple" class="editMultiSelect selectpicker">
                <c:forEach items="${cookieTypeList}" var="cookieTypeList">
                    <c:set var="selectOption" value="false"/>
                    <c:forEach var="cookieType" items="${cookieRow.cookieTypes}">
                        <c:if test="${cookieType.type == cookieTypeList.type}">
                            <c:set var="selectOption" value="true"/>
                        </c:if>
                    </c:forEach>

                    <c:choose>
                        <c:when test="${selectOption}">
                            <option selected="selected"
                                    value="${cookieTypeList.type}">${cookieTypeList.description}</option>
                        </c:when>
                        <c:when test="${!selectOption}">
                            <option value="${cookieTypeList.type}">${cookieTypeList.description}</option>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </select>
            <input type="hidden" name="cookieTypes" class="cookieTypesHidden"/>
        </span>
        <span class="backupEditMulti">
            <select multiple="multiple" class="editMultiSelect">
                <c:forEach items="${cookieTypeList}" var="cookieTypeList">
                    <c:set var="selectOption" value="false"/>
                    <c:forEach var="cookieType" items="${cookieRow.cookieTypes}">
                        <c:if test="${cookieType.type == cookieTypeList.type}">
                            <c:set var="selectOption" value="true"/>
                        </c:if>
                    </c:forEach>

                    <c:choose>
                        <c:when test="${selectOption}">
                            <option selected="selected"
                                    value="${cookieTypeList.type}">${cookieTypeList.description}</option>
                        </c:when>
                        <c:when test="${!selectOption}">
                            <option value="${cookieTypeList.type}">${cookieTypeList.description}</option>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </select>
            <input type="hidden" name="cookieTypes" class="cookieTypesHidden"/>
        </span>
    </td>
    <td>
        <span class="displayCookieRow displayCookieRecipeRow">
            <textarea disabled="disabled">${cookieRow.recipe}</textarea>
        </span>
        <span class="editCookieRow editCookieRecipe">
            <textarea name="cookieRecipe" class="cookieRecipeField" maxlength="1000"></textarea>
        </span>
    </td>
    <td>
        <span class="displayCookieRow">
            <button type="button" class="btn btn-primary editRow">Edit</button>
            <button type="button" class="btn btn-danger deleteRow">Delete</button>
        </span>
        <span class="editCookieRow">
            <button type="button" class="btn btn-success updateRow">Update</button>
            <button type="button" class="btn btn-secondary cancelRow">Cancel</button>
        </span>

        <input type="hidden" name="cookieId" value="${cookieRow.id}" class="cookieIdRow"/>
        <div class="hidden erredList">
            <c:forEach var="error" items="${errors}">
                <span class="error">
                    <input class="key" value="${error.key}" />
                    <input class="value" value="${error.value}" />
                </span>
            </c:forEach>
        </div>
    </td>
</tr>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
    <title>Cookies</title>
    <link href="favicon.ico" rel="shortcut icon">
    <link href="<c:url value="../../resources/css/cookie.css" />" rel="stylesheet">
    <link href="<c:url value="../../resources/core/bootstrap/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="../../resources/core/bootstrap/bootstrap-select.min.css"/>" rel="stylesheet"/>
    <link href="<c:url value="../../resources/core/jquery-ui.min.css"/>" rel="stylesheet"/>
    <script src="<c:url value="../../resources/core/jquery-2.2.3.min.js" />"></script>
    <script src="<c:url value="../../resources/core/bootstrap/bootstrap.min.js" />"></script>
    <script src="<c:url value="../../resources/js/cookie.js" />"></script>
    <script src="<c:url value="../../resources/core/bootstrap/bootstrap-select.min.js" />"></script>
    <script src="<c:url value="../../resources/core/jquery.twbsPagination.min.js" />"></script>
    <script src="<c:url value="../../resources/core/jquery-ui.min.js" />"></script>


</head>
<body>
<form action="/cookie/add">
    <div class="cookieTableContainer">
        <div class="table-responsive">

            <table id="cookieTable" class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th class=".col-md-1">
                        <div class="clickable">
                            <span>Cookie Id</span>
                            <c:if test="${cookieSortAndPage.currentSortColumn.column == 'id'}">

                                <c:if test="${cookieSortAndPage.currentDirection == 'asc'}">
                                    <span class="glyphicon glyphicon-sort-by-attributes"></span>
                                </c:if>
                                <c:if test="${cookieSortAndPage.currentDirection == 'desc'}">
                                    <span class="glyphicon glyphicon-sort-by-attributes-alt"></span>
                                </c:if>
                            </c:if>
                            <input type="hidden" value="id" class="sortColumn"/>
                        </div>
                    </th>
                    <th class=".col-md-2">
                        <div>
                            <span>Cookie Type(s)</span>
                        </div>
                    </th>
                    <th class=".col-md-6">
                        <div class="clickable">
                            <span>Recipe</span>
                            <c:if test="${cookieSortAndPage.currentSortColumn.column == 'recipe'}">
                                <c:if test="${cookieSortAndPage.currentDirection == 'asc'}">
                                    <span class="glyphicon glyphicon-sort-by-alphabet"></span>
                                </c:if>
                                <c:if test="${cookieSortAndPage.currentDirection == 'desc'}">
                                    <span class="glyphicon glyphicon-sort-by-alphabet-alt"></span>
                                </c:if>
                            </c:if>
                            <input type="hidden" value="recipe" class="sortColumn"/>
                        </div>
                    </th>
                    <th class=".col-md-2">
                        <span>Action</span>
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:set var="cookieTypeList" value="${cookieTypeList}" scope="request"/>
                <c:forEach var="cookieRow" items="${cookieList}">
                    <c:set var="cookieRow" value="${cookieRow}" scope="request"/>
                    <jsp:include page="cookieRecipeRow.jsp"/>
                </c:forEach>
                <tr class="newCookieRow">
                    <td>
                        <div>

                        </div>
                    </td>
                    <td>
                        <div class="multiSelectCookieRow">
                            <select multiple="multiple" class="selectpicker">
                                <c:forEach items="${cookieTypeList}" var="cookieType">
                                    <option value="${cookieType.type}">${cookieType.description}</option>
                                </c:forEach>
                            </select>
                            <input type="hidden" name="cookieTypes" class="cookieTypesHidden"/>
                        </div>
                    </td>
                    <td>
                        <span class="editCookieRecipe">
                            <textarea id="createNewCookieRecipeField" maxlength="1000" name="cookieRecipe"
                                  class="cookieRecipeField"></textarea>
                        </span>
                    </td>
                    <td>
                        <button id="createNewCookieButton" type="button" class="btn btn-success createRow">Create
                        </button>
                    </td>

                </tr>
                </tbody>
            </table>

        </div>
    </div>

</form>

<form id="pagingAndSortingForm" action="/cookie/">

    <div class="text-center">
        <ul id="pagination-cookie" class="pagination-sm"></ul>
    </div>
    <input type="hidden" id="totalPages" value="${cookieSortAndPage.totalPages}"/>
    <input type="hidden" name="currentPage" id="currentPage" value="${cookieSortAndPage.currentPage}"/>
    <input type="hidden" name="sortColumn" id="sortColumn" value=""/>
    <input type="hidden" name="currentSort" id="currentSortColumn"
           value="${cookieSortAndPage.currentSortColumn.column}"/>
    <input type="hidden" name="currentDirection" id="currentDirection" value="${cookieSortAndPage.currentDirection}"/>
    <input type="hidden" name="pageChanged" id="pageChanged"/>
    <input type="hidden" id="pageLoaded" value="false"/>
</form>

</body>
</html>
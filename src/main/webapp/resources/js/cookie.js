$(document).ready(function () {
    cookieApp.init();
});

var cookieApp = {

    init: function () {
        var $targetEntirePage = $("html");
        cookieApp.addEditButtonHandler($targetEntirePage);
        cookieApp.addDeleteButtonHandler($targetEntirePage);
        cookieApp.addCancelButtonHandler($targetEntirePage);
        cookieApp.addUpdateButtonHandler($targetEntirePage);
        cookieApp.addCookieCreateHandler();
        cookieApp.addCookieSortHandler();
        cookieApp.initPaging();
    },

    addEditButtonHandler: function (context) {
        context.find(".editRow").on("click", function () {
            var cookieRowId = $(this).parent().parent().find(".cookieIdRow").val();
            cookieApp.editCookieRows(cookieRowId);
        });
    },

    addDeleteButtonHandler: function (context) {
        context.find(".deleteRow").on("click", function () {
            cookieApp.deleteCookieRecipe($(this).parent().parent().find(".cookieIdRow").val())
        });
    },

    addCancelButtonHandler: function (context) {

        context.find(".cancelRow").on("click", function () {

            cookieApp.clearErredFields();

            var cookieRowId = $(this).parent().parent().find(".cookieIdRow").val();
            var $targetCookieRow = $("#cookieRow" + cookieRowId);
            cookieApp.displayCookieRows(cookieRowId);

            //Get rid of tampered with multipicker
            $targetCookieRow.find(".editCookieRow .selectpicker").selectpicker('destroy');
            //Replace html with previous html
            $targetCookieRow.find(".multiSelectCookieRow").html($targetCookieRow.find(".backupEditMulti").html());
            //Initialize new picker by adding class and calling selectpicker
            $targetCookieRow.find(".editCookieRow select").prop('class', 'selectpicker').selectpicker('refresh');
        });
    },

    addUpdateButtonHandler: function (context) {
        context.find(".updateRow").on("click", function () {
            cookieApp.addOrUpdateCookieRecipe($(this).parent().parent().find(".cookieIdRow").val());
        });
    },

    addCookieCreateHandler: function() {
        $("#createNewCookieButton").on("click", function() {
            cookieApp.addOrUpdateCookieRecipe(0);
        });
    },

    addCookieSortHandler: function () {
        $(".clickable").on("click", function () {
           var $pagingAndSortForm = $('#pagingAndSortingForm');
            $pagingAndSortForm.find('#sortColumn').val($(this).find('.sortColumn').val());
            $pagingAndSortForm.submit();
        });
    },

    initPaging: function() {

        $("#pagination-cookie").twbsPagination({
            totalPages: $("#totalPages").val(),
            startPage: $("#currentPage").val(),
            visiblePages: 5,
            onPageClick: function (event, page) {
                $('#currentPage').val(page);
                if ($('#pageLoaded').val() === "true") {
                    $('#pageChanged').val("true");
                    $('#pagingAndSortingForm').submit();
                }

            }
        });
        
        $('#pagination-cookie').find("li.page a").each(function(){
           if ($(this).text() == $("#currentPage").val()) {
               $(this).parent().addClass("active");
           }
        });

        $('#pageLoaded').val("true");
    },

    resetNewCookieFields: function() {
        $('.newCookieRow .selectpicker').selectpicker('deselectAll');
        $("#createNewCookieRecipeField").val("");
    },

    displayCookieRows: function(cookieId) {
        var $cookieRow = $("#cookieRow" + cookieId);
        $cookieRow.find(".displayCookieRow").show();
        $cookieRow.find(".editCookieRow").hide();
    },

    editCookieRows: function(cookieId) {
        var $cookieRow = $("#cookieRow" + cookieId);
        $cookieRow.find(".cookieRecipeField").val($cookieRow.find(".displayCookieRecipeRow textarea").text());
        $cookieRow.find(".displayCookieRow").hide();
        $cookieRow.find(".editCookieRow").show();
        $cookieRow.find('.selectpicker').selectpicker('refresh');
    },

    addOrUpdateCookieRecipe: function(cookieId) {

        cookieApp.clearErredFields();

        var $cookieParent;
        var ajaxURL;
        if (cookieId > 0) {
            $cookieParent = $("#cookieRow" + cookieId);
            ajaxURL = "/cookie/update"
        } else {
            $cookieParent = $(".newCookieRow");
            ajaxURL = $("form").attr("action")
        }

        // Issue with submitting multi-select via form
        $cookieParent.find(".multiSelectCookieRow .cookieTypesHidden").val($cookieParent.find(".multiSelectCookieRow select").val());

        var newCookieRow;

        $.ajax({
            type: "post",
            url: ajaxURL,
            data: $cookieParent.find(".multiSelectCookieRow input.cookieTypesHidden, .cookieRecipeField, .cookieIdRow").serialize(),
            success: function (response) {

                console.log(response);

                if ($(response).find(".erredList .error").length > 0) {

                    $(response).find(".erredList .error").each(function () {
                        var key = $(this).find(".key").val();
                        $cookieParent.find("button").attr("title", "");
                        $cookieParent.find("." + key).parent().addClass("erred").attr("title", $(this).find(".value").val()).tooltip();
                    });
                } else {
                    if (cookieId > 0) {
                        $("#cookieRow" + cookieId).replaceWith(response);
                    } else {
                        $(".newCookieRow").before(response);
                        cookieApp.resetNewCookieFields();
                    }

                    //Just always take the one that is returned. Easier that way
                    var returnedCookieId = $(response).find(".cookieIdRow").val();
                    //Either new one created or we use the same id from the updated one
                    var newCookieRow = $("#cookieRow" + returnedCookieId);

                    cookieApp.addEditButtonHandler(newCookieRow);
                    cookieApp.addDeleteButtonHandler(newCookieRow);
                    cookieApp.addCancelButtonHandler(newCookieRow);
                    cookieApp.addUpdateButtonHandler(newCookieRow);
                }
            }
        });
    },

    deleteCookieRecipe: function (cookieId) {
        $.ajax({
            type: "post",
            url: "/cookie/delete",
            data: {"cookieId" : cookieId},
            success: function() {
                $("#cookieRow" + cookieId).remove();
            }
        })
    },

    clearErredFields: function () {

        console.log("Huh?");

        $(".erred").removeClass("erred");
        $(".tooltip").remove();
    }
}
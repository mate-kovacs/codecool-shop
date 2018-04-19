$(function() {
    addFilterListeners();
});

function addFilterListeners() {
    let button = document.getElementById("filter-button");
    button.addEventListener("click", function (event) {
        let category = document.getElementById("select-category");
        let supplier = document.getElementById("select-supplier");

        $.ajax({
            dataType: "JSON",
            url: '/',
            data: {
                'select_category': category.options[category.selectedIndex].value,
                'select_supplier': supplier.options[supplier.selectedIndex].value,
                'ajax': "ajax"
            },
            // cache: false,
            type: "GET",
            success: function (response) {
                let htmlString = '';
                $.each(response, function(index, value) {
                    htmlString += itemHtmlText(value.id, value.title, value.description, value.price);
                });
                let productList = document.getElementById("products");
                let categoryTitle = document.getElementById("category-title");
                productList.innerHTML = htmlString;
                categoryTitle.innerHTML = category.options[category.selectedIndex].value;
                addEventListenerToButtons();
                changeURL(category.options[category.selectedIndex].value, supplier.options[supplier.selectedIndex].value)
            },
            error: function (xhr) {
                alert('something went wrong');
            }
        });
    })
}

function itemHtmlText(id, title, description, price){

        return "<div class=\"item col-xs-4 col-lg-4\">\n" +
            "            <div class=\"thumbnail\">\n" +
            "                <img class=\"group list-group-image\" src=\"/static/img/product_"+ id +".jpg\" alt=\"\" />\n" +
            "                <div class=\"caption\">\n" +
            "                    <h4 class=\"group inner list-group-item-heading\">"+title+"</h4>\n" +
            "                    <p class=\"group inner list-group-item-text\">"+description+"</p>\n" +
            "                    <div class=\"row\">\n" +
            "                        <div class=\"col-xs-12 col-md-6\">\n" +
            "                            <p class=\"lead\">" + price + "</p>\n" +
            "                        </div>\n" +
            "                        <div class=\"col-xs-12 col-md-6\">\n" +
            "                                <button class=\"btn btn-success addButton\" data-id=\"" + id + "\">\n" +
            "                                    Add to cart\n" +
            "                                </button>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "        </div>";
}

function changeURL(category, supplier) {
    let urlString = "/?select_category=" + category + "&select_supplier=" + supplier;
    window.history.pushState(document.innerHTML, "Codecool Shop", urlString);
}


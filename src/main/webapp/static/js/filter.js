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
                    htmlString += itemHtmlText(value.id, value.title, value.description, value.price, value.supplier);
                });
                addFlyEventListener();
                let productList = document.getElementById("products");
                let categoryTitle = document.getElementById("category-title");
                productList.innerHTML = htmlString;
                addFlyEventListener();
                addEventListenerToButtons();
                changeURL(category.options[category.selectedIndex].value, supplier.options[supplier.selectedIndex].value)
            },
            error: function (xhr) {
                alert('something went wrong');
            }
        });
    });
}


function changeURL(category, supplier) {
    let urlString = "/?select_category=" + category + "&select_supplier=" + supplier;
    window.history.pushState(document.innerHTML, "Codecool Shop", urlString);
}


function itemHtmlText(id, title, description, price, supplier){

    return "<div class=\"lots-productcard\" id=\"product_"+id+"\">\n" +
        "            <div class=\"lots-top_divider\"></div>\n" +
        "            <h4 class=\"lots-supplier_text\">"+supplier+"</h4>\n" +
        "            <h4 class=\"lots-productname_text\">"+title+"</h4>\n" +
        "            <p class=\"lots-productdesc_text\">"+description+"</p>\n" +
        "            <div class=\"lots-bot_divider\"></div>\n" +
        "            <div class=\"lots-productcard_footer\">\n" +
        "                <div class=\"lots-pricewrapper\">\n" +
        "                    <div class=\"lots_price\">"+price+"</div>\n" +
        "                    <div class=\"lots-currency-icon\"></div>\n" +
        "                </div>\n" +
        "            </div>\n" +
        "            <div class=\"lots_add-btn add-to-cart\" data-id=\""+id+"\"></div>\n" +
        "        </div>"
}
function addFilterListeners() {
    var button = document.getElementById("filter-button");
    button.addEventListener("click", function (event) {
        var category = document.getElementById("select-category");
        var supplier = document.getElementById("select-supplier");

        console.log(category.options[category.selectedIndex].value);
        console.log(supplier.options[supplier.selectedIndex].value);

        $.ajax({
            dataType: "text",
            url: '/',
            data: {
                'select_category': category.options[category.selectedIndex].value,
                'select_supplier': supplier.options[supplier.selectedIndex].value,
                'ajax': "ajax"
            },
            cache: false,
            type: "GET",
            success: function (response) {
                console.log(response);
                showFilteredProducts(response);
            },
            error: function (xhr) {
                alert('something went wrong');
            }
        });
    })
}

function showFilteredProducts(products){
    JSON.parse(products);
    console.log(products.elements);
    console.log(products.product0);
    console.log(products.product1);
    htmlText = "";
    for (let i = 0; i < products.elements; i++){
        product = products("product");
        console.log(product);
        /*
        htmlText += "<div class=\"item col-xs-4 col-lg-4\">\n" +
            "            <div class=\"thumbnail\">\n" +
            "                <img class=\"group list-group-image\" src=\"http://placehold.it/400x250/000/fff\" src='/static/img/product_'" + product.id + "'.jpg'\" alt=\"\" />\n" +
            "                <div class=\"caption\">\n" +
            "                    <h4 class=\"group inner list-group-item-heading\"" + product.name + ">Product name</h4>\n" +
            "                    <p class=\"group inner list-group-item-text\"" + product.description + ">Product description... </p>\n" +
            "                    <div class=\"row\">\n" +
            "                        <div class=\"col-xs-12 col-md-6\">\n" +
            "                            <p class=\"lead\"" + product.price + ">100 USD</p>\n" +
            "                        </div>\n" +
            "                        <div class=\"col-xs-12 col-md-6\">\n" +
            "                                <button class=\"btn btn-success addButton\"\n" +
                                                    product.id +
            "                                    Add to cart\n" +
            "                                </button>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "        </div>";
            */
    }


    let productList = document.getElementById("products");
    productList.innerHTML = htmlText;
}

addFilterListeners();
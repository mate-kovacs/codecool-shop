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
                document.innerHTML = response;
            },
            error: function (xhr) {
                alert('something went wrong');
            }
        });
    })
}

addFilterListeners();
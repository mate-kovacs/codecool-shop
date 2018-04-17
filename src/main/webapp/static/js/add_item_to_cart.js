$(function()
{
    let buttons = document.getElementsByClassName('addButton');

    $.each(buttons, function (j, button) {
        let id = button.dataset.id;
        let name = button.dataset.name;
        let price = button.dataset.price;
        button.addEventListener("click", function () {
            $.ajax({
                type: 'POST',
                url: '/',
                data: {'name': name, "id": id, "price": price},
            });
        });
    })

});
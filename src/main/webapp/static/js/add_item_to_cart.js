$(function()
{
    let buttons = document.getElementsByClassName('addButton');

    $.each(buttons, function (j, button) {
        let id = button.dataset.id;
        button.addEventListener("click", function () {
            $.ajax({
                type: 'POST',
                url: '/',
                data: {"id": id},
                success: function (response) {
                    let priceSum = response.priceSum;
                    let numberOfItems = response.numberOfItems;
                    $('#numberOfItems').html(numberOfItems + " items");
                    $('#totalPrice').html(priceSum + " USD")

                }
            });
        });
    })

});
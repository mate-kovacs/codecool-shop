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
            });
        });
    })

});
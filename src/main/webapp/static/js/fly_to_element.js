
function flyToElement(flyer, flyingTo) {
    let $func = $(this);
    let divider = 3;
    let flyerClone = $(flyer).clone();
    $(flyerClone).css({position: 'absolute', top: $(flyer).offset().top + "px",
        left: $(flyer).offset().left + "px", opacity: 1, 'z-index': 1000, transform: "scale(0.5,0.5)"});
    $('body').append($(flyerClone));
    let gotoX = $(flyingTo).offset().left + ($(flyingTo).width() / 2) - ($(flyer).width()/divider)/2;
    let gotoY = $(flyingTo).offset().top + ($(flyingTo).height() / 2) - ($(flyer).height()/divider)/2;

    $(flyerClone).animate({
        opacity: 1,
        left: gotoX,
        top: gotoY,
        // width: $(flyer).width()/divider,
        // height: $(flyer).height()/divider
    }, 1000,
    function () {
        $(flyingTo).fadeOut('fast', function () {
            $(flyingTo).fadeIn('fast', function () {
                $(flyerClone).hide('fast', function () {
                    $(flyerClone).remove();
                });
            });
        });
    });
}

$(document).ready(function () {
    addFlyEventListener();
});


function addFlyEventListener(){
    $('.add-to-cart').on('click', function () {
        let id = this.dataset.id;
        $('html, body').animate({
            'scrolltop' : $(".cart-anchor").position().top
        });
        // let itemImg =  $(this).parent().parent().parent().parent().parent().find('div').eq(0);
        let itemImg = document.getElementById("product_" + id);
        flyToElement(itemImg, $('.cart-anchor'));
    })
}

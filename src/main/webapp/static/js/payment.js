payment = {
    init: function () {
        payment.addEventListenerPaymentButtons();
    },
    
    addEventListenerPaymentButtons: function () {
        let paypal = document.getElementById("pay-button-paypal");
        let creditCard = document.getElementById("pay-button-creditcard");
        paypal.addEventListener('click', function () {
            document.getElementsByClassName("payment-methods")[0].remove();
            document.getElementsByClassName("paypal-form")[0].removeAttribute("hidden");
        });
        creditCard.addEventListener('click', function () {
            document.getElementsByClassName("payment-methods")[0].remove();
            document.getElementsByClassName("cc-form")[0].removeAttribute("hidden");
        })

    }
};

payment.init();
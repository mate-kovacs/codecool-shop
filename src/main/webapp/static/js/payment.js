payment = {
    init: function () {
        payment.addEventListenerPaymentButtons();
        payment.addEventListenerPayButtons();
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
        });
    },

    addEventListenerPayButtons: function () {
        let payingButtons = document.getElementsByClassName("pay-btn");
        for(let payButton of payingButtons) {
            payButton.addEventListener('click', function() {
                document.getElementById("pay-success").removeAttribute("hidden");
            });
        }
    }
};

payment.init();
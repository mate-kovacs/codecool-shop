payment = {
    init: function () {
        payment.addEventListenerPaymentButtons();
        payment.addEventListenerPayButtons();
        payment.addEventListenerCreditCardNumber();
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
        let payingButtons = document.getElementsByClassName("go-to-pay-btn");
        for (let payButton of payingButtons) {
            payButton.addEventListener('click', function () {
                $.ajax({
                    method: "POST",
                    url: "/payment",
                    data: "Success",
                    success: function (response) {
                        document.getElementById("pay-success").removeAttribute("hidden");
                        const timer = ms => new Promise(resolve => setTimeout(resolve, ms));
                        timer(2000).then(() => {
                            document.location.href = "/";
                        })
                    }
                })
            });
        }
    },

    addEventListenerCreditCardNumber: function () {
        let cardNumber = document.getElementById("cc-number");
        cardNumber.addEventListener("input", function (event) {
            payment.formatCreditCardNumber(this, event.data);
        })
    },

    formatCreditCardNumber: function (cardNumber, inputData) {
        let current = cardNumber.value;
        let pattern = "/-*([0-9]{4})/g";

        matches = current.match(/(-*([0-9]{4}))+/g);

        if (matches) {
            for (element of matches) {
                if (element === current) {
                    if (isNaN(parseInt(inputData, 10))) {
                        cardNumber.value = current.substr(0, current.length - 1);
                        break;
                    } else if (current.length < 19) {
                        cardNumber.value = current + "-";
                        break;
                    }
                }
            }
        }
    }
};

payment.init();
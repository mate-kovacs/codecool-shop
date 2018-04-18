checkout = {
    init: function () {
        checkout.addEventListenerToCheckBoxSameAddress();
    },

    addEventListenerToCheckBoxSameAddress: function () {
        let sameAddressCheck = document.getElementById("sameAddressCheck");
        sameAddressCheck.addEventListener('click', function () {
            if (sameAddressCheck.checked) {
                document.getElementById("billAddressForm").style.display = "none";
            } else {
                document.getElementById("billAddressForm").style.display = "block";
            }
        })
    }

};

checkout.init();
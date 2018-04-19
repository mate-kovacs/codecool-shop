checkout = {
    init: function () {
        checkout.addEventListenerToCheckBoxSameAddress();
        checkout.uncheckCheckbox();
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
    },

    uncheckCheckbox: function () {
        let checkbox = document.getElementById("sameAddressCheck");
        checkbox.checked = false;
    }

};

checkout.init();
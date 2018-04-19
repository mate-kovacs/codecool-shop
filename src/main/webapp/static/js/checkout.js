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
                checkout.setRequiredFields();
            } else {
                document.getElementById("billAddressForm").style.display = "block";
                checkout.setRequiredFields();
            }
        })
    },

    setRequiredFields: function () {
        let address = document.getElementById("inputBillAddress");
        let country = document.getElementById("inputBillCountry");
        let city = document.getElementById("inputBillCity");
        let zip = document.getElementById("inputBillZip");
        let fieldList = [address, country, city, zip];
        for (field of fieldList){
            console.log(field);
            if (field.required){
                console.log("required");
                field.removeAttribute("required");
            } else {
                console.log("not required");
                field.setAttribute("required", "required");
            }
        }
    },

    uncheckCheckbox: function () {
        let checkbox = document.getElementById("sameAddressCheck");
        checkbox.checked = false;
    }

};

checkout.init();
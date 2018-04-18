let shoppingCartManager = {
    attachListenersToQuantityButtons: function() {
        let increaseBtns = document.getElementsByClassName("increase_btn");
        for (increaseBtn of increaseBtns) {
            increaseBtn.addEventListener("click", shoppingCartManager.changeQuantity);
        }
        let decreaseBtns = document.getElementsByClassName("decrease_btn");
        for (decreaseBtn of decreaseBtns) {
            decreaseBtn.addEventListener("click", shoppingCartManager.changeQuantity);
        }
    },

    changeQuantity: function() {
        let parentNode = this.parentNode;
        let id = this.dataset.id;
        let process = this.dataset.process;

        $.ajax({
            method: "POST",
            url: "/shoppingcart",
            data: { id: id, process: process},
            success: function(result){
                let quantityField = parentNode.getElementsByClassName("quantity_field")[0];
                if (parseInt(result) > 0) {
                    quantityField.textContent = result;
                } else {
                    document.getElementById("productItem_" + id).remove();
                }
            }
        })
    },

};

shoppingCartManager.attachListenersToQuantityButtons();
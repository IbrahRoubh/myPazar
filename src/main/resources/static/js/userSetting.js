const tabButtons = document.querySelectorAll('.tab');
const tabPanels = document.querySelectorAll('.tabShow');

function tabs(panelIndex) {
    tabPanels.forEach(function (node, index) {
        if (index === panelIndex) {
            node.style.display = "block";
        } else {
            node.style.display = "none";
        }
    });
}

tabs(0);

tabButtons.forEach(function (button) {
    button.addEventListener('click', function () {
        // Remove active class from sibling buttons
        const siblings = Array.from(button.parentNode.children);
        siblings.forEach(function (sibling) {
            if (sibling !== button) {
                sibling.classList.remove('active');
            }
        });

        button.classList.add('active');

        const index = Array.from(siblings).indexOf(button);

        tabs(index);
    });
});
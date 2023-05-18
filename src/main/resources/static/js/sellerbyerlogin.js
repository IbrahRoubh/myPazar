var seller_link = document.querySelector('.a1');
var buyer_link = document.querySelector('.a2');

var seller_form = document.querySelector('.seller-form');
var buyer_form = document.querySelector('.buyer-form');

buyer_link.addEventListener('click', seller_hide);
seller_link.addEventListener('click', buyer_hide);

function seller_hide(){
    seller_form.style.display = "none";
    buyer_form.style.display = "block";
    seller_link.style.backgroundColor = "#8B0000";
    seller_link.style.color = "#fff";
    buyer_link.style.backgroundColor = "#fff";
    buyer_link.style.color = "#8B0000";
}

function buyer_hide(){
    seller_form.style.display = "block";
    buyer_form.style.display = "none";
    seller_link.style.backgroundColor = "#fff";
    seller_link.style.color = "#8B0000";
    buyer_link.style.backgroundColor = "#8B0000";
    buyer_link.style.color = "#fff";
}

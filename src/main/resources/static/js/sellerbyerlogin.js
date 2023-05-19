const logInBuyerButton = document.getElementById('logInBuyer');
const logInSellerButton = document.getElementById('logInSeller');
const container4 = document.getElementById('container4');

logInBuyerButton.addEventListener('click', () => {
  container4.classList.remove("right-panel-active");
});

logInSellerButton.addEventListener('click', () => {
  container4.classList.add("right-panel-active");
});

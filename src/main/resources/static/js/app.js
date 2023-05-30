let searchForm = document.querySelector('.search-form');

document.querySelector('#search-button').onclick = () =>
{
	searchForm.classList.toggle('active');
	cart.classList.remove('active');
	loginForm.classList.remove('active');
	navbar.classList.remove('active');
}

let navbar = document.querySelector('.navbar');

document.querySelector('#Menu-button').onclick = () =>
{
	navbar.classList.toggle('active');
	loginForm.classList.remove('active');
	cart.classList.remove('active');
	searchForm.classList.remove('active');
}



window.onscroll = () =>
{
	searchForm.classList.remove('active');
	cart.classList.remove('active');
	loginForm.classList.remove('active');
	navbar.classList.remove('active');
}


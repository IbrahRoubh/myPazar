document.getElementById('signupForm').addEventListener('submit', function(event) {
  event.preventDefault(); // Prevent form submission

  // Validate the form fields
  var nameInput = document.getElementById('nameInput');
  var emailInput = document.getElementById('emailInput');
  var phoneInput = document.getElementById('phoneInput');
  var locationInput = document.getElementById('locationInput');
  var passwordInput = document.getElementById('passwordInput');
  var coPasswordInput = document.getElementById('coPasswordInput');

  // Reset border color
  nameInput.style.borderColor = '';
  emailInput.style.borderColor = '';
  phoneInput.style.borderColor = '';
  locationInput.style.borderColor = '';
  passwordInput.style.borderColor = '';
  coPasswordInput.style.borderColor = '';

  // Perform validation
  if (nameInput.value.trim() === '') {
    alert('Please enter your name');
    nameInput.style.borderColor = 'red';
    nameInput.focus();
    return false;
  }

  if (emailInput.value.trim() === '') {
    alert('Please enter your email');
    emailInput.style.borderColor = 'red';
    emailInput.focus();
    return false;
  }

  if (phoneInput.value.trim() === '') {
    alert('Please enter your phone number');
    phoneInput.style.borderColor = 'red';
    phoneInput.focus();
    return false;
  }

  if (locationInput.value.trim() === '') {
    alert('Please enter your location');
    locationInput.style.borderColor = 'red';
    locationInput.focus();
    return false;
  }

  if (passwordInput.value.trim() === '') {
    alert('Please enter a password');
    passwordInput.style.borderColor = 'red';
    passwordInput.focus();
    return false;
  }

  if (coPasswordInput.value.trim() === '') {
    alert('Please confirm your password');
    coPasswordInput.style.borderColor = 'red';
    coPasswordInput.focus();
    return false;
  }

  if (passwordInput.value !== coPasswordInput.value) {
    alert('Passwords do not match');
    password

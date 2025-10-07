// validation.js

function login() {
    const username = document.getElementById("loginUsername").value.trim();
    const password = document.getElementById("loginPassword").value.trim();
  
    if (!username || !password) {
      alert("Please enter both username and password.");
      return;
    }
  
    // console.log("Login Credentials:");
    // console.log("Username:", username);
    // console.log("Password:", password);
    console.log(`Login clicked. Username: ${username}, Password: ${password}`);

  }
  
  function register() {
   

    // Frontend validation for registration form
    const name = document.getElementById('registerName').value.trim();
    const email = document.getElementById('registerEmail').value.trim();
    const username = document.getElementById('registerUsername').value.trim();
    const password = document.getElementById('registerPassword').value.trim();
    
    if(!name || !email ||!username ||!password ){
        alert('All field are required.');
        return;
    }
    // Validate email format
   const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
   if(!emailRegex.test(email)){
    alert('Please enter a valid email address.');
    return;
   }
    
    // Validate username (no special characters)
    const usernameRegex = /^[a-zA-Z0-9]+$/;
    if(!usernameRegex.test(username)){
     alert('Username should not contain special characters.');
     return;
    }
    

    // Validate password (at least 8 characters, one capital letter, and one numeric)
    const passwordRegex = /^(?=.*[A-Z])(?=.*\d).{8,}$/;
    if(!passwordRegex.test(password)){
        alert('Password must be at least 8 characters long, contain one capital letter and one number.');
        return;
    }
    console.log(`Register clicked. Name: ${name}, Email: ${email}, Username: ${username}, Password: ${password}`);
    
}

  module.exports = { login, register };

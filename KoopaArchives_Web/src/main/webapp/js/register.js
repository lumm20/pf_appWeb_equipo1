function nextStep() {
    document.getElementById('step1').classList.remove('active');
    document.getElementById('step2').classList.add('active');
}

function previousStep() {
    document.getElementById('step2').classList.remove('active');
    document.getElementById('step1').classList.add('active');
}

function updateImagePreview(input) {
    const preview = document.getElementById('preview-image');
    const placeholder = document.getElementById('image-placeholder');

    if (input.files && input.files[0]) {
        const reader = new FileReader();

        reader.onload = function (e) {
            preview.src = e.target.result;
            preview.classList.add('active');
            placeholder.style.display = 'none';
        }

        reader.readAsDataURL(input.files[0]);
    } else {
        preview.classList.remove('active');
        placeholder.style.display = 'block';
    }
}

let timeOutId;

function showErrorMessage(elementId,mensaje, mostrar) {
    const errorMsj = document.getElementById(elementId);
    if (errorMsj) {
        errorMsj.textContent = mensaje;
        errorMsj.style.display = mostrar ? 'block' : 'none';
    }
}

function validateCharacters(input){
    const regex = /[<>\"'&]/g;
    return regex.test(input);
}

function validateInput(elementId, msj){
    const element = document.getElementById(elementId).value;
    
    if(element.length > 0){
        if(validateCharacters(element)){
            showErrorMessage('invalid-character-error',msj,true);
        }else
            showErrorMessage('invalid-character-error','',false);
    }
}

function validatePassword(){
    const pass = document.getElementById('password').value;
    const confirmPass = document.getElementById('confirm-password').value;

    if (pass.length > 0) {
        if (confirmPass.length > 0 && pass !== confirmPass) {
            showErrorMessage('unmatched-pass-error','Las contraseñas no coinciden', true);
        } else
            showErrorMessage('unmatched-pass-error','', false);
    }
}

function validateWhileTyping(event){
    clearTimeout(timeOutId);
    
    timeOutId  = setTimeout(() => {
        const elementId = event.target.id;
        
        switch(elementId){
            case "confirm-password":
                validatePassword();
            case "username":
                validateInput('username','Se ingreso un caracter invalido en el username');
            case "nombre":
                validateInput('nombre','Se ingreso un caracter invalido en el nombre');
            case "genero":
                validateInput('genero','Se ingreso un caracter invalido en el genero');
            case "email":
                validateInput('email','Se ingreso un caracter invalido en el email');
            case "apellido-paterno":
                validateInput('apellido-paterno','Se ingreso un caracter invalido en el apellido paterno');
            case "apellido-materno":
                validateInput('apellido-materno','Se ingreso un caracter invalido en el apellido materno');
        }
        
    },300);
}

function validateStep1(){
    alert("en validate step1");
    const username = document.getElementById('username').value;
    const email = document.getElementById('email').value;
    const nombre = document.getElementById('nombre').value;
    const genero = document.getElementById('genero').value;
    const apellidoMaterno = document.getElementById('apellido-materno').value;
    const apellidoPaterno = document.getElementById('apellido-paterno').value;
    
    if (!username || !email || !nombre || !genero || !apellidoMaterno || !apellidoPaterno) {
        alert('ccompleta todos los campos');
        return false;
    }
    
    return true;
}

function validateSubmit(event){
    if (event) {
        event.preventDefault();
    }
    
    if(!validateStep1()){
        return false;
    }
    
    const pass = document.getElementById('password').value;
    const confirmPass = document.getElementById('confirm-password').value;
    let valido = true;
    
    if (pass !== confirmPass) {
        showErrorMessage('Las contraseñas no coinciden', true);
        valido = false;
    } else {
        showErrorMessage('', false);
    }
    
    return valido;
}

function goToLogin(){
    window.location.href = 'login.jsp';
}

document.addEventListener('DOMContentLoaded', function() {
    const passwordInput = document.getElementById('password');
    const confirmPass = document.getElementById('confirm-password');
    const btnRegistrar = document.getElementById('btn-registro');
    const btnLogin = document.getElementById('btn-login');
    const inputUsername = document.getElementById('username');
    const inputName = document.getElementById('nombre');
    const inputGender = document.getElementById('genero');
    const inputEmail = document.getElementById('email');
    const inputLastN = document.getElementById('apellido-paterno');
    const inputLastN2 = document.getElementById('apellido-materno');
    
    if (confirmPass) {
        confirmPass.addEventListener('input', validateWhileTyping);
    }

    if (btnRegistrar) {
        btnRegistrar.addEventListener('onclick', validateSubmit);
    }
    
    if(btnLogin){
        btnLogin.addEventListener('click', goToLogin);
    }
    
    if(inputUsername){
        inputUsername.addEventListener('input',validateWhileTyping);
    }
    
    if(inputName){
        inputName.addEventListener('input',validateWhileTyping);
    }
    if(inputGender){
        inputGender.addEventListener('input',validateWhileTyping);
    }
    if(inputEmail){
        inputEmail.addEventListener('input',validateWhileTyping);
    }
    if(inputLastN){
        inputLastN.addEventListener('input',validateWhileTyping);
    }
    if(inputLastN2){
        inputLastN2.addEventListener('input',validateWhileTyping);
    }
});


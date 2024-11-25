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

function showErrorMessage(mensaje, mostrar) {
    const errorMsj = document.getElementById('unmatched-pass-error');
    if (errorMsj) {
        errorMsj.textContent = mensaje;
        errorMsj.style.display = mostrar ? 'block' : 'none';
    }
}

function validateWhileTyping(){
    clearTimeout(timeOutId);
    
    timeOutId  = setTimeout(() => {
        const pass = document.getElementById('password').value;
        const confirmPass = document.getElementById('confirm-password').value;
        
        if (pass.length > 0) {
            if (confirmPass.length > 0 && pass !== confirmPass) {
                showErrorMessage('Las contraseñas no coinciden',true);
            }else
                showErrorMessage('',false);
        }
    },1000);
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

document.addEventListener('DOMContentLoaded', function() {
    const passwordInput = document.getElementById('password');
    const confirmPass = document.getElementById('confirm-password');
    const btnRegistrar = document.getElementById('btn-registro');

    if (confirmPass) {
        confirmPass.addEventListener('input', validateWhileTyping);
    }

    if (btnRegistrar) {
        btnRegistrar.addEventListener('onclick', validateSubmit);
    }
});


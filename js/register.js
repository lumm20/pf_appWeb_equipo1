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
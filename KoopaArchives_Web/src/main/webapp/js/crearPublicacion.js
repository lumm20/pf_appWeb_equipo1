document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('upload-form');
    const btnPost = document.getElementById('post-btn');
    const imageUpload = document.getElementById('image-upload');
    const imagePreview = document.getElementById('image-preview');

    imageUpload.addEventListener('change', (e) => {
        const file = e.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = (e) => {
                imagePreview.innerHTML = `<img src="${e.target.result}" alt="Vista previa">`;
            };
            reader.readAsDataURL(file);
        } else {
            imagePreview.innerHTML = '<span>Vista previa de la imagen</span>';
        }
    });
    
});
async function crearPost(){
    const img = document.getElementById('image-upload').files[0];
    const categoria = document.getElementById('category').value;
    const descripcion = document.getElementById('description').value;
    
    const formData = new FormData();
    formData.append('action', 'publicar'); 
    formData.append('category', categoria); 
    formData.append('image-post', img); 
    if(descripcion){
        formData.append('description',descripcion);
    }
    try{
        const response = await fetch('/private/Publicacion', {
            method: 'post',
            body: formData
        });

        if (!response.ok) {
            const error = await response.json();
            throw new Error(error.error || 'Ocurrio un error desconocido');
        }

        const data = await response.json();

        console.log('Exito:', data.mensaje);
        alert('El post se creo con exito');
        const code = data.codigo;
        window.location.href = "/private/Publicacion?post=" + code;
    }catch (error) {
        console.error('Error: ',error);
        alert('Hubo un error al crear el post');
    }
}


document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('upload-form');
    const btnPost = document.getElementById('post-btn');
    const imageUpload = document.getElementById('image-upload');
    const imagePreview = document.getElementById('image-preview');
    const createBtn = document.getElementById('post-btn');
    
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
    
    createBtn.addEventListener('click',crearPost);
    
});
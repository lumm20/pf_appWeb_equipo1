function createLikeHandler() {
    let timeoutId = null;
    let lastLikeState = null;
    
    function sendLike(currentLikeState) {
        if (timeoutId) {
            clearTimeout(timeoutId);
        }

        if (currentLikeState === lastLikeState) {
            return;
        }
        
        timeoutId = setTimeout(() => {
            lastLikeState = currentLikeState;
            const likesCounter = document.getElementById('cant-likes');
            let cantLikes = likesCounter.firstChild.textContent;
            const codigo = document.getElementById('codigoPost').value;
            console.log(codigo);
//            if(currentLikeState){
//                cantLikes= cantLikes+1;
//            }else
//                cantLikes= cantLikes-1;
            console.log("cantidad likes "+cantLikes);
            const params = new URLSearchParams({
                action: 'reaccion',
                likes: cantLikes,
                codigoPost: codigo
            });
            
            fetch('/private/Publicacion',{
               method: 'post',
               headers: {'Content-Type':'application/x-www-form-urlencoded'},
               body: params.toString()
            }).then(response =>{
                console.log('info sent');
                
                if(response.ok){
                    console.log(response);
                }
            }).catch(error => {
                console.error('Error:',error);
            });
            
            console.log("cambio el estado de like");
            return true;
        }, 7000);
    }

    return sendLike;
}

function editPost(){
    const editBtn = document.getElementById('edit-btn');
    
    editBtn.addEventListener('click',function (){
        const saveBtn = document.getElementById('save');
        const inputTxt = document.getElementById('input-text');
        const pTxt = document.getElementById('parrafo');
        
        if(!saveBtn.classList.contains('active')){
            saveBtn.classList.add('active');
        }
        if(!inputTxt.classList.contains('active')){
            inputTxt.classList.add('active');
        }
        if(pTxt.classList.contains('active')){
            pTxt.classList.remove('active');
        }
    });
}

function cancelEdit(){
    const cancelBtn = document.getElementById('cancel-edit');
    
    cancelBtn.addEventListener('click', function () {
        const saveBtn = document.getElementById('save');
        const inputTxt = document.getElementById('input-text');
        const pTxt = document.getElementById('parrafo');
        
        saveBtn.classList.remove('active');
        inputTxt.classList.remove('active');
        pTxt.classList.add('active');
    });
}

function sendChanges(){
    const saveBtn = document.getElementById('save-btn');
    
    saveBtn.addEventListener('click',function (){
        
        if (!saveBtn.classList.contains('disabled')) {
            const code = document.getElementById('codigoPost').value;
            const newText = document.getElementById('input-text').value;
            
            const params = new URLSearchParams({
                action: 'editarPost',
                contenido: newText,
                codigoPost: code
            });

            fetch('/private/Publicacion', {
                method: 'post',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                body: params.toString()
            }).then(async (response) => {
                if (!response.ok) {
                    const error = await response.json();
                    alert('Hubo un error al editar el post');
                    throw new Error(error.error || 'Ocurrio un error desconocido');
                }

                const data = await response.json();
                console.log('Exito:', data.mensaje);
                alert('El post se edito con exito');
                window.location.href = "./Publicacion?post="+code;
            }).catch(error => {
                console.error('Error:', error);
            });
        }
        
    });
}

function savePost(){
    const saveBtn = document.getElementById('save-btn');
    const txtArea = document.getElementById('input-text');
    let txtActual =document.getElementById('input-text').value;
    
    txtArea.addEventListener('input',function (){
        const txtNuevo =document.getElementById('input-text').value;
        if(txtActual === txtNuevo){
            if(!saveBtn.classList.contains('disabled')){
                saveBtn.classList.add('disabled');
            }
        }else{
            if (saveBtn.classList.contains('disabled')) {
                saveBtn.classList.remove('disabled');
            }
        }
    });
}

function deletePost(){
    const deleteBtn = document.getElementById('delete-btn');
    
    deleteBtn.addEventListener('click',function (){
        
        if (confirm("¿Estás seguro que deseas eliminar el post?")) {
            const codigo = document.getElementById('codigoPost').value;
            
            const params = new URLSearchParams({
                action: 'eliminarPost',
                codigoPost: codigo
            });
            
            fetch('/private/Publicacion', {
                method: 'post',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                body: params.toString()
            }).then(async (response) => {
                if (!response.ok) {
                    const error = await response.json();
                    alert('Hubo un error al eliminar el post');
                    throw new Error(error.error || 'Ocurrio un error desconocido');
                }

                const data = await response.json();
                console.log('Exito:', data.mensaje);
                alert('El post se elimino con exito');
                window.location.href="./Publicacion";
            }).catch(error => {
                console.error('Error:', error);
            });
        }
        
    });
}

document.addEventListener('DOMContentLoaded', function() {
    const iconoComent = document.getElementById('comment-icon');
    const seccionComent = document.getElementById('comments-div');
    const likeIcon = document.getElementById('likeButton');
    const likesCounter = document.getElementById('cant-likes');
    
    let isLiked = false;

    const handleLike = createLikeHandler();

    likeIcon.addEventListener('click', function() {
        let currentLikes = parseInt(likesCounter.firstChild.textContent);
        
        likeIcon.classList.add('like-animation');
        //la animacion solo dura 600 ms
        setTimeout(() => {
            likeIcon.classList.remove('like-animation');
        }, 600);


        const iconBlack = '/imgPrivate/mano.png';  
        const iconPink = '/imgPrivate/mano-rosa.png';

        // alternar likes
        if (!isLiked) {
            currentLikes++;
            likeIcon.src=iconPink;
            isLiked = true;
            handleLike(true);
        } else {
            currentLikes--;
            likeIcon.src=iconBlack;
            isLiked = false;
            handleLike(false);
        }

        // Actualizar el contador
        likesCounter.firstChild.textContent = currentLikes;
    });


    iconoComent.addEventListener('click', function() {
        // Opciones de scroll suave
        seccionComent.scrollIntoView({ 
            behavior: 'smooth',  // Desplazamiento suave
            block: 'start'       // Alinea la parte superior de la sección
        });
    });
    
    deletePost();
    editPost();
    savePost();
    cancelEdit();
    sendChanges();
    
});
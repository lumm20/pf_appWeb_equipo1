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
            if(currentLikeState){
                document.getElementById('like-action').value = 'dislike';
            }else{
                document.getElementById('like-action').value = 'like';
            }
            console.log("cambio el estado de like");
            return true;
        }, 7000); // Espera de 7 segundos antes de enviar
    }

    return sendLike;
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


        const iconBlack = '../img/mano.png';  
        const iconPink = '../img/mano-rosa.png';

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
});
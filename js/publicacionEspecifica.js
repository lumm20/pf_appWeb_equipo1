function createLikeHandler() {
    let timeoutId = null;
    let lastLikeState = null;

    function debouncedLike(currentLikeState) {
        // Cancelar el timeout anterior si existe
        if (timeoutId) {
            clearTimeout(timeoutId);
        }

        // Si el estado es el mismo que el último, no hacer nada
        if (currentLikeState === lastLikeState) {
            return;
        }

        // Establecer un nuevo timeout
        timeoutId = setTimeout(() => {
            // Actualizar el último estado de like
            lastLikeState = currentLikeState;
            console.log("cambio el estado de like");
            return true;
        }, 7000); // Espera de 7 segundos antes de enviar
    }

    return debouncedLike;
}

document.addEventListener('DOMContentLoaded', function() {
    const iconoComent = document.getElementById('comment-icon');
    const seccionComent = document.getElementById('comments-div');
    const likeIcon = document.getElementById('likeButton');
    const likesCounter = document.getElementById('cant-likes');
    
    let isLiked = false;

    const handleLike = createLikeHandler();

    likeIcon.addEventListener('click', function() {
        // Obtener el número actual de likes
        let currentLikes = parseInt(likesCounter.firstChild.textContent);
        
        likeIcon.classList.add('like-animation');

        // Quitar clase de animación después de la animación
        setTimeout(() => {
            likeIcon.classList.remove('like-animation');
        }, 600);


        const iconBlack = '../img/mano.png';  
        const iconPink = '../img/mano-rosa.png';

        // Alternar likes
        if (!isLiked) {
            // Incrementar likes
            currentLikes++;
            likeIcon.src=iconPink;
            isLiked = true;
            handleLike(true);
        } else {
            // Decrementar likes
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
function loadPostDetails(){
    const postCards = document.querySelectorAll(".post-card");
    
    postCards.forEach(card  => {
        card.addEventListener('click', (event) =>{
            console.log('se dio click a un post-card');
            const cardId = event.currentTarget.id;
            window.location.href = '/private/Publicacion?post='+cardId;
            console.log(cardId);
//            const params = new URLSearchParams({
//                idPost: cardId
//            });
//            
//            fetch('/private/Publicacion',{
//               method: 'post',
//               headers: {'Content-Type':'application/x-www-form-urlencoded'},
//               body: params.toString()
//            }).then(response =>{
//                console.log('info sent');
//                console.log(response);
//                if(response.ok){
//                    return response.json();
//                }
//            }).then(data =>{
//                console.log(data);
//            }).catch(error => {
//                console.error('Error:',error);
//            });
        });
    });
}

document.addEventListener('DOMContentLoaded', () => {
    const posts = document.querySelectorAll('.post-card');
    const categoryButtons = document.querySelectorAll('.category-button');

    loadPostDetails();

    categoryButtons.forEach(button => {
        button.addEventListener('click', (event) => {
            const actualActive = document.querySelector('.active').id;
            const eventBtn = event.target.id;
            const category = button.getAttribute('data-categoria');
            console.log(category);
            
            if(actualActive === eventBtn){
                console.log('same');
                return;
            }
            
            posts.forEach(post => {
                const categoriaPost = post.getAttribute('data-categoria');
                console.log(categoriaPost);
                if (category === 'todos' || category === categoriaPost) {
                    post.style.display = 'block';
                } else {
                    post.style.display = 'none';
                }
            });
            const isActive = Array.from(categoryButtons).some(item => item.classList.contains('active'));
            console.log(isActive);
            categoryButtons.forEach(b =>{b.classList.remove('active');});
            button.classList.add('active');
            
        });
    });
});

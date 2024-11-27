document.addEventListener('DOMContentLoaded', () => {
    const posts = document.querySelectorAll('.post-card');
    const categoryButtons = document.querySelectorAll('.category-button');

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
    posts.forEach(post => {
        post.addEventListener('click', (event) => {
            // Aquí puedes agregar la lógica que se ejecutará al hacer clic
            console.log(`Hiciste clic en un post con id: ${post.id}`);
        });
    });
});

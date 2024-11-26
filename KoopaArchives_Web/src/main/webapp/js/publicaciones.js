document.addEventListener('DOMContentLoaded', () => {
    const posts = document.querySelectorAll('.post-card');

    posts.forEach(post => {
        post.addEventListener('click', (event) => {
            // Aquí puedes agregar la lógica que se ejecutará al hacer clic
            console.log(`Hiciste clic en un post con id: ${post.id}`);
        });
    });
});

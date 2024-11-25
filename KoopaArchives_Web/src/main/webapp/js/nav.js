document.addEventListener('DOMContentLoaded', () => {
    const toggle = document.querySelector('.toggle');
    const items = document.querySelectorAll('.item');
    
    toggle.addEventListener('click', (event) => {
        event.preventDefault();

        const isActive = Array.from(items).some(item => item.classList.contains('active'));

        if (isActive) {
            items.forEach(item => item.classList.remove('active'));
            toggle.querySelector('a').innerHTML = '<i class="fas fa-bars"></i>';
        } else {
            items.forEach(item => item.classList.add('active'));
            toggle.querySelector('a').innerHTML = '<i class="fas fa-times"></i>';
        }
    });
    
    const profileButton = document.getElementById('profileButton');
    const profileDropdown = document.getElementById('profileDropdown');
    const profileItem = profileButton.parentElement;

    profileButton.addEventListener('click', (event) => {
        event.preventDefault(); // Evita que recargue la página
        profileItem.classList.toggle('active'); // Alterna la clase 'active'
    });

    // Cierra el menú si se hace clic fuera de él
    document.addEventListener('click', (event) => {
        if (!profileItem.contains(event.target)) {
            profileItem.classList.remove('active');
        }
    });
});



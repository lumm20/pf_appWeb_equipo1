document.addEventListener('DOMContentLoaded', () => {
    const toggle = document.querySelector('.toggle');
    const dropDown = document.querySelector('.profileDropdown');
    const items = document.querySelectorAll('.item');
    
    dropDown.addEventListener('click',() => {
        if(dropDown.classList.contains('active')){
            dropDown.classList.remove('active');
        }
    });
    
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
    
});



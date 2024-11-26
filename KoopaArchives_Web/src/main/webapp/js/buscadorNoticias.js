document.getElementById('newsSearchForm').addEventListener('submit', function (e) {
    e.preventDefault();
    const resultsContainer = document.getElementById('resultsContainer');
    const resultCount = document.getElementById('resultCount');

    // Simulamos resultados de búsqueda
    const results = [
        {
            title: "El sorpresivo último baile de Hyrule en la Nintendo Switch...",
            description: "The Legend of Zelda: Echoes of Wisdom es el más reciente título de la saga más legendaria de Nintendo...",
            category: "The Legend of Zelda",
            image: "../img/noti_ejemplo.png"
        },
        {
            title: "El sorpresivo último baile de Hyrule en la Nintendo Switch...",
            description: "The Legend of Zelda: Echoes of Wisdom es el más reciente título de la saga más legendaria de Nintendo...",
            category: "The Legend of Zelda",
            image: "../img/noti_ejemplo.png"
        }
    ];

    resultsContainer.innerHTML = '';
    if (results.length > 0) {
        resultCount.textContent = results.length;
        results.forEach(result => {
            const card = document.createElement('article');
            card.className = 'result-card';
            card.innerHTML = `
                        <img src="${result.image}" alt="${result.title}">
                        <div class="result-content">
                            <h3>${result.title}</h3>
                            <p>${result.description}</p>
                            <span class="result-category">${result.category}</span>
                        </div>
                    `;
            resultsContainer.appendChild(card);
        });
    } else {
        resultCount.textContent = '0';
        resultsContainer.innerHTML = '<div class="no-results"><p>No hubo resultados :(</p></div>';
    }
});

// Simulamos una búsqueda sin resultados al cargar la página
window.addEventListener('load', function () {
    const resultsContainer = document.getElementById('resultsContainer');
    const resultCount = document.getElementById('resultCount');
    resultCount.textContent = '0';
    resultsContainer.innerHTML = '<div class="no-results"><p>No hubo resultados :(</p></div>';
});
'use strict'
const footer = document.getElementsByClassName('footer')[0];
let counter = 0;
footer.addEventListener(('click'), () => {
    const footerP = footer.getElementsByTagName('p')[0];
    footerP.textContent = 'Prueba javascripst';
    // console.log(footerP)
    counter++;
    const newParagraph = document.createElement('p');
    newParagraph.textContent = `Nuevo párrafo ${counter}`;
    footer.appendChild(newParagraph);
    ;
});
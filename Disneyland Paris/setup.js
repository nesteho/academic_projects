"use strict";

function setUp() { 

    let landsContainer = document.getElementById("landsImages");
    //array  : modification entre tps pas prise en compte
    let landsImages = Array.from(landsContainer.children);
    let fragment = document.createDocumentFragment();

    for (let land of landsImages) {

      let landDiv = Object.assign(document.createElement('div'), {className: 'land'});
      let description = Object.assign(document.createElement('div'),{className: 'description hide'});
      landDiv.appendChild(land);
      landDiv.appendChild(description);
      fragment.appendChild(landDiv);
    }
    landsContainer.innerHTML = "";
    landsContainer.appendChild(fragment); //seule insertion ds DOM

    landsImages = landsContainer.children;
    for (let i = 1; i < landsImages.length; i++) {
        landsImages[i].classList.add("hide");        
    }
}

document.addEventListener("DOMContentLoaded", function (){
    setUp();
});
"use strict";

fetch("https://git.esi-bru.be/api/v4/projects/43728/repository/files/lands.json/raw")
    .then(response => response.json())
    .then(data => displayLandInfo(data));


function displayLandInfo(data) {

    const message = "Propose les attractions suivantes : ";
    const lands = data.lands;
    let landsImages = document.getElementById("landsImages").children;
    for (let i = 0; i < landsImages.length; i++) {

        let fragment = document.createDocumentFragment();

        let name = document.createElement('h2');
        name.textContent = lands[i].nom;

        let description = document.createElement('p');
        description.textContent = lands[i].desc;

        let presenteAttraction = document.createElement('p');
        presenteAttraction.textContent = message;

        let attractionsUl = document.createElement('ul');
        for (let attraction of lands[i].attractions) {

            let attractionLi = document.createElement('li');
            attractionLi.textContent = attraction;
            attractionsUl.appendChild(attractionLi);
        }

        fragment.appendChild(name);
        fragment.appendChild(description);
        fragment.appendChild(presenteAttraction);
        fragment.appendChild(attractionsUl);

        let land = landsImages[i];
        let descriptionDiv = land.querySelector('.description');
        if (descriptionDiv) {
            descriptionDiv.appendChild(fragment); 
        }
    }
}
function showInfo() {
    landsImages = document.getElementById("landsImages").children;
    for (let land of landsImages) {
        if (!land.classList.contains("hide")) {

            let description = land.querySelector('.description');
            if (description) {
                description.classList.toggle('hide');
            }
            break;
        }
    }
}
function showLand(sens) { // true next |  false previous
    let landsImages = Array.from(document.getElementById("landsImages").children);

    const curIndex = landsImages.findIndex(land => !land.classList.contains("hide")); //pas boucle :)
    if (curIndex == -1) return;

    landsImages[curIndex].classList.add("hide");
    const length = landsImages.length;

    let nextIndex;
    if (sens) {
        nextIndex = ( (curIndex + 1) % length)% length;  
    } else {
        nextIndex = (curIndex-1 + length)% length;
    }
    landsImages[nextIndex].classList.remove("hide");
}

document.addEventListener("DOMContentLoaded", function () { // fonction anonyme car passage parametre
    document.getElementById("nextButton").addEventListener("click", () => showLand(true));
    document.getElementById("previousButton").addEventListener("click", () => showLand(false));
    document.getElementById("moreInfoButton").addEventListener("click", showInfo);
})



//$('span:not([class])')   to test jquery
"use strict";

fetch('https://git.esi-bru.be/api/v4/projects/40922/repository/files/recipes-it3.json/raw')
    .then(response => response.json())
    .then(loadCards)
    .catch(alert);


/**
 * @type {Recipe}
 */
let cardForCalendar;

/**
 * Create all cards of from given data
 * @param {RecipesAndRelatedData} data 
 */
function loadCards(data) {
    
    for (const recipe of data.recipes) {

        const template = /** @type{HTMLTemplateElement} */ (document.getElementById("card"));
        let clone = /** @type{HTMLDivElement} */ (template.content.cloneNode(true));
        document.getElementById("cards-container")?.append(clone);

        let card = document.getElementById("cards-container")?.lastElementChild;  // dernière fiche créé
       

        card?.addEventListener("click", function(){

            removeSelectedClass();
            // @ts-ignore
            return addSelectedClass(card);

        } ); 
       
         // @ts-ignore
        addScheduledClass(card, recipe); // this function also calls addToCalendar(card)
       
   
        // @ts-ignore
        card.getElementsByTagName("h2")[0].textContent = recipe.recipeName;
        // @ts-ignore
        card.getElementsByTagName("img")[0].setAttribute("src", recipe.imageLink);

        let set = new Set();

        for (const ing of recipe.ingredients) {

            const element = data.ingredientsData[ing.ingredientName];

            for (let i = 0; i < element.intakes.length; i++) {

                set.add(element.intakes[i])
            }

        }
        //ici 

        set.forEach(intake => {
            addIntakes(intake,data.intakes);
        });
        

        const afficheR = card?.getElementsByClassName("afficheRecette")[0];

        afficheR?.addEventListener("click", function () {

            fillRecipe(recipe, data.units, data);
            showRecipeAction();

        })
    }
}

/**
 * Translate to French all ingredients of from given recipe
 * @param {RecipesAndRelatedData} data 
 * @param {String} ingredientName 
 * 
 */
function translateIngName(data, ingredientName) {
   
    return  data.ingredientsData[ingredientName].fr
}

/**
 * Translate to French one intake
 * @param {Object<string, string>} intakes
 * @param {String} intakeName 
 */
function translateIntakeName(intakes, intakeName) {
    
    return intakes[intakeName];
}


/**
 * Add intakes's recipe to the card
 * @param {String} intake 
 * @param {Object<string, string>} intakes
 */
function addIntakes(intake, intakes) {

    let card = document.getElementById("cards-container")?.lastElementChild;  // dernière fiche créé
    let tag = document.createElement("span");
    let classToAdd;

    if (intake == "proteins")  classToAdd = "Protéines";
    else if (intake == "vegetables")  classToAdd = "Légumes";
    else classToAdd = "Féculents";

    tag.classList.add(classToAdd);
    tag.textContent = translateIntakeName(intakes, intake).toUpperCase();
    card?.getElementsByClassName("fiche-footer")[0]?.append(tag);

}

/**
 * Fill a given recipe 
 * @param {Recipe} recipe 
 * @param {Object<string, string>} units 
 * @param {RecipesAndRelatedData} data 
 */
function fillRecipe(recipe, units, data) {
    
    console.log(data);

    const recipeContainer = document.getElementById("recipe-container");
    // @ts-ignore
    const recipeTitle = recipeContainer.getElementsByTagName("h1")[0];
    recipeTitle.textContent = recipe.recipeName;

    const recipeImg = document.getElementById("recipeImg");
    recipeImg?.setAttribute("src", recipe.imageLink);

    for (const ing of recipe.ingredients) {
        let ingName = translateIngName(data, ing.ingredientName);
        //afficher ds liste course et recette
        

        let quantity;
        let unit;

        // @ts-ignore
        if (ing.quantity) {
            // @ts-ignore
            quantity = ing.quantity;

        }
        // @ts-ignore
        if (ing.unit) {

            // @ts-ignore
            switch (ing.unit) {

                case "cac": unit = units.cac;
                    break;

                case "g": unit = units.g;
                    break;
                case "l": unit = units.l;
                    break;
                case "cl": unit = units.cl;
                    break;
                case "cas": unit = units.cas;
                    break;
                case "box": unit = units.box;
                    break;
                case "gousse": unit = units.gousse;
                    break;
                case "leaf": unit = units.leaf;
                    break;
                case "pot": unit = units.pot;
                    break;
                case "pack": unit = units.pack;
                    break;
                case "cube": unit = units.cube;
                    break;
                case "sprig": unit = units.sprig;
                    break;
                case "pinch": unit = units.pinch;
                    break;
                default: unit = units.piece;

            }
        }

        const ul = recipeContainer?.getElementsByTagName("ul")[0];
        const li = document.createElement("li");

        // @ts-ignore
        if (ing.quantity && ing.unit)  li.textContent += quantity + " " + unit + " " + ingName; 
        else  li.textContent = ingName;
        ul?.append(li);

    }

    for (const step of recipe.steps) {

        const ol = recipeContainer?.getElementsByTagName("ol")[0];
        const li = document.createElement("li");
        li.textContent = step;
        ol?.append(li);
    }

    // on veut ajouter les apport en fct des ing de la recett e: /ingredientData/intakes
   
}
/**
 *  add selected class to a given card
 * @param {HTMLElement} card 
 */
function addSelectedClass(card){

    card.classList.add("selected")
    
}
/**
 *  remove selected class from all cards that have it
 */
function removeSelectedClass(){

    let selectdCards = document.getElementsByClassName("selected"); 

    for (const card of selectdCards) {

        card.classList?.remove("selected");
    }
}
/**
 *  add selected class to a given card
 * @param {HTMLElement} card 
 * @param {Recipe} recipe
 */

function addScheduledClass(card, recipe){

    let ajoutP = card.getElementsByClassName("ajoutP")[0];
   
    ajoutP?.addEventListener("click", function(){

       if(card.classList.contains("selected")){

           card.classList.add("scheduled");

           //Dès l'ajout de la classe "scheduled", on passe le nom de la recette 
           // à ajouter au calendrier
           cardForCalendar= recipe;
           addPlanning(cardForCalendar.recipeName);
       } 
    } );
    
}


// faire en 2 etapes : 1) sauver la fiche schedulded ds une var
//                    2)  quand on click sur un jour , ajouter cette fiche à ce jour 

/**
 *  add scheduled card to the planning at the clicked day
 * @param {String} recipeName
 */
function addPlanning(recipeName){

    let days = document.getElementsByClassName("Jour");

    for (const day of days) {
        day?.addEventListener("click", function(){
            let ul = day.lastElementChild;
            // @ts-ignore
            if(!contains(recipeName,ul)){ 
                const li = document.createElement("li");
                li.textContent= recipeName;
                ul?.append(li);
            }            
        } )
    };
        
}
/**
 * Checks if the recipe is already in the schedule for the clicked day.
 * @param {String} recipeName - Name of the recipe to search for.
 * @param {HTMLUListElement} meals - List of meals for the day.
 * @returns {boolean} - True if the recipe is not in the schedule, otherwise False.
 */

function contains(recipeName, meals){

    const mealsTab = Array.from(meals.children);
    for (const m of mealsTab) {
        if(m.textContent == recipeName){
            return true;
        }
    }
    return false;
}

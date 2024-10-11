"use strict";

/**
 * Show the recipe
 */
function showRecipeAction() {
    document.getElementById("recipe-container")?.classList.remove("hide");
    document.title = "Recette";

    document.getElementById("planning-container")?.classList.add("hide");
    document.getElementById("list-container")?.classList.add("hide");


}
/**
 * Show the planning
 */
function showPlanningAction() {
    document.getElementById("planning-container")?.classList.remove("hide");
    document.title = "Planning";


    document.getElementById("list-container")?.classList.add("hide");
    document.getElementById("recipe-container")?.classList.add("hide");

}
/**
 * Show the shopping list
 */
function showShoppingListAction() {
    document.getElementById("list-container")?.classList.remove("hide");
    document.title = "Liste de courses";

    document.getElementById("planning-container")?.classList.add("hide");
    document.getElementById("recipe-container")?.classList.add("hide");

}

document.addEventListener("DOMContentLoaded", function () {

    const pagePrécédente = document.getElementsByClassName("pagePrécédente");

    for (const button of pagePrécédente) {

        button.addEventListener("click", showPlanningAction);
    }
    const listeCourses= document.getElementById("listeCourses");
    // @ts-ignore
    listeCourses.addEventListener("click", showShoppingListAction)
})




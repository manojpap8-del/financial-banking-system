/*==========================================
        KUBERX MAIN JAVASCRIPT
==========================================*/

"use strict";

document.addEventListener("DOMContentLoaded", function () { // first loading html than load js

    console.log("KuberX JavaScript Loaded Successfully - main.js:9");

    // Sticky Navbar

    const navbar = document.getElementById("navbar");

    window.addEventListener("scroll", function () {

        if (window.scrollY > 80) {

            navbar.classList.add("scrolled");

        } else {

            navbar.classList.remove("scrolled");

        }

    });

});
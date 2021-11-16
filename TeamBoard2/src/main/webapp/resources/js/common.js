
$(function() {
    $(".show-bar").hide();
    $("nav").mouseover(function () {
        $(".show-bar").show();

    });
    $("nav").mouseout(function () {
        $(".show-bar").hide();
    });

    $(".box").mouseover(function () {
        $(".nav_over").after("<div class='border_bottom'></div>")
        $(this).children(".border_bottom").append("<div class='border_bottom_each'></div>")
    });

    $(".box").mouseout(function () {
        $("div").remove(".border_bottom");
    });
});


const nav = document.querySelector('#main'); const navTop = nav.offsetTop; function fixNav() { if (window.scrollY >= navTop) { document.body.classList.add('fixed-nav'); } else { document.body.classList.remove('fixed-nav'); } } window.addEventListener('scroll', fixNav);
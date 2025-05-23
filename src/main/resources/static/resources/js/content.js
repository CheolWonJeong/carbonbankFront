$(function () {

    // faq
    //첫번째 열기 $('.faq-content > li:eq(0) a').addClass('active').next().slideDown();
    $(".faq-content a").click(function () {
        var dropDown = $(this).closest("li").find("div");
        $(this).closest(".faq-content").find("div").not(dropDown).slideUp(); //열고닫기 기능해제
        if ($(this).hasClass("active")) {
          $(this).removeClass("active");
        } else {
          $(this).closest(".faq-content").find("a.active").removeClass("active");
          $(this).addClass("active");
        }
        dropDown.stop(false, true).slideToggle();
      });


    // 슬라이드이미지
    var swiper = new Swiper(".mySwiper", {
        speed: 1000,
        loop: true,
        autoplay: {
        delay: 5000,
        disableOnInteraction: false, // 사용자 상호작용 후에도 autoplay 유지
        },
        navigation: {
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev",
        },
        pagination: {
        el: ".swiper-pagination",
        },
        mousewheel: true,
        keyboard: true,
    });

});


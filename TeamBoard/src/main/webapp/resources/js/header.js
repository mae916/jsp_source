
$(function() {
 

    $(".box").mouseover(function () {
      const idx = $(this).index();
      const boxW = $(this).width();
      const offsetL = (boxW * (idx - 1));
        $(".inner-bar").addClass('on').stop().animate({'width':boxW, 'left': offsetL}, 300);
        //transition 효과추가
      });

    $(".box").mouseout(function () {
        $(".inner-bar").removeClass("on");
    });

    $(".show-bar").hide();
    $("nav").mouseover(function () {
        $(".show-bar").show();
        // $(".show-bar").addClass( 'z-index_top' );

    });
    $(".box").mouseout(function () {
      $(".show-bar").hide();
    });

        const navOffset = $( 'nav' ).offset();
        $( window ).scroll( function() {
          if ( $( window ).scrollTop() > navOffset.top ) {
            $( 'nav' ).addClass( 'fixed-nav' );
          }
          else {
            $( 'nav' ).removeClass( 'fixed-nav' );
          }
        });
        
});
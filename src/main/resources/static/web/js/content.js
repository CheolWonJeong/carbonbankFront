
$(function () {
    // fade
    new WOW().init();

	// 탭
    $('.tab-content > div').hide();
	$('.tab-content > div:first-of-type').show();
	$('.tab-list a').click(function(f){
		f.preventDefault();
			var $this = $(this),
					tabgroup = '#'+$this.parents('.tab-list').data('tabgroup'),
					others = $this.closest('li').siblings().children('a'),
					target = $this.attr('href');
			others.removeClass('active');
			$this.addClass('active');
			$(tabgroup).children('div').hide();
			$(target).show();
	});


});





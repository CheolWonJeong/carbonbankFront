$(function () {


	$('#befBackBtn').on('click',function(){
	    location.href = document.referrer;
	});
	
	$('#ThisClose').on('click',function(){
	    window.close();
	});

});


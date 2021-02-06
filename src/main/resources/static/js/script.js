$('#form').submit( function(ev){
	ev.preventDefault();
	$('form').fadeOut(500);
	$('.wrapper').addClass('form-success');
	//later you decide you want to submit
	setTimeout(() => {$(this).unbind('submit').submit()},500)


});
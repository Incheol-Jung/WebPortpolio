var currentScreen;
var currentTxt;

var AppShowcase = (function() {
	
	var $el = $( '#ac-wrapper' ),
		// device element
		$device = $el.find( '.ac-device' ),
		// the device image wrapper
		$trigger = $device.children( 'a:first' ),
		// the screens
		$screens = $el.find( '.ac-grid > a' ),
		// the device screen image
		$screenImg = $device.find( 'img' ),
		// the device screen title
		$screenTitle = $device.find( '.ac-title' ),
		// HTML Body element
		$body = $( 'body' );

	function init() {
		// show grid
		//$trigger.on( 'click', showGrid );
		// when a grid´s screen is clicked, show the respective image on the device
		$screens.on( 'click', function() {
			showScreen( $( this ) );
			return false;
		} );

		$('.ac-grid:first > a').click();
	}

	function showGrid() {
		$el.addClass( 'ac-gridview' );
		// clicking somewhere else on the page closes the grid view
		$body.off( 'click' ).on( 'click', function() { showScreen(); } );
		return false;
	}

	function showScreen( $screen ) {
		$el.removeClass( 'ac-gridview' );
		if( $screen ) {
			// update image and title on the device
			currentScreen = $screen.find( 'img' );
			currentTxt = $screen.find( 'span' );
			$screenImg.attr( 'src', $screen.find( 'img' ).attr( 'src' ) );
			$screenTitle.val( $screen.find( 'span' ).text() );
			$('.fileupload_img').val(null);
		}
	}

	$('.fileupload_img').on('change', function () {
    	readURL($(this), this);
	});

	$('.fileupload_txt').on('change', function () {
    	currentTxt.text($(this).val());
	});

	function readURL(current, input) {
	    if (input.files && input.files[0]) {
	        var reader = new FileReader();
	        reader.onload = function (e) {
	            //current.closest('.ac-device').find('.item_image').attr('src', e.target.result);
	            $screenImg.attr( 'src', e.target.result );
	            currentScreen.attr( 'src', e.target.result );
	        }

	        reader.readAsDataURL(input.files[0]);
	    }
	}

	return { init : init };

})();
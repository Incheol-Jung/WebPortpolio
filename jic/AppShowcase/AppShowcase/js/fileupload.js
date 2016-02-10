
/*$(function(){

$('.fileupload_img').on('change', function () {
    readURL($(this), this);
});

function readURL(current, input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            current.closest('.ac-device').find('.item_image').attr('src', e.target.result);
            /*var currentID = current.closest('.item_container').attr("name");
            var nextID = parseInt(currentID) + 1;
            $("[id$=hfImageUrl_" + currentID + "]").val(input.files[0].name);
            $("[id$=hfUploadFlag_" + currentID + "]").val(1);
            $(".item_container[name='" + nextID + "']").find('.delete_image').css('display', 'inline-block');
            $(".item_container[name='" + nextID + "']").find('.slt_sort').removeAttr("disabled");
            $(".item_container[name='" + nextID + "']").find('.fileupload_img').removeAttr("disabled");
            SetFilelist();
        }

        reader.readAsDataURL(input.files[0]);
    }
}

});

*/
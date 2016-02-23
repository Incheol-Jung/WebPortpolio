$(function(){
    $('.btnSave').click(function(){
        var i = 0;
        var arrSrc = [];
        var arrDes = [];
        $('.page').each(function(){
            arrSrc[i] = $(this).find('.imgapp').attr('src') == "images/upload.jpg" ? null : $(this).find('.imgapp').attr('src');
            if($(this).find('.desapp').text()) arrDes[i] = $(this).find('.desapp').text();
            else arrDes[i] = null;
            i++;
        })
        for(var j=0; j<arrSrc.length; j++){
            alert("description = "+arrDes[j] + "\n image src = "+ arrSrc[j]);
        }
    }); 
});
/*
docStat	게시판이름
1 환경뉴스
2	기관핫뉴스
3	시정뉴스
4	시정활동	

*/

//추천해요 건수추가
function AddDocRcmnd (docSeq, docStat) {
	
    var param = {
             "docSeq" : docSeq
             , "docStat" : docStat 
     };
	$.ajax({
        url : '/main/AddDocRcmnd'
            , type : 'POST'
            , dataType : 'JSON'
            , cache : false
            , data : param
            , beforeSend : function( xhr, settings ) { 
                //loadingbar_show(); 
            }
            , success : function(data, stauts, request) {
				
					if (data.procInd == "S") {
						let currentVal = parseInt($('#DocRcmnd').text());
						 $('#DocRcmnd').text(currentVal + 1);
					}
                }
               , error : function(xhr, status) {
               }
               , complete : function(xhr, status) {
                   //loadingbar_hide();
               }    
    });
}

//좋아요 건수추가
function AddDocLike (docSeq, docStat) {
	
    var param = {
             "docSeq" : docSeq
             , "docStat" : docStat 
     };
	$.ajax({
        url : '/main/AddDocLike'
            , type : 'POST'
            , dataType : 'JSON'
            , cache : false
            , data : param
            , beforeSend : function( xhr, settings ) { 
                //loadingbar_show(); 
            }
            , success : function(data, stauts, request) {
				
					if (data.procInd == "S") {
						let currentVal = parseInt($('#DocLike').text());
						 $('#DocLike').text(currentVal + 1);
					}
                }
               , error : function(xhr, status) {
               }
               , complete : function(xhr, status) {
                   //loadingbar_hide();
               }    
    });
}

//슬퍼요 건수추가
function AddDocSad (docSeq, docStat) {
	
    var param = {
             "docSeq" : docSeq
             , "docStat" : docStat 
     };
	$.ajax({
        url : '/main/AddDocSad'
            , type : 'POST'
            , dataType : 'JSON'
            , cache : false
            , data : param
            , beforeSend : function( xhr, settings ) { 
                //loadingbar_show(); 
            }
            , success : function(data, stauts, request) {
				
					if (data.procInd == "S") {
						let currentVal = parseInt($('#DocSad').text());
						 $('#DocSad').text(currentVal + 1);
					}
                }
               , error : function(xhr, status) {
               }
               , complete : function(xhr, status) {
                   //loadingbar_hide();
               }    
    });
}

//화나요 건수추가
function AddDocAngry (docSeq, docStat) {
	
    var param = {
             "docSeq" : docSeq
             , "docStat" : docStat 
     };
	$.ajax({
        url : '/main/AddDocAngry'
            , type : 'POST'
            , dataType : 'JSON'
            , cache : false
            , data : param
            , beforeSend : function( xhr, settings ) { 
                //loadingbar_show(); 
            }
            , success : function(data, stauts, request) {
				
					if (data.procInd == "S") {
						let currentVal = parseInt($('#DocAngry').text());
						 $('#DocAngry').text(currentVal + 1);
					}
                }
               , error : function(xhr, status) {
               }
               , complete : function(xhr, status) {
                   //loadingbar_hide();
               }    
    });
}


$(function () {


});


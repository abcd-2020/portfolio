$(function(){//read.jsp 페이지가 로딩될때  선언된 breply에 
		showList();
});//page loading function end	

var replyUL= $(".chat");
function showList(){
	replyService.getList({bbsno:bbsno, sno:sno, eno:eno}, 
	function(list){
		alert(param);
		   var str="";
	         if(list == null || list.length == 0){
	        	 return;
	         }
	     
	         for (var i = 0, len = list.length || 0; i < len; i++) {
	           str +="<li class='list-group-item' data-rnum='"+list[i].rnum+"'>";
	           str +="<div><div class='header'><strong class='primary-font'>"+list[i].id+"</strong>"; 
	           str +="<small class='pull-right text-muted'>"+list[i].regdate+"</small></div>";
	           str +=replaceAll(list[i].content,'\n','<br>')+"</div></li>";
	         }
	 
	         replyUL.html(str);
	     
	         showReplyPage();			 
	 }//function end
	);//getList end
}//showList end

function replaceAll(str, searchStr, replaceStr) {
    return str.split(searchStr).join(replaceStr);
}

var replyPageFooter = $(".panel-footer");
var param = "nPage="+nPage;
param += "&nowPage="+nowPage;
param += "&bbsno="+bbsno;
param += "&col="+colx;
param += "&word="+wordx;

function showReplyPage(){
	
	replyService.getPage(param,
			function(paging){
		console.log(paging);
		var str = "<div><small class='text-muted'>"+paging+"</small></div>";
		replyPageFooter.html(str);
   });

}//showReplyPage end

var modal = $(".modal");
var modalInputContent = modal.find("textarea[name='content']");
var modalModBtn = $("#modalModBtn");
var modalRemoveBtn = $("#modalRemoveBtn");
var modalRegisterBtn = $("#modalRegisterBtn");

$("#modalCloseBtn").on("click", function(e){
	modal.modal('hide');
});

$("#addReplyBtn").on("click", function(e){
	if(session_id=="") { //로그인이 안되었다면
		if(confirm('로그인해야 댓글을 쓸 수 있습니다.')){
			var url = "../member/login";
			url += "?col="+colx;  //url 과 파라메터 구분 이 물음표
			url += "&word="+wordx; //연결
			url += "&nowPage="+nowPage;
			url += "&nPage="+nPage;
			url += "&bbsno="+bbsno;
			url += "&rurl=../bbs/read";
			location.href = url;
		} //confirm end 
	}else{	
	modalInputContent.val("");
	modal.find("button[id != 'modalCloseBtn']").hide();
	modalRegisterBtn.show();
	
	$(".modal").modal("show");
	}//session_id end
});//addReplyBtn on end

modalRegisterBtn.on("click", function(e){
	if(modalInputContent.val()==''){
		alert("댓글을 입력하세요");
		return;
	}
	var reply={
			content:modalInputContent.val(),
			id:session_id,
			bbsno:bbsno
	};
	
	replyService.add(reply,function(result){
		modal.find("input").val("");
		modal.modal("hide");
		
		showList();
	}); //add end
}) //button click end

$(".chat").on("click","li",function(e){
	var rnum = $(this).data("rnum");
	replyService.get(rnum,function(reply){
		modalInputContent.val(reply.content);
		modal.data("rnum", reply.rnum);
		modal.find("button[id != 'modalCloseBtn']").hide();
		
		if(session_id == reply.id){ // 댓글 수정삭제할때 아이디가 같은 사람만 수정,삭제버튼이 보일 수 있게
			modalModBtn.show();
			modalRemoveBtn.show();
		}
		modal.modal("show");
	}) //get end
}) //.chat click end

modalModBtn.on("click", function(e){
	var reply = {
			rnum:modal.data("rnum"),
			content:modalInputContent.val()
	};
	
	replyService.update(reply, function(result){
		modal.modal("hide");
		showList();
	}); //update end
}); //modalModBtn on end

modalRemoveBtn.on("click", function(e){
	var rnum = modal.data("rnum");
	replyService.remove(rnum, function(result){
		modal.modal("hide");
		showList();
	}) //remove end
}) //modalRemoveBtn on end
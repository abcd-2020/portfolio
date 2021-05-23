console.log('*********Reply Module.................')
//서버 요청하는 페이지 DB에서 데이터 갖고 와서 뿌려주는 역할 비동기통신을 할 수 있도록 선언만
var replyService = (function(){
	
	function remove(rnum, callback, error){
		$.ajax({
			type:"delete",
			url:"./reply/"+rnum,
			success:function(result, status, xhr){
			if(callback){
				callback(result);
			   }
			},
			error:function(xhr, status, er){
				 if (error) {
			            error(er);
			          }
			 }
		})//ajax end
	}//remove end
	
	function update(reply, callback, error){
		console.log("rnum: " + reply.rnum);
		$.ajax({
			type:'put',
			url: './reply/'+reply.rnum,
			data:JSON.stringify(reply),
			contentType:"application/json;charset=utf-8",
			success:function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error:function(xhr, status, er){
				 if (error) {
			            error(er);
			          }
			 }
		}) //ajax end
	} //update end
	
	function add(reply, callback, error){
		console.log("add reply............");
		
		$.ajax({
			type:'post',
			url : './reply/create',
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			 success : function(result, status, xhr){
				   if (callback) {
			            callback(result);
			          }
			 },
			 error:function(xhr, status, er){
				 if (error) {
			            error(er);
			          }
			 }
		});
	}
	
	function get(rnum, callback, error){
		  $.get("./reply/" + rnum + ".json", function(result){
			  if (callback) {
		            callback(result);
		          }
		  }).fail(function(xhr, status, err){
			  if (error) {
		             error();
		         }
		  });
	}
	
	function getList(param, callback, error){
		var bbsno = param.bbsno;
		var sno = param.sno;
		var eno = param.eno;
		
		//alert(param.bbsno);
		$.getJSON("./reply/list/"+bbsno+"/"+sno+"/"+eno+".json",
				function(data){
				alert("&&&&&");
				if(callback){
					callback(data);
			     } 				
	         }
		);
	}
	
	function getPage(param, callback, error){
		$.ajax({
			type:'get',
			url:'./reply/page',
			data:param,
			contentType:"application/text;charset=utf-8",
			success:function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error:function(xhr,status,er){
				if(error){
					error(er);
				}
			}
		});
	}
	;
	return{
		getList:getList,
		getPage:getPage,
		add:add,
		get:get,
		update:update,
		remove:remove
	};
	
})();//function()호출(실행)
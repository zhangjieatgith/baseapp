$(function(){
	console.log("index.html");
	var methods = {
		init : function(){
			var menus = JSON.parse(userAuthInfo);
			$('#idSidemenu').sidemenu({
			    data: menus,
			    onSelect : function(item){
			    	selectMenu(item);
				}
			});
		}
	};
	
	methods.init();
});

function selectMenu(item){
	if(!$('#idCenterTabs').tabs('getTab',item['text'])){
		$('#idCenterTabs').tabs('add',{
			title:item['text'],
			selected:true,
			href:item['data'],
			closable:true
		});
	}else{
		$('#idCenterTabs').tabs('select',item['text']);
	}
}

/*子菜单中的js方法*/

function initPassBtn(value,row,index){
	return '<a href="#" onClick="initUserPass('+row['id']+',\''+row['userName']+'\')">初始化密码</a>';
}

function initUserPass(id,userName){
	$.ajax({
		url : '/sysmanage/initUserPass',
		data : {
			id : id,
			userName : userName,
			password : hex_md5("123456")
		},
		async : false,
		method : 'post',
		success : function(e){
			alert(e.msg);
		}
	});
}


function setAuthForRole(value,row,index){
	return '<a href="#" onClick="setAuthForRoleAction('+row['id']+',\''+row['roleName']+'\')">授权</a>';
}

function setAuthForRoleAction(roleId,roleName){
	$.ajax({
		url : '/sysmanage/getAuthsForOneRoleByTree',
		method : 'post',
		data : {
			id : roleId
		},
		async : false,
		success : function(e){
			debugger;
			console.log(e);
			$('#showRoleAuths').dialog('open');
			$('#showRoleAuths').data("roleId",roleId);
			$('#showAuthForOneRoleByTree').tree('loadData',e);
		}
	});
}

function updateAuthOk() {
	var nodeIds = [];
	$('#showAuthForOneRoleByTree').tree('getChecked',['checked','indeterminate']).forEach(function(item){
		nodeIds.push(item.id);
	});
	$.ajax({
		url : '/sysmanage/updateAuthOk',
		method : 'post',
		async : false,
		data : {
			roleId : $('#showRoleAuths').data("roleId"),
			ids : nodeIds.join(',')
		},
		success : function(e){
			console.log(e);
		}
	});
}


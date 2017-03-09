<%-- <%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
 	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>数据字典</title>

	<!-- Loading Bootstrap -->
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/right.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/dtree/dtree.css"/>
	
	<link rel="shortcut icon" href="../dist/img/favicon.ico">
 	<!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at the end of file. -->
    <!--[if lt IE 9]>
      <script src="${ctx}/resources/js/html5shiv.js"></script>
      <script src="${ctx}/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type='text/javascript' src="${ctx}/resources/js/jquery1.42.min.js"></script>
    <script type='text/javascript' src="${ctx}/resources/js/jquery.js"></script>
	<script type='text/javascript' src="${ctx}/resources/dtree/dtreeBF.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/resources/js/jquery.ztree.all-3.5.js"></script>  
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/zTreeStyle/zTreeStyle.css" />  
<script language="javascript" type="text/javascript" src="${ctx}/resources/js/jquery.ztree.all-3.5.min.js"></script>
    <script type="text/javascript">
    /**ztree的参数配置，setting主要是设置一些tree的属性，是本地数据源，还是远程，动画效果，是否含有复选框等等**/    
    var setting = {  
     data: {                                    
      simpleData: {   //简单的数据源，一般开发中都是从数据库里读取，API有介绍，这里只是本地的                           
       enable: true,  
       idKey: "id",  //id和pid，这里不用多说了吧，树的目录级别  
       pIdKey: "pId",  
       rootPId: 0   //根节点  
      }                            
     },                           
     callback: {
      onClick: zTreeOnClick                            
     }  
    };  
    function zTreeOnClick(event, treeId, treeNode) {
        dictItem.location.href = "dateDictList?fatherDictId="+treeNode.id;
    };      

    	$.ajax({  
            async : false,  
            cache:false,  
            type: 'POST',  
            dataType : "json",  
            url: "dictTree",//请求的action路径  
            error: function () {//请求失败处理函数  
                alert('请求失败');  
            },  
            success:function(data){ //请求成功后处理函数。    
                treeNodes = data.message;   //把后台封装好的简单Json格式赋给treeNodes  
            }  
        }); 
      var zTreeDemo = $.fn.zTree.init($("#dictTree"),setting, treeNodes);  
      var node;
      for(var i = 1;i < 100;i++){
      	var node = zTree.getNodeByParam('id', i);//获取id为1的点
      	if(node != null){
      		break;
      	}
      } 
      zTree.selectNode(node);//选择点  
      zTree.setting.callback.onClick(null, zTree.setting.treeId, node);//调用事件 
    });    
</script>
</head>
<body>
			<div class="position_bg">
				<div class="home">
					您当前的位置：<span id="pos">后台管理  &gt;&gt; 数据字典</span>
				</div>
	      	</div>
				<div id="message"></div>
				<!-- <div style="padding-top:10px;">
                    <input type="button"  onclick="alert('保存成功')" title="保 存" value="保 存" class="buttonCss" id="btn_save">
                    <input type="button" onclick="add();" title="添 加" value="添 加" class="buttonCss" id="btn_add">
           		</div> -->
                <div class="ksdw">
                	<input type="text" id="ksdw" value=快速定位 name=key_word onblur="if(value==''){value='快速定位';}" onfocus="if(value=='快速定位'){value=''}">
                </div>
             	<div class="treeDiv">
					<ul id="dictTree" class="ztree"></ul>  
                </div>
                <div class="bmgl_right">
                	<iframe id="dictItem" name="dictItem" width="100%" height="100%" src="dateDictList" frameBorder=0 scrolling=no onload="this.height=this.contentWindow.document.body.scrollHeight;"></iframe>
                </div>
		
	<script type="text/javascript">
		$().ready(function(){
			//判断是否有显示消息参数
			var v = UrlParm.parm("message");  
			if(v == 1){
				showMessage("message","alert-success","保存记录成功");
			}else if(v == 2){
				showMessage("message","alert-success","分配用户角色成功");
			}
			
			$("#delBtn").click(function(){
				var n = $("input[name='subcheckbox']:checked").length;
				if(n == 0){
					showMessage("message","alert-warning","请先选择要删除的记录");
				}else{
					showMessage("message","alert-success","删除记录成功");
				}
			});
			$("#checkAll").click(function(){
				var c = $(this)[0].checked;
				$("input[name='subcheckbox']").each(function(){
					$(this)[0].checked = c;
				});
			});
		});
		function del(id){
			showMessage("message","alert-success","删除记录成功");
		}
	</script>
</body>
</html>
<script>
var aaa=$("#ksdw").val();
var ksdw=document.getElementById("ksdw");
ksdw.onkeydown=function(event){
		var e = event || window.event || arguments.callee.caller.arguments[0];          
		 if(e && e.keyCode==13){ // enter 键
			$(".dTreeNode").each(function(index, element) {
				   $('$(this):contains(各区单位或企业)').addClass('aon')
				});
		}
	}; 
</script> --%>
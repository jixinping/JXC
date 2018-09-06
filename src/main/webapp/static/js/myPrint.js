function printView(type){
	var selectedRows=$("#dg").datagrid("getSelections");
	if(selectedRows.length!=1){
		$.messager.alert("系统提示","请选择一条要预览的订单！");
		return;
	}
	$("#printDirect").val("0");
	$("#printFrame").attr("src","../print.html");
	$("#printDiv").show();
}

function printDirect(){
	var selectedRows=$("#dg").datagrid("getSelections");
	if(selectedRows.length!=1){
		$.messager.alert("系统提示","请选择一条要打印的订单！");
		return;
	}
	
	$("#printDirect").val("1");
	$("#printFrame").attr("src","../print.html");
}

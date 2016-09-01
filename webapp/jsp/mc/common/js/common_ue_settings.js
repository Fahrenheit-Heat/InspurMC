/**
 * 初始化UE编辑器
 * @param selector ID选择器
 * @param type 类型：{edit：可编辑，show：不可编辑}
 * @returns ue {Object：编辑器对象}
 */
function initUESettings(selector, type){
	var ue;
	if(type == "edit"){
		ue = UE.getEditor(selector,{
			toolbars: [
			           [
						'fontfamily', //字体
						'fontsize', //字号
						'forecolor', //字体颜色
						'backcolor', //背景色
						'italic', //斜体
						'underline', //下划线
						'strikethrough', //删除线
						'bold', //加粗
						'justifyleft', //居左对齐
						'justifyright', //居右对齐
						'justifycenter', //居中对齐
						'justifyjustify', //两端对齐
						'indent', //首行缩进
						'preview', //预览
						'horizontal', //分隔线
						'time', //时间
						'date', //日期
						'undo', //撤销
						'redo', //重做
						'emotion', //表情
						'insertorderedlist', //有序列表
						'insertunorderedlist', //无序列表
						'fullscreen', //全屏
						'rowspacingtop', //段前距
						'rowspacingbottom', //段后距
						'pagebreak', //分页
						'lineheight', //行间距
						'edittip ', //编辑提示
						'autotypeset', //自动排版
						'touppercase', //字母大写
						'tolowercase', //字母小写
						'template', //模板
			            ]
			           ],
			           initialFrameHeight:250,
			           minFrameHeight:200
		});
	}else if(type == "show"){
		ue = UE.getEditor(selector,{
			toolbars: [],
			initialFrameHeight:250,
			minFrameHeight:200,
			readonly: true
		});
	}else{
		ue = UE.getEditor(selector,{
			initialFrameHeight:250,
			minFrameHeight:200
		});
	}
	return ue;
}


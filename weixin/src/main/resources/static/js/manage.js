	$(function () {
        $("#importBtn").click(function () {
            var formData = new FormData($('#uploadStudentInfoFile')[0]);
            $.ajax({
                type: 'post',
                url: "/manage/importStudentInfoExcel",
                data: formData,
                cache: false,
                processData: false,
                contentType: false,
            }).success(function (data) {
                alert(data);
                window.location.reload();
            }).error(function () {
                alert("上传失败");
            });
        });
        $("#importBtnFT").click(function () {
            var formData = new FormData($('#uploadFinacoFile')[0]);
            $.ajax({
                type: 'post',
                url: "/manage/importFinacoResultExcel",
                data: formData,
                cache: false,
                processData: false,
                contentType: false,
            }).success(function (data) {
                alert(data);
                window.location.reload();
            }).error(function () {
                alert("上传失败");
            });
        });
    });

	//文件上传初始化
	$(document).ready(function () {
        $("#kv-explorer").fileinput({
            'theme': 'explorer-fas',
            'uploadUrl': '/saveExcelData',
            'allowedFileExtensions': ['xls', 'xlsx'],
            overwriteInitial: false,
            initialPreviewAsData: true,
            language: 'zh'
        });
        
    });

	//条件查询
/*	function searchStudentInfoByCondition(){
		locationUrl('/manage/studentInfoList?student_number=' + $("#studentNumber").val() + '&name=' + $("#studentName").val() + '&academy=' + $("#select_academyopt").val(),'studentInfoList');
	}*/

	//删除学生信息
	function deleteStudentInfoByStudentNumber(student_number) {
		if (confirm('确定删除此数据？')) {
			$.post("/manage/deleteStudentInfo", {
					student_number: student_number
				},
				function(data) {
					if(data == '删除成功'){
						toastr.success(data, '操作成功');
						window.location.reload();
					}else{
						toastr.error(data,'操作失败');
					}
				});
		}
	}
	//删除体测结果
	function deleteFinacoByFinacono(finaco_no) {
		if (confirm('确定删除此数据？')) {
			$.post("/manage/deleteFinacoTestResult", {
				finaco_no: finaco_no
				},
				function(data) {
					if(data == '删除成功'){
						toastr.success(data, '操作成功');
						window.location.reload();
					}else{
						toastr.error(data,'操作失败');
					}
				});
		}
	}
	
	//编辑学生信息
	function editStudentInfoByStudentNumber(student_number){
		$.post("/manage/editStudentInfo",{
			student_number: student_number
		},function(data){
			$("#student_number").val(data.student_number);
			$("#name").val(data.name);
			$("#birth").val(data.birth);
			$("#id_number").val(data.id_number);
			var obj1 = $("#sexopt").get(0);
			for(var i = 0;i < obj1.options.length;i++){
				var tmp = obj1.options[i].value;
				if(tmp == data.sex){
					obj1.options[i].selected="selected";
					break;
				}
			}
			var obj2 = $("#majoropt").get(0);
			for(var i = 0;i < obj2.options.length;i++){
				var tmp = obj2.options[i].value;
				if(tmp == data.major){
					obj2.options[i].selected="selected";
					break;
				}
			}
			var obj3 = $("#academyopt").get(0);
			for(var i = 0;i < obj3.options.length;i++){
				var tmp = obj3.options[i].value;
				if(tmp == data.academy){
					obj3.options[i].selected="selected";
					break;
				}
			}
			addOptions(document.getElementById('majoropt'), academy[$("#academyopt").val()]);
			var obj4 = $("#majoropt").get(0);
			for(var i = 0;i < obj4.options.length;i++){
				var tmp = obj4.options[i].value;
				if(tmp == data.major){
					obj4.options[i].selected="selected";
					break;
				}
			}
		});
	}
	//编辑体测结果信息
	function editFinacoByFinacono(finaco_no){
		$.post("/manage/editFinacoTestResult",{
			finaco_no: finaco_no
		},function(data){
			$("#finaco_no").val(data.finaco_no);
			$("#student_number").val(data.student_number);
			$("#test_result_describe").val(data.test_result_describe);
			var obj1 = $("#project_noopt").get(0);
			for(var i = 0;i < obj1.options.length;i++){
				var tmp = obj1.options[i].value;
				if(tmp == data.project_no){
					obj1.options[i].selected="selected";
					break;
				}
			}
			var obj2 = $("#semesteropt").get(0);
			for(var i = 0;i < obj2.options.length;i++){
				var tmp = obj2.options[i].value;
				if(tmp == data.semester){
					obj2.options[i].selected="selected";
					break;
				}
			}
			var obj3 = $("#gradeopt").get(0);
			for(var i = 0;i < obj3.options.length;i++){
				var tmp = obj3.options[i].value;
				if(tmp == data.grade){
					obj3.options[i].selected="selected";
					break;
				}
			}
		});
	}
	
	
	//更新学生信息
	function updateStudentInfo(){
		var ok = $('#updateStudentInfoForm').parsley().isValid({force: true});
		if(!ok){
			return;
		}
		$.post("/manage/updateStudentInfo",{
			student_number: $("#student_number").val(),
			name: $("#name").val(),
			sex: $("#sexopt").val(),
			major: $("#majoropt").val(),
			academy: $("#academyopt").val(),
			birth: $("#birth").val(),
			id_number: $("#id_number").val()
		},function(data){
			if(data == '更新成功'){
				toastr.success(data, '操作成功');
				window.location.reload();
			}else{
				toastr.error(data,'操作失败');
			}
		});
	}
	//更新体测结果信息
	function updateFinaco(){
		var ok = $('#updateFinacoForm').parsley().isValid({force: true});
		if(!ok){
			return;
		}
		$.post("/manage/updateFinacoTestResult",{
			finaco_no: $("#finaco_no").val(),
			student_number: $("#student_number").val(),
			project_no: $("#project_noopt").val(),
			semester: $("#semesteropt").val(),
			grade: $("#gradeopt").val(),
			test_result_describe: $("#test_result_describe").val()
		},function(data){
			if(data == '更新成功'){
				toastr.success(data, '操作成功');
				window.location.reload();
			}else{
				toastr.error(data,'操作失败');
			}
		});
	}
	
	//新增学生信息
	function insertStudentInfo(){
		var ok = $('#newStudentInfoForm').parsley().isValid({force: true});
		if(!ok){
			return;
		}
		$.post("/manage/addStudentInfo",{
			student_number: $("#add_student_number").val(),
			name: $("#add_name").val(),
			sex: $("#add_sexopt").val(),
			major: $("#add_majoropt").val(),
			academy: $("#add_academyopt").val(),
			birth: $("#add_birth").val(),
			id_number: $("#add_id_number").val()
		},function(data){
			if(data == '新增成功'){
				toastr.success(data, '操作成功');
				window.location.reload();
			}else{
				toastr.error(data,'操作失败');
			}
		});
	}
	//新增体测结果信息
	function insertFinaco(){
		var ok = $('#newFinacoForm').parsley().isValid({force: true});
		if(!ok){
			return;
		}
		$.post("/manage/insertFinacoTestResult",{
			finaco_no: 0,
			student_number: $("#add_student_number").val(),
			project_no: $("#add_project_noopt").val(),
			semester: $("#add_semesteropt").val(),
			grade: $("#add_gradeopt").val(),
			test_result_describe: $("#add_test_result_describe").val()
		},function(data){
			if(data == '新增成功'){
				toastr.success(data, '操作成功');
				window.location.reload();
			}else{
				toastr.error(data,'操作失败');
			}
		});
	}
	
	//页面执行完成后执行检索条件的回显
	$(function(){
		highConditionReShow();
		var a = document.getElementById('academyopt');
		if(document.getElementById('academyopt') != null){
			document.getElementById('academyopt').onchange = function(){
				addOptions(document.getElementById('majoropt'), academy[this.value]);
			}
		}
		if(document.getElementById('academyopt0') != null){
			document.getElementById('academyopt0').onchange = function(){
				addOptions(document.getElementById('majoropt0'), academy[this.value]);
			}
		}
		if(document.getElementById('add_academyopt') != null){
			document.getElementById('add_academyopt').onchange = function(){
				addOptions(document.getElementById('add_majoropt'), academy[this.value]);
			}
		}
	})
	
	//菜单二级联动
/*	document.getElementById('academyopt').onchange = function(){
		addOptions(document.getElementById('majoropt'), academy[this.value]);
	}
	
	document.getElementById('academyopt0').onchange = function(){
		addOptions(document.getElementById('majoropt0'), academy[this.value]);
	}
	
	document.getElementById('add_academyopt').onchange = function(){
		addOptions(document.getElementById('add_majoropt'), academy[this.value]);
	}*/
	
	//为专业选择框动态添加选项，实现与学院选择框的联动
	function addOptions(s, arr, initValue) {
		if (!arr || arr.length == 0) arr = [{ name: '请选择专业', id: '' }];
		if (!s) { alert('select对象不存在！'); return false }
		s.options.length = 0;
		var selectedIndex = 0;
		for (var i = 0; i < arr.length; i++) {
			s.options.add(new Option(arr[i].name, arr[i].name));
			if (arr[i].id == initValue) selectedIndex = i;
		}
	}
	
	
	//查询所有的高级查询条件，作为一个数组返回，学生信息
	function allHighCondition(){
		var divs = $("div[id^='condition']");//搜索所有以condition开头的div
		var conditions = [];
		for(var i = 0;i < divs.length;i++){
			var conditionName = $("#" + divs[i].id).find("span[id^='conditionName']").text();//获取该div中的conditionName的值
			var conditionValue;
			if(conditionName == "学号" || conditionName == "姓名" || conditionName == "身份证号"){
				conditionValue = $("#" + divs[i].id).find("input[id^='conditionValue']").val();
			}
			else if(conditionName == "性别"){
				conditionValue = $("#" + divs[i].id).find("select[id^='sexopt']").val();
			}
			else if(conditionName == "专业"){
				conditionValue = $("#" + divs[i].id).find("select[id^='majoropt']").val();
			}
			else{
				conditionValue = $("#" + divs[i].id).find("select[id^='academyopt']").val();
			}
			var andor = "and";
			var andorValue = $("#" + divs[i].id).find("input[name^='andor']:checked").val();
			if(i == 0)
				andor = "";
			else if(andorValue)
				andor = andorValue;
			console.log(conditionName + "" + conditionValue + andor);
			var con = new Condition(conditionName, conditionValue, andor);
			conditions.push(con);
		}
		return conditions;
	}
	//查询所有的高级查询条件，作为一个数组返回，体测结果信息
	function allHighConditionFT(){
		var divs = $("div[id^='condition']");//搜索所有以condition开头的div
		var conditions = [];
		for(var i = 0;i < divs.length;i++){
			var conditionName = $("#" + divs[i].id).find("span[id^='conditionName']").text();//获取该div中的conditionName的值
			var conditionValue;
			if(conditionName == "学号"){
				conditionValue = $("#" + divs[i].id).find("input[id^='conditionValue']").val();
			}
			else if(conditionName == "体测项目"){
				conditionValue = $("#" + divs[i].id).find("select[id^='projectopt']").val();
			}
			else if(conditionName == "学期"){
				conditionValue = $("#" + divs[i].id).find("select[id^='semesteropt']").val();
			}
			else{
				conditionValue = $("#" + divs[i].id).find("select[id^='gradeopt']").val();
			}
			var andor = "and";
			var andorValue = $("#" + divs[i].id).find("input[name^='andor']:checked").val();
			if(i == 0)
				andor = "";
			else if(andorValue)
				andor = andorValue;
			console.log(conditionName + "" + conditionValue + andor);
			var con = new Condition(conditionName, conditionValue, andor);
			conditions.push(con);
		}
		return conditions;
	}
	
	//高级搜索
	//隐藏表单post提交数据,学生信息
	function search(){
		var conditions = allHighCondition();
		$("#conditionValue").val(JSON.stringify(conditions));
		$("#searchForm").submit();
	}
	//隐藏表单post提交数据,体测结果信息
	function searchFT(){
		var conditions = allHighConditionFT();
		$("#conditionValue").val(JSON.stringify(conditions));
		$("#searchForm").submit();
	}
	
	//条件对象构造函数
	function Condition(conditionName, conditionValue, andor){
		this.conditionName = conditionName;
		this.conditionValue = conditionValue;
		this.andor = andor;
	}
	
	var conditionNums = 1;//全局变量,为了生成动态id
	
	//变化conditionName Label的值,输入框类型转换input->select,学生信息
	function changeConditionNameAndType(ths,num){
		var name = $(ths).text();
		$("#conditionName" + num).text(name);
		if(name == "学号" || name == "姓名" || name == "身份证号"){
			$("#sexopt" + num).hide();
			$("#academyopt" + num).hide();
			$("#majoropt" + num).hide();
			$("#conditionValue" + num).show();
		}
		else if(name == "性别"){
			$("#academyopt" + num).hide();
			$("#majoropt" + num).hide();
			$("#conditionValue" + num).hide();
			$("#sexopt" + num).show();
		}
		else if(name == "所在学院"){
			$("#majoropt" + num).hide();
			$("#conditionValue" + num).hide();
			$("#sexopt" + num).hide();
			$("#academyopt" + num).show();
		}
		else if(name == "专业"){
			$("#academyopt" + num).hide();
			$("#conditionValue" + num).hide();
			$("#sexopt" + num).hide();
			$("#majoropt" + num).show();
		}
	}
	//变化conditionName Label的值,输入框类型转换input->select,体测结果信息
	function changeConditionNameAndTypeFT(ths,num){
		var name = $(ths).text();
		$("#conditionName" + num).text(name);
		if(name == "学号"){
			$("#projectopt" + num).hide();
			$("#semesteropt" + num).hide();
			$("#gradeopt" + num).hide();
			$("#conditionValue" + num).show();
		}
		else if(name == "体测项目"){
			$("#semesteropt" + num).hide();
			$("#gradeopt" + num).hide();
			$("#conditionValue" + num).hide();
			$("#projectopt" + num).show();
		}
		else if(name == "学期"){
			$("#gradeopt" + num).hide();
			$("#conditionValue" + num).hide();
			$("#projectopt" + num).hide();
			$("#semesteropt" + num).show();
		}
		else if(name == "年级"){
			$("#semesteropt" + num).hide();
			$("#conditionValue" + num).hide();
			$("#projectopt" + num).hide();
			$("#gradeopt" + num).show();
		}
	}
	
	//动态添加条件搜索框,学生信息
	function addCondition(){
		var divTemplate = $("#condition0").clone();//克隆div模板
		divTemplate.attr("id", "condition" + conditionNums);//更改div的id
		$("#searchBtnDiv").before(divTemplate);
		
		$("#condition" + conditionNums + " div[id='alterDiv0']").attr("id", "alterDiv" + conditionNums);//更改标签div
	
		//添加and or单选按钮
		$("#alterDiv" + conditionNums).html('<div class="btn-group" data-toggle="buttons"><label class="btn btn-default active"><input type="radio" name="andor' + conditionNums + '" value="and" />and</label><label class="btn btn-default"><input type="radio" name="andor' + conditionNums + '" value="or" />or</label></div>');
		
		$("#condition" + conditionNums + " span[id='conditionName0']").attr("id", "conditionName" + conditionNums);//更改span元素的id
		$("#condition" + conditionNums + " input[id='conditionValue0']").attr("id", "conditionValue" + conditionNums);//更改input元素的id
		$("#condition" + conditionNums + " button[id='rmBtn0']").attr("id", "rmBtn" + conditionNums);//更改删除按钮的id
		
		$("#condition" + conditionNums + " a[id='stdNumber0']").attr("id", "stdNumber" + conditionNums);//更改下拉列表id
		$("#condition" + conditionNums + " a[id='stdName0']").attr("id", "stdName" + conditionNums);
		$("#condition" + conditionNums + " a[id='stdSex0']").attr("id", "stdSex" + conditionNums);
		$("#condition" + conditionNums + " a[id='stdAcademy0']").attr("id", "stdAcademy" + conditionNums);
		$("#condition" + conditionNums + " a[id='stdMajor0']").attr("id", "stdMajor" + conditionNums);
		$("#condition" + conditionNums + " a[id='stdIDnumber0']").attr("id", "stdIDnumber" + conditionNums);
		$("#stdNumber" + conditionNums).attr("onclick", "changeConditionNameAndType(this," + conditionNums + ")");//设置下拉列表绑定事件参数
		$("#stdName" + conditionNums).attr("onclick", "changeConditionNameAndType(this," + conditionNums + ")");
		$("#stdSex" + conditionNums).attr("onclick", "changeConditionNameAndType(this," + conditionNums + ")");
		$("#stdAcademy" + conditionNums).attr("onclick", "changeConditionNameAndType(this," + conditionNums + ")");
		$("#stdMajor" + conditionNums).attr("onclick", "changeConditionNameAndType(this," + conditionNums + ")");
		$("#stdIDnumber" + conditionNums).attr("onclick", "changeConditionNameAndType(this," + conditionNums + ")");
		
		$("#condition" + conditionNums + " select[id='majoropt0']").attr("id", "majoropt" + conditionNums);//设置select框的id
		$("#condition" + conditionNums + " select[id='sexopt0']").attr("id", "sexopt" + conditionNums);
		$("#condition" + conditionNums + " select[id='academyopt0']").attr("id", "academyopt" + conditionNums);
		
		$("#conditionValue" + conditionNums).val("");//清空输入框的内容
		$("#rmBtn" + conditionNums).attr("onclick", "removeCondition(" + conditionNums + ")");//设置删除按钮绑定事件参数
		
		$("#rmBtn" + conditionNums).show();//显示删除按钮
		conditionNums++;
	}
	//动态添加条件搜索框，体测结果信息
	function addConditionFT(){
		var divTemplate = $("#condition0").clone();//克隆div模板
		divTemplate.attr("id", "condition" + conditionNums);//更改div的id
		$("#searchBtnDiv").before(divTemplate);
		
		$("#condition" + conditionNums + " div[id='alterDiv0']").attr("id", "alterDiv" + conditionNums);//更改标签div
	
		//添加and or单选按钮
		$("#alterDiv" + conditionNums).html('<div class="btn-group" data-toggle="buttons"><label class="btn btn-default active"><input type="radio" name="andor' + conditionNums + '" value="and" />and</label><label class="btn btn-default"><input type="radio" name="andor' + conditionNums + '" value="or" />or</label></div>');
		
		$("#condition" + conditionNums + " span[id='conditionName0']").attr("id", "conditionName" + conditionNums);//更改span元素的id
		$("#condition" + conditionNums + " input[id='conditionValue0']").attr("id", "conditionValue" + conditionNums);//更改input元素的id
		$("#condition" + conditionNums + " button[id='rmBtn0']").attr("id", "rmBtn" + conditionNums);//更改删除按钮的id
		
		$("#condition" + conditionNums + " a[id='stdNumber0']").attr("id", "stdNumber" + conditionNums);//更改下拉列表id
		$("#condition" + conditionNums + " a[id='finacoProject0']").attr("id", "finacoProject" + conditionNums);
		$("#condition" + conditionNums + " a[id='semester0']").attr("id", "semester" + conditionNums);
		$("#condition" + conditionNums + " a[id='grade0']").attr("id", "grade" + conditionNums);
		$("#stdNumber" + conditionNums).attr("onclick", "changeConditionNameAndTypeFT(this," + conditionNums + ")");//设置下拉列表绑定事件参数
		$("#finacoProject" + conditionNums).attr("onclick", "changeConditionNameAndTypeFT(this," + conditionNums + ")");
		$("#semester" + conditionNums).attr("onclick", "changeConditionNameAndTypeFT(this," + conditionNums + ")");
		$("#grade" + conditionNums).attr("onclick", "changeConditionNameAndTypeFT(this," + conditionNums + ")");
		
		$("#condition" + conditionNums + " select[id='projectopt0']").attr("id", "projectopt" + conditionNums);//设置select框的id
		$("#condition" + conditionNums + " select[id='semesteropt0']").attr("id", "semesteropt" + conditionNums);
		$("#condition" + conditionNums + " select[id='gradeopt0']").attr("id", "gradeopt" + conditionNums);
		
		$("#conditionValue" + conditionNums).val("");//清空输入框的内容
		$("#rmBtn" + conditionNums).attr("onclick", "removeCondition(" + conditionNums + ")");//设置删除按钮绑定事件参数
		
		$("#rmBtn" + conditionNums).show();//显示删除按钮
		conditionNums++;
	}
	
	//删除
	function removeCondition(num){
		$("#condition" + num).remove();
	}

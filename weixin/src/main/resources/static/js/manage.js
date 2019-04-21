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
                locationUrl('/manage/studentInfoList','');
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
	function searchStudentInfoByCondition(){
		locationUrl('/manage/studentInfoList?student_number=' + $("#studentNumber").val() + '&name=' + $("#studentName").val() + '&academy=' + $("#select_academyopt").val(),'studentInfoList');
	}

	//删除
	function deleteStudentInfoByStudentNumber(student_number) {
		if (confirm('确定删除此数据？')) {
			$.post("/manage/deleteStudentInfo", {
					student_number: student_number
				},
				function(data) {
					toastr.success(data, '操作成功');
					locationUrl('/manage/studentInfoList','studentInfoList');
				});
		}
	}
	
	//编辑
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
	
	//更新
	function updateStudentInfo(){
		$.post("/manage/updateStudentInfo",{
			student_number: $("#student_number").val(),
			name: $("#name").val(),
			sex: $("#sexopt").val(),
			major: $("#majoropt").val(),
			academy: $("#academyopt").val(),
			birth: $("#birth").val(),
			id_number: $("#id_number").val()
		},function(data){
			toastr.success(data, '操作成功');
			locationUrl('/manage/studentInfoList','studentInfoList');
		});
	}
	
	//新增
	function insertStudentInfo(){
		$.post("/manage/addStudentInfo",{
			student_number: $("#add_student_number").val(),
			name: $("#add_name").val(),
			sex: $("#add_sexopt").val(),
			major: $("#add_majoropt").val(),
			academy: $("#add_academyopt").val(),
			birth: $("#add_birth").val(),
			id_number: $("#add_id_number").val()
		},function(data){
			toastr.success(data, '操作成功');
			locationUrl('/manage/studentInfoList','studentInfoList');
		});
	}
	
	document.getElementById('academyopt').onchange = function(){
		addOptions(document.getElementById('majoropt'), academy[this.value]);
	}
	
	document.getElementById('add_academyopt').onchange = function(){
		addOptions(document.getElementById('add_majoropt'), academy[this.value]);
	}
	
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
	
	
	
	//高级搜索
	//ajax请求
	function search(){
		var divs = $("div[id^='condition']");//搜索所有以condition开头的div
		console.log(divs.length);
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
		$.post("/manage/studentInfoList1",{
			conditions: JSON.stringify(conditions)
		},function(data){
			
		})
	}
	
	function Condition(conditionName, conditionValue, andor){
		this.conditionName = conditionName;
		this.conditionValue = conditionValue;
		this.andor = andor;
	}
	
	$(function(){
		$("#rmBtn0").hide();//隐藏初始搜索框的删除按钮
		$("#sexopt0").hide();//隐藏初始搜索框的select框
		$("#academyopt0").hide();
		$("#majoropt0").hide();
	})
	
	var conditionNums = 1;//全局变量,为了生成动态id
	
	//变化conditionName Label的值,输入框类型转换input->select
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
	
	//添加
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
	
	//删除
	function removeCondition(num){
		$("#condition" + num).remove();
	}

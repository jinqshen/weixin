package com.jinqshen.weixin.service.impl;

import com.jinqshen.weixin.mapper.StudentTranscriptMapper;
import com.jinqshen.weixin.pojo.table.StudentInfo;
import com.jinqshen.weixin.service.StudentTranscriptService;
import com.jinqshen.weixin.vo.Transcript;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentTranscriptServiceImpl implements StudentTranscriptService {

    @Autowired
    private StudentTranscriptMapper studentTranscriptMapper;


    @Override
    public Map<String, List<Transcript>> getFinacoTranscript(int student_number,boolean newest) {
        List<String> semesterList = studentTranscriptMapper.findExistSemester(student_number);
        ArrayList<String> sortSemesterList = new ArrayList<>();
        //将学期进行排序
        if(semesterList != null){
            Integer[] sortKey = new Integer[semesterList.size()];
            int i = 0;
            for (String semesterOne : semesterList){
                sortKey[i++] = Integer.parseInt(semesterOne.split("-")[0]);
            }
            Arrays.sort(sortKey);
            if(!newest){
                for(int j = sortKey.length-1;j >= 0;j--){
                    for (String semesterOne : semesterList){
                        if(Integer.parseInt(semesterOne.split("-")[0]) == sortKey[j])
                            sortSemesterList.add(semesterOne);
                    }
                }
            }else {
                for (String semesterOne : semesterList){
                    if(Integer.parseInt(semesterOne.split("-")[0]) == sortKey[sortKey.length - 1])
                        sortSemesterList.add(semesterOne);
                }
            }
        }
        Map<String, List<Transcript>> linkedHashMap = new LinkedHashMap<>();
        if(semesterList != null){
            for (String semesterOne : sortSemesterList) {
                List<Transcript> list = new ArrayList<>();
                StudentInfo student = studentTranscriptMapper.findStudent(student_number);
                String studentName = student.getName();
                if(student.getSex().equals("男")){
                    //身高
                    Transcript height = studentTranscriptMapper.findTheProjectTranscript_b(student_number, "身高",semesterOne);
                    if(height == null){
                        height = new Transcript(student_number,studentName,"身高",0,"","");
                    }
                    list.add(height);
                    //体重
                    Transcript weight = studentTranscriptMapper.findTheProjectTranscript_b(student_number, "体重",semesterOne);
                    if(weight == null){
                        weight = new Transcript(student_number,studentName,"体重",0,"","");
                    }
                    list.add(weight);
                    Transcript bmi_index = null;
                    //BMI指数
                    if(!height.getTest_result_describe().equals("")&&!weight.getTest_result_describe().equals("")){
                        float weightTestResultDescribe = Float.parseFloat(weight.getTest_result_describe());
                        float heightTestResultDescribe = Float.parseFloat(height.getTest_result_describe());
                        float bmi = (float) ((weightTestResultDescribe/heightTestResultDescribe/heightTestResultDescribe)*10000);
                        String bmiLevel = studentTranscriptMapper.findBMILevel_b(bmi);
                        bmi_index = new Transcript(student_number,studentName,"BMI指数",0,String .format("%.2f",bmi),bmiLevel);
                    }else {
                        bmi_index = new Transcript(student_number,studentName,"BMI指数",0,"","");
                    }
                    list.add(bmi_index);
                    //肺活量
                    Transcript vital_capacity = studentTranscriptMapper.findTheProjectTranscript_b(student_number, "肺活量",semesterOne);
                    if(vital_capacity == null){
                        vital_capacity = new Transcript(student_number,studentName,"肺活量",0,"","");
                    }
                    list.add(vital_capacity);
                    Transcript vital_capacity_index = null;
                    //肺活量体重指数
                    if(!vital_capacity.getTest_result_describe().equals("")&&!weight.getTest_result_describe().equals("")){
                        float weightTestResultDescribe = Float.parseFloat(weight.getTest_result_describe());
                        float vital_capacityTestResultDescribe = Float.parseFloat(vital_capacity.getTest_result_describe());
                        float vital_capacity_value = vital_capacityTestResultDescribe/weightTestResultDescribe;
                        String vital_capacity_Level = studentTranscriptMapper.findVital_CapacitLevel_b(vital_capacity_value);
                        Integer vital_capacityScore = studentTranscriptMapper.findVital_CapacitScore_b(vital_capacity_value);
                        vital_capacity_index = new Transcript(student_number,studentName,"肺活量体重指数",vital_capacityScore,String.format("%.2f",vital_capacity_value) + "",vital_capacity_Level);
                    }else {
                        vital_capacity_index = new Transcript(student_number,studentName,"肺活量体重指数",0,"","");
                    }
                    list.add(vital_capacity_index);
                    String[] projects = {"1000米跑","50米跑","引体向上","立定跳远","坐位体前屈"};
                    for (String project : projects) {
                        Transcript projectTranscript = studentTranscriptMapper.findTheProjectTranscript_b(student_number, project,semesterOne);
                        if(projectTranscript == null){
                            projectTranscript = new Transcript(student_number,studentName,project,0,"","");
                        }
                        list.add(projectTranscript);
                    }
                }else {
                    //身高
                    Transcript height = studentTranscriptMapper.findTheProjectTranscript_g(student_number, "身高",semesterOne);
                    if(height == null){
                        height = new Transcript(student_number,studentName,"身高",0,"","");
                    }
                    list.add(height);
                    //体重
                    Transcript weight = studentTranscriptMapper.findTheProjectTranscript_g(student_number, "体重",semesterOne);
                    if(weight == null){
                        weight = new Transcript(student_number,studentName,"体重",0,"","");
                    }
                    list.add(weight);
                    Transcript bmi_index = null;
                    //BMI指数
                    if(!height.getTest_result_describe().equals("")&&!weight.getTest_result_describe().equals("")){
                        float weightTestResultDescribe = Float.parseFloat(weight.getTest_result_describe());
                        float heightTestResultDescribe = Float.parseFloat(height.getTest_result_describe());
                        float bmi = (float) ((weightTestResultDescribe/heightTestResultDescribe/heightTestResultDescribe)*10000);
                        String bmiLevel = studentTranscriptMapper.findBMILevel_g(bmi);
                        bmi_index = new Transcript(student_number,studentName,"BMI指数",0,String .format("%.2f",bmi) + "",bmiLevel);
                    }else {
                        bmi_index = new Transcript(student_number,studentName,"BMI指数",0,"","");
                    }
                    list.add(bmi_index);
                    //肺活量
                    Transcript vital_capacity = studentTranscriptMapper.findTheProjectTranscript_g(student_number, "肺活量",semesterOne);
                    if(vital_capacity == null){
                        vital_capacity = new Transcript(student_number,studentName,"肺活量",0,"","");
                    }
                    list.add(vital_capacity);
                    Transcript vital_capacity_index = null;
                    //肺活量体重指数
                    if(!vital_capacity.getTest_result_describe().equals("")&&!weight.getTest_result_describe().equals("")){
                        float weightTestResultDescribe = Float.parseFloat(weight.getTest_result_describe());
                        float vital_capacityTestResultDescribe = Float.parseFloat(vital_capacity.getTest_result_describe());
                        float vital_capacity_value = vital_capacityTestResultDescribe/weightTestResultDescribe;
                        String vital_capacity_Level = studentTranscriptMapper.findVital_CapacitLevel_g(vital_capacity_value);
                        Integer vital_capacityScore = studentTranscriptMapper.findVital_CapacitScore_g(vital_capacity_value);
                        vital_capacity_index = new Transcript(student_number,studentName,"肺活量体重指数",vital_capacityScore,String.format("%.2f",vital_capacity_value),vital_capacity_Level);
                    }else {
                        vital_capacity_index = new Transcript(student_number,studentName,"肺活量体重指数",0,"","");
                    }
                    list.add(vital_capacity_index);
                    String[] projects = {"1000米跑","50米跑","引体向上","立定跳远","坐位体前屈"};
                    for (String project : projects) {
                        Transcript projectTranscript = studentTranscriptMapper.findTheProjectTranscript_g(student_number, project,semesterOne);
                        if(projectTranscript == null){
                            projectTranscript = new Transcript(student_number,studentName,project,0,"","");
                        }
                        list.add(projectTranscript);
                    }
                }
                linkedHashMap.put(semesterOne,list);
            }
        }

        return linkedHashMap;
    }
}

package com.jinqshen.weixin.service;

import com.jinqshen.weixin.vo.Transcript;

import java.util.List;
import java.util.Map;

/**
 * 学生端  成绩查询服务层
 */
public interface StudentTranscriptService {

    public Map<String, List<Transcript>> getFinacoTranscript(int student_number,boolean newest);

}

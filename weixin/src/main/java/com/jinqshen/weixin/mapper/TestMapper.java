package com.jinqshen.weixin.mapper;

import org.springframework.stereotype.Repository;

import com.jinqshen.weixin.pojo.entity.Players;

@Repository
public interface TestMapper {

	public Integer insertPlayer(Players players);
}

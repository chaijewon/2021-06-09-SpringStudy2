package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class ChatBotDAO {
	@Autowired
    private ChatBotMapper mapper;
	
	public List<String> foodHouseGetNameList()
	{
		return mapper.foodHouseGetNameList();
	}
	
	public FoodLocationVO foodDetailData(String name)
	{
		return mapper.foodDetailData(name);
	}
}

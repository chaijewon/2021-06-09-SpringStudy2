package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public class EmpDAO {
	@Autowired
    private EmpMapper mapper;// interface => mybatis에서 자동 구현 
	
	public List<EmpVO> empListData()
	{
		return mapper.empListData();
	}
	public EmpVO empDetailData(int empno)
	{
		return mapper.empDetailData(empno);
	}
}

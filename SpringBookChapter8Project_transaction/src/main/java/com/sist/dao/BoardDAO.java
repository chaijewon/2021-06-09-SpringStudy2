package com.sist.dao;
// 오라클과 연결만 담당 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {
   @Autowired
   private BoardMapper mapper; //instanceof 연산자 (객체 비교)
}

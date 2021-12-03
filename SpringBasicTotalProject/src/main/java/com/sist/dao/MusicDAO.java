package com.sist.dao;
/*
 *   1. JDBC 
 *   2. MyBatis : XML
 *   2-1. Annotation 
 *        <select id="musicList" resultType="MusicVO" paramerType="hashMap">
 *        => public MusicVO musicList(Map map)
 *        
 *        => SELECT pwd FROM member WHERE id='aaa';
 *                ======
 *                String
 *           SELECT COUNT(*) FROM member WHERE id='aaa';
 *                ======
 *                int
 *           SELECT pwd,name,address,sex FROM member WHERE id='aaa';
 *                ======
 *                MemberVO
 */
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class MusicDAO {
    @Autowired
    private MusicMapper mapper;
    
    public List<MusicVO> musicListData1(){
    	return mapper.musicListData1();
    }
    
    public List<MusicVO> musicListData2(){
    	return mapper.musicListData2();
    }
}














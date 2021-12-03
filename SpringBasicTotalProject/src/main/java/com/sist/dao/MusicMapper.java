package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
/*
 *  NO     NOT NULL NUMBER         
POSTER          VARCHAR2(1000) 
TITLE           VARCHAR2(200)  
SINGER          VARCHAR2(100)  
ALBUM           VARCHAR2(200)  
OK              VARCHAR2(10)   
KEY             VARCHAR2(100)
 */
import org.apache.ibatis.annotations.Select;
// as => 1) 변수명과 컬럼명이 다를 경우 , 2) 내장 함수를 이용하는 경우 
/*
 *   SELECT COUNT(*) as no FROM member;
 *          ========
 *          rs.getInt("COUNT(*)") => 오류 
 *                    ===========
 *                      컬럼명 , 인덱스 
 *                      =====
 */
public interface MusicMapper {
    @Select("SELECT no as melon_no,poster as melon_poster,title as melon_title,"
    	   +"singer as melon_singer,album as melon_album,key as melon_key "
    	   +"FROM melon_cjw ORDER BY no ASC")
    public List<MusicVO> musicListData1(); // #{} => 여러개 (~VO,Map)
    /*
     *    while(rs.next())
     *    {
     *       MusicVO vo=new MusicVO();
     *       vo.setMelon_no(rs.getInt("no"))
     *    }
     */
    // JOIN 
    @Results({
    	@Result(property="melon_no",column="no",javaType=java.lang.Integer.class),
    	@Result(property="melon_poster",column="poster",javaType=java.lang.String.class),
    	@Result(property="melon_title",column="title",javaType=java.lang.String.class),
    	@Result(property="melon_singer",column="singer",javaType=java.lang.String.class),
    	@Result(property="melon_album",column="album",javaType=java.lang.String.class),
    	@Result(property="melon_key",column="key",javaType=java.lang.String.class)
    })
    @Select("SELECT no,poster,title,"
     	   +"singer,album,key "
     	   +"FROM melon_cjw ORDER BY no ASC")
    public List<MusicVO> musicListData2();
    
    // 데이터베이스 => 중복이 안된 값이 반드시 요구 (테이블 : primary key) => foregin key
    //     기본키                                                                                              참조키
    //     ===== (번호:자동 증가)  =========> id
}










package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sist.mapper.*;
import com.sist.vo.CartVO;
import com.sist.vo.GoodsVO;
// 셋팅 => 동작 (메뉴얼) => XML , 요청 => 어노테이션  
/*
 *   1. 사용자 정의 클래스  : 요청 처리 (@Controller,@RestController) <==> Model
 *      => 요청 받기 , 요청 처리 , 결과값 보내기 
 *        ========  =======  ==========
 *        DispatcherServlet , DAO , ViewResolver
 *          => 매개변수로 이용 , HttpServletRequest (보안 => 사용자의 ip)
 *             => 일반 변수 (검색어 , 페이지번호 , 상세보기 => 번호) : int , String , double ....
 *             => 커맨드 객체 (VO: 클래스 객체 단위 => 글쓰기 , 회원가입)
 *             => List (태그의 name : file[0] , file[1]...) 
 *             => 정답은 없다 
 *                상세보기 : <a href="detail.do?no=1&page=1">
 *                            @RequestMapping("")
 *                         => public String detail(int no,int page)
 *             ** DispatcherServlet => *.do (web.xml) 
 *                                  => web.xml을 찾는다  (.do 사용자 정의) => .nhn , / => 확장자 없이 사용
 *                => 사용자 요청 => 구분 
 *                   @RequestMapping() , @GetMapping() , @PostMapping() => 메소드 호출 
 *                => JSP를 찾을 수 있게 경로명/확장명 
 *         DAO 
 *          => 
 *            1.SQL문장 만들기 
 *            2.PreparedStatement
 *            3.문장 전송 
 *            4.결과값 받기 
 *            5.VO,LIST에 값을 채운다
 *            ===========================  1. sql문장 , 2. ?에 어떤값인지 , 3.어떤 VO로 받는지 모른다
 *                           <select>
 *                             SELECT 
 *                           </select>
 *                                                     paramterType="VO" , resultType=""
 *                           @Select("SELECT~~")
 *                                                     리턴형 메소드명(매개변수)
 *                                                     =====       =======
 *                                                     resultType   paramterType
 *   2. MyBatis 셋팅 
 *        = getConnection / disConnection => SqlSessionFactoryBean (오라클 정보 : BasicDataSource)
 *        = mapper(인터페이스 => 선언) => 실제 구현 => MapperFactoryBean 
 */
@Repository
// CartDAO dao=new CartDAO() => @Autowired가 작동하지 않는다 (mapper=null)
public class CartDAO {
    @Autowired
    private CartMapper mapper;
    
    public void goodsInsert(GoodsVO vo)
    {
    	mapper.goodsInsert(vo);
    }
    
    public List<GoodsVO> goodsListData(Map map)
    {
    	return mapper.goodsListData(map);
    }
    public int goodsTotalPage()
    {
    	return mapper.goodsTotalPage();
    }
    public GoodsVO goodsDetailData(int product_id)
    {
    	return mapper.goodsDetailData(product_id);
    }
    public void cartInsert(CartVO vo)
    {
    	mapper.cartInsert(vo);
    }
    public List<CartVO> cartListData(String id)
    {
    	return mapper.cartListData(id);
    }
}







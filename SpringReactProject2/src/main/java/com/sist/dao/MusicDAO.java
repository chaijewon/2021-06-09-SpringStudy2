package com.sist.dao;
/*
 *   react 
 *   ======
 *     1. class형 
 *     2. function형  => 전역변수(X) , 지역변수(O)
 *     3. hooks (16) => state 변수 사용이 가능 => useState 
 *     4. react의 단점 => 단방향 
 *          App
 *           |
 *         Movie
 *           |
 *        MovieDetail
 *        => 양방향 ==> Redux(MVC)   ==> store (저장소)  => vue(MVC=>vuex)
 *                                                       MVVM 
 *        Back-End (MVC) , Front-End(MVC)  => 4:3:3
 *                                            react / vue / jquery(ajax)
 *                                           ===============
 *                                            framework (saga/mobx:우아한 형제들(배민),한국양행:다이소)
 *                                            
 *        => react 
 *               new Vue                beforeMount        디렉티브 v-for      mounted
 *           생성 (constructor()) => componentWillMount() => render() => componentDidMount()
 *                 변수선언                           변수에 값을 설정                 화면 출력 (UI)      메모리 저장 
 *                 
 *                 메모리에 저장 
 *                 ==========
 *                   가상메모리     (가상돔)      실제메모리(돔:트리형태의 저장 방식)  ==> 리스트 , 트리형태 
 *                   ========
 *                     제작된 UI를 올려준다     === 브라우저에 출력
 *                                    비교 (diff) =========> 틀린부분만 수정
 *                     StringBuffer       String 
 *                     ================================================ 디블버퍼링 
 *                     Virtual DOM은 말 그대로 실제 DOM과 똑같은 가상의 돔을 만드는 것입니다.
                                              리액트에서는 데이터가 변하게 되면 Real DOM이 업데이트되는 것이 아닌 Virtual DOM이 업데이트되고 
                                              이전 DOM과 비교하여 바뀐 부분만 Real DOM에 적용하는 방식입니다.
 */
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class MusicDAO {
   @Autowired
   private MusicMapper mapper;
   
   public List<MusicVO> musicListData()
   {
	   return mapper.musicListData(); // 데이터 => react / kotlin  (JSON)
   }
   
   public List<MovieVO> movieListData()
   {
	   return mapper.movieListData();
   }
}

















package org.zerock.b01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.b01.domain.Board;
import org.zerock.b01.repository.search.BoardSearch;

//JpaRepository 상속후 <테이블클래스명, ID 타입>
public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {
    //CRUD 자동 생성
  @Query(value = "select now()", nativeQuery = true)
  String getTime();
}

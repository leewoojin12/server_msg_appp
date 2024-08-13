package com.woojin.repository;

import com.woojin.vo.MemberVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainRepository extends JpaRepository<MemberVO, Long> {
	
	
	
}

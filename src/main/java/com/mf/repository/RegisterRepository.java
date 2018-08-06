package com.mf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mf.entity.Register;

public interface RegisterRepository  extends JpaRepository<Register, Integer>{
	
	/**
	 * 查询注册信息
	 * @param id
	 * @return
	 */
	@Query(value="SELECT * FROM t_register order by id limit 1",nativeQuery=true)
	public Register findRegister();
}

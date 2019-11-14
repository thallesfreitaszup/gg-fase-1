package com.zup.spring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zup.spring.model.Pessoa;
@Transactional
@Repository
//Utilizo JPARepository por ser mais completo que CrudRepository
public interface PessoaRepository extends JpaRepository<Pessoa,String>{
	public Pessoa findByCpf(String cpf);
	@Modifying
	@Query("update Pessoa p set p.nome = ?1 where p.cpf = ?2")
	public int updateByName(String name,String cpf);
	public int deleteByCpf(String cpf);
}

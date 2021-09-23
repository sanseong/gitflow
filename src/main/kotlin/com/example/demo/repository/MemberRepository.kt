package com.example.demo.repository

import com.example.demo.domain.Member
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository//스프링 빈으로 등록, JPA예외를 스프링 기반 예외로 변환
class MemberRepository(
    @PersistenceContext// 엔티티 매니저 주입, @PersistenceUnit: 엔티티 매니저 팩토리 주입
    val em:EntityManager
){
    fun save(member: Member){
        em.persist(member)
    }
    fun findOne(id:Long):Member{
        return em.find(Member::class.java, id)
    }
    fun findAll():MutableList<Member>{
        return em.createQuery("select m from Member m", Member::class.java).resultList
    }
    fun findByName(name:String): MutableList<Member>{
        return em. createQuery("select m from Member m where m.name = :name", Member::class.java)
            .setParameter("name", name).resultList
    }
}

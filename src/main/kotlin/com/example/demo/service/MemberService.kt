package com.example.demo.service

import com.example.demo.domain.Member
import com.example.demo.repository.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)//데이터 변경이 없는 읽기 전용 메서드에 사용(근데 왜 변경이?, data class와 다른 점?)
class MemberService(
    @Autowired
    val memberRepository: MemberRepository
) {


    @Transactional//변경
    fun join(member:Member):Long{
        validateDuplicateMember(member)
        memberRepository.save(member)
        return member.id

    }

    fun validateDuplicateMember(member: Member) {
        val findMember : MutableList<Member> = memberRepository.findByName(member.name)
        if(findMember.isNotEmpty()){
            throw IllegalStateException("이미 존재하는 회원")
        }
    }
    fun findMembers():MutableList<Member> {
        return memberRepository.findAll()
    }
    fun findOne(memberId:Long):Member{
        return memberRepository.findOne(memberId)
    }

}

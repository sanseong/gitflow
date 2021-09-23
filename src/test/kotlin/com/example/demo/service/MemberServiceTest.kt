package com.example.demo.service

import com.example.demo.domain.Member
import com.example.demo.repository.MemberRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional

@RunWith(SpringRunner::class)
@SpringBootTest
@Transactional
class MemberServiceTest(

){
    @Autowired
    lateinit var memberService: MemberService
    @Autowired
    lateinit var memberRepository: MemberRepository

    @Test
    fun 회원가입() {
        var member:Member = Member()
            member.name = "kim"
            val saveId: Long = memberService.join(member)
            assertEquals(member, memberRepository.findOne(saveId))
    }
}
package com.ssafy.realty.user.adapter.out;

import com.ssafy.realty.user.adapter.out.entity.UserJpaEntity;
import com.ssafy.realty.user.adapter.out.repository.UserJpaRepository;
import com.ssafy.realty.user.domain.UserDomain;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@DisplayName("어댑터 레파지토리 통합 테스트")
class CommandUserPersistenceJpaAdapterTest {
    @Autowired
    private CommandUserPersistenceJpaAdapter commandUserPersistenceJpaAdapter;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Test
    @DisplayName("회원 가입")
    @Transactional
    public void regist(){
        // given
        UserDomain userDomain = UserDomain.init(null, "qkfka9045@gmail.com", encoder.encode("a1234567"), "nick");

        // when
        commandUserPersistenceJpaAdapter.regist(userDomain);
        List<UserJpaEntity> allData = userJpaRepository.findAll();
        Long lastAutoIncrementId = allData.get(allData.size()-1).getId();
        UserJpaEntity saved = userJpaRepository.findById(lastAutoIncrementId).get();

        // then
        assertThat(saved.getUsername()).isEqualTo(userDomain.getUserDomainData().getUsername());
        assertTrue(encoder.matches("a1234567", saved.getPassword()));
        assertThat(saved.getRole()).isEqualTo(userDomain.getUserDomainData().getRole());
    }

    @Test
    @DisplayName("삭제")
    @Transactional
    public void delete(){
        // given
        UserDomain userDomain = UserDomain.init(null, "qkfka9045@gmail.com", encoder.encode("a1234567"), "nick");
        commandUserPersistenceJpaAdapter.regist(userDomain);

        List<UserJpaEntity> allData = userJpaRepository.findAll();
        Long lastAutoIncrementId = allData.get(allData.size()-1).getId();

        // when
        commandUserPersistenceJpaAdapter.delete(lastAutoIncrementId);

        // then
        assertThrows(NoSuchElementException.class, () -> userJpaRepository.findById(1L).get());
    }

    @Test
    @DisplayName("회원 정보 수정")
    @Transactional
    public void update(){
        // given
        UserDomain userDomain = UserDomain.init(null, "qkfka9045@gmail.com", encoder.encode("a1234567"), "nick");
        commandUserPersistenceJpaAdapter.regist(userDomain);

        List<UserJpaEntity> allData = userJpaRepository.findAll();
        Long lastAutoIncrementId = allData.get(allData.size()-1).getId();

        UserDomain updateWant = UserDomain.init(lastAutoIncrementId, null, encoder.encode("b1234567"), "changeNick");

        // when
        commandUserPersistenceJpaAdapter.update(updateWant);
        UserJpaEntity updated = userJpaRepository.findById(lastAutoIncrementId).get();

        // then
        assertThat(updated.getUsername()).isEqualTo(userDomain.getUserDomainData().getUsername());
        assertTrue(encoder.matches("b1234567", updated.getPassword()));
        assertThat(updated.getNickname()).isEqualTo(updateWant.getUserDomainData().getNickname());
    }
}
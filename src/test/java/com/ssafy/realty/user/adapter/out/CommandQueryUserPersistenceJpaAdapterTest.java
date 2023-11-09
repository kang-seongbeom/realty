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
class CommandQueryUserPersistenceJpaAdapterTest {

    @Autowired
    private QueryUserPersistenceJpaAdapter queryUserPersistenceJpaAdapter;

    @Autowired
    private CommandUserPersistenceJpaAdapter commandUserPersistenceJpaAdapter;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;


    private UserDomain defaultUserDomain(){
        return UserDomain.init(null, "qkfka9045@gmail.com", encoder.encode("a1234567"), "nick");
    }

    private void registUser(UserDomain userDomain){
        commandUserPersistenceJpaAdapter.regist(userDomain);
    }

    private Long getLastAutoIncrementId(){
        List<UserJpaEntity> allData = userJpaRepository.findAll();
        return allData.get(allData.size()-1).getId();
    }

    @Test
    @DisplayName("회원 가입")
    @Transactional
    public void regist(){
        // given
        UserDomain user = defaultUserDomain();

        // when
        registUser(user);
        UserJpaEntity saved = userJpaRepository.findById(getLastAutoIncrementId()).get();

        // then
        assertThat(saved.getUsername()).isEqualTo(user.getUserDomainData().getUsername());
        assertTrue(encoder.matches("a1234567", saved.getPassword()));
        assertThat(saved.getRole()).isEqualTo(user.getUserDomainData().getRole());
    }

    @Test
    @DisplayName("회원 탈퇴")
    @Transactional
    public void delete(){
        // given
        registUser(defaultUserDomain());
        Long lastAutoIncrementId = getLastAutoIncrementId();

        // when
        commandUserPersistenceJpaAdapter.delete(lastAutoIncrementId);

        // then
        assertThrows(NoSuchElementException.class, () -> userJpaRepository.findById(lastAutoIncrementId).get());
    }

    @Test
    @DisplayName("회원 정보 수정")
    @Transactional
    public void update(){
        // given
        UserDomain userDomain = defaultUserDomain();
        registUser(userDomain);
        Long lastAutoIncrementId = getLastAutoIncrementId();

        UserDomain updateWant = UserDomain.init(lastAutoIncrementId, null, encoder.encode("b1234567"), "changeNick");

        // when
        commandUserPersistenceJpaAdapter.update(updateWant);
        UserJpaEntity updated = userJpaRepository.findById(lastAutoIncrementId).get();

        // then
        assertThat(updated.getUsername()).isEqualTo(userDomain.getUserDomainData().getUsername());
        assertTrue(encoder.matches("b1234567", updated.getPassword()));
        assertThat(updated.getNickname()).isEqualTo(updateWant.getUserDomainData().getNickname());
    }

    @Test
    @DisplayName("회원 정보 조회")
    public void query(){
        // given
        UserDomain userDomain = defaultUserDomain();
        registUser(userDomain);
        Long lastAutoIncrementId = getLastAutoIncrementId();

        // when
        UserDomain queried = queryUserPersistenceJpaAdapter.query(userDomain.getUserDomainData().getUsername());

        // then
        assertThat(queried.getUserDomainId().getValue()).isEqualTo(lastAutoIncrementId);
        assertThat(queried.getUserDomainData().getUsername()).isEqualTo(userDomain.getUserDomainData().getUsername());
        assertThat(queried.getUserDomainData().getPassword()).isEqualTo(userDomain.getUserDomainData().getPassword());
        assertThat(queried.getUserDomainData().getNickname()).isEqualTo(userDomain.getUserDomainData().getNickname());
        assertThat(queried.getUserDomainData().getRole()).isEqualTo(userDomain.getUserDomainData().getRole());
    }
}
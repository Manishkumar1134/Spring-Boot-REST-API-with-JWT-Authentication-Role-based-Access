package com.springSecurityP1.SpringSecurity.service;

import com.springSecurityP1.SpringSecurity.entity.Session;
import com.springSecurityP1.SpringSecurity.entity.User;
import com.springSecurityP1.SpringSecurity.repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SessionService {
    private final SessionRepository sessionRepository;
    private final UserService userService;
    private final int SESSION_LIMIT = 2;

    public void generateNewSession(User user, String refreshToken) {
        List<Session> sessions = sessionRepository.findByUser(user);
        if(sessions.size() == SESSION_LIMIT){
            sessions.sort(Comparator.comparing(Session::getLastCreatedAt));
            Session leastRecentlyUsedSession = sessions.getFirst();
            sessionRepository.delete(leastRecentlyUsedSession);
        }
        Session newSession = Session.builder()
                .user(user)
                .refreshToken(refreshToken)
                .build();
        sessionRepository.save(newSession);
    }

    public void validateSession(String refreshToken) {
        Session session = sessionRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new SessionAuthenticationException("Session is not found."));
        session.setLastCreatedAt(LocalDateTime.now());
        sessionRepository.save(session);
    }

    public void logout(String sessionToken) {
        Session session = sessionRepository.findByRefreshToken(sessionToken)
                .orElseThrow(() -> new SessionAuthenticationException("Session token not found"));
        log.info("Fetch Successfully");
        sessionRepository.delete(session);// only deletes the matching session
        log.info("Delete Successfully");
    }

}

package com.troyboot.java.system.service;

import com.troyboot.java.system.domain.UserOnline;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SessionService {

    List<UserOnline> list();

    boolean forceLogout(String sessionId);

}

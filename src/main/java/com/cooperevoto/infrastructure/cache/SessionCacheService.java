package com.cooperevoto.infrastructure.cache;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class SessionCacheService {

    private final StringRedisTemplate redisTemplate;

    public SessionCacheService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void abrirSessao(String pautaId, long duracaoEmMinutos) {
        redisTemplate.opsForValue().set("sessao:" + pautaId, "ABERTA", duracaoEmMinutos, TimeUnit.MINUTES);
    }

    public boolean isSessaoAberta(String pautaId) {
        return Boolean.TRUE.equals(redisTemplate.hasKey("sessao:" + pautaId));
    }
}

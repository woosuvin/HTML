package com.itwill.spring3.repository.member;

public enum Role {
    
    // 상수 정의
    USER("ROLE_USER", "USER"), ADMIN("ROLE_ADMIN", "ADMIN"); // (key, name) 선언하는 순서 중요, user: 0 / admin: 1
    
    private final String key; 
    private final String name;
    
    Role(String key, String name) {
        this.key = key;
        this.name = name;
    }
    
    public String getKey() {
        return this.key;
    }
    
}

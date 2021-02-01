package com.self.webservice.global.config.auth.dto;

import com.self.webservice.domain.user.Role;
import com.self.webservice.domain.user.User;
import lombok.*;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(value = AccessLevel.PRIVATE)
@Getter
public class OAuthAttributes {

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                                .name( (String) attributes.get("name" ) )
                                .email( (String) attributes.get("email" ) )
                                .picture( (String) attributes.get("picture") )
                                .attributes( attributes )
                                .nameAttributeKey( userNameAttributeName )
                                .build();
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                // 가입 시점에는 게스트 권한을 부여한다.
                .role(Role.GUEST)
                .build();
    }

}

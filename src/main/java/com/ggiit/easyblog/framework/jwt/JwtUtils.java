package com.ggiit.easyblog.framework.jwt;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.ggiit.easyblog.framework.jwt.entity.JwtUser;
import com.ggiit.easyblog.project.system.user.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;

/**
 * JWT工具类
 */
@Component
public class JwtUtils {
    /**
     * 刷新Token的角色
     */
    public static final String ROLE_REFRESH_TOKEN = "ROLE_REFRESH_TOKEN";

    /**
     * 用户ID
     */
    private static final String CLAIM_KEY_USER_ID = "id";
    /**
     * 用户名
     */
    private static final String CLAIM_KEY_USER_NAME = "username";
    /**
     * 用户昵称
     */
    private static final String CLAIM_KEY_NICK_NAME = "nickname";
    /**
     * 用户头像
     */
    private static final String CLAIM_KEY_AVATAR = "avatar";
    /**
     * 用户Email
     */
    private static final String CLAIM_KEY_EMAIL = "email";
    /**
     * 用户状态
     */
    private static final String CLAIM_KEY_STATE = "state";
    /**
     * 用户电话
     */
    private static final String CLAIM_KEY_PHONE = "phone";
    /**
     * 用户上次登陆IP
     */
    private static final String CLAIM_KEY_LOGIN_IP = "loginIp";
    /**
     * 用户上次登陆时间
     */
    private static final String CLAIM_KEY_LOGIN_DATE = "loginDate";
    /**
     * 用户描述
     */
    private static final String CLAIM_KEY_REMARK = "remark";
    /**
     * 用户权限
     */
    private static final String CLAIM_KEY_AUTHORITIES = "authorities";


    /**
     * 密匙key
     */
    @Value("${jwt.secret}")
    private String secret;
    /**
     * 过期时间
     */
    @Value("${jwt.expiration}")
    private Long expiration;
    /**
     * 刷新token时间
     */
    @Value("${jwt.refresh_token}")
    private Long refresh_token;
    /**
     * 签名算法
     */
    private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    /**
     * 通过Token获取用户
     *
     * @param token token
     * @return 用户对象
     */
    public User getUserFromToken(String token) {
        //用户对象
        JwtUser user;
        try {
            //获取Token负载
            final Claims claims = getClaimsFromToken(token);
            //获取用户id
            String userId = (String) claims.get(CLAIM_KEY_USER_ID);
            //获取用户名
            String username = claims.getSubject();
            //获取用户昵称
            String nickname = (String) claims.get(CLAIM_KEY_NICK_NAME);
            //获取用户头像
            String avatar = (String) claims.get(CLAIM_KEY_AVATAR);
            //获取Email
            String email = (String) claims.get(CLAIM_KEY_EMAIL);
            //获取用户是否禁用
            boolean state = (boolean) claims.get(CLAIM_KEY_STATE);
            //获取用户电话
            String phone = (String) claims.get(CLAIM_KEY_PHONE);
            //获取用户最后登陆ip
            String loginIp = (String) claims.get(CLAIM_KEY_LOGIN_IP);
            //获取用户最后登陆时间
            Date loginDate = DateUtil.date((Long)claims.get(CLAIM_KEY_LOGIN_DATE));
            //获取用户描述
            String remark = (String) claims.get(CLAIM_KEY_REMARK);
            //获取权限字符串集合
            List<?> auths = (List<?>) claims.get(CLAIM_KEY_AUTHORITIES);
            //权限集合+
            Collection<? extends GrantedAuthority> authorities = parseArrayToAuthorities(auths);
            user = new JwtUser();
            //给user赋值
            user.setId(userId);
            user.setUsername(username);
            user.setNickname(nickname);
            user.setAvatar(avatar);
            user.setEmail(email);
            user.setState(state);
            user.setPhone(phone);
            user.setLoginIp(loginIp);
            user.setLoginDate(loginDate);
            user.setRemark(remark);
            user.setAuthorities(authorities);
        } catch (Exception e) {
            user = null;
        }
        return user;
    }

    /**
     * 根据Token获取负载
     *
     * @param token token
     * @return Claims 负载
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(generalKey())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 生成过期时间
     *
     * @param expiration Long类型过期时间
     * @return Date类型过期时间
     */
    private Date generateExpirationDate(long expiration) {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * Token是否过期
     *
     * @param token token
     * @return true/false
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 根据token 获取过期时间
     *
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    /**
     * 根据token 获取生成时间
     *
     * @param token
     * @return
     */
    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = claims.getIssuedAt();
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    /**
     * 是否在上次密码重置之前创建
     *
     * @param created
     * @param lastPasswordReset
     * @return
     */
    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    /**
     * 生成负载
     *
     * @param user 用户对象
     * @return 负载Map
     */
    private Map<String, Object> generateClaims(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USER_ID, user.getId());
        claims.put(CLAIM_KEY_USER_NAME, user.getUsername());
        claims.put(CLAIM_KEY_NICK_NAME, user.getNickname());
        claims.put(CLAIM_KEY_EMAIL, user.getEmail());
        claims.put(CLAIM_KEY_STATE, user.getState());
        claims.put(CLAIM_KEY_PHONE, user.getPhone());
        claims.put(CLAIM_KEY_LOGIN_IP, user.getLoginIp());
        claims.put(CLAIM_KEY_LOGIN_DATE, user.getLoginDate());
        claims.put(CLAIM_KEY_REMARK, user.getRemark());
        claims.put(CLAIM_KEY_AUTHORITIES, authoritiesToArray(user.getAuthorities()));
        return claims;
    }

    /**
     * 根据主要信息和负载生成认证Token
     *
     * @param subject
     * @param claims
     * @return
     */
    private String generateAccessToken(String subject, Map<String, Object> claims) {
        return generateToken(subject, claims, expiration);
    }

    /**
     * 用户权限集合转用户权限字符串
     *
     * @param authorities 用户权限集合
     * @return 用户权限字符串
     */
    private List authoritiesToArray(Collection<? extends GrantedAuthority> authorities) {
        List<String> list = new ArrayList<>();
        for (GrantedAuthority ga : authorities) {
            list.add(ga.getAuthority());
        }
        return list;
    }

    /**
     * 将权限字符串集合转换为Security要求的权限集合
     *
     * @param auths 权限字符串集合
     * @return Security要求的权限集合
     */
    private Collection<? extends GrantedAuthority> parseArrayToAuthorities(List auths) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority authority;
        for (Object auth : auths) {
            authority = new SimpleGrantedAuthority(auth.toString());
            authorities.add(authority);
        }
        return authorities;
    }

    /**
     * 根据用戶对象生成Token
     *
     * @param userDetails 用戶對象
     * @return Token
     */
    public String generateRefreshToken(UserDetails userDetails) {
        User user = (User) userDetails;
        Map<String, Object> claims = generateClaims(user);
        // 只授于更新 token 的权限
        String roles[] = new String[]{ROLE_REFRESH_TOKEN};
        claims.put(CLAIM_KEY_AUTHORITIES, JSONUtil.toJsonStr(JSONUtil.toJsonStr(roles)));
        return generateRefreshToken(user.getUsername(), claims);
    }

    /**
     * 根据用户信息 生成token
     *
     * @param userDetails
     * @return
     */
    public String generateAccessToken(UserDetails userDetails) {
        User user = (User) userDetails;
        Map<String, Object> claims = generateClaims(user);
        claims.put(CLAIM_KEY_AUTHORITIES, JSONUtil.toJsonStr(authoritiesToArray(user.getAuthorities())));
        return generateAccessToken(user.getUsername(), claims);
    }



    /**
     * 根据sub主要信息和负载生成刷新Token
     *
     * @param subject 主要信息
     * @param claims  负载
     * @return Token
     */
    private String generateRefreshToken(String subject, Map<String, Object> claims) {
        return generateToken(subject, claims, expiration);
    }

    /**
     * Token是否可以刷新
     *
     * @param token             token
     * @param lastPasswordReset 密碼最後更改時間
     * @return true/false
     */
    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && (!isTokenExpired(token));
    }

    /**
     * 根据过期Token刷新Token
     *
     * @param token 过期Token
     * @return 新Token
     */
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            refreshedToken = generateAccessToken(claims.getSubject(), claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * 由字符串生成加密key
     * @return
     */
    public  SecretKey generalKey() {
        try {
            // 一般服务端自定义一个KEY值，转换成byte[] 再转成SecretKey
            String stringKey = "UnWhVr6cKjw5gzwnR6j5FjYpox6kRoyHbvaTwcfexb11QrKrvVeoGGP3YD3cxlKvyJL6lrK0XX0oMGcA5nPIq7ucGeUFFZ7sIuR";
            byte[] encodedKey = Base64.decode(stringKey);
            SecretKey key = Keys.hmacShaKeyFor(encodedKey);

            //如果嫌麻烦，可以直接使用jjwt 提供key算法
//            SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256); //or HS384 or HS512
            return key;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据sub主要信息和负载及过期时间生成Token
     *
     * @param subject    主要信息
     * @param claims     负载
     * @param expiration 过期时间
     * @return Token
     */
    private String generateToken(String subject, Map<String, Object> claims, long expiration) {
        return Jwts.builder()
                // 自定义属性
                .setClaims(claims)
                // 主题
                .setSubject(subject)
                // JWT_ID
                .setId(UUID.randomUUID().toString())
                // 签发时间
                .setIssuedAt(new Date())
                // 过期时间
                .setExpiration(generateExpirationDate(expiration))
                //body压缩
                .compressWith(CompressionCodecs.DEFLATE)
                .signWith(generalKey())
                .compact();
    }


    /**
     * 验证Token是否有效
     *
     * @param token       token
     * @param userDetails 用戶對象
     * @return true/false
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        User user = (User) userDetails;
        //获取Token负载
        final Claims claims = getClaimsFromToken(token);
        final String userId = (String) claims.get(CLAIM_KEY_NICK_NAME);
        final String username = (String) claims.get(CLAIM_KEY_USER_NAME);
        // final Date created = getCreatedDateFromToken(token);
        // final Date expiration = getExpirationDateFromToken(token);
        return (userId.equals(user.getId())
                && username.equals(user.getUsername())
                && !isTokenExpired(token)
                /* && !isCreatedBeforeLastPasswordReset(created, userDetails.getLastPasswordResetDate()) */
        );
    }
}

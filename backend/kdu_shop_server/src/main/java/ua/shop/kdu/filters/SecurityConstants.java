package ua.shop.kdu.filters;

public class SecurityConstants {

    static final String SECRET = "keqwdgqetwerfweasfsdf";
    static final long EXPIRATION_TIME = 32_536_000;
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";

    public static final String SIGN_IN_URL = "/api/auth/sign-in";

}

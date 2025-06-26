package pe.com.dashboard.dashboard.config;

public final class ApiConstants {
    // Ruta base de la API y contexto de la aplicación
    public static final String APP_CONTEXT = "/dashboard";
    public static final String API_BASE_PATH = APP_CONTEXT + "/api/v1";

    // Rutas de autenticación
    public static final String AUTH_PATH = "/auth";
    public static final String LOGIN_PATH = "/login";
    public static final String FULL_LOGIN_PATH = API_BASE_PATH + AUTH_PATH + LOGIN_PATH; // /gadol/api/v1/auth/login
    public static final String LOGOUT_PATH = "/logout";

    // Rutas públicas (acceso sin autenticación)
    // public static final String PUBLIC_PATH = "/public";
    // public static final String REGISTER_PATH = "/register";

    // Rutas de administración
    // public static final String DASHBOARD_PATH = "/dashboard";
    // public static final String USERS_PATH = "/users";
    // public static final String ROLES_PATH = "/roles";
    // public static final String TELAS_PATH = "/telas";

    // Rutas de recursos estáticos
    public static final String STATIC_RESOURCES_PATH = "/static";
    public static final String CSS_PATH = "/css/**";

    // Configuración de cookies
    public static final String JWT_COOKIE_NAME = "jwtToken";
    public static final String REFRESH_TOKEN_COOKIE_NAME = "refreshToken";
    public static final String JWT_COOKIE_PATH = APP_CONTEXT;
    public static final int JWT_COOKIE_MAX_AGE_HOURS = 12;
    public static final int JWT_COOKIE_MAX_AGE_MS = JWT_COOKIE_MAX_AGE_HOURS * 60 * 60 * 1000;
    public static final int REFRESH_TOKEN_MAX_AGE_HOURS = 24;
    public static final int REFRESH_TOKEN_MAX_AGE_MS = REFRESH_TOKEN_MAX_AGE_HOURS * 60 * 60 * 1000;

    // Configuración de seguridad
    public static final boolean COOKIE_SECURE = !isDevelopment(); // Cambiar a true en producción con HTTPS
    public static final boolean COOKIE_HTTP_ONLY = true;
    public static final String COOKIE_SAME_SITE = "Lax";

    // Headers
    public static final String AUTH_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    // Configuración de recursos
    public static final String[] RESOURCE_LOCATIONS = {
            "classpath:/static/",
            "classpath:/public/",
            "classpath:/resources/",
            "classpath:/META-INF/resources/",
    };

    // Configuración de CORS
    public static final String[] ALLOWED_ORIGINS_DEV = { "http://localhost:3000", "http://localhost:4200" };
    public static final String[] ALLOWED_ORIGINS_PDN = { "https://production-domain.com" };
    public static final String[] ALLOWED_METHODS = { "GET", "POST", "PUT", "DELETE", "OPTIONS" };
    public static final String[] ALLOWED_HEADERS = {
            "Origin", "Accept", "X-Requested-With", "Content-Type",
            "Access-Control-Request-Method", "Access-Control-Request-Headers",
            AUTH_HEADER
    };
    public static final String[] EXPOSED_HEADERS = {
            "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"
    };

    // Configuración de Swagger (si lo usas)
    public static final String SWAGGER_PATH = "/swagger-ui/**";
    public static final String API_DOCS_PATH = "/v3/api-docs/**";
    public static final String SWAGGER_RESOURCES_PATH = "/swagger-resources/**";

    // Configuración de caché para recursos estáticos (en segundos)
    public static final int CACHE_PERIOD_STATIC_RESOURCES = 365 * 24 * 60 * 60; // 1 año

    private static boolean isDevelopment() {
        return java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments().toString()
                .contains("jdwp");
    }

    // Evitar instanciación
    private ApiConstants() {
        throw new AssertionError("No ApiConstants instances for you!");
    }
}

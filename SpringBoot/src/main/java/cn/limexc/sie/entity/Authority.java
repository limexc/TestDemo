package cn.limexc.sie.entity;

public class Authority {
    /**
     * 用户权限
     */
    public static final String USER = "用户管理";
    public static final String USER_INSERT = "新增用户";
    public static final String USER_UPDATE = "修改用户";
    public static final String USER_SELECT = "查询用户";
    public static final String USER_DELETE = "删除用户";
    public static final String USER_SELECT_LIKE = "用户模糊查询";

    /**
     * 角色权限
     */
    public static final String ROLE = "角色管理";
    public static final String ROLE_INSERT = "新增角色";
    public static final String ROLE_UPDATE = "修改角色";
    public static final String ROLE_SELECT = "查询角色";
    public static final String ROLE_DELETE = "删除角色";
    public static final String ROLE_SELECT_LIKE = "角色模糊查询";

    /**
     * 菜单权限
     */
    public static final String MENU = "菜单管理";
    public static final String MENU_INSERT = "新增菜单";
    public static final String MENU_UPDATE = "修改菜单";
    public static final String MENU_SELECT = "查询菜单";
    public static final String MENU_DELETE = "删除菜单";
    public static final String MENU_SELECT_LIKE = "菜单模糊查询";

    /**
     * 用户角色权限
     */
    public static final String ROLE_USER = "用户权限管理";
    public static final String ROLE_USER_SET = "设置用户角色";
    public static final String ROLE_USER_SELECT = "查看用户角色";

    /**
     * 角色菜单权限
     */
    public static final String ROLE_MENU = "角色权限管理";
    public static final String ROLE_MENU_SELECT = "查看角色权限";
    public static final String ROLE_MENU_SET = "设置角色权限";

    @SuppressWarnings("all")
    private final static Authority instance = new Authority();

    private Authority(){}

    public static Authority getInstance() {
        return instance;
    }
}

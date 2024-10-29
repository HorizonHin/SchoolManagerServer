import java.io.Serial;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import com.schoolmanagerserver.pojos.Permission;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import java.io.Serializable;

@Data
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    //暂时留着GrantedAuthority，没用的话后续删掉
    private Collection<? extends GrantedAuthority> authorities;
    private String avatar;
    private Date createTime;
    private Long departmentId;
    private String departmentName;
    private String email;
    private Integer gender;
    private Long id;
    private boolean isAccountExpired = false;
    private boolean isAccountLocked = false;
    private Integer isAdmin;
    private boolean isCredentialsExpired = false;
    private Integer isDelete;
    private boolean isEnabled = true;
    private String nickName;
    private String password;
    private List<Permission> permissionList;
    private String phone;
    private String realName;
    private List<String> roleNameList;
    private Date updateTime;
    private String username;

    // 省略getter和setter方法

    // ... 其他方法
}
package edu.fzu.anop.service;

import edu.fzu.anop.pojo.UserInfo;
import edu.fzu.anop.resource.UserInfoResource;
import edu.fzu.anop.resource.UserInfoUpdateResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author SilverBay
 */
public interface UserInfoService {

    /**
     * 根据用户ID获取用户信息
     *
     * @param userId
     * @return 若找到，则返回对应用户对象；否则返回null
     */
    UserInfo getUserInfoByUserId(int userId);

    /**
     * 根据用户ID获取用户信息请求对应的资源类
     *
     * @param userId
     * @return 若找到，返回对应资源类对象；否则返回null
     */
    UserInfoResource getUserInfoResource(int userId);

    /**
     * 更新用户信息
     *
     * @param userInfo 待更新的用户信息对象
     * @param resource 用户信息更新请求对应的资源类对象
     * @return 该次更新受影响的数据库记录条数
     */
    int updateUserInfo(UserInfo userInfo, UserInfoUpdateResource resource);

    /**
     * 保存用户头像文件
     *
     * @param file
     * @return 用户头像文件保存路径
     * @throws IOException 保存失败时抛出该异常
     */
    String saveAvatarFile(MultipartFile file) throws IOException;

    /**
     * 更新用户信息的头像url字段
     *
     * @param userInfo
     * @param url
     * @return 该次更新受影响的数据库条数
     */
    int updateAvatarUrl(UserInfo userInfo, String url);

}

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

    UserInfo getUserInfoByUserId(int userId);

    UserInfoResource getUserInfoResource(int userId);

    int updateUserInfo(UserInfo userInfo, UserInfoUpdateResource resource);

    String saveAvatarFile(MultipartFile file) throws IOException;

    int updateAvatarUrl(UserInfo userInfo, String url);

}

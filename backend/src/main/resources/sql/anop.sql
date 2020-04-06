-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- 主机： 127.0.0.1
-- 生成日期： 2020-04-06 12:00:07
-- 服务器版本： 10.4.10-MariaDB
-- PHP 版本： 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `anop`
--

-- --------------------------------------------------------

--
-- 表的结构 `notification`
--

CREATE TABLE `notification` (
  `id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `title` varchar(63) NOT NULL,
  `content` varchar(255) NOT NULL DEFAULT '',
  `creation_date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `notification`
--

INSERT INTO `notification` (`id`, `group_id`, `user_id`, `title`, `content`, `creation_date`) VALUES
(1, 1, 1, '123456', '1234567', '2020-04-04 15:09:05');

-- --------------------------------------------------------

--
-- 表的结构 `noti_group`
--

CREATE TABLE `noti_group` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `name` varchar(31) NOT NULL,
  `remark` varchar(255) NOT NULL DEFAULT '',
  `creation_date` datetime NOT NULL,
  `permission` tinyint(4) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `noti_group`
--

INSERT INTO `noti_group` (`id`, `user_id`, `name`, `remark`, `creation_date`, `permission`) VALUES
(1, 1, 'test', 'test', '2020-04-06 15:07:25', 0);

-- --------------------------------------------------------

--
-- 表的结构 `noti_group_user`
--

CREATE TABLE `noti_group_user` (
  `id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `role` tinyint(4) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 表的结构 `noti_receiver`
--

CREATE TABLE `noti_receiver` (
  `id` int(11) NOT NULL,
  `notification_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 表的结构 `noti_user_request`
--

CREATE TABLE `noti_user_request` (
  `id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT 0,
  `request_time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 表的结构 `role_permission`
--

CREATE TABLE `role_permission` (
  `id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- 表的结构 `sys_permission`
--

CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL,
  `url` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `pid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `sys_permission`
--

INSERT INTO `sys_permission` (`id`, `url`, `name`, `description`, `pid`) VALUES
(1, '/user', 'user', NULL, 0),
(2, '/resource', 'resource', NULL, 0);

-- --------------------------------------------------------

--
-- 表的结构 `sys_role`
--

CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 表的结构 `sys_user_role`
--

CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 表的结构 `todo`
--

CREATE TABLE `todo` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `title` varchar(63) NOT NULL,
  `content` varchar(255) NOT NULL DEFAULT '',
  `begin_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `remind_date` datetime DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `status` tinyint(4) NOT NULL DEFAULT 0,
  `is_important` tinyint(4) NOT NULL DEFAULT 0,
  `is_favorite` tinyint(4) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 表的结构 `todo_category`
--

CREATE TABLE `todo_category` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `name` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(31) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`id`, `username`, `email`, `password`, `status`) VALUES
(1, 'admin', '1822467@qq.com', 'e10adc3949ba59abbe56e057f20f883e', 0),
(2, 'user', '23421923@qq.com', 'e10adc3949ba59abbe56e057f20f883e', 0);

-- --------------------------------------------------------

--
-- 表的结构 `user_info`
--

CREATE TABLE `user_info` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `nickname` varchar(31) NOT NULL,
  `creation_time` datetime NOT NULL,
  `avatar_url` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `user_info`
--

INSERT INTO `user_info` (`id`, `user_id`, `nickname`, `creation_time`, `avatar_url`) VALUES
(1, 1, 'zhangsan', '2020-04-07 18:57:08', ''),
(2, 2, 'lisi', '2020-04-01 22:23:36', '');

-- --------------------------------------------------------

--
-- 表的结构 `valid_email`
--

CREATE TABLE `valid_email` (
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `code` varchar(15) NOT NULL,
  `expire` datetime NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转储表的索引
--

--
-- 表的索引 `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_group_id` (`group_id`) USING BTREE,
  ADD KEY `idx_user_id` (`user_id`) USING BTREE;

--
-- 表的索引 `noti_group`
--
ALTER TABLE `noti_group`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_user_id` (`user_id`) USING BTREE;

--
-- 表的索引 `noti_group_user`
--
ALTER TABLE `noti_group_user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_group_id` (`group_id`) USING BTREE,
  ADD KEY `idx_user_id` (`user_id`) USING BTREE;

--
-- 表的索引 `noti_receiver`
--
ALTER TABLE `noti_receiver`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_notification_id` (`notification_id`) USING BTREE,
  ADD KEY `idx_user_id` (`user_id`) USING BTREE;

--
-- 表的索引 `noti_user_request`
--
ALTER TABLE `noti_user_request`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_group_id` (`group_id`) USING BTREE,
  ADD KEY `idx_user_id` (`user_id`) USING BTREE;

--
-- 表的索引 `role_permission`
--
ALTER TABLE `role_permission`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `idx_permission_id` (`permission_id`) USING BTREE,
  ADD KEY `idx_role_id` (`role_id`) USING BTREE;

--
-- 表的索引 `sys_permission`
--
ALTER TABLE `sys_permission`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `sys_role`
--
ALTER TABLE `sys_role`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `sys_user_role`
--
ALTER TABLE `sys_user_role`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_role_id` (`role_id`) USING BTREE,
  ADD KEY `idx_user_id` (`user_id`) USING BTREE;

--
-- 表的索引 `todo`
--
ALTER TABLE `todo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_category_id` (`category_id`) USING BTREE,
  ADD KEY `idx_user_id` (`user_id`) USING BTREE;

--
-- 表的索引 `todo_category`
--
ALTER TABLE `todo_category`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_user_id` (`user_id`) USING BTREE;

--
-- 表的索引 `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `idx_name` (`username`) USING BTREE,
  ADD UNIQUE KEY `idx_email` (`email`) USING BTREE;

--
-- 表的索引 `user_info`
--
ALTER TABLE `user_info`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_user_id` (`user_id`) USING BTREE;

--
-- 表的索引 `valid_email`
--
ALTER TABLE `valid_email`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `idx_email` (`email`) USING BTREE;

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `notification`
--
ALTER TABLE `notification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- 使用表AUTO_INCREMENT `noti_group`
--
ALTER TABLE `noti_group`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- 使用表AUTO_INCREMENT `noti_group_user`
--
ALTER TABLE `noti_group_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `noti_receiver`
--
ALTER TABLE `noti_receiver`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `noti_user_request`
--
ALTER TABLE `noti_user_request`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `role_permission`
--
ALTER TABLE `role_permission`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- 使用表AUTO_INCREMENT `sys_permission`
--
ALTER TABLE `sys_permission`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- 使用表AUTO_INCREMENT `sys_role`
--
ALTER TABLE `sys_role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- 使用表AUTO_INCREMENT `sys_user_role`
--
ALTER TABLE `sys_user_role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- 使用表AUTO_INCREMENT `todo`
--
ALTER TABLE `todo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `todo_category`
--
ALTER TABLE `todo_category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- 使用表AUTO_INCREMENT `user_info`
--
ALTER TABLE `user_info`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- 使用表AUTO_INCREMENT `valid_email`
--
ALTER TABLE `valid_email`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- 限制导出的表
--

--
-- 限制表 `notification`
--
ALTER TABLE `notification`
  ADD CONSTRAINT `fk_noti_group_notification` FOREIGN KEY (`group_id`) REFERENCES `noti_group` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_user_notification` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- 限制表 `noti_group`
--
ALTER TABLE `noti_group`
  ADD CONSTRAINT `fk_user_noti_group` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- 限制表 `noti_group_user`
--
ALTER TABLE `noti_group_user`
  ADD CONSTRAINT `fk_noti_group_noti_group_user` FOREIGN KEY (`group_id`) REFERENCES `noti_group` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_user_noti_group_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- 限制表 `noti_receiver`
--
ALTER TABLE `noti_receiver`
  ADD CONSTRAINT `fk_noti_group_user_noti_receiver` FOREIGN KEY (`user_id`) REFERENCES `noti_group_user` (`user_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_notification_noti_receiver` FOREIGN KEY (`notification_id`) REFERENCES `notification` (`id`) ON DELETE CASCADE;

--
-- 限制表 `noti_user_request`
--
ALTER TABLE `noti_user_request`
  ADD CONSTRAINT `fk_noti_group_noti_user_request` FOREIGN KEY (`group_id`) REFERENCES `noti_group` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_user_noti_user_request` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- 限制表 `role_permission`
--
ALTER TABLE `role_permission`
  ADD CONSTRAINT `fk_sys_permission_sys_role_permission` FOREIGN KEY (`permission_id`) REFERENCES `sys_permission` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_sys_role_sys_role_permission` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE;

--
-- 限制表 `sys_user_role`
--
ALTER TABLE `sys_user_role`
  ADD CONSTRAINT `fk_sys_role_sys_role_user` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_user_sys_role_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- 限制表 `todo`
--
ALTER TABLE `todo`
  ADD CONSTRAINT `fk_todo_category_todo` FOREIGN KEY (`category_id`) REFERENCES `todo_category` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_user_todo` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- 限制表 `todo_category`
--
ALTER TABLE `todo_category`
  ADD CONSTRAINT `fk_user_todo_category` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- 限制表 `user_info`
--
ALTER TABLE `user_info`
  ADD CONSTRAINT `fk_user_user_info` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

CREATE TABLE `board` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category` varchar(45) NOT NULL,
  `boardType` enum('ARTICLE','GUIDE','FREETALK','NOTI','QNA','ETC') NOT NULL COMMENT '‘ARTICLE’, ‘GUIDE’, ‘FREETALK’,’NOTI’,’QNA’,’ETC’',
  `title` varchar(255) NOT NULL,
  `content` mediumblob,
  `mainImage` varchar(512) DEFAULT NULL,
  `viewCount` int(11) NOT NULL DEFAULT '0',
  `likeCount` int(11) NOT NULL DEFAULT '0',
  `dislikeCount` int(11) NOT NULL DEFAULT '0',
  `createdAt` datetime NOT NULL,
  `createdBy` varchar(100) NOT NULL,
  `modifiedAt` datetime DEFAULT NULL,
  `modifiedBy` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `uploadFile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `boardType` enum('ARTICLE','GUIDE','FREETALK','NOTI','QNA','ETC') NOT NULL,
  `boardId` bigint(20) NOT NULL,
  `order` int(11) NOT NULL,
  `originalFileName` varchar(500) NOT NULL,
  `filePath` varchar(500) NOT NULL,
  `fileName` varchar(100) NOT NULL,
  `createdAt` datetime NOT NULL,
  `createdBy` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parentCommentId` bigint(20) DEFAULT NULL,
  `boardId` bigint(20) NOT NULL,
  `comment` mediumblob NOT NULL,
  `likeCount` int(11) NOT NULL DEFAULT '1',
  `dislikeCount` int(11) NOT NULL DEFAULT '1',
  `createdAt` datetime NOT NULL,
  `createdBy` varchar(100) NOT NULL,
  `modifiedAt` datetime DEFAULT NULL,
  `modifiedBy` varchar(100) DEFAULT NULL,
  `level` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

DROP DATABASE IF EXISTS
  `instrumentshop`;
CREATE DATABASE `instrumentshop` DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
use `instrumentshop`;

DROP TABLE IF EXISTS `products`;
DROP TABLE IF EXISTS `categories`;
DROP TABLE IF EXISTS `manufacturers`;

CREATE TABLE `categories` (
	`id` int not null primary key auto_increment,
    `name` varchar(50) not null unique
);
INSERT INTO `categories`(`name`) VALUES ('Punchers'), ('Screwdrivers'), ('Electroscrew'), ('Electrofret'), ('electroplane'), ('Routers');

CREATE TABLE `manufacturers` (
	`id` int not null primary key auto_increment,
    `name` varchar(50) not null unique
);
INSERT INTO `manufacturers`(`name`) VALUES ('Makita'), ('Boshc'), ('intertool'), ('Dnepr');


CREATE TABLE `products` (
	`id` int not null primary key auto_increment,
    `name` varchar(50) not null,
    `manufacturer_id` int not null,
    `price` long not null check (`price` > 0),
    `category_id` int not null,
    `weight` double check (`weight` > 0),
    `description` varchar(500),
    `imgFile` varchar(50),
    `available` boolean not null,
    constraint `prod_category_fk` foreign key(`category_id`) references `categories`(`id`)
		on delete cascade on update cascade,
	constraint `prod_manufacturer_fk` foreign key(`manufacturer_id`) references `manufacturers`(`id`)
		on delete cascade on update cascade
);
CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `firstName` varchar(50) CHARACTER SET utf32 NOT NULL,
  `lastName` varchar(50) CHARACTER SET utf32 NOT NULL,
  `email` varchar(50) CHARACTER SET utf32 NOT NULL,
  `password` varchar(50) CHARACTER SET utf32 NOT NULL,
  `subscribe` tinyint(1) NOT NULL,
  `photoPath` varchar(150) CHARACTER SET utf32 NOT NULL 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);


ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
  


INSERT INTO `users` (`id`,`firstName`,`lastName`,`email`,`password`,`subscribe`,`photoPath`) VALUES (177,'Yaroslav','Chuikov','chuikov_yaroslav@mail.ru','1234',0,'C:\\Users\\Yaroslav_Chuikov\\newworkspace\\repository\\chuikov_yaroslav@mail.ru.jpg');
INSERT INTO `users` (`id`,`firstName`,`lastName`,`email`,`password`,`subscribe`,`photoPath`) VALUES (178,'Alex','Axxx','acccv@maer.rt','1234',0,'');
INSERT INTO `users` (`id`,`firstName`,`lastName`,`email`,`password`,`subscribe`,`photoPath`) VALUES (179,'Vika','Chuikova','chuykova@mail.ru','1234',0,'C:\\Users\\Yaroslav_Chuikov\\newworkspace\\repository\\chuykova@mail.ru.jpg');
INSERT INTO `users` (`id`,`firstName`,`lastName`,`email`,`password`,`subscribe`,`photoPath`) VALUES (180,'Arrr','Bffff','vvvnb@mail.ru','1234',0,'C:\\Users\\Yaroslav_Chuikov\\newworkspace\\repository\\vvvnb@mail.ru.jpg');


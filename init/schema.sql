DROP TABLE IF EXISTS `orders` CASCADE
;

DROP TABLE IF EXISTS `promocodes` CASCADE
;

/* Create Tables */

CREATE TABLE `orders`
(
    `order_id`     INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `promocode_id` INT UNSIGNED,
    CONSTRAINT `PK_orders` PRIMARY KEY (`order_id` ASC)
)
;

CREATE TABLE `promocodes`
(
    `promocode_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name`         VARCHAR(150) NOT NULL,
    `discount`     INT UNSIGNED NOT NULL,
    CONSTRAINT `PK_promocodes` PRIMARY KEY (`promocode_id` ASC)
)
;

/* add DATA */

INSERT INTO `orders` (`order_id`, `promocode_id`)
VALUES (1, 2),
       (2, null),
       (3, null),
       (4, 5),
       (5, null),
       (6, null),
       (7, 1);

INSERT INTO `promocodes` (`promocode_id`, `name`, `discount`)
VALUES (1, 'JAN', 11),
       (2, 'FEB', 12),
       (3, 'MAR', 13),
       (4, 'APR', 14),
       (5, 'May', 15),
       (6, 'June', 16),
       (7, 'July', 17);


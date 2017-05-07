USE mydb;

DROP TABLE IF EXISTS `compromissos`;
CREATE TABLE `compromissos` (
    `id` int not null primary key auto_increment,
    `titulo` varchar(45) NOT NULL,
    `tipo` varchar(45) NOT NULL,
    `data` datetime NOT NULL,
    `local` varchar(45) NOT NULL,
    `duracao` decimal(10,2) NOT NULL,
    `observacao` varchar(200)
);

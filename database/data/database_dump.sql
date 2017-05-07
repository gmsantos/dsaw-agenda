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

INSERT INTO `compromissos` (`titulo`, `tipo`, `data`, `local`, `duracao`, `observacao`) VALUES 
    ("Meu evento", "Encontro", "2018-05-08 03:15:00", "São Paulo", 10, null),
    ("Evento Bacana", "Outro", "2018-05-08 03:15:00", "São José", 1, "Tem comida"),
    ("Evento", "Encontro", "2018-06-08 03:15:00", "Longe", 1, null);

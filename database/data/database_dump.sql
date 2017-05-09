USE mydb;
ALTER DATABASE mydb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
    `id` int not null primary key auto_increment,
    `nome` varchar(45) NOT NULL,
    `login` varchar(45) NOT NULL,
    `senha` char(32) NOT NULL
);

INSERT INTO `usuarios` (`nome`, `login`, `senha`) VALUES
    ("João", "joao", md5(123456)),
    ("Mario", "mario", md5(123456));

DROP TABLE IF EXISTS `compromissos`;
CREATE TABLE `compromissos` (
    `id` int not null primary key auto_increment,
    `titulo` varchar(45) NOT NULL,
    `tipo` varchar(45) NOT NULL,
    `data` datetime NOT NULL,
    `local` varchar(45) NOT NULL,
    `duracao` decimal(10,2) NOT NULL,
    `observacao` varchar(200),
    `usuario_id` int not null,
    foreign key (`usuario_id`) references `usuarios`(`id`)
);

INSERT INTO `compromissos` (`titulo`, `tipo`, `data`, `local`, `duracao`, `observacao`, `usuario_id`) VALUES
    ("Meu evento", "Encontro", "2018-05-08 03:15:00", "São Paulo", 10, null, 1),
    ("Evento Bacana", "Outro", "2018-05-08 03:15:00", "São José", 1, "Tem comida", 2),
    ("Evento", "Encontro", "2018-06-08 03:15:00", "Longe", 1, null, 1),
    ("Meu evento", "Encontro", now(), "São Paulo", 10, null, 1),
    ("Evento Bacana", "Outro", now(), "São José", 1, "Tem comida", 2),
    ("Evento", "Encontro", "2018-06-08 03:15:00", "Longe", 1, null, 1),
    ("Meu evento", "Encontro", now(), "São Paulo", 10, null, 1),
    ("Evento Bacana", "Outro", now(), "São José", 1, "Tem comida", 2),
    ("Evento", "Encontro", "2018-06-08 03:15:00", "Longe", 1, null, 1);

INSERT INTO usuario(nome, email) VALUES ('Alan', 'alan@mail.com');

INSERT INTO produto(nome, descricao, preco, ativo) VALUES ('Violão', 'Corda de Nylon', 659.00, true);
INSERT INTO produto(nome, descricao, preco, ativo) VALUES ('Guitarra', 'Modelo Telecaster', 1600.00, true);
INSERT INTO produto(nome, descricao, preco, ativo) VALUES ('Baixo', 'Modelo Jazz Bass', 4300.00, true);
INSERT INTO produto(nome, descricao, preco, ativo) VALUES ('Teclado', '61 Teclas', 1100.00, true);
INSERT INTO produto(nome, descricao, preco, ativo) VALUES ('Guaita', 'Diatonica em Dó', 120.00, true);
INSERT INTO produto(nome, descricao, preco, ativo) VALUES ('Bateria', 'Sem os Pratos', 5020.00, true);
INSERT INTO produto(nome, descricao, preco, ativo) VALUES ('Jg de Pratos de Bateria', '4 Pratos', 3125.00, true);
INSERT INTO produto(nome, descricao, preco, ativo) VALUES ('Afinador', 'Para Instrumentos de Corda ', 60.60, true);
INSERT INTO produto(nome, descricao, preco, ativo) VALUES ('Cordas de Violão', 'Nylon com Bolinha', 25.00, true);
INSERT INTO produto(nome, descricao, preco, ativo) VALUES ('Cordas de Guitarra', 'Calibre 09', 45.00, true);

INSERT INTO carrinho(data, valor_total, aberto, usuario_id) VALUES (now(), 1660.60 , false, 1);

INSERT INTO item_carrinho(preco_unitario, quantidade, preco_total, carrinho_id, produto_id) VALUES (1600.00, 1, 1600.00, 1, 2);
INSERT INTO item_carrinho(preco_unitario, quantidade, preco_total, carrinho_id, produto_id) VALUES (60.60, 1, 60.60, 1, 8);






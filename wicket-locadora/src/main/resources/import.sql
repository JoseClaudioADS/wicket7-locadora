insert into cli_cliente (cli_id, nome, email, cidade, logradouro, uf, data_hora_criacao, data_hora_ultima_atualizacao) values (1, 'Coragem o cão covarde', 'coragem@email.com', 'Lugar nenhum', 'Rua tal de algum lugar', 'PE', 1496448323, 1496448323);

insert into cat_categoria (cat_id, nome, data_hora_criacao, data_hora_ultima_atualizacao) values (1, 'Terror', 1496448323, 1496448323);
insert into cat_categoria (cat_id, nome, data_hora_criacao, data_hora_ultima_atualizacao) values (2, 'Suspense', 1496448323, 1496448323);
insert into cat_categoria (cat_id, nome, data_hora_criacao, data_hora_ultima_atualizacao) values (3, 'Animação', 1496448323, 1496448323);
insert into cat_categoria (cat_id, nome, data_hora_criacao, data_hora_ultima_atualizacao) values (4, 'Ficção Científica', 1496448323, 1496448323);
insert into cat_categoria (cat_id, nome, data_hora_criacao, data_hora_ultima_atualizacao) values (5, 'Comédia', 1496448323, 1496448323);

insert into flm_filme (flm_id, titulo, descricao, capa, data_hora_criacao, data_hora_ultima_atualizacao) values (1, 'Coragem o cão covarde', 'Coragem é um cachorro que vive em uma fazenda distante onde acontecem aparições de monstros, fantasmas e outras criaturas. O animal está sempre defendendo e salvando seus donos idosos de eventos misteriosos.', 'coragem.jpg', 1496448323, 1496448323);

insert into flm_filme_categorias (flm_id, cat_id) values (1, 3);
insert into flm_filme_categorias (flm_id, cat_id) values (1, 5);

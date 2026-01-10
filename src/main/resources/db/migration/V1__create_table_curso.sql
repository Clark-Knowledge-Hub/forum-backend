create table curso(
      id bigint not null auto_increment,
      nome varchar(50) not null,
      categoria varchar(50) not null,
      primary key(id)
);

insert into curso values(1, 'Kotlin', 'PROGRAMACAO');
insert into curso values(2, 'Java', 'PROGRAMACAO');
insert into curso values(3, 'JavaScript', 'PROGRAMACAO');
insert into curso values(4, 'Python', 'PROGRAMACAO');
insert into curso values(5, 'Go', 'PROGRAMACAO');
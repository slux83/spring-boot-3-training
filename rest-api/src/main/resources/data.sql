insert into user_details (id, birth_date, name)
values (10001, current_date(), 'Alessio');

insert into user_details (id, birth_date, name)
values (10002, current_date(), 'Elon');

insert into user_details (id, birth_date, name)
values (10003, current_date(), 'Linus');

insert into post (id, description, user_id)
values(20001, 'I want to learn AWS', 10001);

insert into post (id, description, user_id)
values(20002, 'I want to learn Spring', 10001);

insert into post (id, description, user_id)
values(20003, 'I want to learn Linux', 10002);

insert into post (id, description, user_id)
values(20004, 'I want to learn Cloud', 10003);
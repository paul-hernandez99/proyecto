
insert into Usuarios (type,username,password,name,surnames,email,fec_nac,age) 
	values (0,"paul","lqaz1875","Paul","Hernandez Guridi","paul.hernandez@opendeusto.es","30-01-1999",20);

insert into Usuarios (type,username,password,name,surnames,email,fec_nac,age) 
	values (0,"andoni","ando","Andoni","Azpiazu","andoni@opendeusto.es","30-01-1998",19);

insert into Fotos (id_user,path, fec) values (1,"Imagenes/data/paul_1.jpg","14:00 02/01/2019");

insert into User_User (id_follower,id_followed) values (2,1);

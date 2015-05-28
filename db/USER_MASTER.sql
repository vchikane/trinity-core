CREATE TABLE USER_MASTER (
    USER_ID             	VARCHAR(100)     NOT NULL  PRIMARY KEY,
    USER_NAME           	VARCHAR(100)     NOT NULL             ,
    PASSWORD            	VARCHAR(200)     NOT NULL             ,
    ACTIVE              	VARCHAR(1)                            ,
    LAST_UPDATED        	BIGINT		                          ,
    VALID_TILL          	BIGINT		                          ,
    ROLE_ID             	VARCHAR(200)  	                      ,
    CREATED_ON          	BIGINT								  ,
	LOCKED					VARCHAR(1)							  	
);

--password is encrypted using bcrypt default is '1'.
insert into USER_MASTER values('ashim','ashim usmani', '$2a$10$FJs16sHn47pCbFcO9d6AgeftJdAe6FuHdBUfg/LCsep9xyHVoOw3e','Y',13142015111111,13142020111111,'ADMIN',13142015111111,'N');
insert into USER_MASTER values('tapo','tapojyoti b', '$2a$10$FJs16sHn47pCbFcO9d6AgeftJdAe6FuHdBUfg/LCsep9xyHVoOw3e','Y',13142015111111,13142020111111,'ADMIN',13142015111111,'N');
insert into USER_MASTER values('abcd','xyz1', '$2a$10$FJs16sHn47pCbFcO9d6AgeftJdAe6FuHdBUfg/LCsep9xyHVoOw3e','Y',13142015111111,13142020111111,'ADMIN,ROLE_SPECIAL',13142015111111,'N');
commit;
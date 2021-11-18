
------ 공지사항  -------

create table comm_notice (
	notice_no number primary key,                        -- 공지사항 번호
	member_id varchar2(50) references users(username),   -- foreign key
	notice_title varchar2(200) not null,                 -- 공지사항 제목
	notice_content varchar2(4000) not null,              -- 공지사항 내용
	notice_regdate date not null,                        -- 공지사항 생성일자
	notice_hit number default 0,                         -- 공지사항 조회수
	del char(1)
	);               
	
	select * from COMM_NOTICE;
	select * from tab;
	
	
	insert into comm_notice values(13,'0','title','content',sysdate,0,'n');

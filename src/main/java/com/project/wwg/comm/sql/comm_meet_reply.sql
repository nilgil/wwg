
------ 후기게시판 댓글관리 -------

create table comm_meet_reply (
	meet_re_no number primary key,         -- 후기게시판 댓글관리 번호
    meet_fno number references comm_meet(meet_no),  -- foreign key, 후기게시판의 게시글의 번호를 가져온다.
	member_id varchar2(50) references users(username),                  -- foreign key
	meet_re_content varchar2(4000) not null,  -- 후기게시판 댓글관리 내용
	meet_re_regdate date not null         -- 후기게시판 댓글관리 생성일자
	);               
	
	select * from comm_review;


ALTER TABLE comm_review_reply ADD CONSTRAINT member_id   -- member_id foreign key 생성
FOREIGN KEY(member_id) REFERENCES member(member_id);

insert into comm_review_reply values(1,2,'아이디','content',sysdate);


	
	
	
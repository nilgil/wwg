
------ 후기게시판 댓글관리 -------

create table comm_review_reply (
	review_re_no number primary key,         -- 후기게시판 댓글관리 번호
    review_no number references comm_review(review_no),  -- foreign key, 후기게시판의 게시글의 번호를 가져온다.
	member_id varchar2(50) references users(username),                  -- foreign key
	review_re_content varchar2(4000) not null,  -- 후기게시판 댓글관리 내용
	review_re_regdate date not null         -- 후기게시판 댓글관리 생성일자
	);               
	
	select * from comm_review;


ALTER TABLE comm_review_reply ADD CONSTRAINT member_id   -- member_id foreign key 생성
FOREIGN KEY(member_id) REFERENCES member(member_id);

insert into comm_review_reply values(1,'2','아이디','content',sysdate);


	
	
	
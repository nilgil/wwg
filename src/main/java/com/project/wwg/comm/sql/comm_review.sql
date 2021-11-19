
------ 후기게시판 메인 -------

create table comm_review (
	review_no number primary key,            -- 후기게시판 번호
	member_id varchar2(50) references users(username),       -- foreign key
	review_title varchar2(200) not null,     -- 후기게시판 제목
	review_content varchar2(4000) not null,  -- 후기게시판 내용
	review_regdate date not null,            -- 후기게시판 생성일자
	review_hit number default 0,             -- 후기게시판 조회수
	review_photo varchar2(200) not null      -- 업로드된 사진 경로
	);               
	
	select * from comm_review;
	
ALTER TABLE comm_review ADD CONSTRAINT member_id   -- member_id foreign key 생성
FOREIGN KEY(member_id) REFERENCES users(username);
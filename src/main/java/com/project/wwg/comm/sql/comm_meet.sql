
------ 후기게시판 메인 -------

create table comm_meet (
	meet_no number primary key,            -- 모임게시판 번호
	member_id varchar2(50) references users(username),       -- foreign key
	meet_title varchar2(200) not null,     -- 모임게시판 제목
	meet_content varchar2(4000) not null,  -- 모임게시판 내용
	meet_regdate date not null,            -- 모임게시판 생성일자
	meet_hit number default 0,             -- 모임게시판 조회수
	meet_photo varchar2(200) not null,     -- 업로드된 사진 경로
	meet_like number default 0             -- 좋아요
	);               
	
	select * from meet_review;
	
ALTER TABLE comm_review ADD CONSTRAINT member_id   -- member_id foreign key 생성
FOREIGN KEY(member_id) REFERENCES users(username);
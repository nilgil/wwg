
------ 후기게시판 좋아요 -------

create table comm_review_like (
    review_no number references comm_review(review_no),  -- foreign key, 후기게시판의 게시글의 번호를 가져온다.
	member_id varchar2(50) references users(username),                  -- foreign key
	review_like number default 0            -- 후기게시판 좋아요 갯수
	);               
	
	select * from comm_review_like;


ALTER TABLE comm_review_like ADD CONSTRAINT member_id   -- member_id foreign key 생성
FOREIGN KEY(member_id) REFERENCES users(username);


	
	
	
select * from tab;

-- 여행정보(여행지 페이지)
drop table trip_tour;
create table trip_tour(
    tour_no number primary key,
    username varchar2(20) not null references users(username),
    tour_title varchar2(50) not null,
    tour_content varchar2(500) not null,
    tour_photo varchar2(200),
    tour_regdate date not null,
    tour_hit number default 0
);

-- 여행정보(여행지 페이지-댓글)
drop table trip_tour_reply;
create table trip_tour_reply(
    tour_re_no number primary key,
    tour_no number not null references trip_tour(tour_no),
    username varchar2(20) not null references users(username),
    tour_re_content varchar2(500) not null,
    tour_re_regdate date not null
);

-- 여행정보(여행지 페이지-좋아요)
drop table trip_tour_like;
create table trip_tour_like(
    tour_no number not null references trip_tour(tour_no),
    username varchar2(20) not null references users(username),
    tour_like number default 0
);

-- 여행정보(숙소 페이지)
drop table trip_stay;
create table trip_stay(
    stay_no number primary key,
    username varchar2(20) not null references users(username),
    stay_title varchar2(50) not null,
    stay_content varchar2(500) not null,
    stay_photo varchar2(200),
    stay_regdate date not null,
    stay_hit number default 0
);

-- 여행정보(숙소 페이지-댓글)
drop table trip_stay_reply;
create table trip_stay_reply(
    stay_re_no number primary key,
    stay_no number not null references trip_stay(stay_no),
    username varchar2(20) not null references users(username),
    stay_re_content varchar2(500) not null,
    stay_re_regdate date not null
);

-- 여행정보(숙소 페이지-좋아요)
drop table trip_stay_like;
create table trip_stay_like(
    stay_no number not null references trip_stay(stay_no),
    username varchar2(20) not null references users(username),
    stay_like number default 0
);

-- 여행정보(맛집 페이지)
drop table trip_food;
create table trip_food(
    food_no number primary key,
    username varchar2(20) not null references users(username),
    food_title varchar2(50) not null,
    food_content varchar2(500) not null,
    food_photo varchar2(200),
    food_regdate date not null,
    food_hit number default 0
);

-- 여행정보(맛집 페이지-댓글)
drop table trip_food_reply;
create table trip_food_reply(
    food_re_no number primary key,
    food_no number not null references trip_food(food_no),
    username varchar2(20) not null references users(username),
    food_re_content varchar2(500) not null,
    food_re_regdate date not null
);

-- 여행정보(맛집 페이지-좋아요)
drop table trip_food_like;
create table trip_food_like(
    food_no number not null references trip_food(food_no),
    username varchar2(20) not null references users(username),
    food_like number default 0
);


-- QnA 게시판
drop table qna_board;
create table qna_board(
      qna_no number primary key,
      username varchar2(20) references users(username), --FK
      qna_title varchar2(50),
      qna_content varchar2(500),
      qna_regdate date not null,
      qna_re_ref number not null,  --부모 댓글 번호
      qna_re_lev number not null,  --자식 댓글 순서
      qna_re_seq number not null,  --댓글 오름차순 순서
      qna_hit number default 0
);

select * from tab;
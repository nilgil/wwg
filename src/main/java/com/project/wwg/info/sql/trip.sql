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
    rtour_no number not null references trip_tour(tour_no),
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
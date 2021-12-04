select * from tab;

-- 여행정보(맛집 페이지)
drop table trip_food;
create table trip_food(
    food_no number primary key,
    username varchar2(20) not null references users(username) on delete cascade,
    food_title varchar2(100) not null,
    food_content varchar2(1000) not null,
    food_regdate date not null,
    food_hit number default 0,
    food_like number default 0
);

-- 여행정보(맛집 페이지-댓글)
drop table trip_food_reply;
create table trip_food_reply(
    food_re_no number primary key,
    rfood_no number not null references trip_food(food_no) on delete cascade,
    username varchar2(20) not null references users(username) on delete cascade,
    food_re_content varchar2(500) not null,
    food_re_regdate date not null
);

-- 여행정보(여행지 페이지)
drop table trip_tour;
create table trip_tour(
    tour_no number primary key,
    username varchar2(20) not null references users(username) on delete cascade,
    tour_title varchar2(100) not null,
    tour_content varchar2(1000) not null,
    tour_regdate date not null,
    tour_hit number default 0,
    tour_like number default 0
);

-- 여행정보(여행지 페이지-댓글)
drop table trip_tour_reply;
create table trip_tour_reply(
    tour_re_no number primary key,
    rtour_no number not null references trip_tour(tour_no) on delete cascade,
    username varchar2(20) not null references users(username) on delete cascade,
    tour_re_content varchar2(500) not null,
    tour_re_regdate date not null
);

-- 여행정보(숙소 페이지)
drop table trip_stay;
create table trip_stay(
    stay_no number primary key,
    username varchar2(20) not null references users(username) on delete cascade,
    stay_title varchar2(100) not null,
    stay_content varchar2(1000) not null,
    stay_regdate date not null,
    stay_hit number default 0,
    stay_like number default 0
);

-- 여행정보(숙소 페이지-댓글)
drop table trip_stay_reply;
create table trip_stay_reply(
    stay_re_no number primary key,
    rstay_no number not null references trip_stay(stay_no) on delete cascade,
    username varchar2(20) not null references users(username) on delete cascade,
    stay_re_content varchar2(500) not null,
    stay_re_regdate date not null
);
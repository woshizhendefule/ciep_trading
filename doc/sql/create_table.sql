-- auto-generated definition
create table user
(
    id           int auto_increment comment '自增id'
        primary key,
    name         varchar(128)           not null comment '用户名',
    password     varchar(128)           not null comment '密码',
    student_id   varchar(20) default '' null comment '学号',
    phone        varchar(20) default '' null comment '手机',
    credit_score int         default 0  null comment '用户信用评分',
    is_seller    tinyint     default 0  not null comment '用户类型 0-买家 1-卖家 2-待审核',
    is_admin     tinyint     default 0  not null comment '管理员用户类型 0-用户 1-管理员',
    constraint user_name_pk
        unique (name),
    constraint user_student_id_pk
        unique (student_id)
)
    comment '用户表';

-- auto-generated definition
create table goods
(
    id           int auto_increment comment '自增id'
        primary key,
    name         varchar(255)                           not null comment '商品名称',
    introduce    varchar(255) default ''                null comment '商品介绍',
    picture      longblob                               null comment '商品图片',
    credential   longblob                               null comment '商品凭证',
    price        double       default 0                 null comment '商品价格',
    release_time timestamp    default CURRENT_TIMESTAMP null comment '发布时间',
    is_release   tinyint      default 0                 not null comment '商品状态 0-已发布 1-未发布 2-待审核',
    user_id      int                                    null comment '用户id',
    constraint goods_user_id_fk
        foreign key (user_id) references user (id)
            on update cascade on delete cascade
)
    comment '商品表';

-- auto-generated definition
create table collection
(
    id       int auto_increment comment '自增id'
        primary key,
    user_id  int null comment '用户id',
    goods_id int null comment '商品id',
    constraint collection_goods_id_fk
        foreign key (goods_id) references goods (id),
    constraint collection_user_id_fk
        foreign key (user_id) references user (id)
)
    comment '用户商品收藏表';

-- auto-generated definition
create table goods_order
(
    id            int auto_increment comment '自增id'
        primary key,
    status        tinyint   default 0                 not null comment '订单状态 0-未交付 1-已交付',
    create_time   timestamp default CURRENT_TIMESTAMP null comment '订单开始时间',
    complete_time timestamp                           null comment '订单完成时间',
    seller_score  int       default 0                 null comment '订单完成卖家评分（百分制）',
    goods_user_id int                                 null comment '商品卖家编号',
    user_id       int                                 null comment '买家编号',
    constraint goods_order_user_id_fk
        foreign key (user_id) references user (id),
    constraint order_goods_user_id_fk
        foreign key (goods_user_id) references user (id)
)
    comment '订单表';

-- auto-generated definition
create table message
(
    id            int auto_increment comment '自增id'
        primary key,
    details       varchar(255) default ''                null comment '留言内容',
    create_time   timestamp    default CURRENT_TIMESTAMP null comment '留言时间',
    goods_user_id int                                    null comment '商品卖家编号',
    user_id       int                                    null comment '买家编号',
    constraint massage_goods_user_id_fk
        foreign key (goods_user_id) references user (id)
            on update cascade on delete cascade,
    constraint message_user_id_fk
        foreign key (user_id) references user (id)
            on update cascade on delete cascade
)
    comment '留言表';


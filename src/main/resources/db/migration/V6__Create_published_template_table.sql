create TABLE PublishedTemplate(
    ID CHAR(36) not null,
    NAME VARCHAR(200),
    PUBLISHED_BY int not null,
    PUBLISHED_DATE TIMESTAMP not null,
    LOGIC_TEMPLATE_ID CHAR(36) not null,
    PROGRAMMING_QUESTION_PAPER_ID CHAR(36) not null,
    PRIMARY KEY (ID)
);
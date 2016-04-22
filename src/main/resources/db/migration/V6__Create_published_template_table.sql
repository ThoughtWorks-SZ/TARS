create TABLE PublishedTemplate(
    ID int not null AUTO_INCREMENT,
    NAME VARCHAR(200),
    PUBLISHED_BY int not null,
    LOGIC_TEMPLATE_ID int not null,
    PROGRAMMING_QUESTION_PAPER_ID int not null,
    PRIMARY KEY (ID)
);
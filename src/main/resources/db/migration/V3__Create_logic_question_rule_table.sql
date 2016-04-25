create TABLE LogicQuestionRule(
    ID CHAR(36) not null,
    LEVEL int,
    AMOUNT int,
    REPO_ID CHAR(36),
    TEMPLATE_ID CHAR(36),
    PRIMARY KEY (ID),
    FOREIGN KEY (TEMPLATE_ID) REFERENCES LogicTemplate(ID) ON DELETE CASCADE ON UPDATE CASCADE
);
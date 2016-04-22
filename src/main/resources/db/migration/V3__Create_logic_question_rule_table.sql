create TABLE LogicQuestionRule(
    ID int not null AUTO_INCREMENT,
    LEVEL int,
    AMOUNT int,
    REPO_ID int,
    TEMPLATE_ID int,
    PRIMARY KEY (ID),
    FOREIGN KEY (TEMPLATE_ID) REFERENCES LogicTemplate(ID) ON DELETE CASCADE ON UPDATE CASCADE
);
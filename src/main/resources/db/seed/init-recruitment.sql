INSERT INTO LogicTemplate(ID) VALUES (1);

INSERT INTO LogicQuestionRepo (ID, NAME, URL) VALUES (1, 'Mathematical Logic', 'localhost:3010/mathematical-logic');
INSERT INTO LogicQuestionRepo (ID, NAME, URL) VALUES (2, 'Thinking Logic', 'localhost:3010/thinking-logic');

INSERT INTO LogicQuestionRule (ID, LEVEL, AMOUNT, REPO_ID, TEMPLATE_ID) VALUES (1, 0, 3, 1, 1);
INSERT INTO LogicQuestionRule (ID, LEVEL, AMOUNT, REPO_ID, TEMPLATE_ID) VALUES (2, 0, 4, 2, 1);
INSERT INTO LogicQuestionRule (ID, LEVEL, AMOUNT, REPO_ID, TEMPLATE_ID) VALUES (3, 1, 2, 2, 1);
INSERT INTO LogicQuestionRule (ID, LEVEL, AMOUNT, REPO_ID, TEMPLATE_ID) VALUES (4, 2, 2, 1, 1);

INSERT INTO ProgrammingQuestionPaper (ID) VALUES (1);

INSERT INTO ProgrammingQuestion (ID, CONTENT, ANSWER, DURATION_HOUR, PAPER_ID) VALUES
  (1, 'h1. This is a PROGRAMMING Exam', 'localhost:3011/test-ci', 148, 1);


INSERT INTO PublishedTemplate(ID, NAME, PUBLISHED_BY, LOGIC_TEMPLATE_ID, PROGRAMMING_QUESTION_PAPER_ID) VALUES
  (1, 'This is a template.', 1, 1, 1);
  INSERT INTO PublishedTemplate(ID, NAME, PUBLISHED_BY, LOGIC_TEMPLATE_ID, PROGRAMMING_QUESTION_PAPER_ID) VALUES
  (2, 'This is another template.', 1, 1, 1);
CREATE TABLE ERS_USERS (
USER_ID INT PRIMARY KEY,
USERNAME VARCHAR2(20) NOT NULL UNIQUE,
USER_PASSWORD VARCHAR2(20) NOT NULL,
MANAGER NUMBER(1,0) NOT NULL
);

CREATE TABLE REIMBURSEMENT_REQUESTS (
REQUEST_ID NUMBER PRIMARY KEY,
USER_ID INT NOT NULL,
FOREIGN KEY (USER_ID) REFERENCES ERS_USERS(USER_ID),
AMOUNT NUMBER(*,2) NOT NULL,
REQUEST_DATE DATE,
DESCIPTION NVARCHAR2(100),
APPROVED NUMBER(1,0)
);

CREATE SEQUENCE SEQ_USER_ID;
CREATE SEQUENCE SEQ_REQUEST_ID;

CREATE PROCEDURE PROC_INSERT_EMPLOYEE (P_USERNAME IN ERS_USERS.USERNAME%TYPE, P_PASSWORD IN ERS_USERS.USER_PASSWORD%TYPE)
AS BEGIN
  INSERT INTO ERS_USERS VALUES(SEQ_USER_ID.NEXTVAL, P_USERNAME, P_PASSWORD, 0);
END PROC_INSERT_EMPLOYEE;

CREATE PROCEDURE PROC_SELECT_USER_ID (P_USERNAME IN ERS_USERS.USERNAME%TYPE, O_USER_ID OUT INTEGER)
AS BEGIN
  SELECT USER_ID INTO O_USER_ID FROM ERS_USERS WHERE P_USERNAME = USERNAME;
END PROC_SELECT_USER_ID;

CREATE PROCEDURE PROC_COMPARE_USER_PASSWORD (P_USER_ID IN ERS_USERS.USER_ID%TYPE, P_USER_PASSWORD IN ERS_USERS.USER_PASSWORD%TYPE, O_PASSWORDS_EQUAL OUT BOOLEAN)
AS
  N_USER_PASSWORD ERS_USERS.USER_PASSWORD%TYPE;
BEGIN
  SELECT USER_PASSWORD INTO N_USER_PASSWORD FROM ERS_USERS WHERE USER_ID = P_USER_ID;
  O_PASSWORDS_EQUAL := N_USER_PASSWORD = P_USER_PASSWORD;
END PROC_COMPARE_USER_PASSWORD;

CREATE PROCEDURE PROC_UPDATE_USER_PASSWORD (P_USER_ID IN ERS_USERS.USER_ID%TYPE, P_NEW_PASSWORD IN ERS_USERS.USER_PASSWORD%TYPE)
AS BEGIN
  UPDATE ERS_USERS SET USER_PASSWORD = P_NEW_PASSWORD WHERE USER_ID = P_USER_ID;
END PROC_UPDATE_USER_PASSWORD;

CREATE PROCEDURE PROC_REQUESTS_UPDATE_APPROVED (P_REQUEST_ID IN REIMBURSEMENT_REQUESTS.REQUEST_ID%TYPE,
P_APPROVED IN REIMBURSEMENT_REQUESTS.APPROVED%TYPE)
AS BEGIN
  UPDATE REIMBURSEMENT_REQUESTS SET APPROVED = P_APPROVED WHERE REQUEST_ID = P_REQUEST_ID;
END PROC_REQUESTS_UPDATE_APPROVED;
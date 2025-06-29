
-- PL/SQL Stored Procedures Exercises

-- Scenario 1: Process monthly interest for savings accounts
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest IS
BEGIN
    FOR rec IN (SELECT account_id, balance FROM accounts WHERE account_type = 'SAVINGS') LOOP
        UPDATE accounts
        SET balance = balance + (rec.balance * 0.01)
        WHERE account_id = rec.account_id;
    END LOOP;
    COMMIT;
END;
/

-- Scenario 2: Update employee bonus based on department and bonus percentage
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(dept_id IN NUMBER, bonus_percent IN NUMBER) IS
BEGIN
    UPDATE employees
    SET salary = salary + (salary * bonus_percent / 100)
    WHERE department_id = dept_id;
    COMMIT;
END;
/

-- Scenario 3: Transfer funds between accounts
CREATE OR REPLACE PROCEDURE TransferFunds(
    from_account IN NUMBER,
    to_account IN NUMBER,
    amount IN NUMBER
) IS
    from_balance NUMBER;
BEGIN
    SELECT balance INTO from_balance FROM accounts WHERE account_id = from_account FOR UPDATE;

    IF from_balance < amount THEN
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds');
    END IF;

    UPDATE accounts
    SET balance = balance - amount
    WHERE account_id = from_account;

    UPDATE accounts
    SET balance = balance + amount
    WHERE account_id = to_account;

    COMMIT;
END;
/

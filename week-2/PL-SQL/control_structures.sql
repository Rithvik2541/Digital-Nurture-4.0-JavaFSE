
-- PL/SQL Control Structures Exercises

-- Scenario 1: Apply 1% discount for customers aged above 60
BEGIN
    FOR rec IN (SELECT customer_id, age, interest_rate FROM customers) LOOP
        IF rec.age > 60 THEN
            UPDATE customers
            SET interest_rate = interest_rate - (interest_rate * 0.01)
            WHERE customer_id = rec.customer_id;
        END IF;
    END LOOP;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Interest rate discount applied to senior citizens.');
END;
/

-- Scenario 2: Set IsVIP to TRUE for customers with balance > 10,000
BEGIN
    FOR rec IN (SELECT customer_id, balance FROM customers) LOOP
        IF rec.balance > 10000 THEN
            UPDATE customers
            SET IsVIP = 'TRUE'
            WHERE customer_id = rec.customer_id;
        END IF;
    END LOOP;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('VIP status updated for eligible customers.');
END;
/

-- Scenario 3: Print reminder for loans due in next 30 days
DECLARE
    CURSOR due_loans IS
        SELECT l.loan_id, l.customer_id, c.name, l.due_date
        FROM loans l
        JOIN customers c ON c.customer_id = l.customer_id
        WHERE l.due_date <= SYSDATE + 30;

BEGIN
    FOR rec IN due_loans LOOP
        DBMS_OUTPUT.PUT_LINE('Reminder: Loan ID ' || rec.loan_id ||
                             ' for customer ' || rec.name ||
                             ' is due on ' || TO_CHAR(rec.due_date, 'DD-MON-YYYY'));
    END LOOP;
END;
/

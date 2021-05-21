INSERT INTO Classification (category,discount,promotion) VALUES
  ('Comic', 10,'PROMO1'),
  ('Fiction', 5,'PROMO2'),
  ('Drama', 40,'PROMO2');

INSERT INTO Book (name,description,author,classification_id,price,isbn) VALUES
  ('Wings Of Fire', 'Autobiography','APG Abdul Kalam',1, 100.00,'IS1001'),
  ('Alice in Wonderland', 'Alice','Charles Dickens',2, 200.00,'IS1002'),
  ('Macbeth', 'Shake Mac','Shakesphere',2, 300.00,'IS1003'),
  ('Romeo and Juliet', 'Love Drama','Shakesphere',3, 400.00,'IS1004');
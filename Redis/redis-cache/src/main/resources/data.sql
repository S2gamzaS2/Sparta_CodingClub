INSERT INTO item (name,description,price)
VALUES
    ('mouse','purus,',68707),
    ('monitor','ullamcorper',11034),
    ('keyboard','magnis dis parturient',37700),
    ('speaker','faucibus leo, in lobortis',58281),
    ('mouse','at pretium',61395),
    ('monitor','massa rutrum',53854),
    ('keyboard','vulputate dui, nec',10952),
    ('speaker','gravida sagittis.',18103),
    ('mouse','tellus. Nunc lectus',81846),
    ('monitor','sagittis augue,',23507);
INSERT INTO item (name,description,price)
VALUES
    ('keyboard','ultricies ornare, elit elit',25511),
    ('speaker','est ac',19597),
    ('mouse','ut nisi',13688),
    ('monitor','natoque penatibus et',62116),
    ('keyboard','vulputate mauris',25028),
    ('speaker','Quisque ac libero nec',22685),
    ('mouse','lobortis ultrices. Vivamus',32101),
    ('monitor','arcu. Morbi sit',56267),
    ('keyboard','Mauris vel',48496),
    ('speaker','in',70633);
INSERT INTO item (name,description,price)
VALUES
    ('mouse','neque. Nullam',32901),
    ('monitor','eu tellus. Phasellus',35059),
    ('keyboard','posuere cubilia Curae Phasellus',22529),
    ('speaker','Nunc commodo auctor',94930),
    ('mouse','risus. Donec egestas. Aliquam',16251),
    ('monitor','et malesuada fames',60813),
    ('keyboard','justo.',33390),
    ('speaker','sem mollis',56080),
    ('mouse','sit',19070),
    ('monitor','In at pede.',33048);
INSERT INTO item (name,description,price)
VALUES
    ('keyboard','vulputate, posuere vulputate, lacus.',45197),
    ('speaker','nulla ante,',96083),
    ('mouse','mauris. Morbi',70104),
    ('monitor','malesuada id, erat. Etiam',55592),
    ('keyboard','facilisis facilisis,',89730),
    ('speaker','posuere',71030),
    ('mouse','nec',26254),
    ('monitor','neque. Nullam ut',61081),
    ('keyboard','scelerisque neque.',77121),
    ('speaker','congue, elit sed',47105);

UPDATE item
SET price = (price / 1000) * 1000;
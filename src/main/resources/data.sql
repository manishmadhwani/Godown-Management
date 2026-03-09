--- CITIES Insert statements
insert into CITIES (name, state) VALUES ('Akola', 'Maharashtra');
insert into CITIES (name, state) VALUES ('Amravati', 'Maharashtra');
insert into CITIES (name, state) VALUES ('Badnera', 'Maharashtra');

insert into CITIES (name, state) VALUES ('Shegaon', 'Maharashtra');
insert into CITIES (name, state) VALUES ('Nagpur', 'Maharashtra');
insert into CITIES (name, state) VALUES ('Jalgaon', 'Maharashtra');

insert into CITIES (name, state) VALUES ('Bhusawal', 'Maharashtra');

--- Register sample users
insert into USERS (user_Name, contact_No, password) VALUES ('madhwm', '7977608828', 'Happier@2026');

insert into USERS (user_Name, contact_No, password) VALUES ('saritaSales', '9421822959', 'Thankyou@2026');
insert into USERS (user_Name, contact_No, password) VALUES ('nco', '9405666660', 'KiaSonet@2026');


--- Insert sample Items for users to put into godown
insert into ITEMS (comodity, type, marka_name, packing, address_from) VALUES ('Rice', 'Mussurie', 'Cadboury', '26', 'Chhattisgarh');
insert into ITEMS (comodity, type, marka_name, packing, address_from) VALUES ('Rice', 'BPT', 'Anarkali', '26', 'Raipur, Chhattisgarh');

-- HMT Rice
insert into ITEMS (comodity, type, marka_name, packing, address_from) VALUES ('Rice', 'HMT', 'Chand Tara', '30', 'Nagpur/Hyderabad');
insert into ITEMS (comodity, type, marka_name, packing, address_from) VALUES ('Rice', 'HMT', 'OM HMT', '26', 'Balaghat, Madhya Pradesh');

-- Kolam Rice
insert into ITEMS (comodity, type, marka_name, packing, address_from) VALUES ('Rice', 'Kolam', 'Sharda Tara', '30', 'Gadchiroli, Maharashtra');
insert into ITEMS (comodity, type, marka_name, packing, address_from) VALUES ('Rice', 'Kolam', 'RJU', '26', 'Andra Pradesh');
insert into ITEMS (comodity, type, marka_name, packing, address_from) VALUES ('Rice', 'Kolam', 'Bazigaar', '30', 'Nagpur, Maharashtra');
insert into ITEMS (comodity, type, marka_name, packing, address_from) VALUES ('Rice', 'Kolam', 'Kesar Karturi', '30', 'Gadchiroli, Maharashtra');

-- Kali Mooch Rice
insert into ITEMS (comodity, type, marka_name, packing, address_from) VALUES ('Rice', 'Kali Mooch', 'Mannat', '30', 'Bramhapuri, Maharashtra');
insert into ITEMS (comodity, type, marka_name, packing, address_from) VALUES ('Rice', 'Kali Mooch', 'Maharaja', '30', 'Bramhapuri, Maharashtra');
insert into ITEMS (comodity, type, marka_name, packing, address_from) VALUES ('Rice', 'Kali Mooch', 'Kesar Malai', '30', 'Balaghat, Madhya Pradesh');

-- Basmati Rice
insert into ITEMS (comodity, type, marka_name, packing, address_from) VALUES ('Rice', 'Basmati', 'Maamujaan', '30', 'Delhi');
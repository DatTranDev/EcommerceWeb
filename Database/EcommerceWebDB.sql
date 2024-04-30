CREATE DATABASE IF NOT EXISTS EcommerceWeb;
USE EcommerceWeb;

CREATE TABLE SiteUser (
                          ID INT AUTO_INCREMENT PRIMARY KEY,
                          DisplayName TEXT,
                          Email VARCHAR(255),
                          PhoneNumber VARCHAR(20),
                          UserName VARCHAR(255),
                          PassWord VARCHAR(255),
                          BirthDay DATE,
                          Gender ENUM('Male', 'Female', 'Other'),
                          Role VARCHAR(100) DEFAULT 'Khách hàng',
                          IsDeleted BIT DEFAULT 0
);

CREATE TABLE Address (
                         ID INT AUTO_INCREMENT PRIMARY KEY,
                         Value TEXT
);

CREATE TABLE UserAddress (
                             UserID INT,
                             AddressID INT,
                             IsDefault BIT,
                             IsDeleted BIT DEFAULT 0,
                             PRIMARY KEY (UserID, AddressID),
                             FOREIGN KEY (UserID) REFERENCES SiteUser(ID),
                             FOREIGN KEY (AddressID) REFERENCES Address(ID)
);

CREATE TABLE Promotion (
                           ID INT AUTO_INCREMENT PRIMARY KEY,
                           DisplayName TEXT,
                           Description TEXT,
                           DiscountRate FLOAT,
                           StartDate DATETIME DEFAULT CURRENT_TIMESTAMP,
                           EndDate DATETIME DEFAULT NULL,
                           IsDeleted BIT DEFAULT 0
);

CREATE TABLE ProductCategory (
                                 ID INT AUTO_INCREMENT PRIMARY KEY,
                                 ParentCategoryID INT,
                                 CategoryName TEXT,
                                 IsDeleted BIT DEFAULT 0,
                                 FOREIGN KEY (ParentCategoryID) REFERENCES ProductCategory(ID)
);

CREATE TABLE PromotionCategory (
                                   CategoryID INT,
                                   PromotionID INT,
                                   IsDeleted BIT DEFAULT 0,
                                   PRIMARY KEY (CategoryID, PromotionID),
                                   FOREIGN KEY (PromotionID) REFERENCES Promotion(ID),
                                   FOREIGN KEY (CategoryID) REFERENCES ProductCategory(ID)
);

CREATE TABLE Product (
                         ID INT AUTO_INCREMENT PRIMARY KEY,
                         CategoryID INT,
                         DisplayName TEXT,
                         Description TEXT,
                         ProductImage VARCHAR(255),
                         IsDeleted BIT DEFAULT 0,
                         FOREIGN KEY (CategoryID) REFERENCES ProductCategory(ID)
);

CREATE TABLE ProductItem (
                             ID INT AUTO_INCREMENT PRIMARY KEY,
                             ProductID INT,
                             SKU VARCHAR(100),
                             QuantityInStock INT,
                             ProductImage VARCHAR(255),
                             Price DECIMAL(10, 2) DEFAULT 0,
                             IsDeleted BIT DEFAULT 0,
                             FOREIGN KEY (ProductID) REFERENCES Product(ID)
);

CREATE TABLE Variation (
                           ID INT AUTO_INCREMENT PRIMARY KEY,
                           CategoryID INT,
                           DisplayName TEXT,
                           IsDeleted BIT DEFAULT 0,
                           FOREIGN KEY (CategoryID) REFERENCES ProductCategory(ID)
);

CREATE TABLE VariationOption (
                                 ID INT AUTO_INCREMENT PRIMARY KEY,
                                 VariationID INT,
                                 Value TEXT,
                                 IsDeleted BIT DEFAULT 0,
                                 FOREIGN KEY (VariationID) REFERENCES Variation(ID)
);

CREATE TABLE ProductConfig (
                               ProductItemID INT,
                               VariationID INT,
                               IsDeleted BIT DEFAULT 0,
                               PRIMARY KEY (ProductItemID, VariationID),
                               FOREIGN KEY (ProductItemID) REFERENCES ProductItem(ID),
                               FOREIGN KEY (VariationID) REFERENCES VariationOption(ID)
);

CREATE TABLE ShippingMethod (
                                ID INT AUTO_INCREMENT PRIMARY KEY,
                                DisplayName TEXT,
                                Price DECIMAL(10, 2) DEFAULT 0,
                                IsDeleted BIT DEFAULT 0
);

CREATE TABLE OrderStatus (
                             ID INT AUTO_INCREMENT PRIMARY KEY,
                             Status TEXT,
                             IsDeleted BIT DEFAULT 0
);

CREATE TABLE PaymentMethod (
                               ID INT AUTO_INCREMENT PRIMARY KEY,
                               DisplayName TEXT,
                               IsDeleted BIT DEFAULT 0
);

CREATE TABLE ShopOrder (
                           ID INT AUTO_INCREMENT PRIMARY KEY,
                           UserID INT,
                           OrderDate DATETIME DEFAULT CURRENT_TIMESTAMP,
                           PaymentMethodID INT,
                           ShippingAddressID INT,
                           ShippingMethodID INT,
                           OrderTotal DECIMAL(10, 2) DEFAULT 0,
                           OrderStatusID INT,
                           Description TEXT,
                           IsDeleted BIT DEFAULT 0,
                           FOREIGN KEY (UserID) REFERENCES SiteUser(ID),
                           FOREIGN KEY (PaymentMethodID) REFERENCES PaymentMethod(ID),
                           FOREIGN KEY (ShippingAddressID) REFERENCES Address(ID),
                           FOREIGN KEY (ShippingMethodID) REFERENCES ShippingMethod(ID),
                           FOREIGN KEY (OrderStatusID) REFERENCES OrderStatus(ID)
);

CREATE TABLE OrderLine (
                           ID INT AUTO_INCREMENT PRIMARY KEY,
                           ProductItemID INT,
                           OrderID INT,
                           Quantity INT,
                           Price DECIMAL(10, 2),
                           IsDeleted BIT DEFAULT 0,
                           FOREIGN KEY (ProductItemID) REFERENCES ProductItem(ID),
                           FOREIGN KEY (OrderID) REFERENCES ShopOrder(ID)
);

CREATE TABLE UserReview (
                            ID INT AUTO_INCREMENT PRIMARY KEY,
                            UserID INT,
                            OrderedProductID INT,
                            RatingValue INT,
                            Comment TEXT,
                            IsDeleted BIT DEFAULT 0,
                            FOREIGN KEY (UserID) REFERENCES SiteUser(ID),
                            FOREIGN KEY (OrderedProductID) REFERENCES OrderLine(ID)
);

CREATE TABLE ShoppingCart (
                              ID INT AUTO_INCREMENT PRIMARY KEY,
                              UserID INT,
                              FOREIGN KEY (UserID) REFERENCES SiteUser(ID)
);

CREATE TABLE ShoppingCartItem (
                                  ID INT AUTO_INCREMENT PRIMARY KEY,
                                  CartID INT,
                                  ProductItemID INT,
                                  Quantity INT,
                                  IsDeleted BIT DEFAULT 0,
                                  FOREIGN KEY (CartID) REFERENCES ShoppingCart(ID),
                                  FOREIGN KEY (ProductItemID) REFERENCES ProductItem(ID)
);
-- Category
INSERT INTO ProductCategory (ParentCategoryID, CategoryName) VALUES (NULL, 'Giày dép');
-- Giày dép ID 1
INSERT INTO ProductCategory (ParentCategoryID, CategoryName) VALUES (NULL, 'Phụ kiện');
-- Phụ kiện ID 2
INSERT INTO ProductCategory (ParentCategoryID, CategoryName) VALUES (1, 'Giày Sneaker');
-- Giày Sneaker ID 3
INSERT INTO ProductCategory (ParentCategoryID, CategoryName) VALUES (1, 'Giày cao cổ');
-- Giày cao cổ ID 4
INSERT INTO ProductCategory (ParentCategoryID, CategoryName) VALUES (1, 'Giày lười');
-- Giày lười ID 5
INSERT INTO ProductCategory (ParentCategoryID, CategoryName) VALUES (2, 'Lót giày');
-- Lót giày ID 6

-- Product
INSERT INTO Product (CategoryID, DisplayName, Description, ProductImage)
VALUES (3, 'Nike Air Max', 'Experience comfort and style with Nike Air Max, ideal for daily wear.', 'nike_air_max.jpg');

INSERT INTO Product (CategoryID, DisplayName, Description, ProductImage)
VALUES (3, 'Adidas Ultraboost', 'Boost your running with Adidas Ultraboost, designed for ultimate comfort.', 'adidas_ultraboost.jpg');

-- Size, màu
-- Creating 'Size' variation
INSERT INTO Variation (CategoryID, DisplayName) VALUES (1, 'Size');  -- Assume this gets ID 1
-- Creating 'Color' variation
INSERT INTO Variation (CategoryID, DisplayName) VALUES (1, 'Màu'); -- Assume this gets ID 2

-- Size options for shoes
INSERT INTO VariationOption (VariationID, Value) VALUES (1, '36');
INSERT INTO VariationOption (VariationID, Value) VALUES (1, '37');
INSERT INTO VariationOption (VariationID, Value) VALUES (1, '38');
INSERT INTO VariationOption (VariationID, Value) VALUES (1, '39');
INSERT INTO VariationOption (VariationID, Value) VALUES (1, '40');
INSERT INTO VariationOption (VariationID, Value) VALUES (1, '41');
INSERT INTO VariationOption (VariationID, Value) VALUES (1, '42');
INSERT INTO VariationOption (VariationID, Value) VALUES (1, '43');
INSERT INTO VariationOption (VariationID, Value) VALUES (1, '44');

-- Color options for shoes
INSERT INTO VariationOption (VariationID, Value) VALUES (2, 'Đen');
INSERT INTO VariationOption (VariationID, Value) VALUES (2, 'Trắng');
INSERT INTO VariationOption (VariationID, Value) VALUES (2, 'Hồng');
INSERT INTO VariationOption (VariationID, Value) VALUES (2, 'Xanh dương');

-- For Nike Air Max
INSERT INTO ProductItem (ProductID, SKU, QuantityInStock, ProductImage, Price)
VALUES ((SELECT ID FROM Product WHERE DisplayName = 'Nike Air Max'), 'NAIRMAX-BLK-9', 100, 'nike_air_max_black_size9.jpg', 120.00);

INSERT INTO ProductItem (ProductID, SKU, QuantityInStock, ProductImage, Price)
VALUES ((SELECT ID FROM Product WHERE DisplayName = 'Nike Air Max'), 'NAIRMAX-WHT-10', 150, 'nike_air_max_white_size10.jpg', 120.00);

-- For Adidas Ultraboost
INSERT INTO ProductItem (ProductID, SKU, QuantityInStock, ProductImage, Price)
VALUES ((SELECT ID FROM Product WHERE DisplayName = 'Adidas Ultraboost'), 'AULTRBST-PNK-8', 100, 'adidas_ultraboost_pink_size8.jpg', 180.00);

INSERT INTO ProductItem (ProductID, SKU, QuantityInStock, ProductImage, Price)
VALUES ((SELECT ID FROM Product WHERE DisplayName = 'Adidas Ultraboost'), 'AULTRBST-BLU-7', 150, 'adidas_ultraboost_blue_size7.jpg', 180.00);

-- Product configurations linking ProductItems to Variations and Options
-- Linking size and color for Nike Air Max Black Size 9
INSERT INTO ProductConfig (ProductItemID, VariationID) VALUES
    ((SELECT ID FROM ProductItem WHERE SKU = 'NAIRMAX-BLK-9'), (SELECT ID FROM VariationOption WHERE VariationID = 1 AND Value = '42'));
INSERT INTO ProductConfig (ProductItemID, VariationID) VALUES
    ((SELECT ID FROM ProductItem WHERE SKU = 'NAIRMAX-BLK-9'), (SELECT ID FROM VariationOption WHERE VariationID = 2 AND Value = 'Đen'));

-- And similarly for other products
-- Nike Air Max White Size 10
INSERT INTO ProductConfig (ProductItemID, VariationID) VALUES
    ((SELECT ID FROM ProductItem WHERE SKU = 'NAIRMAX-WHT-10'), (SELECT ID FROM VariationOption WHERE VariationID = 1 AND Value = '44'));
INSERT INTO ProductConfig (ProductItemID, VariationID) VALUES
    ((SELECT ID FROM ProductItem WHERE SKU = 'NAIRMAX-WHT-10'), (SELECT ID FROM VariationOption WHERE VariationID = 2 AND Value = 'Trắng'));

-- Adidas Ultraboost Pink Size 8
INSERT INTO ProductConfig (ProductItemID, VariationID) VALUES
    ((SELECT ID FROM ProductItem WHERE SKU = 'AULTRBST-PNK-8'), (SELECT ID FROM VariationOption WHERE VariationID = 1 AND Value = '40'));
INSERT INTO ProductConfig (ProductItemID, VariationID) VALUES
    ((SELECT ID FROM ProductItem WHERE SKU = 'AULTRBST-PNK-8'), (SELECT ID FROM VariationOption WHERE VariationID = 2 AND Value = 'Hồng'));

-- Adidas Ultraboost Blue Size 7
INSERT INTO ProductConfig (ProductItemID, VariationID) VALUES
    ((SELECT ID FROM ProductItem WHERE SKU = 'AULTRBST-BLU-7'), (SELECT ID FROM VariationOption WHERE VariationID = 1 AND Value = '38'));
INSERT INTO ProductConfig (ProductItemID, VariationID) VALUES
    ((SELECT ID FROM ProductItem WHERE SKU = 'AULTRBST-BLU-7'), (SELECT ID FROM VariationOption WHERE VariationID = 2 AND Value = 'Xanh dương'));

insert into SiteUser(DisplayName, Email, PhoneNumber, PassWord, Gender, Role)
    value ("Dat Tran", "neban0444@gmail.com", "0123456789", "admin", "Male", "admin");
insert into SiteUser(DisplayName, Email, PhoneNumber, PassWord, Gender)
    value ("Nhan Tran", "user@gmail.com", "0123456789", "user", "Male");
update Siteuser set password = "ee11cbb19052e40b07aac0ca060c23ee" where id = 2;
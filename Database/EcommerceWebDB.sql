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

INSERT INTO Product (CategoryID, DisplayName, Description, ProductImage)
VALUES (6, 'Lót Đế Giày Biti\'s Hunter Street', 'Dành cho sneaker bitis', 'https://product.hstatic.net/1000230642/product/lot-de-giay-biti-s-hunter-street-aiuh00500den-den-e2dac-color-den_81063266f3754668a79293900ee51997.jpg');

INSERT INTO Product (CategoryID, DisplayName, Description, ProductImage)
VALUES (4, 'Dincox E06HI', 'Dòng cao cổ vintage.', 'https://product.hstatic.net/1000365025/product/dincox40_3af9e6f6d64646aabcb4e4859543c1be_master.jpg');

INSERT INTO Product (CategoryID, DisplayName, Description, ProductImage)
VALUES (4, 'Biti\'s Hunter Street ZX Collection', 'Một đôi giày thể thao chất lượng là một “người bạn đồng hành” không thể thiếu của những dân chơi thể thao chuyên nghiệp. Theo đó, nếu chưa biết lựa chọn mẫu giày nào phù hợp, bạn hãy cân nhắc sản phẩm “quốc dân” vừa được Biti’s trình làng: Giày Thể Thao Nam Biti\'s Hunter Street ZX Collection. Cùng tìm hiểu chi tiết nhé!', 'https://product.hstatic.net/1000230642/product/dswh06200trg39_4__07be906bc1bc4d3ba9ee1294da6f9bb5.jpg');

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
INSERT INTO VariationOption (VariationID, Value) VALUES (2, 'Nâu');

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
update Siteuser set password = "21232f297a57a5a743894a0e4a801fc3" where id = 1;
UPDATE `product` SET `DisplayName` = 'Dincox E06HI',`ProductImage` ='https://product.hstatic.net/1000365025/product/dincox40_3af9e6f6d64646aabcb4e4859543c1be_master.jpg', `Description` = 'Dòng cao cổ vintage' WHERE (`ID` = '4');
UPDATE `product` SET `DisplayName` = 'Biti\'s Hunter Street Bloomin\' Central 12',`ProductImage` = 'https://product.hstatic.net/1000230642/product/08500den__12__a72c6a17dd844182b888f9e0f0fff7b7.jpg', `Description` = 'Giày Thể Thao Nữ Biti\'s Hunter Street Bloomin\' Central DSWH08500 mang thiết kế trẻ trung, sang trọng và gai góc, phù hợp với những cô nàng cá tính. Với họa tiết tinh tế, bụi bặm, sản phẩm chắc chắn sẽ là sự lựa chọn không thể tuyệt vời hơn đối với các tín đồ thời trang theo đuổi phong cách hiện đại có chút đường phố này. Để biết thêm về sản phẩm, bạn hãy tham khảo nội dung ở dưới đây.' WHERE (`ID` = '1');
UPDATE `product` SET `DisplayName` = 'Giày Đi Bộ Nữ Biti\'s Hunter Jogging',`ProductImage` = 'https://product.hstatic.net/1000230642/product/dswh05300ked9_a3ef2e3602714439bbe024e568bfa3f8.jpg', `Description` = 'Nếu như bạn muốn mua một đôi giày đi bộ êm chân lại có khả năng tôn dáng người của mình thì giày đi bộ Biti\'s Hunter Jogging DSWH05300 chính là dòng sản phẩm tốt nhất dành cho bạn. Với thiết kế kiểu dáng thể thao đẹp mắt, chất liệu vải mềm mại sẽ giúp bạn di chuyển vừa thoải mái vừa êm chân trong thời gian dài. Để hiểu hơn về sản phẩm, cùng theo dõi ngay sau đây' WHERE (`ID` = '2');
UPDATE `product` SET `DisplayName` = 'Lót Đế Giày Biti\'s Hunter Street', `ProductImage` = 'https://product.hstatic.net/1000230642/product/lot-de-giay-biti-s-hunter-street-aiuh00500den-den-e2dac-color-den_81063266f3754668a79293900ee51997.jpg',`Description` = 'Dành cho sneaker bitis' WHERE (`ID` = '3');
UPDATE `product` SET `DisplayName` = 'Biti\'s Hunter Street ZX Collection', `ProductImage` = 'https://product.hstatic.net/1000230642/product/dswh06200trg39_4__07be906bc1bc4d3ba9ee1294da6f9bb5.jpg',`Description` = 'Một đôi giày thể thao chất lượng là một “người bạn đồng hành” không thể thiếu của những dân chơi thể thao chuyên nghiệp. Theo đó, nếu chưa biết lựa chọn mẫu giày nào phù hợp, bạn hãy cân nhắc sản phẩm “quốc dân” vừa được Biti’s trình làng: Giày Thể Thao Nam Biti\'s Hunter Street ZX Collection. Cùng tìm hiểu chi tiết nhé!' WHERE (`ID` = '5');

INSERT INTO `productitem` (`ProductID`, `SKU`, `QuantityInStock`, `ProductImage`, `Price`) VALUES ( '3', 'LOTGIAY-SNK-41', '200', 'https://product.hstatic.net/1000230642/product/lot-de-giay-biti-s-hunter-street-aiuh00500den-den-e2dac-color-den_81063266f3754668a79293900ee51997.jpg', '90000');
INSERT INTO `productitem` (`ProductID`, `SKU`, `QuantityInStock`, `ProductImage`, `Price`) VALUES ( '3', 'LOTGIAY-SNK-42', '200', 'https://product.hstatic.net/1000230642/product/lot-de-giay-biti-s-hunter-street-aiuh00500den-den-e2dac-color-den_81063266f3754668a79293900ee51997.jpg', '90000');

INSERT INTO `productitem` (`ProductID`, `SKU`, `QuantityInStock`, `ProductImage`, `Price`) VALUES ( '4', 'DINCOX-COCAO', '100', 'https://product.hstatic.net/1000365025/product/dincox40_3af9e6f6d64646aabcb4e4859543c1be_master.jpg', '400000');
INSERT INTO `productitem` (`ProductID`, `SKU`, `QuantityInStock`, `ProductImage`, `Price`) VALUES ( '5', 'ZX-STREET', '200', 'https://product.hstatic.net/1000230642/product/dswh06200trg39_4__07be906bc1bc4d3ba9ee1294da6f9bb5.jpg', '350000');
UPDATE `productitem` SET `ProductImage` = 'https://product.hstatic.net/1000230642/product/08500den__12__a72c6a17dd844182b888f9e0f0fff7b7.jpg', price = 300000 WHERE (`ID` = '1');
UPDATE `productitem` SET `ProductImage` = 'https://product.hstatic.net/1000230642/product/08500den__12__a72c6a17dd844182b888f9e0f0fff7b7.jpg', price = 320000 WHERE (`ID` = '2');
UPDATE `productitem` SET `ProductImage` = 'https://product.hstatic.net/1000230642/product/dswh05300ked9_a3ef2e3602714439bbe024e568bfa3f8.jpg', price = 260000 WHERE (`ID` = '3');
UPDATE `productitem` SET `ProductImage` = 'https://product.hstatic.net/1000230642/product/dswh05300ked9_a3ef2e3602714439bbe024e568bfa3f8.jpg', price = 280000 WHERE (`ID` = '4');

-- Lot giay
INSERT INTO ProductConfig (ProductItemID, VariationID) VALUES
    ((SELECT ID FROM ProductItem WHERE SKU = 'LOTGIAY-SNK-41'), (SELECT ID FROM VariationOption WHERE VariationID = 1 AND Value = '41'));
INSERT INTO ProductConfig (ProductItemID, VariationID) VALUES
    ((SELECT ID FROM ProductItem WHERE SKU = 'LOTGIAY-SNK-42'), (SELECT ID FROM VariationOption WHERE VariationID = 1 AND Value = '42'));
    
-- Dincox co cao nau
INSERT INTO ProductConfig (ProductItemID, VariationID) VALUES
    ((SELECT ID FROM ProductItem WHERE SKU = 'DINCOX-COCAO'), (SELECT ID FROM VariationOption WHERE VariationID = 1 AND Value = '41'));
INSERT INTO ProductConfig (ProductItemID, VariationID) VALUES
    ((SELECT ID FROM ProductItem WHERE SKU = 'DINCOX-COCAO'), (SELECT ID FROM VariationOption WHERE VariationID = 2 AND Value = 'Nâu'));
    
-- Dincox co cao nau
INSERT INTO ProductConfig (ProductItemID, VariationID) VALUES
    ((SELECT ID FROM ProductItem WHERE SKU = 'ZX-STREET'), (SELECT ID FROM VariationOption WHERE VariationID = 1 AND Value = '41'));
INSERT INTO ProductConfig (ProductItemID, VariationID) VALUES
    ((SELECT ID FROM ProductItem WHERE SKU = 'ZX-STREET'), (SELECT ID FROM VariationOption WHERE VariationID = 2 AND Value = 'Trắng'));
    
    
    
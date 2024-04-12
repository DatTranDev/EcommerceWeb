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

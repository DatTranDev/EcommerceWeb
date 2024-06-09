<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@taglib uri="http://example.com/utils" prefix="utils" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thông tin người dùng</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
<!-- Header Section Start -->
<div class="container-fluid page-header py-5 bg-primary text-white">
    <h1 class="text-center display-4">Thông tin người dùng</h1>
</div>
<!-- Header Section End -->

<!-- User Information Section Start -->
<div class="container mt-5">
    <h1 class="text-center mb-4 bg-dark text-white">Thông tin cá nhân</h1>
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <div class="card">
                <div class="card-body">
                    <div class="mb-3">
                        <strong>Họ tên:</strong> <span id="displayName">${siteUser.displayName}</span>
                    </div>
                    <div class="mb-3">
                        <strong>Email:</strong> <span id="email">${siteUser.email}</span>
                    </div>
                    <div class="mb-3">
                        <strong>Số điện thoại:</strong> <span id="phoneNumber">${siteUser.phoneNumber}</span>
                        <c:if test="${siteUser.phoneNumber == 'Chưa có'}">
                            <button class="btn btn-primary" type="button" onclick="showPhoneNumberInput()">
                                <i class="fa fa-plus"></i>
                            </button>
                        </c:if>
                    </div>
                    <div class="mb-3" id="phoneNumberInput" style="display: none;">
                        <textarea class="form-control" id="newPhoneNumber" rows="1" placeholder="Nhập số điện thoại mới"></textarea>
                        <button class="btn btn-success mt-2" type="button" onclick="addPhoneNumber()">Lưu số điện thoại</button>
                        <button class="btn btn-secondary mt-2" type="button" onclick="cancelAddPhoneNumber()">Hủy</button>
                    </div>
                    <div class="mb-3">
                        <strong>Giới tính:</strong> <span id="gender">${siteUser.gender}</span>
                    </div>
                    <div class="mb-3">
                        <strong>Địa chỉ thanh toán:</strong>
                        <c:forEach var="address" items="${userAddressList}" varStatus="status">
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="billingAddress"
                                       id="billingAddress${address.addressID}" value="${address.addressID}"
                                       <c:if test="${status.first}">checked</c:if>>
                                <label class="form-check-label" for="billingAddress${address.addressID}">
                                        ${address.address.value}
                                </label>
                            </div>
                        </c:forEach>
                    </div>
                    <!-- Thêm nút Thêm địa chỉ và textarea -->
                    <div class="mb-3">
                        <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#addAddress" aria-expanded="false" aria-controls="addAddress">
                            Thêm địa chỉ
                        </button>
                        <div class="collapse mt-3" id="addAddress">
                            <textarea class="form-control" id="newAddress" rows="3" placeholder="Nhập địa chỉ mới"></textarea>
                            <button class="btn btn-success mt-2" type="button" onclick="addNewAddress()">Lưu địa chỉ</button>
                            <button class="btn btn-secondary mt-2" type="button" onclick="cancelAddAddress()">Hủy</button>
                        </div>
                    </div>
                    <!-- Kết thúc thêm nút và textarea -->
                    <!-- Thêm nút Xóa địa chỉ -->
                    <div class="mb-3">
                        <button class="btn btn-danger" type="button" onclick="deleteAddress()">Xóa địa chỉ đang chọn</button>
                    </div>
                    <!-- Kết thúc thêm nút Xóa địa chỉ -->
                </div>
            </div>
        </div>
    </div>
</div>
<!-- User Information Section End -->

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    function showPhoneNumberInput() {
        document.getElementById("phoneNumberInput").style.display = "block";
    }

    function addPhoneNumber() {
        var newPhoneNumber = document.getElementById("newPhoneNumber").value;
        if (isValidPhoneNumber(newPhoneNumber)) {
            console.log(newPhoneNumber);
            document.getElementById("newPhoneNumber").value = "";

            const valuePhoneNumber = {
                newPhoneNumber,
            };

            fetch('${pageContext.request.contextPath}/api-site-user', {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(valuePhoneNumber)
            })
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                    if (data.success) {
                        alert('Thêm số điện thoại thành công!');
                        window.location.reload();
                    }
                    else {
                        alert('Thêm số điện thoại thất bại!');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('Thêm số điện thoại thất bại!');
                });
        } else {
            alert("Vui lòng nhập số điện thoại hợp lệ.");
        }
    }

    function cancelAddPhoneNumber() {
        document.getElementById("newPhoneNumber").value = "";
        document.getElementById("phoneNumberInput").style.display = "none";
    }

    function isValidPhoneNumber(phoneNumber) {
        // Regex pattern for validating a phone number (Example pattern, modify as needed)
        var phonePattern = /^[0-9]{10}$/;
        return phonePattern.test(phoneNumber);
    }

    function addNewAddress() {
        var newAddress = document.getElementById("newAddress").value;
        if (newAddress) {
            console.log(newAddress);
            document.getElementById("newAddress").value = "";

            const valueAddress = {
                newAddress,
            };

            fetch('${pageContext.request.contextPath}/api-site-user', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(valueAddress)
            })
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                    if (data.success) {
                        alert('Thêm địa chỉ thành công!');
                        window.location.reload();
                    }
                    else {
                        alert('Thêm địa chỉ thất bại!');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('Thêm địa chỉ thất bại!');
                });
        } else {
            alert("Vui lòng nhập địa chỉ.");
        }
    }

    function cancelAddAddress() {
        document.getElementById("newAddress").value = "";
        $('#addAddress').collapse('hide');
    }

    function deleteAddress() {
        var selectedAddress = document.querySelector('input[name="billingAddress"]:checked');
        if (selectedAddress) {
            var addressID = selectedAddress.value;

            const valueAddress = {
                addressID,
            };

            fetch('${pageContext.request.contextPath}/api-site-user', {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(valueAddress)
            })
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                    if (data.success) {
                        alert('Xóa địa chỉ thành công!');
                        window.location.reload();
                    }
                    else {
                        alert('Xóa địa chỉ thất bại!');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('Xóa địa chỉ thất bại!');
                });
        } else {
            alert("Vui lòng chọn địa chỉ để xóa.");
        }
    }
</script>
</body>
</html>
